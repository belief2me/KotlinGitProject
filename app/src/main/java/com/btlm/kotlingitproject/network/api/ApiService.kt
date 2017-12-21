package com.btlm.kotlingitproject.network.api

import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Administrator on 2017/12/21.
 */
interface ApiService {

    /**
     * 视频评论
     */
    @GET("/x/v2/reply?access_key=0e6adb874025dfabaa3ced3a7b22049d&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&oid=9938411&plat=2&platform=android&pn=1&ps=20&sort=0&ts=1497422373&type=1&sign=071c418a32ebc452e078308a04e1be4e")
    fun getVideoDetailComment(): Flowable<VideoDetailComment>
}