package com.aviharez.labs.footballmatchschedule.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.aviharez.labs.footballmatchschedule.*
import com.aviharez.labs.footballmatchschedule.adapter.RecyclerViewAdapter
import com.aviharez.labs.footballmatchschedule.api.ApiRest
import com.aviharez.labs.footballmatchschedule.api.ApiService
import com.aviharez.labs.footballmatchschedule.api.MatchRepositoryImpl
import com.aviharez.labs.footballmatchschedule.model.Event
import com.aviharez.labs.footballmatchschedule.util.hide
import com.aviharez.labs.footballmatchschedule.util.show
import org.jetbrains.anko.AnkoLogger


class LastMatchFragment : Fragment(), AnkoLogger, LMContract.View {

    lateinit var lmPresenter: LMPresenter

    lateinit var last_match_loading : ProgressBar

    lateinit var rv_last_match : RecyclerView

    private var matchLists : MutableList<Event> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_last_match, container, false)
        last_match_loading = v.findViewById(R.id.last_match_loading)
        rv_last_match = v.findViewById(R.id.rv_last_match)
        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MatchRepositoryImpl(service)
        lmPresenter = LMPresenter(this, request)
        lmPresenter.getFootballMatchData()
        return v
    }

    companion object {
        fun newInstance(): LastMatchFragment = LastMatchFragment()
    }

    override fun hideLoading() {
        last_match_loading.hide()
        rv_last_match.visibility = View.VISIBLE
    }

    override fun showLoading() {
        last_match_loading.show()
        rv_last_match.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        Log.d("Coba:", " "+matchList.size)
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        rv_last_match.layoutManager = layoutManager
        rv_last_match.adapter = RecyclerViewAdapter(matchList, activity!!.applicationContext)
    }

}
