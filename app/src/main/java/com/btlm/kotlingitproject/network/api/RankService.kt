package com.btlm.kotlingitproject.network.api

import com.btlm.kotlingitproject.bean.recommend.AllStationRank
import com.btlm.kotlingitproject.bean.region.AllRegionRank
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Administrator on 2017/12/26.
 */
interface RankService {
    /**
     * 全区排行

     * @param type
     * *
     * @return
     */
    @GET("index/rank/{type}")
    fun getAllRegionRank(@Path("type") type: String): Flowable<AllRegionRank>

    /**
     * 全站排行
     * @param type
     * *
     * @return
     */
    @GET("index/rank/{type}")
    fun getAllStationRank(@Path("type") type: String): Flowable<AllStationRank>
}