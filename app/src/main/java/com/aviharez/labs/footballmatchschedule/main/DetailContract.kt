package com.aviharez.labs.footballmatchschedule.main

import com.aviharez.labs.footballmatchschedule.model.Team

interface DetailContract {

    interface View {
        fun displayTeamBadgeHome(team: Team)
        fun displayTeamBadgeAway(team: Team)
    }

    interface Presenter {
        fun getTeamsBadgeAway(id: String)
        fun getTeamsBadgeHome(id: String)
    }

}