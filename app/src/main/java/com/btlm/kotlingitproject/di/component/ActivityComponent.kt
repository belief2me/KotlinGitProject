package com.btlm.kotlingitproject.di.component

import android.app.Activity
import com.btlm.kotlingitproject.module.app.SplashActivity
import com.yoyiyi.soleil.di.module.ActivityModule
import com.yoyiyi.soleil.di.scope.ActivityScope
import dagger.Component

/**
 * Created by Administrator on 2017/12/15.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity() : Activity

    fun inject(splashActivity: SplashActivity)
}