package ir.selfino.android.Function;

public class PageParser
{
    private static final String base="javascript:";



    private static final String  captcha = "(function()" +
            "{" +

            "try" +
            "{" +
            "var object='{';" +
            "object=object+'\"ok\":true,';" +
            "object=object+'\"result\":{';" +
            "object=object+'\"message\":\"'+document.querySelector('span#lblmessage').textContent.toString()+'\"';" +
            "object=object+'\"captcha_image\":\"'+document.querySelector('img[src^=CaptchaImage]').src.toString()+'\"';" +
            "object=object+'}';" +
            "object=object+'}';" +
            "window.jsObject.onReceiveCaptcha(object);" +
            "}" +
            "catch(err)" +
            "{" +
            "var object='{';" +
            "object=object+'\"ok\":true,';" +
            "object=object+'\"result\":{';" +
            "object=object+'\"message\":\"'+document.querySelector('span#lblmessage').textContent.toString()+'\"';" +
            "object=object+'\"captcha_image\":\"'+document.querySelector('img[src^=GenerateCaptcha]').src.toString()+'\"';" +
            "object=object+'}';" +
            "object=object+'}';" +
            "window.jsObject.onReceiveCaptcha(object);" +
            "}" +

            "}" +
            "());";

    public static String getCaptcha()
    {
        return base+captcha;
    }
}
