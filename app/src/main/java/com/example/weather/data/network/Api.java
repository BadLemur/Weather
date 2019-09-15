package com.example.weather.data.network;

import com.example.weather.data.network.data.GetPostDataWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String DOMAIN = "https://api.openweathermap.org";

    @GET("/data/2.5/weather")
    Observable<GetPostDataWeather> getDataWeatherByCity(@Query("id") long idWeather, @Query("appid") String appId, @Query("units") String units);

    class Instance {
        private static Retrofit getRetrofit() {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .baseUrl(DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(okHttpClient.build());
            return retrofit.build();
        }

        private static Gson getGson() {
            return new GsonBuilder()
                    .setLenient()
                    .create();
        }

        public static Api getApi() {
            return getRetrofit().create(Api.class);
        }
    }
}