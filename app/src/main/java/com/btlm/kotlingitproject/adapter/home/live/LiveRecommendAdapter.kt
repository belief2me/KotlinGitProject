package com.btlm.kotlingitproject.adapter.home.live

import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.utils.NumberUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.bean.live.LiveRecommend

/**
 * Created by Administrator on 2017/12/20.
 */
class LiveRecommendAdapter(data : List<LiveRecommend.RecommendData.Lives>)
    : BaseQuickAdapter<LiveRecommend.RecommendData.Lives,BaseViewHolder>(R.layout.layout_item_home_live_body_mul,data){
    override fun convert(helper: BaseViewHolder?, item: LiveRecommend.RecommendData.Lives?) {
        Glide.with(mContext)
                .load(item?.cover?.src)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(helper?.getView<ImageView>(R.id.iv_video_preview))

        helper?.setText(R.id.tv_video_live_up,item?.owner?.name)
                //在线人数
                ?.setText(R.id.tv_video_online, NumberUtils.format("${item?.online}"))
        helper?.setText(R.id.tv_video_title,item?.title)

    }
}