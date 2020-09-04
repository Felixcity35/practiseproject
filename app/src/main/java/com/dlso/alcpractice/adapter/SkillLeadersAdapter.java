package com.dlso.alcpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dlso.alcpractice.R;
import com.dlso.alcpractice.model.SkillLeadersModel;

import java.util.ArrayList;
import java.util.List;

public class SkillLeadersAdapter extends RecyclerView.Adapter<SkillLeadersAdapter.ViewHolder> {

    private List<SkillLeadersModel> SkillLeadersList = new ArrayList<>();
    private Context context ;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skilliq_items,parent,false);

        return new SkillLeadersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

  SkillLeadersModel skillLeadersModel = SkillLeadersList.get(position);

        holder.name.setText(skillLeadersModel.getName());
        holder.score.setText(""+skillLeadersModel.getScore() + " Skill IQ Score, ");
        holder.country.setText(skillLeadersModel.getCountry());


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.top_learner)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform() ;
        Glide.with(context).load(skillLeadersModel.getBadgeUrl()).apply(options).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return SkillLeadersList.size();
    }

    public void setSkillLeaders(List<SkillLeadersModel> skillLeaders, Context context){
        this.SkillLeadersList = skillLeaders ;
        this.context = context ;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

          ImageView image;
          TextView name, country, score ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            score = itemView.findViewById(R.id.hours);
        }
    }
}
