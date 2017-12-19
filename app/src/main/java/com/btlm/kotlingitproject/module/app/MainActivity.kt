package com.btlm.kotlingitproject.module.app

import android.app.Fragment
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.base.BaseActivity
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mFragments = mutableListOf<Fragment>()
    /**
     * 侧滑面板监听事件

     * @param item
     * *
     * @return
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        AppUtils.runOnUIDelayed({
            when (item.itemId) {
//                R.id.item_vip -> startActivity(Intent(this@MainActivity, VipActivity::class.java))
//                R.id.item_unicom -> BrowerActivity.startActivity(this@MainActivity, Constants.BLACK_BOARD_URL, "联通免流量服务", "")
//                R.id.item_down -> startActivity(Intent(this@MainActivity, OfflineDownloadActivity::class.java))
            }
        }, 230)
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initStatusBar() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this,mDrawerLayout,AppUtils.getColor(R.color.colorPrimary))
    }

    override fun initVariables() {
      initFragment()
    }

    private fun initFragment(){
    }


}
