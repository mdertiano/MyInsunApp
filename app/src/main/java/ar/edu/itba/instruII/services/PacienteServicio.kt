package ar.edu.itba.instruII.services

import android.app.Application
import android.util.Log
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.Paciente
import java.lang.Exception

// Esta es la capa intermedia entre mi view y mi base de datos.

class PacienteServicio {
    fun insertPaciente(paciente : Paciente, applicationContext : Application): Paciente? {
        try {
            var id = InstruIIDatabase.get(application = applicationContext).pacienteDao().savePaciente(paciente)
            paciente.id = id
            return paciente
        }catch (e: Exception){
            Log.d("Excepcion", "Error insertando paciente")
            return null
        }
    }

    fun getPacientes(applicationContext : Application): List<Paciente> {
        try {
            var list: List<Paciente> = emptyList()
           list = InstruIIDatabase.get(application = applicationContext).pacienteDao().getAllPaciente()
            return list
        }catch (e: Exception){
            Log.d("Excepcion", "error levantando pacientes")
            return emptyList()
        }
    }

    fun updatePacientes(applicationContext: Application, paciente: Paciente): Boolean {
        val actualizados = InstruIIDatabase.get(application = applicationContext).pacienteDao().updatePacientes(paciente)
        if(actualizados == 0){
            return true
        }
        return false
    }

    fun existenciaPaciente(applicationContext: Application): Int{
        var list = InstruIIDatabase.get(application = applicationContext).pacienteDao().getAllPaciente()
        return list.size
    }
}