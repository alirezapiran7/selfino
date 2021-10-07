package ir.selfino.android.View;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

/**
 * Created by Alireza on 3/25/2018.
 */

public class CSpinner extends android.support.v7.widget.AppCompatSpinner
{
    public CSpinner(Context context)
    {
        super(context);
    }

    public CSpinner(Context context, int mode)
    {
        super(context, mode);
    }

    public CSpinner(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CSpinner(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public CSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode)
    {
        super(context, attrs, defStyleAttr, mode);
    }

    public CSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme)
    {
        super(context, attrs, defStyleAttr, mode, popupTheme);

    }
}
