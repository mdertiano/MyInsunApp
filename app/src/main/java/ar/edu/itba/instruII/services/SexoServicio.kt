package ar.edu.itba.instruII.services

import android.app.Application
import android.util.Log
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.Paciente
import ar.edu.itba.instruII.models.Sexo
import java.lang.Exception

class SexoServicio {
    fun insertSexo(sexo: Sexo, applicationContext : Application): Sexo? {
        try {
            InstruIIDatabase.get(application = applicationContext).sexoDao().saveSexo(sexo)
            return sexo
        }catch (e: Exception){
            Log.d("Excepcion", "Error insertando sexo")
            return null
        }
    }

    fun getSexos(applicationContext : Application): List<Sexo> {
        try {
            var list: List<Sexo> = emptyList()
            list = InstruIIDatabase.get(application = applicationContext).sexoDao().getAllSexo()
            for(element: Sexo in list){
                Log.d("Nuevo Sexo", "Sexo: ${element.sexo} id: ${element.id}")
            }
            Log.d("Cantidad Sexos", "Sexo: ${list.size}")
            return list
        }catch (e: Exception){
            Log.d("Excepcion", "error levantando sexo")
            return emptyList()
        }
    }

    fun deleteTabla(applicationContext: Application){
        try {
            InstruIIDatabase.get(application = applicationContext).sexoDao().deleteSexo()
        }catch (e: Exception){
            Log.d("Excepcion", "Error borrando tabla Sexo")
        }
    }
}