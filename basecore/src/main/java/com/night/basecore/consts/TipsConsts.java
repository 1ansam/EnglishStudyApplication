package com.night.basecore.consts;

/**
 * Created by KXDoffice on 2015/11/11.
 */
public class TipsConsts {

    /** app加载页 */
    public class Splash {
        /** 欢迎页超时 */
        public static final String SPLASH_TIME_OUT = "网络不给力，请您耐心等待";
    }

    /** 登录注册页 */
    public class LoginAndRegister {
        /** 必填选项 */
        public static final String LOGIN_USERNAME_REQUIRED            = "请输入账户名";

        public static final String USERNAME_REQUIRED                  = "请输入用户名";

        public static final String PASSWORD_REQUIRED                  = "请输入密码";

        public static final String VERIFYCODE_REQUIRED                = "请输入图形验证码";

        public static final String MOBILE_REQUIRED                    = "请输入手机号码";

        public static final String PROTOCAL_REQUIRED                  = "请同意并勾选协议";

    }

    public class ForgetPwd {
        public static final String SMS_CODE_LENGTH_ERROR                     = "请输入6位验证码";

        public static final String RESET_NEW_PWD_REQUIRED                    = "请输入新密码";

        public static final String RESET_CONFIRM_NEW_PWD_REQUIRED            = "请输入确认新密码";

        public static final String RESET_NEW_PWD_NOT_EQUAL_CONFIRM_PWD_ERROR = "确认新密码与上方新密码输入不一致";

        public static final String RESET_PWD_SUCCESS                         = "您的密码已经修改成功，请牢记！";
    }

    public class ModifyPwd {
        public static final String MODIFY_OLD_PWD_REQUIRED         = "当前密码不能为空";

        public static final String MODIFY_NEW_PWD_REQUIRED         = "新密码不能为空";

        public static final String MODIFY_NEW_PWD_SAME_AS_OLD      = "新密码不能与旧密码相同";

        public static final String MODIFY_CONFIRM_INPUT_ERROR      = "新密码确认输入与上方新密码不一致";

    }

    /** 手机激活页 */
    public class ActiveMobile {
        public static final String MOBILE_REQUIRED     = "请输入手机号码";

        public static final String VERIFYCODE_REQUIRED = "请输入验证码";

        public static final String MOBILE_STYLE_ERROR  = "请输入正确的手机号";

    }

    public class Main {
        /** 主页退出 */
        public static final String EXIT_APP_TIP      = "再按一次退出程序";

        /** 系统维护 */
        public static final String SYSTEM_MAINTAIN   = "系统正在维护";

        /** 登录超时 */
        public static final String RELOGIN           = "登录已超时，请重新登录";

        /** 默认错误信息 */
        public static final String DEFAULT_ERROR_TIP = "系统繁忙，请稍后再试";
    }

    public class UpdateAPK {
        public static final String UPDATE_APK_ERROR   = "更新程序失败，请检查网络";

        public static final String LATEST_VERSION_TIP = "已是最新版，请您放心使用";
    }

    public class DialogTip {
        public static final String TIP                             = "提示";

        public static final String WARM_TIP                        = "温馨提示";

        public static final String CONFIRM_BUTTON                  = "确定";

        public static final String CANCEL_BUTTON                   = "取消";

        public static final String CONTINUE_BUTTON                 = "继续";

        public static final String AGREE_BUTTON                    = "同意";

        public static final String DISAGREE_BUTTON                 = "不同意";

        public static final String I_KNOW_BUTTON                   = "我知道了";

        public static final String RE_LOGIN_BUTTON                 = "重新登录";

    }
}