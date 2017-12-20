package com.btlm.kotlingitproject.module.app

import android.support.design.internal.NavigationMenuView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.view.KeyEvent
import android.view.MenuItem
import com.btlm.kotlingitproject.KotlinGitApplication
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.base.BaseActivity
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.module.home.HomeFragment
import com.btlm.kotlingitproject.rx.RxBus
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mFragments = mutableListOf<Fragment>()

    private var exitTime = 0L

    private var mCurrentPos = -1
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

    override fun initWidget() {
        disableNavigationViewScrollbars(navView)
        navView.setNavigationItemSelectedListener(this)
        switchFragmentIndex(0)
    }
    fun switchFragmentIndex(index : Int){
        supportFragmentManager.beginTransaction()
                .apply {
                    if(mCurrentPos != -1){
                        hide(mFragments[mCurrentPos])
                    }else{
                        add(R.id.flContent,mFragments[index])
                    }
                    show(mFragments[index]).commit()
                }
    }

    /**
     * 去掉滚动条
     */
    private fun disableNavigationViewScrollbars(navigationView: NavigationView?){
        navigationView?.let {
            val navigationMenuView = it.getChildAt(0) as NavigationMenuView
            navigationMenuView.isVerticalScrollBarEnabled = false
        }
    }





    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initStatusBar() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this,mDrawerLayout,AppUtils.getColor(R.color.colorPrimary))
    }

    override fun initVariables() {
      initFragment()

        // 监听事件
        RxBus.toFlowable(Event.StartNavigationEvent::class.java)
                .compose(bindToLifecycle())
                .subscribe({
                    if(it.start){
                        // 打开
                        toggleDrawer()
                    }
                })
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    fun toggleDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START)
        }
    }



    private fun initFragment(){
        mFragments.add(HomeFragment.newInstance())
    }

    /**
     * 监听back键处理
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))){
                mDrawerLayout.closeDrawers()
            }else{
                exitApp()
            }
        }
        return true
    }

    /**
     * 双击退出App
     */
    private fun exitApp(){
        if(System.currentTimeMillis() - exitTime > 2000){
            toast("再按一次退出")
            exitTime = System.currentTimeMillis()
        }else{
            KotlinGitApplication.instance.exitApp()
        }
    }


}
