package com.vn.base.api.retrofit;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vn.base.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ngoclv on 12/11/2017.
 */

public class ApiService<T> {

    private String mHost;

    protected T mServiceInterface;

    public ApiService(@NonNull String host, @NonNull Class<T> clazz) {
        mHost = host;
        init(clazz);
    }

    private void init(Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        setCustomGsonBuilder(gsonBuilder);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        final OkHttpClient.Builder builder = getOkHttpClient()
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return ApiService.this.verify(hostname, session);
                    }
                })
                .addInterceptor(interceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.addHeader("Accept", "application/json");
                requestBuilder.addHeader("Content-Type", "application/json");
                addHeader(requestBuilder);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        final long timeOut = 60;
        OkHttpClient okHttpClient = builder.connectTimeout(timeOut, TimeUnit.SECONDS)
                                           .readTimeout(3*timeOut, TimeUnit.SECONDS)
                                           .writeTimeout(3* timeOut, TimeUnit.SECONDS)
                                           .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mHost)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .client(okHttpClient)
                .build();
        mServiceInterface = retrofit.create(clazz);
    }


    private OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            X509TrustManager trustManager = (X509TrustManager) trustAllCerts[0];
            return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OkHttpClient.Builder();
    }

    /**
     * add header to request
     *
     * @param requestBuilder builder
     */
    @SuppressLint("LogNotTimber")
    protected void addHeader(@NonNull Request.Builder requestBuilder) {
        //handle add custom request params at here
        Log.i("ApiService", "add Header");
    }

    /**
     * implement custom gson builder if need
     *
     * @param builder gsonBuilder
     */
    protected void setCustomGsonBuilder(@NonNull GsonBuilder builder) {

    }

    /**
     * get OkHttpClient Builder
     * default always return unsafe OkHttpClient
     */
    protected OkHttpClient.Builder getOkHttpClient() {
        return getUnsafeOkHttpClient();
    }

    protected SSLContext getSSLContext() {
        return null;
    }

    @SuppressLint("LogNotTimber")
    protected boolean verify(String hostname, SSLSession session) {
        Log.i("ApiService", "hostname = " + hostname);
        return true;
    }
}
