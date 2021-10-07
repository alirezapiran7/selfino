package ir.selfino.android.Function.Url;

/**
 * Created by psychdelik on 7/2/2017.
 */

public class UrlSelf
{



    private static String baseUrl = "http://ss.usb.ac.ir/";
//    private static String baseUrl = "http://self.zaums.ac.ir/";
    private static String loginUrl="login.aspx";
    private static String reserveUrl="reserve.aspx";
    private static String etebarUrl="Etebar.aspx";
    private static String nazarSanjiUrl ="Nazarsanji/Voting.aspx";
    private static String errorPage ="ErrorPage.aspx";

    public static String getEtebarUrl()
    {
        return baseUrl+etebarUrl;
    }
    public static String getReserveUrl()
    {
        return baseUrl+reserveUrl;
    }

    public static String getBaseUrl()
    {
        return baseUrl;
    }
    public static String getLoginUrl()
    {
        return baseUrl +loginUrl;
    }


    public static String getNazarSanjiUrl()
    {
        return baseUrl+nazarSanjiUrl;
    }

    public static String getErrorPage()
    {
        return baseUrl+errorPage;
    }

}
