package ir.selfino.android.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;

import ir.selfino.android.R;

public class DialogErrorConnection extends Dialog
{
    private Context context;
    private String massage;
    public Button btnTry;
    public DialogErrorConnection(final Context context)
    {
        super(context);
        this.context=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_error_connection);
        btnTry=findViewById(R.id.btn_try_again_erc_dialog);
        this.setCancelable(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
