package com.btlm.kotlingitproject.adapter.home.section.chase

import android.view.View
import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.chase.RecommendBangumi
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class ChaseAdSection(private val ad: RecommendBangumi.Ad) : StatelessSection<Nothing>(R.layout.layout_item_home_chase_footer, R.layout.layout_empty) {


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        Glide.with(mContext)
                .load<Any>(ad.img)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<View>(R.id.iv_video_preview) as ImageView)
        holder.setVisible(R.id.tv_title, false)
                .setVisible(R.id.tv_new_tag, false)
                .setVisible(R.id.tv_des, false)

    }
}
