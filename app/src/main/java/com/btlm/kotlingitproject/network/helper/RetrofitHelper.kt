package com.yoyiyi.soleil.network.helper


import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.network.api.ApiService
import com.btlm.kotlingitproject.network.api.LiveService
import com.btlm.kotlingitproject.network.response.HttpResponse
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.network.api.AppService

import io.reactivex.Flowable


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/10 17:57
 * * 描述:RetrofitHelper 帮助类
 */

class RetrofitHelper(private val appService: AppService,
                     private val liveService: LiveService,
                     private val apiService: ApiService
                     ) {


    fun getSplash(): Flowable<Splash> = appService.getSplash()

    fun getLivePartition() : Flowable<HttpResponse<LivePartition>> = liveService.getLivePartition()

    fun getLiveRecommend() : Flowable<HttpResponse<LiveRecommend>> = liveService.getLiveRecommend()

    fun getVideoDetail() : Flowable<VideoDetail> = appService.getVideoDetail()

    fun getVideoDetailComment(): Flowable<VideoDetailComment> = apiService.getVideoDetailComment()






}
