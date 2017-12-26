package com.btlm.kotlingitproject.network.helper


import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.bean.app.video.VideoDetail
import com.btlm.kotlingitproject.bean.app.video.VideoDetailComment
import com.btlm.kotlingitproject.bean.app.video.VideoPlayer
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetail
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailComment
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailRecommend
import com.btlm.kotlingitproject.bean.discover.HotSearchTag
import com.btlm.kotlingitproject.bean.live.LivePartition
import com.btlm.kotlingitproject.bean.live.LiveRecommend
import com.btlm.kotlingitproject.bean.region.Region
import com.btlm.kotlingitproject.bean.region.RegionRecommend
import com.btlm.kotlingitproject.bean.region.RegionType
import com.btlm.kotlingitproject.bean.search.Search
import com.btlm.kotlingitproject.network.api.ApiService
import com.btlm.kotlingitproject.network.api.AppService
import com.btlm.kotlingitproject.network.api.BangumiService
import com.btlm.kotlingitproject.network.api.LiveService
import com.btlm.kotlingitproject.network.response.HttpResponse

import io.reactivex.Flowable


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/10 17:57
 * * 描述:RetrofitHelper 帮助类
 */

class RetrofitHelper(private val appService: AppService,
                     private val liveService: LiveService,
                     private val apiService: ApiService,
                     private val bangumiService: BangumiService) {


    fun getSplash(): Flowable<Splash> = appService.getSplash()

    fun getLivePartition() : Flowable<HttpResponse<LivePartition>> = liveService.getLivePartition()

    fun getLiveRecommend() : Flowable<HttpResponse<LiveRecommend>> = liveService.getLiveRecommend()

    fun getVideoDetail() : Flowable<VideoDetail> = appService.getVideoDetail()

    fun getVideoDetailComment(): Flowable<VideoDetailComment> = apiService.getVideoDetailComment()

    fun getVideoPlayer(): Flowable<VideoPlayer> = appService.getVideoPlayer()

    fun getBangumiDetail(): Flowable<HttpResponse<BangumiDetail>> = bangumiService.getBangumiDetail()

    fun getHotSearchTag(): Flowable<HttpResponse<HotSearchTag>> = appService.getHotSearchTag()


    fun getBangumiDetailRecommend(): Flowable<HttpResponse<BangumiDetailRecommend>> = bangumiService.getBangumiDetailRecommend()

    fun getBangumiDetailComment(): Flowable<BangumiDetailComment> = apiService.getBangumiDetailComment()

    fun getRegion(): Flowable<HttpResponse<List<Region>>> = appService.getRegion()

    fun getRegionRecommend(rid: Int): Flowable<HttpResponse<RegionRecommend>> = appService.getRegionRecommend(rid)

    fun getRegionType(rid: Int): Flowable<HttpResponse<RegionType>> = appService.getRegionType(rid)

    fun getSearch(): Flowable<Search> = appService.getSearch()





}
