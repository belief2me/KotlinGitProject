package com.btlm.kotlingitproject.module.home

import android.support.v7.widget.GridLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.RecommendAdapter
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.mvp.contract.home.RecommendContract
import com.btlm.kotlingitproject.mvp.presenter.home.RecommendPresenter
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.divider.VerticalDividerItemDecoration
import com.yoyiyi.soleil.bean.recommend.MulRecommend
import com.yoyiyi.soleil.bean.recommend.Recommend
import kotlinx.android.synthetic.main.fragment_home_recommend.*

/**
 * Created by Administrator on 2017/12/21.
 */
class RecommendFragment : BaseRefreshFragment<RecommendPresenter,MulRecommend>(),RecommendContract.View{

    private var mAdapter : RecommendAdapter? = null

    companion object {
        fun newInstance() : RecommendFragment = RecommendFragment()
    }
    override fun showRecommend(recommend: List<Recommend>) {
        recommend.forEach {
            if (it.banner_item != null && it.banner_item.isNotEmpty()) {
                mList.add(MulRecommend(itemTypez = MulRecommend.TYPE_HEADER, spanSize = MulRecommend.HEADER_SPAN_SIZE, data = it.banner_item))
            } else {
                mList.add(MulRecommend(itemTypez = MulRecommend.TYPE_ITEM, spanSize = MulRecommend.ITEM_SPAN_SIZE, recommend = it))
            }
        }
        finishTask()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_recommend
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getRecommendData()
    }

    override fun initWidget() {
        super.initWidget()
        ivRank.setOnClickListener {

        }
    }

    override fun initRecyclerView() {
        mAdapter = RecommendAdapter(mList)
        val mLayoutManager = GridLayoutManager(activity,2)
        mAdapter?.setSpanSizeLookup({
            _,i -> mList[i].spanSize
        })
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
        //添加分割条
        val build = VerticalDividerItemDecoration.Builder(activity)
                .color(AppUtils.getColor(R.color.transparent))
                .sizeResId(R.dimen.dp10)
                .showLastDivider()
                .build()
        mRecycler?.addItemDecoration(build)
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }
}