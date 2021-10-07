package ir.selfino.android.Function.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import ir.selfino.android.Function.Values;

public class ValueShared
{
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String SHARED_REFFRENCE_NAME = "Values";
    private Values values;


    public ValueShared(Context con) {
        this.context = con;
        sharedPreferences = context.getSharedPreferences(SHARED_REFFRENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        values = new Values(context);
    }

    /* Delet all shared */
    public void delete() {
        editor.clear();
        editor.commit();
    }
}
