package com.aviharez.labs.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aviharez.labs.footballmatchschedule.R
import com.aviharez.labs.footballmatchschedule.main.DetailActivity
import com.aviharez.labs.footballmatchschedule.model.Event
import kotlinx.android.synthetic.main.isi_list.view.*
import org.jetbrains.anko.startActivity

class RecyclerViewAdapter(val eventList: List<Event>, val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.RVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return RVViewHolder(LayoutInflater.from(context).inflate(R.layout.isi_list, parent, false))
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event) {
            if (event.intHomeScore == null) {
                itemView.tv_tanggal.setTextColor(context.getColor(android.R.color.holo_red_light))
            }
            itemView.tv_tanggal.text = event.dateEvent
            itemView.tv_home_club.text = event.strHomeTeam
            itemView.tv_skor_home.text = event.intHomeScore
            itemView.tv_away_club.text = event.strAwayTeam
            itemView.tv_skor_away.text = event.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>("match" to event)
            }
        }

    }


}