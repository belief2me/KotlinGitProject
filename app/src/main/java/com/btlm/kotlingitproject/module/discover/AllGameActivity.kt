package com.btlm.kotlingitproject.module.discover

import android.support.v7.widget.LinearLayoutManager

import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.discover.section.GameCenterGameListSection
import com.btlm.kotlingitproject.base.BaseRefreshActivity
import com.btlm.kotlingitproject.bean.discover.GameCenter
import com.btlm.kotlingitproject.mvp.contract.discover.GameCenterContract
import com.btlm.kotlingitproject.mvp.presenter.discover.GameCenterPresenter
import com.btlm.kotlingitproject.widget.section.SectionedRVAdapter



/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 22:28
 * * 描述:所有游戏
 */
class AllGameActivity : BaseRefreshActivity<GameCenterPresenter, GameCenter.GameListBean>(), GameCenterContract.View {
    private var mSectionedAdapter: SectionedRVAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_game_center


    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "全部游戏"
    }

    override fun loadData() {
        mPresenter.getGameCenterData()
    }


    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun showGameCenter(gameCenter: GameCenter) {
        mList?.addAll(gameCenter.game_list)
        finishTask()
    }

    override fun finishTask() {
        mList?.let {
            mSectionedAdapter?.addSection(GameCenterGameListSection(false, it))
        }
        mSectionedAdapter?.notifyDataSetChanged()
    }
}
