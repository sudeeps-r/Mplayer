package com.mplayer._core.di.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mplayer.BuildConfig
import com.mplayer._core._AppConstant
import com.mplayer._core.di.scope.ApplicationScope
import com.mplayer._core.error_handler.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

import okhttp3.OkHttpClient


/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@ApplicationScope
@Module
class NetworkModule {


    @Provides
    @Named(_AppConstant.BASIC_HTTP)
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().retryOnConnectionFailure(true).addInterceptor(interceptor).build()
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(client)
                .build();
    }

}