package ir.selfino.android.Function.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ir.selfino.android.Function.Controller;
import ir.selfino.android.R;

/**
 * Created by Alireza on 3/26/2018.
 */

public class SelfSpinnerAdapter extends ArrayAdapter<String>
{
    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource)
    {
        super(context, resource);
    }

    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId)
    {
        super(context, resource, textViewResourceId);
    }

    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects)
    {
        super(context, resource, objects);
    }

    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull String[] objects)
    {
        super(context, resource, textViewResourceId, objects);
    }

    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects)
    {
        super(context, resource, objects);
    }

    public SelfSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<String> objects)
    {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        TextView view=(TextView)super.getDropDownView(position,convertView,parent);
        //view.setBackground(getContext().getResources().getDrawable(R.drawable.selector_self_spinner));
        view.setTextColor(getContext().getResources().getColor(R.color.text_hint));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        view.setTypeface(Controller.MyTypeface);
        return view;
    }
}
