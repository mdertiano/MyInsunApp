package ar.edu.itba.instruII.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.itba.instruII.models.Tipo_diabetes

@Dao
interface Tipo_diabetesDAO {
    @Insert
    fun saveTipoDiabetes(tipoDiabetes: Tipo_diabetes)

    @Query(value = "Select * from Tipo_diabetes")
    fun getAllTipoDiabetes(): List<Tipo_diabetes>
}