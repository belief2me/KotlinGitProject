package com.btlm.kotlingitproject.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.btlm.kotlingitproject.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2017/12/19.
 */
@Module
class FragmentModule(val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideActivity() : Activity = fragment.activity
}