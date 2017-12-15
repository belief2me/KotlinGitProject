package com.btlm.kotlingitproject.di.module

import com.btlm.kotlingitproject.network.helper.OkHttpHelper
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.yoyiyi.soleil.di.qualifier.AppUrl
import com.yoyiyi.soleil.network.api.AppService
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.network.support.ApiConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Administrator on 2017/12/15.
 */
@Module
class ApiModule {

    fun createRetrofit(builder: Retrofit.Builder, client: OkHttpClient, url: String): Retrofit
            = builder
            .baseUrl(url)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpHelper.getOkHttpClient()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun provideRetrofitHelper(appService: AppService): RetrofitHelper
            = RetrofitHelper(appService)

    @Singleton
    @Provides
    @AppUrl
    fun provideAppRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit =
            createRetrofit(builder, client, ApiConstants.APP_BASE_URL)

    @Singleton
    @Provides
    fun provideAppService(@AppUrl retrofit: Retrofit): AppService
            = retrofit.create(AppService::class.java)
}