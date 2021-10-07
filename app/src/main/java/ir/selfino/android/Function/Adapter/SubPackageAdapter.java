package ir.selfino.android.Function.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ir.selfino.android.Function.Models.Food;
import ir.selfino.android.Function.Models.Meal;
import ir.selfino.android.Function.Models.SubPackage;
import ir.selfino.android.Interfaces.FoodListener;
import ir.selfino.android.Interfaces.SubPackageListener;
import ir.selfino.android.R;
import ir.selfino.android.Struct.MealStatus;


/**
 * Created by Alireza on 10/3/2017.
 */

public class SubPackageAdapter extends RecyclerView.Adapter<SubPackageAdapter.ReserveHolder>
{
    List<SubPackage> items;
    Context context;
    public int lastSelection = -1;
    private SubPackageListener listener;

    public SubPackageAdapter(Context context,List<SubPackage>items ,SubPackageListener listener)
    {

        this.context = context;
        this.items=items;
        this.listener = listener;
    }

    @Override
    public ReserveHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sub_package_alarm, parent, false);
        return new ReserveHolder(view);
    }

    @Override
    public void onBindViewHolder(ReserveHolder holder, final int position)
    {
        final SubPackage item=items.get(position);
            holder.checkFood.setChecked(lastSelection==position);
            holder.checkFood.setText(item.getName()+"\t"+item.getDay_count());
            if(item.getOff()==0)
            {
                holder.txtoff.setVisibility(View.GONE);
                holder.txtoffPrice.setVisibility(View.GONE);
                holder.txtPrice.setText(item.getPrice()+" تومان ");
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.greenBlue));
            }else
            {
                holder.txtoff.setVisibility(View.VISIBLE);
                holder.txtoff.setText(item.getOff()+"%"+" تخفیف ");
                holder.txtoffPrice.setVisibility(View.VISIBLE);
                holder.txtPrice.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                holder.txtPrice.setPaintFlags(holder.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtoffPrice.setTextColor(context.getResources().getColor(R.color.greenBlue));
                holder.txtPrice.setText(item.getPrice()+" تومان ");
                int temp=item.getPrice()-((item.getOff()/100)*item.getPrice());
                holder.txtoffPrice.setText(temp+" تومان ");
            }
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    lastSelection = position;
                    listener.onSubPackageListener(item);
                    notifyDataSetChanged();

                }
            });
        holder.checkFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                lastSelection = position;
                listener.onSubPackageListener(item);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class ReserveHolder extends RecyclerView.ViewHolder
    {
        public CheckBox checkFood;
        public TextView txtPrice;
        public TextView txtoffPrice;
        public TextView txtoff;
        public ReserveHolder(View itemView)
        {
            super(itemView);
            checkFood = (CheckBox) itemView.findViewById(R.id.cb_package_alarm_row);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_package_alarm_row);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_off_price_package_alarm_row);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_off_package_alarm_row);

        }
    }
}
