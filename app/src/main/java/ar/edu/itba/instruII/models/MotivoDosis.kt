package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MotivoDosis (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var Tipo: String
){
    constructor(): this(0,"")
}
