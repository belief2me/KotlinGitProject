package com.btlm.kotlingitproject.module.app.video

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.app.video.CommentAdapter
import com.btlm.kotlingitproject.base.BaseInjectFragment
import com.btlm.kotlingitproject.bean.app.video.MulComment
import com.btlm.kotlingitproject.mvp.contract.app.video.CommentContract
import com.btlm.kotlingitproject.mvp.presenter.app.video.CommentPresenter
import kotlinx.android.synthetic.main.fragment_comment.*

/**
 * Created by Administrator on 2017/12/21.
 */
class CommentFragment : BaseInjectFragment<CommentPresenter>(),CommentContract.View{
    private var mAdapter : CommentAdapter? = null
    private val mList = mutableListOf<MulComment>()

    companion object {
        fun newInstance() : CommentFragment{
            return CommentFragment()
        }
    }
    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        mPresenter.getCommentData()
    }

    override fun initWidget() {
        initRecyclerView()
    }

    override fun initRecyclerView() {
        mAdapter = CommentAdapter(mList)
        recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = mAdapter

    }
    override fun showComment(mulComments: List<MulComment>) {
        mList.addAll(mulComments)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_comment
    }
}