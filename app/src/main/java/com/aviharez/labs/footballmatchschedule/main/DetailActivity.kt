package com.aviharez.labs.footballmatchschedule.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aviharez.labs.footballmatchschedule.model.Event
import com.aviharez.labs.footballmatchschedule.R
import com.aviharez.labs.footballmatchschedule.model.Team
import com.aviharez.labs.footballmatchschedule.api.ApiRest
import com.aviharez.labs.footballmatchschedule.api.ApiService
import com.aviharez.labs.footballmatchschedule.api.MatchRepositoryImpl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    lateinit var presenter: DetailPresenter

    override fun displayTeamBadgeHome(team: Team) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(iv_logo_home)
    }

    override fun displayTeamBadgeAway(team: Team) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(iv_logo_away)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MatchRepositoryImpl(service)

        presenter = DetailPresenter(this, request)

        val event = intent.getParcelableExtra<Event>("match")
        presenter.getTeamsBadgeHome(event.idHomeTeam)
        presenter.getTeamsBadgeAway(event.idAwayTeam)
        initData(event)
        supportActionBar?.title = event.strEvent
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    fun initData(event: Event) {
        if (event.intHomeScore == null) {
            tv_tanggal.setTextColor(applicationContext.getColor(android.R.color.holo_red_dark))
        }

        tv_tanggal.text = event.dateEvent
        tv_nama_home.text = event.strHomeTeam
        tv_skor_home.text = event.intHomeScore
        tv_scorer_home.text = event.strHomeGoalDetails
        tv_gk_home.text = event.strHomeLineupGoalkeeper
        tv_def_home.text = event.strHomeLineupDefense
        tv_mid_home.text = event.strHomeLineupMidfield
        tv_fwd_home.text = event.strHomeLineupForward
        tv_sub_home.text = event.strHomeLineupSubstitutes

        tv_nama_away.text = event.strAwayTeam
        tv_skor_away.text = event.intAwayScore
        tv_scorer_away.text = event.strAwayGoalDetails
        tv_gk_away.text = event.strAwayLineupGoalkeeper
        tv_def_away.text = event.strAwayLineupDefense
        tv_mid_away.text = event.strAwayLineupMidfield
        tv_fwd_away.text = event.strAwayLineupForward
        tv_sub_away.text = event.strAwayLineupSubstitutes

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
