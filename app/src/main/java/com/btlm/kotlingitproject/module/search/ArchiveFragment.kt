
package com.btlm.kotlingitproject.module.search

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.search.MulArchiveAdapter
import com.btlm.kotlingitproject.bean.search.MulSearchArchive
import com.btlm.kotlingitproject.mvp.contract.search.ArchiveContract
import com.btlm.kotlingitproject.mvp.presenter.search.ArchivePresenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 11:46
 * * 描述:综合搜索
 */
class ArchiveFragment : BaseSearchFragment<ArchivePresenter, MulSearchArchive>(), ArchiveContract.View {


    private var mAdapter: MulArchiveAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_search_archive



    override fun initInject() {
       fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }


    override fun lazyLoadData() {
        mPresenter.getSearchArchiveData()
    }

    override fun showSearchArchive(mulSearchArchiveList: List<MulSearchArchive>) {
        mList.addAll(mulSearchArchiveList)
        finishTask()
    }


    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun initRecyclerView() {
        mAdapter = MulArchiveAdapter(mList)
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
    }

    companion object {
        fun newsInstance(): ArchiveFragment {
            val fragment = ArchiveFragment()
            return fragment
        }
    }


}
