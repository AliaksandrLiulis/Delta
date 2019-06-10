package by.delta.validator.paramsvalidator.abstractvalidator

import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import org.slf4j.LoggerFactory

class IncomingParametersValidator : AbstractParamsValidator() {

    override fun checkResolvedSortParameters(sortedParameters: List<String>) {
        sortedParameters.forEach { sortVal: String ->
            if (!sortVal.equals("id", ignoreCase = true)
                    && !sortVal.equals("-id", ignoreCase = true)) {
                LOGGER.error("Value sort not exist")
                throw ValidationException(ServiceErrorCode.SORT_PARAMS_FOR_USERS_NOT_EXIST, sortVal)
            }
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IncomingParametersValidator::class.java)
    }
}