package by.delta.validator.paramsvalidator

import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.util.ConstParamService
import by.delta.validator.paramsvalidator.abstractvalidator.AbstractParamsValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class UserParametersValidator : AbstractParamsValidator() {

    override fun checkResolvedParams(params: MutableMap<String, String>) {
        params.forEach { key, value ->
            if (!key.equals(ConstParamService.SORT_KEY, ignoreCase = true)
                    && !key.equals(ConstParamService.LIMIT, ignoreCase = true)
                    && !key.equals(ConstParamService.PAGE, ignoreCase = true)
                    && !key.equals(ConstParamService.NICKNAME, ignoreCase = true)
                    && !key.equals(ConstParamService.NAME, ignoreCase = true)
                    && !key.equals(ConstParamService.EMAIL, ignoreCase = true)
                    && !key.equals(ConstParamService.SURNAME, ignoreCase = true)
                    && !key.equals(ConstParamService.PATRONYMIC, ignoreCase = true)
                    && !key.equals(ConstParamService.SEX, ignoreCase = true)
            ) {
                LOGGER.error("Key parameter not exist")
                throw ValidationException(ServiceErrorCode.KEY_PARAMS_FOR_MESSAGE_NOT_EXIST, key)
            }
        }
    }

    override fun checkFoundParams(key: String, value: String) {
        if (StringUtils.isEmpty(value)) {
            LOGGER.info("Empty found parameter")
            throw ValidationException(ServiceErrorCode.ERROR_SORT_PARAMS, "$key parameter")
        }
        when (key.toLowerCase()) {
            ConstParamService.NAME -> checkString(key, value)
            ConstParamService.SURNAME -> checkString(key, value)
            ConstParamService.NICKNAME -> checkString(key, value)
            ConstParamService.EMAIL -> checkString(key, value)
            ConstParamService.PATRONYMIC -> checkString(key, value)
            ConstParamService.SEX -> checkString(key, value)
        }

    }

    private fun checkString(key: String, str: String) {
        val splitString = str.split(",").map { s -> s.trim() }
        when (key.toLowerCase()) {
            ConstParamService.ID -> newParams[ConstParamService.ID] = splitString
            ConstParamService.NAME -> newParams[ConstParamService.NAME] = splitString
            ConstParamService.SURNAME -> newParams[ConstParamService.SURNAME] = splitString
            ConstParamService.NICKNAME -> newParams[ConstParamService.NICKNAME] = splitString
            ConstParamService.EMAIL -> newParams[ConstParamService.EMAIL] = splitString
            ConstParamService.PATRONYMIC -> newParams[ConstParamService.PATRONYMIC] = splitString
            ConstParamService.SEX -> newParams[ConstParamService.SEX] = splitString

        }
    }

    override fun checkResolvedSortParameters(sortedParameters: List<String>) {
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
