package com.btlm.kotlingitproject.di.module

import android.app.Activity
import com.btlm.kotlingitproject.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 16:41
 * * 描述:Activity模型
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = activity
}
