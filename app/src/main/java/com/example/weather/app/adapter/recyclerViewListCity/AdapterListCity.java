package com.example.weather.app.adapter.recyclerViewListCity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;

import java.util.List;

import lombok.Setter;

public class AdapterListCity extends RecyclerView.Adapter<AdapterListCity.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btnClose;
        private TextView nameEnCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnClose = itemView.findViewById(R.id.btn_close);
            nameEnCity = itemView.findViewById(R.id.tv_name_en_city_adapter);
        }
    }

    @Setter
    private List<AdapterListCityItem> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }
}