package com.example.weather.app.adapter.recyclerViewListCity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.eventBus.RemoveItemCityUser;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import lombok.Setter;

public class AdapterListCity extends RecyclerView.Adapter<AdapterListCity.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btnClose;
        private TextView nameCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnClose = itemView.findViewById(R.id.btn_close);
            nameCity = itemView.findViewById(R.id.tv_nameCity);
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
        holder.nameCity.setText(list.get(position).getNameCity());
        holder.btnClose.setOnClickListener(v -> {
            EventBus.getDefault().post(new RemoveItemCityUser(list.get(position).getIdWeather()));
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }
}