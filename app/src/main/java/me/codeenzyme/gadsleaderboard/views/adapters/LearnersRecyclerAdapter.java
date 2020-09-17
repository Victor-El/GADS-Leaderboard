package me.codeenzyme.gadsleaderboard.views.adapters;

import android.content.Context;
import android.util.Log;
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
import me.codeenzyme.gadsleaderboard.models.TopLearner;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TopLearner> list;

    public LearnersRecyclerAdapter(Context context, ArrayList<TopLearner> topLearnerArrayList) {
        this.context = context;
        this.list = topLearnerArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_top_learner_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getBadgeUrl())
                .dontAnimate()
                .centerCrop()
                .placeholder(android.R.drawable.gallery_thumb)
                .into(holder.getCircleImageView());

        holder.getNameTextView().setText(list.get(position).getName());

        holder.getLearningHoursAndCountryTextView().setText(context
                .getString(R.string.learner_detail, list.get(position).getHours(), list.get(position).getCountry()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView nameTextView;
        private TextView learningHoursAndCountryTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.profile_image);
            nameTextView = itemView.findViewById(R.id.tv_learner_name);
            learningHoursAndCountryTextView = itemView.findViewById(R.id.tv_learner_detail);

        }

        public CircleImageView getCircleImageView() {
            return circleImageView;
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getLearningHoursAndCountryTextView() {
            return learningHoursAndCountryTextView;
        }
    }
}
