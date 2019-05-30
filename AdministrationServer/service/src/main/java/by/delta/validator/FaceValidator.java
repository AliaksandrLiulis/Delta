package by.delta.validator;

import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.util.ConstParamService;
import org.springframework.stereotype.Component;

@Component
public class FaceValidator {

    public void checkId(final Long id) {
        CommonValidator.checkIdNullParams(id, "Face id is null", ServiceErrorCode.ID_FACE_IS_NULL, ConstParamService.FACE_ID_STRING);
        CommonValidator.checkIdLengthParams(id, 10, "Face Id is long so much", ServiceErrorCode.ID_FACE_IS_LONG_SO_MUCH, ConstParamService.FACE_ID_STRING);
        CommonValidator.checkIdLessParams(id, 0, "Face id is less than 0", ServiceErrorCode.ID_FACE_IS_LESS_THAN_0, ConstParamService.FACE_ID_STRING);
        CommonValidator.checkIdEqualsParams(id, 0, "Face id equals 0", ServiceErrorCode.ID_FACE_IS_EQUALS_0, ConstParamService.FACE_ID_STRING);
    }
}
