package com.btlm.kotlingitproject.adapter.region

import android.widget.ImageView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.region.RegionEnter
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.rx.RxBus

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 23:30
 * * 描述:分区入口
 */
class RegionRecommendEntranceAdapter(data: List<RegionEnter>?) : BaseQuickAdapter<RegionEnter, BaseViewHolder>(R.layout.item_live_entrance, data) {

    override fun convert(holder: BaseViewHolder, item: RegionEnter) {
        Glide.with(mContext)
                .load(item.img)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_icon))
        holder.setText(R.id.tv_title, item.title)
        holder.itemView.setOnClickListener {
            //发送点击事件
            val positionEvent = Event.RegionEntrancePositionEvent()
            positionEvent.position = holder.adapterPosition
            RxBus.post(positionEvent)
        }
    }

}