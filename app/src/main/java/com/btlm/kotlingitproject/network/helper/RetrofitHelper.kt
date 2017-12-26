package com.btlm.kotlingitproject.network.helper


import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.bean.app.video.VideoDetail
import com.btlm.kotlingitproject.bean.app.video.VideoDetailComment
import com.btlm.kotlingitproject.bean.app.video.VideoPlayer
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetail
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailComment
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailRecommend
import com.btlm.kotlingitproject.bean.discover.*
import com.btlm.kotlingitproject.bean.live.LivePartition
import com.btlm.kotlingitproject.bean.live.LiveRecommend
import com.btlm.kotlingitproject.bean.recommend.AllStationRank
import com.btlm.kotlingitproject.bean.region.AllRegionRank
import com.btlm.kotlingitproject.bean.region.Region
import com.btlm.kotlingitproject.bean.region.RegionRecommend
import com.btlm.kotlingitproject.bean.region.RegionType
import com.btlm.kotlingitproject.bean.search.*
import com.btlm.kotlingitproject.bean.user.UpDetail
import com.btlm.kotlingitproject.network.api.*
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
                     private val bangumiService: BangumiService,
                     private val rankService : RankService,
                     private val im9Service: Im9Service) {


    fun getSplash(): Flowable<Splash> = appService.getSplash()

    fun getLivePartition() : Flowable<HttpResponse<LivePartition>> = liveService.getLivePartition()

    fun getLiveRecommend() : Flowable<HttpResponse<LiveRecommend>> = liveService.getLiveRecommend()

    fun getVideoDetail() : Flowable<VideoDetail> = appService.getVideoDetail()

    fun getVideoDetailComment(): Flowable<VideoDetailComment> = apiService.getVideoDetailComment()

    fun getVideoPlayer(): Flowable<VideoPlayer> = appService.getVideoPlayer()

    fun getBangumiDetail(): Flowable<HttpResponse<BangumiDetail>> = bangumiService.getBangumiDetail()

    fun getHotSearchTag(): Flowable<HttpResponse<HotSearchTag>> = appService.getHotSearchTag()
    fun getSearchArchive(keyword: String, page: Int, pagesize: Int): Flowable<HttpResponse<SearchArchive>> = appService.getSearchArchive(keyword, page, pagesize)

    fun getUpDetail(): Flowable<UpDetail> = appService.getUpDetail()


    fun getBangumiDetailRecommend(): Flowable<HttpResponse<BangumiDetailRecommend>> = bangumiService.getBangumiDetailRecommend()

    fun getBangumiDetailComment(): Flowable<BangumiDetailComment> = apiService.getBangumiDetailComment()

    fun getRegion(): Flowable<HttpResponse<List<Region>>> = appService.getRegion()

    fun getRegionRecommend(rid: Int): Flowable<HttpResponse<RegionRecommend>> = appService.getRegionRecommend(rid)

    fun getRegionType(rid: Int): Flowable<HttpResponse<RegionType>> = appService.getRegionType(rid)
    fun getAllStationRank(type: String): Flowable<AllStationRank> = rankService.getAllStationRank(type)

    fun getSearch(): Flowable<Search> = appService.getSearch()


    fun getSeason(): Flowable<Season> = appService.getSeason()

    fun getUp(): Flowable<Up> = appService.getUp()


    fun getMovie(): Flowable<Movie> = appService.getMovie()

    fun getAllRegionRank(type: String): Flowable<AllRegionRank> = rankService.getAllRegionRank(type)

    fun getTopicCenter(): Flowable<TopicCenter> = apiService.getTopicCenter()
    fun getActivityCenter(page: Int, pageSize: Int): Flowable<ActivityCenter> = apiService.getActivityCenter(page, pageSize)
    fun getInterestAd(): Flowable<HttpResponse<InterestAd>> = im9Service.getInterestAd()
    fun getInterestCategrory(): Flowable<HttpResponse<InterestCategrory>> = im9Service.getInterestCategrory()

    fun getCommunity(): Flowable<HttpResponse<Community>> = im9Service.getCommunity()





}
