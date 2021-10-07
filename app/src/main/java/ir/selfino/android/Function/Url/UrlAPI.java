package ir.selfino.android.Function.Url;

/**
 * Created by psychdelik on 7/2/2017.
 */

public class UrlAPI
{

    private static String basePath = "http://selfinoo.ir/api/v1/";


    private static String sendVerifyCode="sendVerifyCode";

    private static String signUp="register";

    public static String getSendVerifyCode() {
        return basePath + sendVerifyCode;
    }
    public static String getSignUp() {
        return basePath + signUp;
    }
}
