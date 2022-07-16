package fr.upsilon.spotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.upsilon.spotify.Model.entities.ArtistObject
import fr.upsilon.spotify.Model.entities.Artists


class MyAdapter (
    val ArtistsList: ArtistObject
    ): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var ArtistTitle: TextView = itemView.findViewById(R.id.artistTitle)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_search, parent, false)
        Log.d("OOOOOOO", "yes $itemView ")
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ArtistTitles = ArtistsList.artists[position].strArtist

        Log.d("OOOOOOO", "yes $ArtistTitles ")
        holder.ArtistTitle.text = ArtistTitles

    }

}