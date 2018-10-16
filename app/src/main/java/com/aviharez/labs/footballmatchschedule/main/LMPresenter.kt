package com.aviharez.labs.footballmatchschedule.main

import com.aviharez.labs.footballmatchschedule.api.MatchRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LMPresenter(val view: LMContract.View, val matchRepositoryImpl: MatchRepositoryImpl) : LMContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData() {
        view.showLoading()
        compositeDisposable.add(matchRepositoryImpl.getFootballMatch("4328")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    view.displayFootballMatch(it.events)
                    view.hideLoading()
                })
    }

}