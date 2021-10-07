package ir.selfino.android.Function.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

import ir.selfino.android.Function.Models.Food;
import ir.selfino.android.Function.Models.Meal;
import ir.selfino.android.Interfaces.FoodListener;
import ir.selfino.android.R;
import ir.selfino.android.Struct.MealStatus;


/**
 * Created by Alireza on 10/3/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ReserveHolder>
{
    List<Food> foods;
    Context context;
    Meal meal;
    public int lastSelection = -1;
    public boolean status = true;
    private FoodListener listener;

    public FoodAdapter(Context context, FoodListener listener, Meal meal)
    {
        this.foods = meal.getFoods();
        this.context = context;
        this.meal = meal;
        this.listener = listener;
    }

    @Override
    public ReserveHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_meal_food, parent, false);
        return new ReserveHolder(view);
    }

    @Override
    public void onBindViewHolder(ReserveHolder holder, int position)
    {
        if (meal.getStatus() == MealStatus.UnReserve_Changable)
        {
            holder.checkFood.setText(foods.get(position).getName());
            holder.txtPrice.setText((foods.get(position).getPrice() / 10) + " تومان");
            holder.checkFood.setChecked((lastSelection == position) && status);
            if (lastSelection == position)
            {
                if (status)
                {
                    listener.selectFood(meal, meal.getFoods().get(position));
                } else
                {
                    listener.cancelFood(meal, meal.getFoods().get(position));
                    lastSelection = -1;
                }
            }
        } else if (meal.getStatus() == MealStatus.Reserved_changeable)
        {
            holder.checkFood.setText(foods.get(position).getName());
            holder.txtPrice.setText("رزرو شده");
            //holder.txtname.setText(FormatHelper.toPersianNumber(""+(options.get(position).getPrice()/10))+ " تومان");
            holder.checkFood.setChecked(status);
            if (status)
            {
                lastSelection = 0;
                listener.selectFood(meal, foods.get(position));


            } else
            {
                lastSelection = -1;
                listener.cancelFood(meal, foods.get(position));
            }
        } else if (meal.getStatus() == MealStatus.Reserved_UnChangable)
        {
            holder.checkFood.setEnabled(false);
            holder.checkFood.setText(foods.get(position).getName());
            holder.checkFood.setChecked(true);
            holder.txtPrice.setText("رزرو شده");
        } else if (meal.getStatus() == MealStatus.UnReserve_UnChangable)
        {
            holder.checkFood.setEnabled(false);
            holder.checkFood.setChecked(false);
            holder.txtPrice.setText("رزرو نداری!");
        } else if (meal.getStatus() == MealStatus.Reserved_Use)
        {
            holder.checkFood.setText(foods.get(position).getName());
            holder.txtPrice.setText("نوش جان");
            holder.checkFood.setEnabled(false);
            holder.checkFood.setChecked(true);
        }
    }

    @Override
    public int getItemCount()
    {
        return foods.size();
    }

    public class ReserveHolder extends RecyclerView.ViewHolder
    {
        public CheckBox checkFood;
        public TextView txtPrice;

        public ReserveHolder(View itemView)
        {
            super(itemView);
            checkFood = (CheckBox) itemView.findViewById(R.id.cb_select_food_row);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_name_food_row);
            checkFood.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (lastSelection == getAdapterPosition())
                    {
                        status = false;
                    } else
                    {
                        status = true;
                        lastSelection = getAdapterPosition();
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
