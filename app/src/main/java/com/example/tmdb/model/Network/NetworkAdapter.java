package com.example.tmdb.model.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tmdb.model.Network.Constants.TMDB_BASE_URL;

public class NetworkAdapter {

    private static final Object LOCK = new Object();
    private static volatile Retrofit sInstance;

    public static Retrofit getRetrofitInstance() {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(TMDB_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    private static OkHttpClient getOkhttpBuild() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

}
