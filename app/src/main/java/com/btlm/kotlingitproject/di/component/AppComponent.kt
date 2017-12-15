package com.btlm.kotlingitproject.di.component

import android.content.Context
import com.btlm.kotlingitproject.di.module.ApiModule
import com.btlm.kotlingitproject.di.module.AppModule
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Administrator on 2017/12/15.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,ApiModule::class))
interface AppComponent {

    fun getContext() : Context

    fun getRetrofitHelper() : RetrofitHelper
}