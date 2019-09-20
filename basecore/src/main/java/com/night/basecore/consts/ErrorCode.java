package com.night.basecore.consts;

/**
 * Created by seast on 2015/10/21.
 */
public class ErrorCode {
    // 参数为空
    public static final String PARAM_NULL                       = "PARAM_NULL";

    // 参数不合法
    public static final String PARAM_ILLEGAL                    = "PARAM_ILLEGAL";

    // 网络错误
    public static final String VOLLEY_ERROR                     = "VOLLEY_ERROR";

    // 网络连接超时
    public static final String TIME_OUT_ERROR                   = "TIME_OUT_ERROR";

    // 图形验证码输入错误
    public static final String VALIDATE_CODE_ERROR              = "VALIDATE_CODE_ERROR";

    // 系统维护
    public static final String SYSTEM_MAINTAINING               = "SYSTEM_MAINTAINING";

    /** 登录超时 */
    public static final String SYSTEM_ERROR_TIMEOUT             = "_GE_LOGIN_OUT_TIME";

    /** 重复登录 */
    public static final String SYSTEM_ERROR_DUPLICATE_LOGIN     = "_GE_LOGIN_DUPLICATE_ENTRIES";

}
