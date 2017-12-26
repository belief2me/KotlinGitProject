package com.btlm.kotlingitproject.adapter.region.sectiton

import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.region.RegionRecommend
import com.btlm.kotlingitproject.ext.startAnim
import com.btlm.kotlingitproject.module.app.BrowerActivity
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder
import com.youth.banner.Banner

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:分区推荐主播section
 */
class RegionRecommendBannerSection(private val list: List<RegionRecommend.BannerBean.TopBean>) : StatelessSection<Nothing>(R.layout.layout_banner, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {

        val banner = holder.getView<Banner>(R.id.banner)
        val urls = list.map { it.image }
        banner.startAnim(urls)
        banner.setOnBannerListener { i ->
            val bannerBean = list[i]
            BrowerActivity.startActivity(mContext, bannerBean.uri, bannerBean.title, bannerBean.image)
        }
    }



}
