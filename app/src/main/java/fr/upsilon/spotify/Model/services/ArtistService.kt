package fr.upsilon.spotify.model.services

import fr.upsilon.spotify.model.entities.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {

    @GET("/artist.php")
    fun listAlbumsRank(@Query("i") id: Int): Call<Artist>

}