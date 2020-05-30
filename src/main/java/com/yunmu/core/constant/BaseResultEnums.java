package com.yunmu.core.constant;


import com.yunmu.core.base.BaseEnum;

/**
 * 基础返回信息枚举值
 */
public enum BaseResultEnums implements BaseEnum<String> {

    SUCCESS("request.success"),

    FAILURE("request.failure"),

    OPERATION_SUCCESS("operation.success"),

    OPERATION_FAILURE("operation.failure"),

    ERROR("error.error"),

    ERROR_NET("error.network"),

    UNAUTHORIZED("error.unauthorized"),

    FORBIDDEN("error.forbidden"),

    NOT_FOUND("error.not_found"),

    DATA_EXISTS("error.data_exists"),

    DATA_NOT_EXISTS("error.data_not_exists"),

    DATA_INVALID("error.data_invalid"),

    NOT_LOGIN("error.not_login"),

    ERROR_SQL_EXCEPTION("error.sql_exception"),

    UPDATE_FAILED("error.record_not_exists_or_version_not_match"),

    PARAMETER_NOT_NULL("validation.parameter_not_be_null");

    private String code;

    BaseResultEnums(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
