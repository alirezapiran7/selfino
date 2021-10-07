package ir.selfino.android.View;

import android.content.Context;
import android.util.AttributeSet;

import ir.selfino.android.Function.Controller;


/**
 * Created by Alireza on 3/26/2018.
 */

public class CButton extends android.support.v7.widget.AppCompatButton
{
    public CButton(Context context)
    {
        super(context);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }
}
