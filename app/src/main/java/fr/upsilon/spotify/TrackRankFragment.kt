package fr.upsilon.spotify

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import fr.upsilon.spotify.model.entities.Track
import fr.upsilon.spotify.model.repository.TrackStateError
import fr.upsilon.spotify.model.repository.TrackStateLoading
import fr.upsilon.spotify.model.repository.TrackStateSuccess
import fr.upsilon.spotify.viewModel.TrackViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrackRankFragment : Fragment() {

    val viewModel: TrackViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shimmer_layout = view.findViewById<ShimmerFrameLayout>(R.id.shimmer_layout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        shimmer_layout.visibility = View.VISIBLE
        viewModel.listTrack.observe(viewLifecycleOwner) {
            when (it) {
                is TrackStateError -> {
                    shimmer_layout.visibility = View.GONE
                }
                TrackStateLoading -> {
                    shimmer_layout.visibility = View.VISIBLE
                }
                is TrackStateSuccess -> {
                    shimmer_layout.visibility = View.GONE
                    recyclerView.adapter = ListAdapterTrack(it.tracks as MutableList<Track>)
                    recyclerView.layoutManager = GridLayoutManager(context, 1)
                }
            }
        }
    }

}

class ListAdapterTrack(val tracks: MutableList<Track>) : RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rank, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setItem(tracks[position], (position + 1))
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

}


class TrackViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val trackRank = v.findViewById<TextView>(R.id.rank)
    private val trackThumb = v.findViewById<ImageView>(R.id.thumb)
    private val trackTitle = v.findViewById<TextView>(R.id.title)
    private val trackArtist = v.findViewById<TextView>(R.id.artist)

    fun setItem(item: Track, rank: Int) {
        trackRank.text = rank.toString()
        trackTitle.text = item.title
        trackArtist.text = item.artist
        Picasso.get().load(item.thumb).into(trackThumb)
    }
}