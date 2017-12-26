package com.btlm.kotlingitproject.adapter.bangumi

import android.content.Intent
import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.bangumi.BangumiDetailRecommend
import com.btlm.kotlingitproject.module.bangumi.BangumiDetailActivity
import com.btlm.kotlingitproject.utils.NumberUtils

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/12 19:05
 * * 描述:
 */

internal class BangumiDetailRecommendAdapter(data: List<BangumiDetailRecommend.ListBean>?) : BaseQuickAdapter<BangumiDetailRecommend.ListBean, BaseViewHolder>(R.layout.item_bangumi_detail_recommend, data) {

    override fun convert(holder: BaseViewHolder, listBean: BangumiDetailRecommend.ListBean) {
        Glide.with(mContext)
                .load(listBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_follow, "${NumberUtils.format("${listBean.follow}")}追番")
                .setText(R.id.tv_video_title, listBean.title)
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, BangumiDetailActivity::class.java)) }

    }
}
