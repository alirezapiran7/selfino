package ir.selfino.android.View;

import android.content.Context;
import android.util.AttributeSet;

import ir.selfino.android.Function.Controller;

/**
 * Created by Alireza on 3/25/2018.
 */

public class CTextView extends android.support.v7.widget.AppCompatTextView
{
    public CTextView(Context context)
    {
        super(context);
        if(!isInEditMode())
        setTypeface(Controller.MyTypeface);
    }

    public CTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }
}
