package fr.upsilon.spotify

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.upsilon.spotify.model.entities.Albums
import fr.upsilon.spotify.model.entities.Artists

class MyAdapterAlbum (
    val AlbumList: List<Albums>
): RecyclerView.Adapter<MyAdapterAlbum.ViewHolder>() {

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var Title: TextView = itemView.findViewById(R.id.albumTitle)
        var ArtistTitle: TextView = itemView.findViewById(R.id.albumArtist)
        var AlbumImage: ImageView = itemView.findViewById(R.id.thumb2)

    }

    override fun getItemCount(): Int = AlbumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album_search, parent, false)
        Log.d("OOOOOOO", "yes $itemView ")
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val AlbumTitles = AlbumList[position].strArtist
        val AlbumImage = AlbumList[position].strAlbumThumb
        val ArtistTitles = AlbumList[position].strAlbum


        Log.d("OOOOOOO", "yes $ArtistTitles ")
        holder.Title.text = AlbumTitles
        holder.ArtistTitle.text = ArtistTitles
        Picasso.get().load(AlbumImage).into(holder.AlbumImage);


    }

}