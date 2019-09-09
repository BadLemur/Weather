package com.example.weather.app.activities.findCity.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.presenter.PresenterFindCity;
import com.example.weather.app.adapter.AdapterFindCity;
import com.example.weather.app.adapter.ItemAdapterFindCity;
import com.example.weather.data.DB.city.City;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class FindCity extends MvpAppCompatActivity implements ViewFindCity {
    private static final String TAG = "FindCity";
    @InjectPresenter
    PresenterFindCity presenter;

    @BindView(R.id.flex_box_container)
    FlexboxLayout flexboxContainer;

    @BindView(R.id.recycler_list_city)
    RecyclerView recyclerViewCity;

    @BindView(R.id.sw_city)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_city);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCity.setLayoutManager(layoutManager);
        recyclerViewCity.setHasFixedSize(false);

        Observable<String> observable = Observable.create(emitter ->
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        emitter.onNext(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        emitter.onNext(newText);
                        return false;
                    }
                }));
        presenter.searchViewObservable(observable);
    }

    private AdapterFindCity adapterFindCity;

    @Override
    public void updateRecyclerView(List<ItemAdapterFindCity> cityList) {
        if (recyclerViewCity.getVisibility() == View.GONE) {
            flexboxContainer.setVisibility(View.GONE);
            recyclerViewCity.setVisibility(View.VISIBLE);
            adapterFindCity = new AdapterFindCity(cityList);
        }
        adapterFindCity.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}