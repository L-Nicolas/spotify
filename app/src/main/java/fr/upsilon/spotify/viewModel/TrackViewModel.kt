package fr.upsilon.spotify.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.upsilon.spotify.model.repository.TrackRepository
import fr.upsilon.spotify.model.repository.TrackState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TrackViewModel : ViewModel() {

    private val _tracks = MutableLiveData<TrackState>()
    val listTrack = _tracks

    init {
        getAlbumsRank()
    }

    fun getAlbumsRank() {
        viewModelScope.launch {
            TrackRepository.fetchTracksRank().collect {
                _tracks.value = it
            }
        }
    }

}