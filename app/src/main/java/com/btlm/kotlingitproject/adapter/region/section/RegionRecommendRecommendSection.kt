package com.btlm.kotlingitproject.adapter.region.sectiton

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.region.RegionRecommend
import com.btlm.kotlingitproject.module.app.video.VideoDetailActivity
import com.btlm.kotlingitproject.module.region.RegionTypeActivity
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.NumberUtils
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/4 17:07
 * * 描述:
 */
class RegionRecommendRecommendSection(recommendBeanList: List<RegionRecommend.RecommendBean>) : StatelessSection<RegionRecommend.RecommendBean>(R.layout.layout_item_region_head, R.layout.layout_item_region_body, recommendBeanList) {

    override fun convert(holder: ViewHolder, recommendBean: RegionRecommend.RecommendBean, position: Int) {
        Glide.with(mContext)
                .load(recommendBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, recommendBean.title)
                .setText(R.id.tv_video_play_num, NumberUtils.format("${recommendBean.play}"))
                .setText(R.id.tv_video_favourite, NumberUtils.format("${recommendBean.danmaku}"))
        if (position % 2 == 0) {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        } else {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        }
          holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }


    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "热门推荐")
                .setImageResource(R.id.iv_icon, R.mipmap.ic_category_promo)
                .setVisible(R.id.tv_rank, true)
                .setVisible(R.id.tv_look_up, false)
        holder.getView<TextView>(R.id.tv_rank)
                .setOnClickListener({
                    if (mContext is RegionTypeActivity) {
                        val activity = mContext as RegionTypeActivity
//                        AllRegionRankActivity.startActivity(mContext, activity.mTitle)
                    }
                })
    }
}
