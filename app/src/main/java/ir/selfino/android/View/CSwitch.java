package ir.selfino.android.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import ir.selfino.android.Function.Controller;


/**
 * Created by Alireza on 5/18/2018.
 */

public class CSwitch extends Switch
{
    public CSwitch(Context context)
    {
        super(context);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CSwitch(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CSwitch(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }

    public CSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        if(!isInEditMode())
            setTypeface(Controller.MyTypeface);
    }
}
