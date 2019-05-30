package by.delta.exception.errorCode;

public enum ServiceErrorCode {

    // user validation(000001-000030)
    NAME_USER_EXISTS(Constants.ERROR_000001),
    REFERENCE_USER_IS_NULL(Constants.ERROR_000002),
    ID_USER_IS_NULL(Constants.ERROR_000003),
    ID_USER_IS_LONG_SO_MUCH(Constants.ERROR_000004),
    ID_USER_IS_LESS_THAN_0(Constants.ERROR_000005),
    ID_USER_IS_EQUALS_0(Constants.ERROR_000006),
    NAME_USER_IS_NULL(Constants.ERROR_000007),
    NAME_USER_IS_EMPTY(Constants.ERROR_000008),
    NAME_USER_IS_LONG_SO_MUCH(Constants.ERROR_000009),
    PASSWORD_USER_IS_NULL(Constants.ERROR_000010),
    PASSWORD_USER_IS_EMPTY(Constants.ERROR_000011),
    PASSWORD_USER_IS_LONG_SO_MUCH(Constants.ERROR_000012),
    EMAIL_USER_EXISTS(Constants.ERROR_000013),
    EMAIL_USER_IS_NULL(Constants.ERROR_000014),
    EMAIL_USER_IS_EMPTY(Constants.ERROR_000015),
    EMAIL_USER_IS_LONG_SO_MUCH(Constants.ERROR_000016),
    EMAIL_USER_IS_NOT_CORRECT(Constants.ERROR_000017),
    NICK_NAME_USER_IS_NULL(Constants.ERROR_000018),
    NICK_NAME_USER_IS_EMPTY(Constants.ERROR_000019),
    NICK_NAME_USER_IS_LONG_SO_MUCH(Constants.ERROR_000020),
    SUR_NAME_USER_IS_NULL(Constants.ERROR_000021),
    SUR_NAME_USER_IS_EMPTY(Constants.ERROR_000022),
    SUR_NAME_USER_IS_LONG_SO_MUCH(Constants.ERROR_000023),
    PATRONYMIC_USER_IS_NULL(Constants.ERROR_000024),
    PATRONYMIC_USER_IS_EMPTY(Constants.ERROR_000025),
    PATRONYMIC_USER_IS_LONG_SO_MUCH(Constants.ERROR_000026),
    SEX_USER_IS_NULL(Constants.ERROR_000027),
    SEX_USER_IS_EMPTY(Constants.ERROR_000028),
    SEX_USER_IS_NOT_CORRECT(Constants.ERROR_000029),


    //message validation(000031-000060)
    MESSAGE_SUBJECT_IS_NULL(Constants.ERROR_000031),
    BODY_MESSAGE_IS_NULL(Constants.ERROR_000032),
    REPLY_MESSAGE_IS_NOT_NUMBER(Constants.ERROR_000033),
    FACE_ID_IS_NOT_NUMBER(Constants.ERROR_000034),


    //face validator(000061-000090)
    ID_FACE_IS_NULL(Constants.ERROR_000061),
    ID_FACE_IS_LONG_SO_MUCH(Constants.ERROR_000062),
    ID_FACE_IS_LESS_THAN_0(Constants.ERROR_000063),
    ID_FACE_IS_EQUALS_0(Constants.ERROR_000064);

    private String errorCode;

    ServiceErrorCode(String errorId) {
        this.errorCode = "DELTA" + "-" + errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    private static class Constants {
        private static final String ERROR_000001 = "000001";
        private static final String ERROR_000002 = "000002";
        private static final String ERROR_000003 = "000003";
        private static final String ERROR_000004 = "000004";
        private static final String ERROR_000005 = "000005";
        private static final String ERROR_000006 = "000006";
        private static final String ERROR_000007 = "000007";
        private static final String ERROR_000008 = "000008";
        private static final String ERROR_000009 = "000009";
        private static final String ERROR_000010 = "000010";
        private static final String ERROR_000011 = "000011";
        private static final String ERROR_000012 = "000012";
        private static final String ERROR_000013 = "000013";
        private static final String ERROR_000014 = "000014";
        private static final String ERROR_000015 = "000015";
        private static final String ERROR_000016 = "000016";
        private static final String ERROR_000017 = "000017";
        private static final String ERROR_000018 = "000018";
        private static final String ERROR_000019 = "000019";
        private static final String ERROR_000020 = "000020";
        private static final String ERROR_000021 = "000021";
        private static final String ERROR_000022 = "000022";
        private static final String ERROR_000023 = "000023";
        private static final String ERROR_000024 = "000024";
        private static final String ERROR_000025 = "000025";
        private static final String ERROR_000026 = "000026";
        private static final String ERROR_000027 = "000027";
        private static final String ERROR_000028 = "000028";
        private static final String ERROR_000029 = "000029";

        private static final String ERROR_000031 = "000031";
        private static final String ERROR_000032 = "000032";
        private static final String ERROR_000033 = "000033";
        private static final String ERROR_000034 = "000034";

        private static final String ERROR_000061 = "000061";
        private static final String ERROR_000062 = "000062";
        private static final String ERROR_000063 = "000063";
        private static final String ERROR_000064 = "000064";

        private Constants() {
        }
    }
}
