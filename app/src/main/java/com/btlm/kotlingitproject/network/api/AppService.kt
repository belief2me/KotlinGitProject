package com.yoyiyi.soleil.network.api

import com.btlm.kotlingitproject.bean.app.Splash
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/13 14:32
 * 描述:
 */
interface AppService {
    /**
     * splash界面

     * @return
     */
    @GET("/x/v2/splash?mobi_app=android&build=505000&channel=360&width=1080&height=1920&ver=4344558841496142006")
     fun getSplash(): Flowable<Splash>


    /**
     * 视频详情
     */
    @GET("/x/v2/view?access_key=18b6350cf0e8fb0cacb6cf323fc2feba&aid=3852397&appkey=1d8b6e7d45233436&build=505000&from=3&mobi_app=android&plat=0&platform=android&trackid=14859045423998458858&ts=1498645383&sign=94a3c4d143f44f023558ea52a33be403")
    fun getVideoDetail(): Flowable<VideoDetail>





}