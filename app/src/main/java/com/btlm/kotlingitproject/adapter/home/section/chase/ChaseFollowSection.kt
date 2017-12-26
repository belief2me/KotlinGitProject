package com.btlm.kotlingitproject.adapter.home.section.chase

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.ChaseFllowAdapter
import com.btlm.kotlingitproject.bean.chase.ChaseBangumi
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.SpanUtils
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder

/**
 * Created by Administrator on 2017/12/22.
 */
class ChaseFollowSection(private val count : String,private val mList: List<ChaseBangumi.Follows>) : StatelessSection<Nothing>
(R.layout.layout_item_home_chase_head,R.layout.layout_item_home_chase_body){

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        // 设置标题图片
        holder?.setText(R.id.tv_title,"我的追番")
                ?.setImageResource(R.id.iv_icon,R.drawable.bangumi_follow_home_ic_mine)
        if(count == "0"){
            holder?.setText(R.id.tv_more,"查看更多")
        }else{
            holder?.setText(R.id.tv_more,SpanUtils().append("最近更新")
                    .append(count)
                    .setForegroundColor(AppUtils.getColor(R.color.pink_text_color)).create())
        }
    }
    override fun onBindItemViewHolder(holder: ViewHolder, position: Int) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ChaseFllowAdapter(mList)
    }
}