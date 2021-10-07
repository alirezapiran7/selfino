package ir.selfino.android.Function.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginShared
{
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String SHARED_REFFRENCE_NAME = "LoginSelfinoo";

    private String phoneNumber;
    private String verifyCode;
    private Boolean existent;


    public LoginShared(Context con) {
        this.context = con;
        sharedPreferences = context.getSharedPreferences(SHARED_REFFRENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //
    }
    public String getPhoneNumber()
    {
        return sharedPreferences.getString( "phoneNumber","");
    }

    public void setPhoneNumber(String phoneNumber)
    {
        editor.putString("phoneNumber" , phoneNumber);
        editor.commit();
    }

    public String getVerifyCode()
    {
        return sharedPreferences.getString( "VerifyCode","");
    }

    public void setVerifyCode(String testCode)
    {
        editor.putString("VerifyCode" , testCode);
        editor.commit();
    }

    public boolean getExistent()
    {
        return sharedPreferences.getBoolean( "existent",false);
    }

    public void setExistent(boolean existent)
    {
        editor.putBoolean("existent" , existent);
        editor.commit();
    }

}
