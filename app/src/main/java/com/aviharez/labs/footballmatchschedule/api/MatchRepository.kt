package com.aviharez.labs.footballmatchschedule.api

import com.aviharez.labs.footballmatchschedule.model.FootballMatch
import com.aviharez.labs.footballmatchschedule.model.Teams
import io.reactivex.Flowable

interface MatchRepository {

    fun getFootballMatch(id: String) : Flowable<FootballMatch>

    fun getTeams(id: String = "0") : Flowable<Teams>

    fun getNextMatch(id: String) : Flowable<FootballMatch>

}