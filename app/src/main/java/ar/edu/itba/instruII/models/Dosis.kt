package ar.edu.itba.instruII.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ar.edu.itba.instruII.daos.Conversores
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(entity = Paciente::class, parentColumns = ["id"], childColumns = ["id_paciente"]), ForeignKey(entity = MotivoDosis::class, parentColumns = ["id"], childColumns = ["id_motivo"])])
data class Dosis(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var id_paciente: Long,// esto viene de la tabla paciente, no lo pone el usuario.
    var volumen: Double, // Esto viene del CALCULO.
    @TypeConverters(Conversores::class) var fecha: LocalDateTime?, //Se saca del código mismo, es la fecha y HACE LA INYECCIÓN DE INSULINA.
    var hora: String,
    var id_motivo: Long //Este id viene de la tabla MotivoDosis, y va a ser manejado INTERNAMENTE, el usuario nunca toca esto.
){
    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(0, 0,0.0, LocalDateTime.MIN,"", 0)
}

