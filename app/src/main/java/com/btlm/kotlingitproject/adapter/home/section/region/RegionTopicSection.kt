package com.btlm.kotlingitproject.adapter.home.section.region

import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.region.Region
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页分区话题section
 */
class RegionTopicSection(private val bodyBean: Region.Body) : StatelessSection<Region.Body>(R.layout.layout_item_home_region_topic, R.layout.layout_empty) {


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        //设置标题图片
        holder.setText(R.id.tv_title, "话题")
                .setImageResource(R.id.iv_icon, R.drawable.ic_header_topic)
        Glide.with(mContext)
                .load(bodyBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
    }

}
