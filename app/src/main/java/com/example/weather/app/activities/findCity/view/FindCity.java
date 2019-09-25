package com.example.weather.app.activities.findCity.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.presenter.PresenterFindCity;
import com.example.weather.app.adapter.recyclerViewFindCity.AdapterFindCity;
import com.example.weather.app.adapter.recyclerViewFindCity.ItemAdapterFindCity;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindCity extends MvpAppCompatActivity implements ViewFindCity {

    @InjectPresenter PresenterFindCity presenter;

    @BindView(R.id.flex_box_container) FlexboxLayout flexboxContainer;

    @BindView(R.id.recycler_list_city) RecyclerView recyclerViewCity;

    @BindView(R.id.sw_city) SearchView searchView;
    @BindView(R.id.tv_popular_cities) TextView tvPopularCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_city);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCity.setLayoutManager(layoutManager);
        recyclerViewCity.setHasFixedSize(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.setTextSearch(newText);
                return false;
            }
        });
    }

    private AdapterFindCity adapterFindCity;

    @Override
    public void updateRecyclerView(List<ItemAdapterFindCity> cityList) {
        if (recyclerViewCity.getVisibility() == View.GONE) {
            flexboxContainer.setVisibility(View.GONE);
            tvPopularCities.setVisibility(View.GONE);
            recyclerViewCity.setVisibility(View.VISIBLE);
            adapterFindCity = new AdapterFindCity(cityList);
            recyclerViewCity.setAdapter(adapterFindCity);
        }
        adapterFindCity.notifyDataSetChanged();
    }

//    @OnClick(R.id.btn_search_city)
//    public void SearchCity() {
////        presenter.
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        presenter.disposable();
    }
}