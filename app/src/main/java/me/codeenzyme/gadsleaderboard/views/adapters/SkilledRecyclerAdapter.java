package me.codeenzyme.gadsleaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.codeenzyme.gadsleaderboard.R;
import me.codeenzyme.gadsleaderboard.models.TopSkilled;

public class SkilledRecyclerAdapter extends RecyclerView.Adapter<SkilledRecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TopSkilled> list;


    public SkilledRecyclerAdapter(Context context, ArrayList<TopSkilled> topSkilledArrayList) {
        this.context = context;
        this.list = topSkilledArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_top_skilled_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getBadgeUrl())
                .dontAnimate()
                .centerCrop()
                .placeholder(android.R.drawable.gallery_thumb)
                .into(holder.profileImage);

        holder.detailTextView.setText(context
                .getString(R.string.skilled_detail, list.get(position).getScore(), list.get(position).getCountry()));

        holder.nameTextView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profileImage;
        private TextView nameTextView;
        private TextView detailTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image_skilled);
            nameTextView = itemView.findViewById(R.id.tv_skilled_name);
            detailTextView = itemView.findViewById(R.id.tv_skilled_detail);
        }
    }
}
