package com.btlm.kotlingitproject.mvp.presenter.search




import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.search.Movie
import com.btlm.kotlingitproject.mvp.contract.search.MovieContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面主页presenter
 */

class MoviePresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<MovieContract.View>(), MovieContract.Presenter<MovieContract.View> {

    override fun getSearchMovieData() {
        val subscriber = retrofitHelper.getMovie()
                .compose(rxSchedulerHelper())
                .doOnSubscribe{ mView?.showLoading() }
                //   .delay(5, TimeUnit.SECONDS
                .subscribeWith(object : BaseSubscriber<Movie>(mView) {
                    override fun onSuccess(movie: Movie) {
                        mView?.showSearchMovie(movie)
                    }
                })
        addSubscribe(subscriber)
    }

}
