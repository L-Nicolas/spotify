package fr.upsilon.spotify.model.services

import com.google.gson.annotations.SerializedName
import fr.upsilon.spotify.model.entities.Album
import fr.upsilon.spotify.model.entities.Rank
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


data class AlbumData(
    @SerializedName("album")
    val content: List<AlbumDataContent>,
)

data class AlbumDataNameYears(
    @SerializedName("album")
    val content: List<AlbumDataResume>,
)

data class AlbumDataContent(
    val idArtist: String,
    val strAlbum: String,
    val strGenre: String,
    val strArtist:String,
    val intYearReleased: String,
    val strAlbumThumb: String,
    val intScore: String,
    val intScoreVotes: String,
    val strDescriptionEN: String
)

data class AlbumDataResume(
    val strAlbum: String,
    val intYearReleased: String,
)

data class TrackSongData(
    @SerializedName("track")
    val content: List<TrackDataContent>,
)

data class TrackDataContent(
    val idTrack: String,
    val strTrack: String,
    val intTrackNumber: String,
)


interface AlbumService {

    // album les plus aimés
    @GET("mostloved.php")
    fun listAlbumsRank(@Query("format") format: String = "album"): Call<Rank<Album>>

    // get album par artiste
    @GET("album.php") //2115888({albumid}
    fun getAlbumByIDDataAsync(@Query("m")value:String): Deferred<AlbumData> // albumid:String

    // get pour l'écran classement onglet Titres
    @GET("track.php") //2115888{albumID}
    fun getTrackByAlbumIdDataAsync(@Query("m")value:String): Deferred<TrackSongData>

    @GET("discography.php") // coldplay({artistName}
    fun getAlbumNameYearsByArtisteDataAsync(@Query("s")value:String): Deferred<AlbumDataNameYears>

    @GET("searchalbum.php") // daft_punk{artistName}
    fun getAlbumByAtisteName(@Query("s")value:String): Deferred<AlbumData>
}