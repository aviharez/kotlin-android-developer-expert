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


class NextMatchFragment : Fragment(), AnkoLogger, NMContract.View {

    lateinit var nmPresenter: NMPresenter
    lateinit var next_match_loading : ProgressBar
    lateinit var rv_next_match : RecyclerView

    private var matchLists : MutableList<Event> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next_match, container, false)
        next_match_loading = v.findViewById(R.id.next_match_loading)
        rv_next_match = v.findViewById(R.id.rv_next_match)
        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MatchRepositoryImpl(service)
        nmPresenter = NMPresenter(this, request)
        nmPresenter.getFootballNextMatch()
        return v
    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }

    override fun hideLoading() {
        next_match_loading.hide()
        rv_next_match.visibility = View.VISIBLE
    }

    override fun showLoading() {
        next_match_loading.show()
        rv_next_match.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        Log.d("Coba:", " " + matchList.size)
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        rv_next_match.layoutManager = layoutManager
        rv_next_match.adapter = RecyclerViewAdapter(matchList, activity!!.applicationContext)
    }

}
