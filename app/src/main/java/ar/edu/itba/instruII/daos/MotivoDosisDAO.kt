package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.MotivoDosis

@Dao
interface MotivoDosisDAO {
    @Insert
    fun saveMotivoDosis(motivoDosis: MotivoDosis): Long

    @Query(value = "Delete from MotivoDosis")
    fun deleteMotivoDosis()

    @Query(value = "Select * from MotivoDosis where Tipo = :motivo")
    fun getMotivo(motivo: String) : MotivoDosis

}