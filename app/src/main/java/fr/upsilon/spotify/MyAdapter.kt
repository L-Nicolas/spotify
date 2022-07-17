package fr.upsilon.spotify

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.upsilon.spotify.model.entities.Artists

class MyAdapter (
    val ArtistsList: List<Artists>
    ): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var ArtistTitle: TextView = itemView.findViewById(R.id.artistTitle)
        var ArtistImage: ImageView = itemView.findViewById(R.id.imageView2)
        var ArtistImage2 = ArtistImage.setClipToOutline(true)

    }

    override fun getItemCount(): Int = ArtistsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_search, parent, false)
        Log.d("OOOOOOO", "yes $itemView ")
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ArtistTitles = ArtistsList[position].strArtist
        val ArtistImage = ArtistsList[position].strArtistThumb

        Log.d("OOOOOOO", "yes $ArtistTitles ")
        holder.ArtistTitle.text = ArtistTitles
        Picasso.get().load(ArtistImage).into(holder.ArtistImage);


    }

}