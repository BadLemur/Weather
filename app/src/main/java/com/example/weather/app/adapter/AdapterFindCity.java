package com.example.weather.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;

import java.util.List;

public class AdapterFindCity extends RecyclerView.Adapter<AdapterFindCity.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameRuCity, nameEnCity, region, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameRuCity = itemView.findViewById(R.id.tv_name_ru_city_adapter);
            nameEnCity = itemView.findViewById(R.id.tv_name_en_city_adapter);
            region = itemView.findViewById(R.id.tv_region_adapter);
            country = itemView.findViewById(R.id.tv_country_adapter);
        }
    }

    private List<ItemAdapterFindCity> list;

    public AdapterFindCity(List<ItemAdapterFindCity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.find_city_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemAdapterFindCity item = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}