package ar.edu.itba.instruII.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
@Entity//(foreignKeys = [ForeignKey(entity = Sexo::class, parentColumns = ["id"], childColumns = ["id_sexo"]), ForeignKey(entity = Tipo_diabetes::class,parentColumns = ["id"],childColumns = ["id_diabetes"]), ForeignKey(entity = Unidad::class, parentColumns = ["id"], childColumns = ["unidad"])])
data class Paciente (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var nombre: String,// Lo completa el usuario
    var FSI: Int,
    //val id_sexo: Int,
    //val id_diabetes: Int,
    //val unidad: Int,-
    var RIC: Int // Lo completa el usuario.
// Si esta tabla no esta completa tiene que tirar error porque de esto surge el calculo a hacer. En especial de RIC.
){
    constructor() : this(0, "",0, 0)
}
