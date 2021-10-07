package ir.selfino.android.View;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.selfino.android.Function.Controller;

public class CTabLayout extends TabLayout
{

    public CTabLayout(Context context) {
        super(context);

    }

    public CTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void addTab(Tab tab) {
        super.addTab(tab);

        ViewGroup mainView = (ViewGroup) getChildAt(0);
        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());

        int tabChildCount = tabView.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            View tabViewChild = tabView.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(Controller.MyTypeface, Typeface.NORMAL);
            }
        }
    }

}
