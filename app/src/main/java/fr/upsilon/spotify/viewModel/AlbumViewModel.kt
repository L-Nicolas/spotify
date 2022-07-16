package fr.upsilon.spotify.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.upsilon.spotify.model.repository.AlbumRepository
import fr.upsilon.spotify.model.repository.AlbumState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {

    private val _albums = MutableLiveData<AlbumState>()
    val listAlbums = _albums

    init {
        getAlbumsRank()
    }

    fun getAlbumsRank() {
        viewModelScope.launch {
            AlbumRepository.fetchAlbumsRank().collect {
                _albums.value = it
            }
        }
    }

}
