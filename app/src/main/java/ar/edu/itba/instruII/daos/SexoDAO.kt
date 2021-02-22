package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.Sexo

@Dao
interface SexoDAO {
    @Insert
    fun saveSexo(sexo: Sexo)

    @Query(value = "Select * from Sexo")
    fun getAllSexo(): List<Sexo>

    @Query(value = "Delete from Sexo")
    fun deleteSexo()
}