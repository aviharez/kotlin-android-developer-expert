package com.aviharez.labs.footballmatchschedule.main

import com.aviharez.labs.footballmatchschedule.model.Event

interface NMContract {

    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList: List<Event>)
    }

    interface Presenter{
        fun getFootballNextMatch()
    }

}