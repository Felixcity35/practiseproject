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
import com.dlso.alcpractice.model.LearningLeadersModel;

import java.util.ArrayList;
import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder> {

    private List<LearningLeadersModel> leadersModelList = new ArrayList<>();
    private Context context ;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_leaders_item,parent,false);

        return new LearningLeadersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LearningLeadersModel learningLeadersModel = leadersModelList.get(position);

        holder.name.setText(learningLeadersModel.getName());
        holder.hours.setText(""+learningLeadersModel.getHours() + " Learning Hours,");
        holder.country.setText(learningLeadersModel.getCountry());


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.badge)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform() ;
        Glide.with(context).load(learningLeadersModel.getBadgeUrl()).apply(options).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return leadersModelList.size();
    }

    public void setLearningLeaders(List<LearningLeadersModel> leadersModelList, Context context){
        this.leadersModelList = leadersModelList ;
        this.context = context ;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

          ImageView image;
          TextView name, country, hours ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            hours = itemView.findViewById(R.id.hours);
        }
    }
}
