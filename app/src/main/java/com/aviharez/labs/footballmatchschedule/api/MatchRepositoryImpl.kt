package com.aviharez.labs.footballmatchschedule.api

import com.aviharez.labs.footballmatchschedule.model.FootballMatch
import com.aviharez.labs.footballmatchschedule.model.Teams
import io.reactivex.Flowable

class MatchRepositoryImpl(private val apiRest: ApiRest) : MatchRepository {

    override fun getNextMatch(id: String): Flowable<FootballMatch> = apiRest.getNextMatch(id)

    override fun getFootballMatch(id: String): Flowable<FootballMatch> = apiRest.getLastMatch(id)

    override fun getTeams(id: String): Flowable<Teams> = apiRest.getDetail(id)

}