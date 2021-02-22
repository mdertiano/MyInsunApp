package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tipo_diabetes (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var tipo: String
){
    constructor() : this(0, "")
}
