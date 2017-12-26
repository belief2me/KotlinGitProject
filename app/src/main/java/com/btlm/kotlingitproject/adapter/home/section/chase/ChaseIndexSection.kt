package com.btlm.kotlingitproject.adapter.home.section.chase

import android.view.View
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder

/**
 * Created by Administrator on 2017/12/22.
 */
class ChaseIndexSection : StatelessSection<Nothing>(R.layout.layout_item_home_chase_bangumi_index,R.layout.layout_empty){

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.getView<View>(R.id.ll_bangumi_timeline)
                ?.setOnClickListener({
//                    mContext.startActivity(Intent(mContext, BangumiScheduleActivity::class.java))
                })
        holder?.getView<View>(R.id.ll_bangumi_index)
                ?.setOnClickListener({
//                    mContext.startActivity(Intent(mContext, BangumiIndexActivity::class.java))
                })
    }
}