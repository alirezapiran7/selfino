package ir.selfino.android.Function;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class Controller extends Application
{
    public static final String TAG = Controller.class.getSimpleName();
    private static Controller controller;
    public static WebView MyWeb;
    public boolean home_load_next = true;
    public boolean home_loading_call = true;
    private RequestQueue queue;
    private ImageLoader mImageLoader;
    public static Typeface MyTypeface;
    public static  Context context;
    private static AssetManager Assets;

    public static synchronized Controller getPermission() {
        return controller;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        controller = this;
        context=getApplicationContext();
        Assets=getAssets();
        MyTypeface=Typeface.createFromAsset(Assets,"fonts/iran_sans.ttf");
        MyWeb=new WebView(context);
        setWebViewOptions();
        clearCookies();
    }

    public Context getContext()
    {
        if(context==null)
            context=getApplicationContext();
        return context;
    }


    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getBaseContext());
        }
        return queue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the def tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }
    public void setWebViewOptions()
    {
        MyWeb.getSettings().setJavaScriptEnabled(true);
        MyWeb.getSettings().setAllowContentAccess(true);
        MyWeb.getSettings().setAllowFileAccess(true);
        MyWeb.getSettings().setLoadsImagesAutomatically(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            MyWeb.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        MyWeb.getSettings().setLoadWithOverviewMode(true);
        MyWeb.getSettings().setUseWideViewPort(false);
        MyWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        MyWeb.getSettings().setSupportMultipleWindows(true);
        MyWeb.getSettings().setDomStorageEnabled(true);
    }
    public static String getWebUrl()
    {
        return MyWeb.getUrl().toLowerCase();
    }
    public static void clearCookies()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            //Log.d(C.TAG, "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            //Log.d(C.TAG, "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }
}
