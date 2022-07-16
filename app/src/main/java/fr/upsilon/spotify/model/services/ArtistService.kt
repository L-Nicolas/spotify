package fr.upsilon.spotify.model.services

import com.google.gson.JsonElement
import fr.upsilon.spotify.model.entities.Artists
import fr.upsilon.spotify.model.entities.Artist
import fr.upsilon.spotify.model.entities.ArtistObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {

    @GET("/artist.php")
    fun listAlbumsRank(@Query("i") id: Int): Deferred<Artist>

    @GET("/artist.php")
    fun searchArtist(@Query("s") id: String): Deferred<Artists>


    @GET("search.php/?s=coldplay")
    fun getArtists(): Deferred<ArtistObject>

}