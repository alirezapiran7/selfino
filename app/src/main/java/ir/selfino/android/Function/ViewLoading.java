package ir.selfino.android.Function;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by soner graphic on 7/20/2017.
 */

public class ViewLoading {
    private ProgressDialog progressDialog;
    private Context context;

    public ViewLoading(Context con) {
        this.context = con;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setTitle("لطفا صبر کنید ...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage("در حال آماده سازی ...");
    }

    public void show() {
        this.progressDialog.show();
    }

    public void close() {
        this.progressDialog.dismiss();
    }
}
