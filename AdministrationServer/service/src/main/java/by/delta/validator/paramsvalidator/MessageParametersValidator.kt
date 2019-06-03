package by.delta.validator.paramsvalidator

import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.util.ConstParamService
import by.delta.validator.paramsvalidator.abstractvalidator.AbstractParamsValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MessageParametersValidator: AbstractParamsValidator() {

    override fun checkResolvedParams(params: MutableMap<String, String>) {
        params.forEach { key, value ->
            if (!key.equals(ConstParamService.SORT_KEY, ignoreCase = true)
                    && !key.equals(ConstParamService.LIMIT, ignoreCase = true)
                    && !key.equals(ConstParamService.PAGE, ignoreCase = true)
            ) {
                LOGGER.error("Key parameter not exist")
                throw ValidationException(ServiceErrorCode.KEY_PARAMS_FOR_MESSAGE_NOT_EXIST, key)
            }
        }
    }

    override fun checkResolvedSortParameters(sortedParameters: List<String>) {
        sortedParameters.forEach { sortVal: String ->
            if (!sortVal.equals("id", ignoreCase = true) && !sortVal.equals("-id", ignoreCase = true)) {
               LOGGER.error("Value sort not exist")
                throw ValidationException(ServiceErrorCode.SORT_PARAMS_FOR_MESSAGE_NOT_EXIST, sortVal)
            }
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MessageParametersValidator::class.java)
    }
}