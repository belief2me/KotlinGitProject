package com.btlm.kotlingitproject.module.region

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.base.BaseInjectActivity
import com.flyco.tablayout.SlidingTabLayout

/**
 * Created by Administrator on 2017/12/21.
 */
abstract class BaseRegionActivity<T : BaseContract.BasePresenter<*>,K> : BaseInjectActivity<T>(){
    protected var mTvTitle : TextView? = null
    protected var mIvBack: ImageView? = null
    protected var mList = mutableListOf<K>()
    protected var mTitles = mutableListOf<String>()
    protected var mFragment = mutableListOf<Fragment>()
    protected var mSlidingTabLayout: SlidingTabLayout? = null
    var mViewPager: ViewPager? = null

    override fun initToolbar() {
        mTvTitle = findViewById(R.id.tv_title)
        mIvBack = findViewById(R.id.iv_back)
        mIvBack?.setOnClickListener {
            finish()
        }
    }

   protected  fun setTitle(title : String){
       mTvTitle?.text = title
   }

    override fun initWidget() {
        super.initWidget()
        initSlidingTabLayout()
    }
    protected fun initSlidingTabLayout(){
        mSlidingTabLayout = findViewById(R.id.sliding_tabs)
        mViewPager = findViewById(R.id.view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_region,menu)
        return true
    }
    protected open fun initFragment(){

    }
    protected open fun initTitle(){

    }

    override fun finishTask() {
        initTitle()
        initFragment()
        initViewPager()
        initEvent()
    }
    protected open fun initViewPager(){
        mViewPager?.offscreenPageLimit = mTitles.size
        mViewPager?.adapter = BaseRegionTypeAdapter(supportFragmentManager)
        mSlidingTabLayout?.setViewPager(mViewPager)
    }

    protected fun setCurrentItem(pos : Int){
        mViewPager?.currentItem = pos
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 初始化事件
     */
    protected open fun initEvent(){

    }

    inner class BaseRegionTypeAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){
        override fun getCount(): Int {
            return mTitles.size
        }

        override fun getItem(position: Int): Fragment {
            mFragment.let {
                return it[position]
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mTitles[position]
        }
    }

}