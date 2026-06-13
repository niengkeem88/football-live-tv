package com.footballlive.tv.data.remote;

import com.footballlive.tv.BuildConfig;
import com.footballlive.tv.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {
    private static ApiService service;

    private RetrofitClient() {}

    public static ApiService get() {
        if (service == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> chain.proceed(
                            chain.request().newBuilder()
                                    .addHeader("X-RapidAPI-Key", BuildConfig.RAPIDAPI_KEY)
                                    .addHeader("X-RapidAPI-Host", Constants.RAPIDAPI_HOST)
                                    .build()))
                    .addInterceptor(logging)
                    .build();

            service = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return service;
    }
}