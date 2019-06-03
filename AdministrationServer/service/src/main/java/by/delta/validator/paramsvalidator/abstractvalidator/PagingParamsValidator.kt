package by.delta.validator.paramsvalidator.abstractvalidator

import by.delta.exception.ValidationException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.util.ConstParamService
import org.slf4j.LoggerFactory
import org.springframework.util.CollectionUtils

object PagingParamsValidator {

    private val LOGGER = LoggerFactory.getLogger(PagingParamsValidator::class.java)

    fun checkRequestParams(allRequestParams: MutableMap<String, String>) {
        if (!CollectionUtils.isEmpty(allRequestParams)) {
            if (allRequestParams.containsKey(ConstParamService.LIMIT)) {
                checkLimitParams(allRequestParams.getValue(ConstParamService.LIMIT))
            } else {
                setDefaultLimit(allRequestParams)
            }
            if (allRequestParams.containsKey(ConstParamService.PAGE)) {
                checkPageParams(allRequestParams.getValue(ConstParamService.PAGE))
                replacePageParamsOnOffsetParams(allRequestParams)
            } else {
                setDefaultOffset(allRequestParams)
            }
        } else {
            setDefaultLimit(allRequestParams)
            setDefaultOffset(allRequestParams)
        }
    }

    private fun checkLimitParams(limit: String) {
        var limitValue: Int
        try {
            limitValue = Integer.parseInt(limit)
        } catch (e: NumberFormatException) {
            LOGGER.error("limit value not number")
            throw ValidationException(ServiceErrorCode.PAGING_LIMIT_NOT_NUMBER, ConstParamService.LIMIT_PARAMS_STRING)
        }
        if (limitValue < 1) {
            LOGGER.error("limit value less than 1")
            throw ValidationException(ServiceErrorCode.PAGING_LIMIT_LESS_THAN_1, ConstParamService.LIMIT_PARAMS_STRING)
        }
    }

    private fun checkPageParams(page: String) {
        var pageValue: Int
        try {
            pageValue = Integer.parseInt(page)
        } catch (e: NumberFormatException) {
            LOGGER.error("page value not number")
            throw ValidationException(ServiceErrorCode.PAGING_PAGE_NOT_NUMBER, ConstParamService.PAGE_PARAMS_STRING)
        }
        if (pageValue < 1) {
            LOGGER.error("page value less than 1")
            throw ValidationException(ServiceErrorCode.PAGING_PAGE_LESS_THAN_1, ConstParamService.PAGE_PARAMS_STRING)
        }
    }

    private fun replacePageParamsOnOffsetParams(allRequestParams: MutableMap<String, String>) {
        val limit = Integer.parseInt(allRequestParams[ConstParamService.LIMIT])
        val page = Integer.parseInt(allRequestParams[ConstParamService.PAGE])
        var off = page * limit - limit
        allRequestParams.remove(ConstParamService.PAGE)
        allRequestParams[ConstParamService.OFFSET] = off.toString()
    }

    private fun setDefaultLimit(allRequestParams: MutableMap<String, String>) {
        allRequestParams[ConstParamService.LIMIT] = "10"
    }

    private fun setDefaultOffset(allRequestParams: MutableMap<String, String>) {
        allRequestParams[ConstParamService.OFFSET] = "0"
    }
}