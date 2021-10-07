package ir.selfino.android.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class CProgressbar extends ProgressBar
{
    public CProgressbar(Context context)
    {
        super(context);
    }

    public CProgressbar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CProgressbar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public CProgressbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void show()
    {
        this.setVisibility(VISIBLE);
    }
    public void misdiss()
    {
        this.setVisibility(GONE);
    }
}
