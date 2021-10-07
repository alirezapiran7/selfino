package ir.selfino.android.View;

import android.content.Context;
import android.util.AttributeSet;

import ir.selfino.android.Function.Controller;

/**
 * Created by Alireza on 3/25/2018.
 */

public class CEditText extends android.support.v7.widget.AppCompatEditText
{

    public CEditText(Context context)
    {
        super(context);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode())
        setTypeface(Controller.MyTypeface);
    }
}
