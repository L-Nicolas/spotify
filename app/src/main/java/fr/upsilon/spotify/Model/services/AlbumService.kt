package fr.upsilon.spotify.model.services

import fr.upsilon.spotify.model.entities.Album
import fr.upsilon.spotify.model.entities.Rank
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    @GET("mostloved.php")
    fun listAlbumsRank(@Query("format") format: String = "album"): Call<Rank<Album>>

}