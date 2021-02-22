package ar.edu.itba.instruII.daos

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.itba.instruII.models.Paciente

@Dao
interface PacienteDAO {
    @Insert
    fun savePaciente(paciente: Paciente): Long

    @Query(value = "Select * from Paciente")
    fun getAllPaciente(): List<Paciente>

    @Update
    fun updatePacientes(paciente: Paciente): Int

}