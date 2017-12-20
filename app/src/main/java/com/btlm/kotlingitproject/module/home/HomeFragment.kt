package com.btlm.kotlingitproject.module.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.MainAdapter
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.rx.RxBus
import kotlinx.android.synthetic.main.layout_main_toolbar.*

/**
 * Created by Administrator on 2017/12/19.
 */
class HomeFragment : BaseHomeFragment(){

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun initWidget() {
        super.initWidget()
        initViewPager()
        llNavigation.setOnClickListener {
            val event = Event.StartNavigationEvent()
            event.start = true
            RxBus.post(event)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
//            R.id.menu_game ->
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initViewPager(){
        val adapter = MainAdapter(childFragmentManager)
        viewPager.offscreenPageLimit = 5
        viewPager.adapter = adapter
        stlTabs.setViewPager(viewPager)
        viewPager.currentItem = 0
    }

}