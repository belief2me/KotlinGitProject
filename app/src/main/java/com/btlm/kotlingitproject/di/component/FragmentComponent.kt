package com.btlm.kotlingitproject.di.component

import android.app.Activity
import com.btlm.kotlingitproject.di.module.FragmentModule
import com.btlm.kotlingitproject.di.scope.FragmentScope
import com.btlm.kotlingitproject.module.app.video.CommentFragment
import com.btlm.kotlingitproject.module.app.video.SummaryFragment
import com.btlm.kotlingitproject.module.discover.InterestFragment
import com.btlm.kotlingitproject.module.home.*
import com.btlm.kotlingitproject.module.recommend.AllStationRankFragment
import com.btlm.kotlingitproject.module.region.AllRegionRankFragment
import com.btlm.kotlingitproject.module.region.RegionTypeFragment
import com.btlm.kotlingitproject.module.region.RegionTypeRecommendFragment
import com.btlm.kotlingitproject.module.search.ArchiveFragment
import com.btlm.kotlingitproject.module.search.MovieFragment
import com.btlm.kotlingitproject.module.search.SeasonFragment
import com.btlm.kotlingitproject.module.search.UpFragment
import dagger.Component

/**
 * Created by Administrator on 2017/12/19.
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun getActivity() : Activity

    fun inject(liveFragment: LiveFragment)
    fun inject(recommendFragment: RecommendFragment)
    fun inject(summaryFragment: SummaryFragment)
    fun inject(commentFragment: CommentFragment)

    fun inject(chaseBangumiFragment: ChaseBangumiFragment)

    fun inject(regionFragment: RegionFragment)

    fun inject(regionTypeRecommendFragment: RegionTypeRecommendFragment)
    fun inject(regionTypeFragment: RegionTypeFragment)

    fun inject(dynamicFragment: DynamicFragment)

    fun inject(discoverFragment: DiscoverFragment)
    fun inject(archiveFragment : ArchiveFragment)
    fun inject(seasonFragment: SeasonFragment)
    fun inject(upFragment: UpFragment)
    fun inject(movieFragment: MovieFragment)
    fun inject(allRegionRankFragment: AllRegionRankFragment)
    fun inject(interestFragment: InterestFragment)
    fun inject(allStationRankFragment: AllStationRankFragment)
    fun inject(archiveFragment: com.btlm.kotlingitproject.module.app.up.ArchiveFragment)

}