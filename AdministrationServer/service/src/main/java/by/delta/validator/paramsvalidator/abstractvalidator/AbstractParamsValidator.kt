package by.delta.validator.paramsvalidator.abstractvalidator

import by.delta.exception.ValidationException
import org.springframework.util.CollectionUtils
import by.delta.util.ConstParamService
import by.delta.exception.errorCode.ServiceErrorCode
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils

abstract class AbstractParamsValidator {

    private var newParams: MutableMap<String, List<String>> = HashMap()

    fun validate(params: MutableMap<String, String>): MutableMap<String, List<String>> {
        newParams = HashMap()
        checkResolvedParams(params)
        PagingParamsValidator.checkRequestParams(params)
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach { k, v ->
                if (k.equals(ConstParamService.SORT_KEY, ignoreCase = true)) {
                    checkSortParameters(v)
                }
            }
        }
        return newParams
    }

    internal open fun checkResolvedParams(params: MutableMap<String, String>) {
        params.forEach { key, value ->
            if (!key.equals(ConstParamService.SORT_KEY, ignoreCase = true)
                    && !key.equals(ConstParamService.LIMIT, ignoreCase = true)
                    && !key.equals(ConstParamService.PAGE, ignoreCase = true)
            ) {
                LOGGER.error("Key parameter not exist")
                throw ValidationException(ServiceErrorCode.KEY_PARAMS_FOR_USERS_NOT_EXIST, key)
            }
        }
    }

    internal open fun checkResolvedSortParameters(sortedParameters: List<String>) {
        sortedParameters.forEach { sortVal: String ->
            if (!sortVal.equals("id", ignoreCase = true) && !sortVal.equals("-id", ignoreCase = true)) {
                LOGGER.error("Value sort not exist")
                throw ValidationException(ServiceErrorCode.SORT_PARAMS_FOR_USERS_NOT_EXIST, sortVal)
            }
        }
    }

    private fun checkSortParameters(sortParameter: String) {
        if (StringUtils.isEmpty(sortParameter)) {
            LOGGER.error("Value sort is empty")
            throw ValidationException(ServiceErrorCode.ERROR_SORT_PARAMS, ConstParamService.SORT_KEY)
        }
        val sortedParameters: List<String> = sortParameter.split(",")
        checkResolvedSortParameters(sortedParameters)
        newParams.put("sort", sortedParameters)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AbstractParamsValidator::class.java)
    }
}