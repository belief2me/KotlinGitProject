package com.yoyiyi.soleil.network.api

import com.btlm.kotlingitproject.bean.app.Splash
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





}