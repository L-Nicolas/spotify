package fr.upsilon.spotify

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.isVisible
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
    private lateinit var recyclerView2: RecyclerView

    private var viewManager = LinearLayoutManager(this.context)
    private var viewManager2 = LinearLayoutManager(this.context)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewArtist)
        recyclerView2 = view.findViewById(R.id.recyclerview_albums)
        val searchTextField = view.findViewById<EditText>(R.id.searchTextField)
        view.findViewById<ImageView>(R.id.line_a2).isVisible = false
        view.findViewById<ImageView>(R.id.line_a3).isVisible = false
        view.findViewById<TextView>(R.id.title2).isVisible = true
        view.findViewById<TextView>(R.id.title3).isVisible = false

        view.findViewById<Button>(R.id.clear_textField).setOnClickListener {
            searchTextField.text.clear()
            view.findViewById<ImageView>(R.id.line_a2).isVisible = false
            view.findViewById<ImageView>(R.id.line_a3).isVisible = false
            view.findViewById<TextView>(R.id.title2).isVisible = true
            view.findViewById<TextView>(R.id.title3).isVisible = false
            view.findViewById<RecyclerView>(R.id.recyclerViewArtist).isVisible = false
            view.findViewById<RecyclerView>(R.id.recyclerview_albums).isVisible = false


        }


        recyclerView.layoutManager = viewManager
        recyclerView2.layoutManager = viewManager2


        searchTextField.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() === KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    hideKeyboardFrom(this@SearchFragment.context!!, view)
                    getArtists(searchTextField.text.toString())
                    getAlbums(searchTextField.text.toString())

                    return true
                }
                return false
            }
        })


        return view
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getArtists(artist: String){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val dataRes = ApiConnection.connection().create(ArtistService::class.java).getArtists(artist).await()
                Log.i("AAAAAAAAAAAAA", dataRes.toString())
                if (dataRes.artists == null){
                    Toast.makeText(this@SearchFragment.context, "Cet Artiste n'existe pas", Toast.LENGTH_SHORT)
                        .show()
                }
                else {
                    view?.findViewById<ImageView>(R.id.line_a2)?.isVisible = true
                    view?.findViewById<ImageView>(R.id.line_a3)?.isVisible = true
                    view?.findViewById<TextView>(R.id.title2)?.isVisible = true
                    view?.findViewById<TextView>(R.id.title3)?.isVisible = true
                    view?.findViewById<RecyclerView>(R.id.recyclerViewArtist)?.isVisible = true
                    view?.findViewById<RecyclerView>(R.id.recyclerview_albums)?.isVisible = true
                    recyclerView.adapter = MyAdapter(dataRes.artists)

                }
            }
        }
    }

    private fun getAlbums(artist: String){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val dataRes = ApiConnection.connection().create(ArtistService::class.java).getAlbum(artist).await()
                Log.i("AAAAAAAAAAAAA", dataRes.toString())

                if (dataRes.album == null){

                }
                else {
                    recyclerView2.adapter = MyAdapterAlbum(dataRes.album)

                }
            }
        }
    }
}