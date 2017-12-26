package com.btlm.kotlingitproject.module.bangumi

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.bangumi.BangumiDetailAdapter
import com.btlm.kotlingitproject.base.BaseRefreshActivity
import com.btlm.kotlingitproject.bean.bangumi.MulBangumiDetail
import com.btlm.kotlingitproject.mvp.contract.bangumi.BangumiDetailContract
import com.btlm.kotlingitproject.mvp.presenter.bangumi.BangumiDetailPresenter
import com.btlm.kotlingitproject.utils.LogUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_bangumi_detail.*

class BangumiDetailActivity : BaseRefreshActivity<BangumiDetailPresenter, MulBangumiDetail>(),
BangumiDetailContract.View{

    private var mDistanceY: Int = 0
    private var mAdapter: BangumiDetailAdapter? = null

    override fun showMulBangumiDetail(mulBangumiDetails: List<MulBangumiDetail>, title: String) {
        mList?.clear()
        mList?.addAll(mulBangumiDetails)
        tv_title?.text = title
        finishTask()
    }

    override fun initStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,0)
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_bangumi_detail
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        mPresenter.getBangumiDetailData()
    }

    override fun initToolbar() {
        super.initToolbar()
        // 设置标题
        mToolbar?.title = ""
    }

    override fun initRecyclerView() {
        mList?.let {
            mAdapter = BangumiDetailAdapter(it)
        }
        mRecycler?.setHasFixedSize(true)
        mRecycler?.isNestedScrollingEnabled = false
        val mLayoutManager = LinearLayoutManager(mContext)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
        initHead()
        mAdapter?.notifyDataSetChanged()
    }

    override fun initWidget() {
        super.initWidget()
        mRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //滑动的距离
                mDistanceY += dy
                //toolbar的高度
                val toolbarHeight = mToolbar?.bottom
                toolbarHeight?.let {
                    LogUtils.d("mDistanceY :" + (mDistanceY>= toolbarHeight && toolbarHeight != 0))

                    if (mDistanceY <= it && toolbarHeight != 0) {
                        val scale = mDistanceY / toolbarHeight
                        val alpha = scale * 255
                        mToolbar?.setBackgroundColor(Color.argb(alpha, 251, 114, 153))
                    }
                } ?: mToolbar?.setBackgroundResource(R.color.colorPrimary)

//                 //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
//                  if (mDistanceY <= toolbarHeight!!) {
//                      val scale = mDistanceY / toolbarHeight
//                      val alpha = scale * 255
//                      mToolbar?.setBackgroundColor(Color.argb(alpha.toInt(), 251, 114, 153))
//                  } else {
//                      //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
//                      //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
//                      //将标题栏的颜色设置为完全不透明状态
//                      mToolbar?.setBackgroundResource(R.color.colorPrimary)
//                  }

            }
        })
    }

    private fun initHead() {
        mList?.add(MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_HEAD, isPrepare = true))
    }
    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

}
