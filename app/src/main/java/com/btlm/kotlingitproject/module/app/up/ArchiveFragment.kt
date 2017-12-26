package com.btlm.kotlingitproject.module.app.up


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.app.up.ArchiveAdapter
import com.btlm.kotlingitproject.base.BaseInjectFragment
import com.btlm.kotlingitproject.bean.user.MulUpDetail
import com.btlm.kotlingitproject.constant.Constants
import com.btlm.kotlingitproject.mvp.contract.app.up.ArchiveContract
import com.btlm.kotlingitproject.mvp.presenter.app.up.ArchivePresenter
import kotlinx.android.synthetic.main.activity_bangumi_detail.*
import kotlinx.android.synthetic.main.fragment_up_archive.*


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/16 15:09
 * * 描述:主页
 */

class ArchiveFragment : BaseInjectFragment<ArchivePresenter>(), ArchiveContract.View {



    private var mSetting: Int = 0
    private val mList = mutableListOf<MulUpDetail>()
    private var mAdapter: ArchiveAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_up_archive
    }

    override fun initVariables() {
        arguments?.let {
            mSetting = it.getInt(Constants.EXTRA_SETTING)
        }
        if (mSetting == 0) {
            visible(iv_empty)
        } else {
            gone(iv_empty)
        }
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        if (mSetting == 1)
            mPresenter.getArchiveData()
    }

    override fun initRecyclerView() {
        mAdapter = ArchiveAdapter(mList)
        val mLayoutManager = GridLayoutManager(activity, 2)
        mAdapter?.setSpanSizeLookup{ _, position -> mList[position].spanSize }
        recycler?.layoutManager = mLayoutManager
        recycler?.adapter = mAdapter
    }


    override fun initWidget() {
        initRecyclerView()
    }

    override fun finishTask() {
        gone(iv_empty, cl_error)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showError(msg: String) {
        visible(cl_error)
        gone(iv_empty)
    }

    override fun showArchive(mulUpDetailList: List<MulUpDetail>) {
        mList.addAll(mulUpDetailList)
        finishTask()
    }

    companion object {

        fun newInstance(setting: Int): ArchiveFragment {
            val fragment = ArchiveFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.EXTRA_SETTING, setting)
            fragment.arguments = bundle
            return fragment
        }
    }
}
