package com.btlm.kotlingitproject.adapter.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.module.home.*
import com.btlm.kotlingitproject.utils.AppUtils

/**
 * Created by Administrator on 2017/12/20.
 */
class MainAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val mTitle = AppUtils.getStringArray(R.array.main_title)
    private val mFragment = arrayOfNulls<Fragment>(mTitle.size)

    override fun getItem(position: Int): Fragment? {
        if(mFragment[position] == null){
            when(position){
                //直播
                0 -> mFragment[position] = LiveFragment.newInstance()
                //推荐
                1 -> mFragment[position] = RecommendFragment.newInstance()
                //追番
                2 -> mFragment[position] = ChaseBangumiFragment.newInstance()
                //分区
                3 -> mFragment[position] = RegionFragment.newInstance()
                //动态
                4 -> mFragment[position] = DynamicFragment.newInstance()
                //发现
                5 -> mFragment[position] = DiscoverFragment.newInstance()
            }
        }
        return mFragment[position]
    }

    override fun getCount(): Int {
        return mTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitle[position]
    }
}