package ir.selfino.android.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import ir.selfino.android.R;

public class DialogProgressBar extends Dialog
{
    private Context context;
    private String message;
    public DialogProgressBar( Context context)
    {
        super(context);
        this.context=context;
        message = "در حال بارگذاری"+"\n"+"لطفا چند لحظه صبر کنید...";
        createView();
    }
    public DialogProgressBar( Context context,String message)
    {
        super(context);
        this.context=context;
        this.message=message;
        createView();
    }
    private void createView()
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_progress_bar);
        this.setCancelable(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvMessage = findViewById(R.id.tv_message_progress_bar_dialog);
        tvMessage.setText(message);
    }
}