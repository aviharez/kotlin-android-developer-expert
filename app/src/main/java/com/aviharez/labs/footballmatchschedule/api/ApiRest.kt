package com.aviharez.labs.footballmatchschedule.api

import com.aviharez.labs.footballmatchschedule.model.FootballMatch
import com.aviharez.labs.footballmatchschedule.model.Teams
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRest {

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id : String) : Flowable<FootballMatch>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id : String) : Flowable<FootballMatch>

    @GET("lookupteam.php")
    fun getDetail(@Query("id") id : String) : Flowable<Teams>

}