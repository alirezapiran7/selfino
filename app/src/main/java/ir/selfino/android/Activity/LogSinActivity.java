package ir.selfino.android.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.selfino.android.Fragment.LogSin.LoginFragment;
import ir.selfino.android.Fragment.LogSin.PhoneFragment;
import ir.selfino.android.R;

public class LogSinActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sin);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fr_log_sin_activity , new PhoneFragment());
        transaction.commit();
    }
}
