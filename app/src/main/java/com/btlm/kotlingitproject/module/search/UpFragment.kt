package com.btlm.kotlingitproject.module.search

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.search.UpAdapter
import com.btlm.kotlingitproject.bean.search.Up
import com.btlm.kotlingitproject.mvp.contract.search.UpContract
import com.btlm.kotlingitproject.mvp.presenter.search.UpPresenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 11:46
 * * 描述:up
 */
class UpFragment : BaseSearchFragment<UpPresenter, Up.DataBean.ItemsBean>(), UpContract.View {
    private var mAdapter: UpAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_search_up


    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getSearchUpData()
    }

    override fun initRecyclerView() {
        mAdapter = UpAdapter(mList)
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
                false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
    }

    override fun showSearchUp(up: Up) {
        mList.addAll(up.data.items)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newsInstance(): UpFragment {
            return UpFragment()
        }
    }
}
