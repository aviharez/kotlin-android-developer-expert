package com.aviharez.labs.footballmatchschedule.main

import com.aviharez.labs.footballmatchschedule.api.MatchRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NMPresenter(val view : NMContract.View, val matchRepositoryImpl: MatchRepositoryImpl) : NMContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFootballNextMatch() {
        view.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getNextMatch("4328")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view.displayFootballMatch(it.events)
                    view.hideLoading()
                })
    }

}