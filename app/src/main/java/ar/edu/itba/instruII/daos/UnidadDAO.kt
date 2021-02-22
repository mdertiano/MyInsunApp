package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.Unidad

@Dao
interface UnidadDAO {
    @Insert
    fun saveUnidad(unidad: Unidad)

    @Query(value = "Select * from Unidad")
    fun getAllUnidad(): List<Unidad>
}