package com.btlm.kotlingitprojec.adapter.discover

import android.widget.FrameLayout
import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.discover.GameCenter
import com.btlm.kotlingitproject.module.app.BrowerActivity
import com.btlm.kotlingitproject.utils.AppUtils

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/6 12:57
 * * 描述:
 */

class GameCenterBookGiftAdapter(data: List<GameCenter.BookGiftBean>?) : BaseQuickAdapter<GameCenter.BookGiftBean, BaseViewHolder>(R.layout.item_game_center_book_gift, data) {


    override fun convert(holder: BaseViewHolder, bookGiftBean: GameCenter.BookGiftBean) {
        holder.setText(R.id.tv_title, bookGiftBean.name)
        Glide.with(mContext)
                .load(bookGiftBean.image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_preview))
        holder.itemView.setOnClickListener { BrowerActivity.startActivity(mContext, bookGiftBean.link, bookGiftBean.name, bookGiftBean.image) }
        val position = holder.adapterPosition
        if (position == itemCount - 1) {
            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.MATCH_PARENT)
            params.setMargins(AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
            holder.itemView.layoutParams = params
        }

    }
}
