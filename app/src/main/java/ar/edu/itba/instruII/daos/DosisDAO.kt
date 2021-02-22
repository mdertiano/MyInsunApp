package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.Dosis

@Dao
interface DosisDAO {
    @Insert
    fun saveDosis(dosis: Dosis): Long

    @Query(value = "Select * from Dosis")
    fun getAllDosis() : List<Dosis>

    @Query(value = "Delete from Dosis")
    fun deleteDosis()
}