package com.example.shreya_festival_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder> {

    private List<Festival> festivalList;

    public FestivalAdapter(List<Festival> festivalList) {
        this.festivalList = festivalList;
    }

    @NonNull
    @Override
    public FestivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.festival_item, parent, false);
        return new FestivalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
        Festival festival = festivalList.get(position);
        holder.name.setText(festival.getName());
        holder.date.setText(festival.getDate());
        holder.cultureInfo.setText(festival.getCultureInfo());
    }

    @Override
    public int getItemCount() {
        return festivalList.size();
    }

    public static class FestivalViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, cultureInfo;

        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.festivalName);
            date = itemView.findViewById(R.id.festivalDate);
            cultureInfo = itemView.findViewById(R.id.cultureInfo);
        }
    }
}
