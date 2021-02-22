package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alimentos (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var tipoAlimento: String,
    var gxPorcion: Int,
    var gHcxPorcion: Double
){
    constructor() : this(0, "",0,0.0)
}
