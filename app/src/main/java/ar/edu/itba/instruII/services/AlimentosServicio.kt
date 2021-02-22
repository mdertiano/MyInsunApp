package ar.edu.itba.instruII.services

import android.app.Application
import android.util.Log
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.Alimentos

class AlimentosServicio {
    fun insertAlimento(alimentos: Alimentos,applicationContext : Application): Alimentos? {
        try{
            var id = InstruIIDatabase.get(application = applicationContext).alimentosDao().saveAlimento(alimentos)
            alimentos.id = id
            return alimentos
        }catch (e: Exception){
            Log.d("Exception", "Error ingresando alimentos")
            return null
        }
    }

    fun deleteAlimentos(applicationContext: Application){
        try {
            InstruIIDatabase.get(application = applicationContext).alimentosDao().deleteAlimentos()
        }catch (e: Exception){
            Log.d("Exception", "Error borrando la tabla de Alimentos")
        }
    }
}