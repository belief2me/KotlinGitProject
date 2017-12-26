package com.btlm.kotlingitproject.module.search

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.search.MovieAdapter
import com.btlm.kotlingitproject.bean.search.Movie
import com.btlm.kotlingitproject.mvp.contract.search.MovieContract
import com.btlm.kotlingitproject.mvp.presenter.search.MoviePresenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 11:46
 * * 描述:影视
 */
class MovieFragment : BaseSearchFragment<MoviePresenter, Movie.DataBean.ItemsBean>(), MovieContract.View {
    private var mAdapter: MovieAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_search_movie


    override fun lazyLoadData() {
        mPresenter.getSearchMovieData()
    }

    override fun showSearchMovie(movie: Movie) {
        mList.addAll(movie.data.items)
        finishTask()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }
    
    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun initRecyclerView() {
        mAdapter = MovieAdapter(mList)
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
    }

    companion object {

        fun newsInstance(): MovieFragment {
            return MovieFragment()
        }
    }
}
