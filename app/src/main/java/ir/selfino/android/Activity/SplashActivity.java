package ir.selfino.android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Values;
import ir.selfino.android.R;

public class SplashActivity extends AppCompatActivity
{

    private UsernameShared usernameShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);


        usernameShared = new UsernameShared(this);

        /* set status translucent */
        Values.translucent_status(getWindow());
        Values.setSleep(1000);

        Intent i;
        //if (usernameShared.checkExist())
            i = new Intent(this, MainActivity.class);
//        //else
//            i = new Intent(this, UniversityActivity.class);
            //Todo check and choise best activity
        startActivity(i);
        finish();
    }
}
