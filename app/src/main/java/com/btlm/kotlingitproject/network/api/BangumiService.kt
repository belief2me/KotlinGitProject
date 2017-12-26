package com.btlm.kotlingitproject.network.api

import com.btlm.kotlingitproject.bean.bangumi.BangumiDetail
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailRecommend
import com.btlm.kotlingitproject.network.response.HttpResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 12:01
 * * 描述:
 */

interface BangumiService {

    /**
     * 番剧详情番剧推荐
     */
    @GET("api/season/recommend/rnd/6066.json?appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ts=1497169314&sign=da4d668fe4aaf97de55541f8d05ac57f")
    fun getBangumiDetailRecommend(): Flowable<HttpResponse<BangumiDetailRecommend>>

    /**
     * 番剧详情
     */
    @GET("api/season_v5?access_key=ccfbb1b10ce8ab8418a2e00b9ca9a3a0&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&season_id=6066&ts=1497169313&type=bangumi&sign=c6796f6ea4a6cae28a4d8fc555fde2da")
    fun getBangumiDetail(): Flowable<HttpResponse<BangumiDetail>>
}
