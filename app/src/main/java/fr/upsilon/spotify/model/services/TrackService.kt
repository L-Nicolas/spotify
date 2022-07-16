package fr.upsilon.spotify.model.services

import fr.upsilon.spotify.model.entities.Rank
import fr.upsilon.spotify.model.entities.Track
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackService {

    @GET("mostloved.php")
    fun listTracksRank(@Query("format") format: String = "track"): Call<Rank<Track>>

}