package ir.selfino.android.Function.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.selfino.android.Function.Models.PackageOption;
import ir.selfino.android.Interfaces.OptionsListener;
import ir.selfino.android.R;
import ir.selfino.android.Struct.MealStatus;


/**
 * Created by Alireza on 10/3/2017.
 */

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.AdapterHolder>
{
    List<PackageOption> options;
    Context context;
    OptionsListener listener;
    public OptionAdapter(Context context, List<PackageOption> options, OptionsListener listener )
    {
        this.options = options;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_package_option, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position)
    {
        PackageOption item=options.get(position);
        if(item.getFree_status()==1)
            holder.imgFree.setImageResource(R.drawable.ic_save_128);
        else
            holder.imgFree.setImageResource(R.drawable.ic_save_128);

        if(item.getSilver_status()==1)
            holder.imgFree.setImageResource(R.drawable.ic_save_128);
        else
            holder.imgFree.setImageResource(R.drawable.ic_save_128);

        if(item.getGold_status()==1)
            holder.imgFree.setImageResource(R.drawable.ic_save_128);
        else
            holder.imgFree.setImageResource(R.drawable.ic_save_128);
        holder.imgSilver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onOptionListener(0);
            }
        });
        holder.imgSilver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
             listener.onOptionListener(1);
            }
        });
        holder.imgSilver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onOptionListener(2);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return options.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgFree,imgSilver,imgGold;
        public TextView txtname;

        public AdapterHolder(View itemView)
        {
            super(itemView);
            imgFree = (ImageView) itemView.findViewById(R.id.img_free_package_row);
            imgGold = (ImageView) itemView.findViewById(R.id.img_gold_package_row);
            imgSilver = (ImageView) itemView.findViewById(R.id.img_silver_package_row);
            txtname = (TextView) itemView.findViewById(R.id.txt_name_food_row);
        }
    }
}
