package com.btlm.kotlingitproject.adapter.discover

import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.discover.TopicCenter
import com.btlm.kotlingitproject.module.app.BrowerActivity

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 23:15
 * * 描述:话题中心
 */
class TopicCenterAdapter(data: List<TopicCenter.ListBean>?) : BaseQuickAdapter<TopicCenter.ListBean, BaseViewHolder>(R.layout.item_topic_center, data) {


    override fun convert(holder: BaseViewHolder, listBean: TopicCenter.ListBean) {
        Glide.with(mContext)
                .load(listBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_preview))
        holder.setText(R.id.tv_title, listBean.title)
        holder.itemView.setOnClickListener { BrowerActivity.startActivity(mContext, listBean.link, listBean.title, listBean.cover) }
    }
}
