package com.btlm.kotlingitproject.di.component

import android.app.Activity
import com.btlm.kotlingitproject.di.module.FragmentModule
import com.btlm.kotlingitproject.di.scope.FragmentScope
import com.btlm.kotlingitproject.module.home.LiveFragment
import dagger.Component

/**
 * Created by Administrator on 2017/12/19.
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun getActivity() : Activity

    fun inject(liveFragment: LiveFragment)

}