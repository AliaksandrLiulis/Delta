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
    EMAIL_USER_NOT_EXISTS(Constants.ERROR_000030),
    ID_USER_NOT_EXISTS(Constants.ERROR_000500),


    //message validation(000031-000060)
    MESSAGE_SUBJECT_IS_NULL(Constants.ERROR_000031),
    MESSAGE_BODY_IS_NULL(Constants.ERROR_000032),
    REPLY_MESSAGE_IS_NOT_NUMBER(Constants.ERROR_000033),
    FACE_ID_IS_NOT_NUMBER(Constants.ERROR_000034),
    MESSAGE_REFERENCE_IS_NULL(Constants.ERROR_000035),
    MESSAGE_SUBJECT_IS_EMPTY(Constants.ERROR_000036),
    MESSAGE_SUBJECT_IS_LONG_SO_MUCH(Constants.ERROR_000037),
    MESSAGE_BODY_IS_EMPTY(Constants.ERROR_000038),
    MESSAGE_ID_NOT_EXIST(Constants.ERROR_000039),
    ID_MESSAGE_IS_NULL(Constants.ERROR_000040),
    ID_MESSAGE_IS_LONG_SO_MUCH(Constants.ERROR_000041),
    ID_MESSAGE_IS_LESS_THAN_0(Constants.ERROR_000042),
    ID_MESSAGE_IS_EQUALS_0(Constants.ERROR_000043),


    //face validator(000061-000090)
    ID_FACE_IS_NULL(Constants.ERROR_000061),
    ID_FACE_IS_LONG_SO_MUCH(Constants.ERROR_000062),
    ID_FACE_IS_LESS_THAN_0(Constants.ERROR_000063),
    ID_FACE_IS_EQUALS_0(Constants.ERROR_000064),

    //paging parameters validation(000150-000180)
    PAGING_LIMIT_NOT_NUMBER(Constants.ERROR_000150),
    PAGING_LIMIT_LESS_THAN_1(Constants.ERROR_000151),
    PAGING_PAGE_NOT_NUMBER(Constants.ERROR_000152),
    PAGING_PAGE_LESS_THAN_1(Constants.ERROR_000153),


    //sort parameters validation(000181-000210)
    ERROR_SORT_PARAMS(Constants.ERROR_000181),
    SORT_PARAMS_FOR_USERS_NOT_EXIST(Constants.ERROR_000182),
    KEY_PARAMS_FOR_USERS_NOT_EXIST(Constants.ERROR_000183),
    KEY_PARAMS_FOR_MESSAGE_NOT_EXIST(Constants.ERROR_000184),
    SORT_PARAMS_FOR_MESSAGE_NOT_EXIST(Constants.ERROR_000185),


    //organization validation(000221-000260)
    REFERENCE_ORGANIZATION_IS_NULL(Constants.ERROR_000221),
    NAME_ORGANIZATION_IS_NULL(Constants.ERROR_000222),
    NAME_ORGANIZATION_IS_EMPTY(Constants.ERROR_000223),
    NAME_ORGANIZATION_IS_LONG_SO_MUCH(Constants.ERROR_000224),
    SHORT_NAME_ORGANIZATION_IS_NULL(Constants.ERROR_000225),
    SHORT_NAME_ORGANIZATION_IS_EMPTY(Constants.ERROR_000226),
    SHORT_NAME_ORGANIZATION_IS_LONG_SO_MUCH(Constants.ERROR_000227),
    ICON_NAME_ORGANIZATION_IS_NULL(Constants.ERROR_000228),
    ICON_NAME_ORGANIZATION_IS_EMPTY(Constants.ERROR_000229),
    ICON_NAME_ORGANIZATION_IS_LONG_SO_MUCH(Constants.ERROR_000230),
    UNP_ORGANIZATION_IS_NULL(Constants.ERROR_000231),
    UNP_ORGANIZATION_IS_EMPTY(Constants.ERROR_000232),
    UNP_ORGANIZATION_IS_LONG_SO_MUCH(Constants.ERROR_000233),

    //organization validator(000261-000280)
    REFERENCE_AUTHENTICATION_IS_NULL(Constants.ERROR_000261),


    ;

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
        private static final String ERROR_000030 = "000030";

        private static final String ERROR_000031 = "000031";
        private static final String ERROR_000032 = "000032";
        private static final String ERROR_000033 = "000033";
        private static final String ERROR_000034 = "000034";
        private static final String ERROR_000035 = "000035";
        private static final String ERROR_000036 = "000036";
        private static final String ERROR_000037 = "000037";
        private static final String ERROR_000038 = "000038";
        private static final String ERROR_000039 = "000039";
        private static final String ERROR_000040 = "000040";
        private static final String ERROR_000041 = "000041";
        private static final String ERROR_000042 = "000042";
        private static final String ERROR_000043 = "000043";
        private static final String ERROR_000044 = "000044";

        private static final String ERROR_000061 = "000061";
        private static final String ERROR_000062 = "000062";
        private static final String ERROR_000063 = "000063";
        private static final String ERROR_000064 = "000064";

        private static final String ERROR_000150 = "000150";
        private static final String ERROR_000151 = "000151";
        private static final String ERROR_000152 = "000152";
        private static final String ERROR_000153 = "000153";

        private static final String ERROR_000181 = "000181";
        private static final String ERROR_000182 = "000182";
        private static final String ERROR_000183 = "000183";
        private static final String ERROR_000184 = "000184";
        private static final String ERROR_000185 = "000185";

        private static final String ERROR_000221 = "000221";
        private static final String ERROR_000222 = "000222";
        private static final String ERROR_000223 = "000223";
        private static final String ERROR_000224 = "000224";
        private static final String ERROR_000225 = "000225";
        private static final String ERROR_000226 = "000226";
        private static final String ERROR_000227 = "000227";
        private static final String ERROR_000228 = "000228";
        private static final String ERROR_000229 = "000229";
        private static final String ERROR_000230 = "000230";
        private static final String ERROR_000231 = "000231";
        private static final String ERROR_000232 = "000232";
        private static final String ERROR_000233 = "000233";

        private static final String ERROR_000261 = "000261";

        private static final String ERROR_000500 = "000500";

        private Constants() {
        }
    }
}
