package fr.upsilon.spotify.model.services

import fr.upsilon.spotify.Model.entities.ArtistObject
import fr.upsilon.spotify.Model.entities.Artists
import fr.upsilon.spotify.model.entities.Album
import fr.upsilon.spotify.model.entities.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistService {

    @GET("/artist.php")
    fun listAlbumsRank(@Query("i") id: Int): Call<Artist>

    @GET("/artist.php")
    fun searchArtist(@Query("s") id: String): Call<Artists>


    @GET("search.php/?s=coldplay")
    fun getArtists(): Call<ArtistObject>

}