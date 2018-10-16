package com.aviharez.labs.footballmatchschedule.main

import com.aviharez.labs.footballmatchschedule.api.MatchRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val view: DetailContract.View, val matchRepositoryImpl: MatchRepositoryImpl) : DetailContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTeamsBadgeHome(id: String) {
        compositeDisposable.add(matchRepositoryImpl.getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view.displayTeamBadgeHome(it.teams[0])
                })
    }

    override fun getTeamsBadgeAway(id: String) {
        compositeDisposable.add(matchRepositoryImpl.getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view.displayTeamBadgeAway(it.teams[0])
                })
    }

}