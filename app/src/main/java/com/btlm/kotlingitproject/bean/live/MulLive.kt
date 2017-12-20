package com.btlm.kotlingitproject.bean.live

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend

/**
 * Created by Administrator on 2017/12/20.
 */
class MulLive(val title: String? = null,
              val url: String? = null,
              val count: Int? = null,
              val itemTypez: Int,
              val hasMore: Boolean? = null,
              val bannerData: LiveRecommend.RecommendData.BannerData? = null,
              val bannerBeanList: List<LivePartition.Banner>? = null,
              val recommendLives: List<LiveRecommend.RecommendData.Lives>? = null,
              val partityLives: List<LivePartition.Partitions.Lives>? = null)
    : MultiItemEntity {


    companion object {

        val TYPR_HEADER = 1//
        val TYPE_RECOMMEND_ITEM = 2
        val TYPE_PARTY_ITEM = 3
        val TYPE_FOOTER = 4//
        val TYPE_RECOMMEND_BANNER = 9

        val TYPE_BANNER = 5//
        val TYPE_ENTRANCE = 6//

    }

    override fun getItemType(): Int = itemTypez

}
