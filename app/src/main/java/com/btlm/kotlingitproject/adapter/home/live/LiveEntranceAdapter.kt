package com.btlm.kotlingitproject.adapter.home.live

import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.live.support.LiveEnter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by Administrator on 2017/12/20.
 */
class LiveEntranceAdapter(data : List<LiveEnter>?) : BaseQuickAdapter<LiveEnter,BaseViewHolder>(R.layout.item_live_entrance,data) {
    override fun convert(helper: BaseViewHolder?, item: LiveEnter?) {
        helper?.setText(R.id.tv_title,item?.title)
                ?.setImageResource(R.id.iv_icon,item?.img!!)
    }
}