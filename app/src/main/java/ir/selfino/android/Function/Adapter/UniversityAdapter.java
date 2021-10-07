package ir.selfino.android.Function.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.selfino.android.Function.Models.University;
import ir.selfino.android.Interfaces.UniversityListener;
import ir.selfino.android.R;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;

public class UniversityAdapter  extends RecyclerView.Adapter<UniversityAdapter.AdapterHolder>
{
    private Context context;
    private List<University> universities;
    private UniversityListener universityListener;

    public UniversityAdapter(Context context, List<University> universities, UniversityListener universityListener)
    {
        this.context = context;
        this.universities = universities;
        this.universityListener = universityListener;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_university, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterHolder holder, final int position)
    {

        final University item= universities.get(position);
        holder.txtName.setText("");
        //Picasso.get().load( item.getImage()).placeholder(R.drawable.ic_default_store_prudoct_image).error(R.drawable.ic_default_store_prudoct_image).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(NO_CACHE).into(holder.imgFood);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                universityListener.onSelectedUniversity(item);
            }
        });
    }
    @Override
    public int getItemCount()
    {
        return universities.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder
    {
        TextView txtName;
        ImageView imgIcon;
        public AdapterHolder(View itemView)
        {
            super(itemView);
            txtName=(TextView) itemView.findViewById(R.id.txt_name_store_dialog_row);
            imgIcon=(ImageView) itemView.findViewById(R.id.img_icon_university_dialog_row);
        }
    }
}
