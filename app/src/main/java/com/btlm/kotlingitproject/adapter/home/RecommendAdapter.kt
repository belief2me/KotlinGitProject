package com.btlm.kotlingitproject.adapter.home

import android.content.Intent
import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.recommend.MulRecommend
import com.btlm.kotlingitproject.bean.recommend.Recommend
import com.btlm.kotlingitproject.ext.startAnim
import com.btlm.kotlingitproject.module.app.BrowerActivity
import com.btlm.kotlingitproject.module.app.video.VideoDetailActivity
import com.btlm.kotlingitproject.utils.NumberUtils
import com.btlm.kotlingitproject.utils.time.FormatUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youth.banner.Banner

/**
 * Created by Administrator on 2017/12/21.
 */
class RecommendAdapter(data : List<MulRecommend>) : BaseMultiItemQuickAdapter<MulRecommend,BaseViewHolder>(data) {
    init {
        addItemType(MulRecommend.TYPE_HEADER, R.layout.layout_recommend_banner)
        addItemType(MulRecommend.TYPE_ITEM, R.layout.layout_item_home_recommend_body)
    }
    override fun convert(holder: BaseViewHolder?, mulRecommend: MulRecommend?) {
        when (holder?.itemViewType) {
            MulRecommend.TYPE_HEADER -> {
                val banner = holder?.getView<Banner>(R.id.banner)
                val banner_item = mulRecommend?.data
                val urls = banner_item?.map(Recommend.BannerItem::image)
                banner.startAnim(urls)
                banner.setOnBannerListener {
                    val i = it
                    banner_item?.let {
                        val bannerBean = it[i]
                        BrowerActivity.startActivity(mContext, bannerBean.uri, bannerBean.title, bannerBean.image)

                    }
                }
            }

            MulRecommend.TYPE_ITEM -> {
                Glide.with(mContext)
                        .load(mulRecommend?.recommend?.cover)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bili_default_image_tv)
                        .dontAnimate()
                        .into(holder.getView<ImageView>(R.id.iv_video_preview))
                holder.setText(R.id.tv_video_play_num, NumberUtils.format("${mulRecommend?.recommend?.play}"))
                        .setText(R.id.tv_video_time, FormatUtils.formatDuration("${mulRecommend?.recommend?.duration}"))
                        .setText(R.id.tv_video_danmaku, NumberUtils.format("${mulRecommend?.recommend?.danmaku}"))
                        .setText(R.id.tv_video_title, mulRecommend?.recommend?.title)
                if (mulRecommend?.recommend?.open != 0) {
                    //直播
                    holder.setText(R.id.tv_video_tag, mulRecommend?.recommend?.area)
                } else {
                    //推荐
                    holder.setText(R.id.tv_video_tag, "${mulRecommend?.recommend?.tname}·${mulRecommend?.recommend?.tag?.tag_name}")
                }
                holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }
            }
        }
    }
}