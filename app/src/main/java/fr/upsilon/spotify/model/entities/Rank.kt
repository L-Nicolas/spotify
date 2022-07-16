package fr.upsilon.spotify.model.entities

import com.google.gson.annotations.SerializedName

class Rank<T>(listLoved: List<T>) {

    @SerializedName("loved")
    var listLoved: List<T>

    init {
        this.listLoved = listLoved
    }

}