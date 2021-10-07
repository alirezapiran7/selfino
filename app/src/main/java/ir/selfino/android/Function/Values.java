package ir.selfino.android.Function;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import ir.selfino.android.Function.SharedPreferences.UsernameShared;


public class Values
{

    private Context context ;
    private UsernameShared usernameShared ;

    public Pattern patternMelliCode, patternEmpCode, patternActiviteCode , patternPhoneNumber , patternConfirmationCode;

    public Values(Context con)
    {
        this.context = con ;;
        this.patternMelliCode = Pattern.compile("^[0-9۰-۹]{10,11}$") ;
        this.patternEmpCode = Pattern.compile("^[0-9۰-۹]{5,9}$") ;
        this.patternPhoneNumber = Pattern.compile("^(?:09|۰۹)[0-9۰-۹]{9}$") ;
        this.patternConfirmationCode = Pattern.compile("^[0-9۰-۹]{6}$") ;
        this.patternActiviteCode = Pattern.compile("^[0-9۰-۹]$") ;
        this.usernameShared = new UsernameShared(con) ;
    }

    /* write to files*/
    public void writeToFile(String string , String nameOfString, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nameOfString, Context.MODE_PRIVATE));
            outputStreamWriter.write(string);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* device Info */
    public String getDeviceInfo()
    {
        String info = "" ;
        info += "brand=" + Build.BRAND + "|" ;
        info += "model=" + Build.MODEL + "|" ;
        info += "deviceFingerPrint=" + Build.FINGERPRINT + "|";
        info += "sdkVersion=" + Build.VERSION.SDK_INT;
        return info ;
    }

    public static void setSleep(long time){
        try {
            Thread.sleep(time);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void translucent_status(Window getWindow){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public int addStatusBarHeight(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return getStatusBarHeight();
        }else{
            return 0;
        }
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        else {
            return dpToPx(24);
        }
        return result;
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
    /* check permisson is active */
    public boolean checkPermissionGranted(String permission)
    {
        /*
        * return true   : yani inke dast rasi dadeh shode .
        * return false  : yani dastrasi nadade .
        * */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int res = context.checkCallingOrSelfPermission(permission);
            return res == PackageManager.PERMISSION_GRANTED;
        }
        return true ;
    }

    /* Check gps status */
    public boolean checkGpsStatus()
    {
        /*
        * return true =>  gps is enable .
        * return false => gps is disable .
        * */
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void shareLocationLatLng(Context context , double lat , double lng){

        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ lat + "," + lng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
        else{
            Toast.makeText(context, "برنامه نقشه یافت نشد! ", Toast.LENGTH_SHORT).show();
        }
    }

 /* convert second to day , hour , minute , second */
    public String secondsToTime(int seconds)
    {

        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

        ArrayList<String> arrData = new ArrayList<>() ;

        if(day != 0) arrData.add(day+" روز ") ;
        if(hours != 0) arrData.add(hours+" ساعت ") ;
        if(minute != 0) arrData.add(minute+" دقیقه ") ;
//        if(second != 0) arrData.add(second+" ثانیه ") ;

        return TextUtils.join(" و ", arrData) ;
    }

    /* get service center typeid to type name*/

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
