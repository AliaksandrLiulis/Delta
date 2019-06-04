package by.delta.validator.paramsvalidator

import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.validator.paramsvalidator.abstractvalidator.AbstractParamsValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UserParametersValidator : AbstractParamsValidator(){

    override  fun checkResolvedSortParameters(sortedParameters: List<String>) {
        sortedParameters.forEach { sortVal: String ->
            if (!sortVal.equals("id", ignoreCase = true)
                    && !sortVal.equals("-id", ignoreCase = true)
                    && !sortVal.equals("nickName", ignoreCase = true)
                    && !sortVal.equals("-nickName", ignoreCase = true)
                    && !sortVal.equals("name", ignoreCase = true)
                    && !sortVal.equals("-name", ignoreCase = true)
                    && !sortVal.equals("surName", ignoreCase = true)
                    && !sortVal.equals("-surName", ignoreCase = true)
                    && !sortVal.equals("patronymic", ignoreCase = true)
                    && !sortVal.equals("-patronymic", ignoreCase = true)
                    && !sortVal.equals("email", ignoreCase = true)
                    && !sortVal.equals("-email", ignoreCase = true)
                    && !sortVal.equals("birthDay", ignoreCase = true)
                    && !sortVal.equals("-birthDay", ignoreCase = true)
                    && !sortVal.equals("sex", ignoreCase = true)
                    && !sortVal.equals("-sex", ignoreCase = true)) {
                LOGGER.error("Value sort not exist")
                throw ValidationException(ServiceErrorCode.SORT_PARAMS_FOR_USERS_NOT_EXIST, sortVal)
            }
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserParametersValidator::class.java)
    }
}
