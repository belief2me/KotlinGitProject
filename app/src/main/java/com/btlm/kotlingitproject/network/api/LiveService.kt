package com.btlm.kotlingitproject.network.api

import com.btlm.kotlingitproject.bean.live.LivePartition
import com.btlm.kotlingitproject.bean.live.LiveRecommend
import com.btlm.kotlingitproject.network.response.HttpResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Administrator on 2017/12/20.
 */
interface LiveService {

    @GET("/AppNewIndex/recommend?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639021&sign=9d024a5b09edddd51636d17d860622d2")
    fun getLiveRecommend(): Flowable<HttpResponse<LiveRecommend>>

    @GET("/AppNewIndex/common?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639884&sign=74b510ce56ef302742aafad2e20f9899")
    fun getLivePartition(): Flowable<HttpResponse<LivePartition>>
}