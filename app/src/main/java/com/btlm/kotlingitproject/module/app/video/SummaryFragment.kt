package com.btlm.kotlingitproject.module.app.video

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.base.BaseInjectFragment
import com.btlm.kotlingitproject.mvp.contract.app.video.SummaryContract
import com.yoyiyi.soleil.adapter.app.video.SummaryAdapter
import com.yoyiyi.soleil.bean.app.video.MulSummary
import com.yoyiyi.soleil.mvp.presenter.app.video.SummaryPresenter
import kotlinx.android.synthetic.main.common_recycler.*

/**
 * Created by Administrator on 2017/12/21.
 */
class SummaryFragment : BaseInjectFragment<SummaryPresenter>(),SummaryContract.View{

    private val mList = mutableListOf<MulSummary>()
    private var mAdapter: SummaryAdapter? = null

    companion object {

        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }

    override fun initWidget() {
        initRecyclerView()
    }

    override fun initRecyclerView() {
        mAdapter = SummaryAdapter(mList)
        recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = mAdapter
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }
    override fun showSummary(mulSummaries: List<MulSummary>) {
        mList.addAll(mulSummaries)
        finishTask()
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_summary
    }

    override fun loadData() {
        mPresenter.getSummaryData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }
}