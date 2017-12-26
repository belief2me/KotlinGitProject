package com.btlm.kotlingitproject.adapter.discover

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.discover.MulInterest
import com.btlm.kotlingitproject.ext.startAnim
import com.btlm.kotlingitproject.module.app.BrowerActivity
import com.btlm.kotlingitproject.utils.time.TimeUtils


import com.btlm.kotlingitproject.widget.CircleImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youth.banner.Banner


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 22:45
 * * 描述:
 */
class InterestAdapter(data: List<MulInterest>) : BaseMultiItemQuickAdapter<MulInterest, BaseViewHolder>(data) {

    init {
        addItemType(MulInterest.TYPE_BANNER, R.layout.layout_banner)
        addItemType(MulInterest.TYPE_CATEGRORY, R.layout.layout_item_interest_categroty)
        addItemType(MulInterest.TYPR_HEADER, R.layout.layout_item_interest_head)
        addItemType(MulInterest.TYPR_ITEM, R.layout.layout_item_interest_item)

    }

    override fun convert(holder: BaseViewHolder, mulInterest: MulInterest) {
        when (mulInterest.itemType) {
            MulInterest.TYPE_BANNER -> {
                val banner = holder.getView<Banner>(R.id.banner)
                val adList = mulInterest.interestAdList?.result
                val urls = adList?.map({ bannerBean -> bannerBean.ads_image })
                banner.startAnim(urls)
                banner.setOnBannerListener { i ->
                    adList?.let {
                        val interestAd = it[i]
                        BrowerActivity.startActivity(mContext, interestAd.ads_image_link, interestAd.ads_title, interestAd.ads_image)
                    }
                }
            }
            MulInterest.TYPE_CATEGRORY -> {
                val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(false)
                recyclerView.isNestedScrollingEnabled = false
                val layoutManager = GridLayoutManager(mContext, 4)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = IntersetCategroyAdapter(mulInterest.interestCategroryList)
            }
            MulInterest.TYPR_HEADER -> {
            }
            MulInterest.TYPR_ITEM -> {
                mulInterest.community?.let {
                    Glide.with(mContext)
                            .load(it.post_info.author_avatar)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<CircleImageView>(R.id.iv_author_avatar))
                    holder.setText(R.id.tv_name, it.post_info.author_name)
                            .setText(R.id.tv_summary, it.post_info.post_summary)
                            .setText(R.id.tv_title, it.post_info.post_title)
                            .setText(R.id.tv_group, "[${it.community_info.community_name}]")
                            .setText(R.id.tv_time, com.btlm.kotlingitproject.utils.TimeUtils.formatDate(TimeUtils.millis2String(it.post_info.post_time)))
                            .setText(R.id.tv_img_count, "${it.post_info.image_count}")
                            .setText(R.id.tv_reply_count, "${it.post_info.reply_count}")
                            .setVisible(R.id.iv_image, it.post_info.image_count != 0)
                            .setVisible(R.id.tv_img_count, it.post_info.image_count != 0)
                    if (it.post_info.image_count != 0) {
                        Glide.with(mContext)
                                .load(it.post_info.post_image_list[0].image_url)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .dontAnimate()
                                .into(holder.getView<ImageView>(R.id.iv_image))
                    }
                }
            }
        }
    }

}
