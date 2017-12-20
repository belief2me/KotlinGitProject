package com.btlm.kotlingitproject.adapter.home.live

import com.btlm.kotlingitproject.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.bean.live.LiveRecommend

/**
 * Created by Administrator on 2017/12/20.
 */
class LiveRecommendAdapter(data : List<LiveRecommend.RecommendData.Lives>)
    : BaseQuickAdapter<LiveRecommend.RecommendData.Lives,BaseViewHolder>(R.layout.layout_item_home_live_body_mul,data){
    override fun convert(helper: BaseViewHolder?, item: LiveRecommend.RecommendData.Lives?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}