package com.example.weather.app.adapter.recyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class AdapterFindCity extends RecyclerView.Adapter<AdapterFindCity.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameEnCity, country;
        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.find_city_layout);
            nameEnCity = itemView.findViewById(R.id.tv_name_en_city_adapter);
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
        holder.country.setText(item.getCountry().trim());
        if (item.getNameCityEN() != null)
            holder.nameEnCity.setText(item.getNameCityEN().trim());
        holder.layout.setOnClickListener(view -> {
            EventBus.getDefault().post(new ClickItemRecyclerView(list.get(position).getIdWeather()));
            ((Activity) holder.itemView.getContext()).finish();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}