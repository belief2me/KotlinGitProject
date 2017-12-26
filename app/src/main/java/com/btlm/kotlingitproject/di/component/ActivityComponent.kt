package com.btlm.kotlingitproject.di.component

import android.app.Activity
import com.btlm.kotlingitproject.di.module.ActivityModule
import com.btlm.kotlingitproject.di.scope.ActivityScope
import com.btlm.kotlingitproject.module.app.SplashActivity
import com.btlm.kotlingitproject.module.app.video.VideoDetailActivity
import com.btlm.kotlingitproject.module.app.video.VideoPlayerActivity
import com.btlm.kotlingitproject.module.bangumi.BangumiDetailActivity
import com.btlm.kotlingitproject.module.region.RegionTypeActivity
import com.btlm.kotlingitproject.module.search.SearchActivity
import dagger.Component

/**
 * Created by Administrator on 2017/12/15.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity() : Activity

    fun inject(splashActivity: SplashActivity)

    fun inject(videoDetailActivity: VideoDetailActivity)
    fun inject(videoPlayerActivity: VideoPlayerActivity)
    fun inject(bangumiDetailActivity: BangumiDetailActivity)
    fun inject(regionTypeActivity: RegionTypeActivity)

    fun inject(searchActivity: SearchActivity)
}