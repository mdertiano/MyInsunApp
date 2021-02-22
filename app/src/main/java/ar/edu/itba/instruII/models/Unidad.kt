package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unidad (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var nombre: String,
    var factor_de_conversion: Int
){
    constructor() : this(0, "",0)
}
