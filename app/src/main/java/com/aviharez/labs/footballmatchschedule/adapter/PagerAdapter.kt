package com.aviharez.labs.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.aviharez.labs.footballmatchschedule.main.LastMatchFragment
import com.aviharez.labs.footballmatchschedule.main.NextMatchFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment? = when (position) {

        0 -> LastMatchFragment.newInstance()
        1 -> NextMatchFragment.newInstance()
        else -> null

    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Last Match"
        1 -> "Next Match"
        else -> ""
    }

    override fun getCount(): Int = 2
}