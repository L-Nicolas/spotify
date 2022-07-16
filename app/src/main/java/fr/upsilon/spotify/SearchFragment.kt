package fr.upsilon.spotify

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.upsilon.spotify.model.services.ApiConnection
import fr.upsilon.spotify.model.services.ArtistService
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var viewManager = LinearLayoutManager(this.context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewArtist)
        recyclerView.layoutManager = viewManager
        getArtists()
        return view
    }

    private fun getArtists(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val dataRes = ApiConnection.connection().create(ArtistService::class.java).getArtists().await()
                Log.i("AAAAAAAAAAAAA", dataRes.toString())
                recyclerView.adapter = MyAdapter(dataRes.artists)
            }
        }
    }
}