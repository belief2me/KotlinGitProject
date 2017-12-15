package com.btlm.kotlingitproject.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2017/12/15.
 */
@Module
class AppModule(val context : Context) {

    @Provides
    fun provideContext() : Context = context
}