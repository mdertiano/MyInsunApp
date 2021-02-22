package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sexo (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var sexo: String
) {
    constructor() : this(0, "")
}
