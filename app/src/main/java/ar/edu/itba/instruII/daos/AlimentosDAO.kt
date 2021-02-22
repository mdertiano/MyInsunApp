package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.Alimentos

@Dao
interface AlimentosDAO {
    @Insert
    fun saveAlimento(alimentos: Alimentos): Long

    @Query(value = "Delete from Alimentos")
    fun deleteAlimentos()

    @Query(value = "Select * from Alimentos where tipoAlimento = :alimento")
    fun getAlimento(alimento: String): Alimentos //Quiero que me devuelva un alimento.
}