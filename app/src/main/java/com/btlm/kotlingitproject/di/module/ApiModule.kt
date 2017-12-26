package com.btlm.kotlingitproject.di.module

import com.btlm.kotlingitproject.di.qualifier.ApiUrl
import com.btlm.kotlingitproject.di.qualifier.AppUrl
import com.btlm.kotlingitproject.di.qualifier.BangumiUrl
import com.btlm.kotlingitproject.di.qualifier.LiveUrl
import com.btlm.kotlingitproject.network.api.ApiService
import com.btlm.kotlingitproject.network.api.AppService
import com.btlm.kotlingitproject.network.api.BangumiService
import com.btlm.kotlingitproject.network.api.LiveService
import com.btlm.kotlingitproject.network.helper.OkHttpHelper
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.network.support.ApiConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

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
    fun provideRetrofitHelper(appService: AppService, liveService: LiveService, apiService : ApiService, bangumiService: BangumiService): RetrofitHelper
            = RetrofitHelper(appService,liveService,apiService,bangumiService)

    @Singleton
    @Provides
    @AppUrl
    fun provideAppRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit =
            createRetrofit(builder, client, ApiConstants.APP_BASE_URL)

    @Singleton
    @Provides
    fun provideAppService(@AppUrl retrofit: Retrofit): AppService
            = retrofit.create(AppService::class.java)

    @Singleton
    @Provides
    @LiveUrl
    fun provideLiveRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.LIVE_BASE_URL)


    @Singleton
    @Provides
    fun provideLiveService(@LiveUrl retrofit: Retrofit): LiveService
            = retrofit.create<LiveService>(LiveService::class.java)

    @Singleton
    @Provides
    @ApiUrl
    fun provideApiRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.API_BASE_URL)


    @Singleton
    @Provides
    fun provideApiService(@ApiUrl retrofit: Retrofit): ApiService
            = retrofit.create<ApiService>(ApiService::class.java)

    @Singleton
    @Provides
    @BangumiUrl
    fun provideBangumiRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.BANGUMI_BASE_URL)


    @Singleton
    @Provides
    fun provideBangumiService(@BangumiUrl retrofit: Retrofit): BangumiService
            = retrofit.create<BangumiService>(BangumiService::class.java)

}