package fr.upsilon.spotify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.upsilon.spotify.model.services.AlbumService
import fr.upsilon.spotify.model.services.ApiConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val albumImage = findViewById<ImageView>(R.id.albumImage)
        val backroundImage = findViewById<ImageView>(R.id.albumImageBackground)
        val artistName = findViewById<TextView>(R.id.artistName)
        val name = findViewById<TextView>(R.id.name)
        val note = findViewById<TextView>(R.id.note)
        val votes = findViewById<TextView>(R.id.votes)
        val description = findViewById<TextView>(R.id.description)
        val info = findViewById<TextView>(R.id.info)
        val list = findViewById<RecyclerView>(R.id.list)

        val backLogo = findViewById<ImageView>(R.id.backLogo)
        backLogo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val favorisLogo = findViewById<ImageView>(R.id.favorisLogo)
        favorisLogo.setOnClickListener {
            favorisLogo.setImageResource(R.drawable.ic_like_on)
        }

        var annonceArray: MutableList<Title> = ArrayList()
        val album_id = intent.getStringExtra("id")

        lifecycleScope.launch{
            try {
                Log.i("data", album_id.toString())
                val dataRes = ApiConnection.connection().create(AlbumService::class.java).getAlbumByIDDataAsync(album_id.toString()).await()
                Log.i("data", dataRes.toString())
                dataRes.content.forEach {
                    artistName.text = it.strArtist
                    name.text = it.strAlbum
                    Picasso.get().load(it.strAlbumThumb).into(albumImage);
                    Picasso.get().load(it.strAlbumThumb).into(backroundImage);
                    note.text = it.intScore
                    if(it.intScoreVotes == "null"){
                        votes.text = "pas de votes"
                    }else{
                        votes.text = it.intScoreVotes + " votes"
                    }
                    description.text = it.strDescriptionEN
                }

            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                }
            }
            try {
                val dataRes2 = ApiConnection.connection().create(AlbumService::class.java).getTrackByAlbumIdDataAsync(album_id.toString()).await()
                withContext(Dispatchers.Main) {
                    dataRes2.content.forEach {
                        annonceArray.add(
                            Title(it.idTrack, it.strTrack)
                        )
                    }
                    list.layoutManager = LinearLayoutManager(this@AlbumActivity)
                    list.adapter = TitleAdapter(
                        titles = annonceArray
                    )
                    info.text = annonceArray.count().toString() + " chansons"
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }
}


data class Title(
    val id: String? = null,
    val title: String? = null
) : java.io.Serializable {
}
class TitleAdapter(private val titles: List<Title>) :
    RecyclerView.Adapter<TitleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        return TitleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.titles_cell, parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.updateItem(
            position = position + 1,
            task = titles[position]
        )
    }
}

class TitleViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val titleTextView: TextView = v.findViewById<TextView>(R.id.titreCell)
    val indexTextView: TextView = v.findViewById<TextView>(R.id.indexCell)
    var titleID: String = ""

    init {
        titleTextView.setOnClickListener {
            val intent = Intent(v.context, MainActivity::class.java)
            GlobalScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    intent.putExtra("idTitle", titleID);
                    v.context.startActivity(intent)
                }
            }
        }
    }

    fun updateItem(position: Int, task: Title){
        titleTextView.text = task.title.toString()
        indexTextView.text = position.toString()
    }

}