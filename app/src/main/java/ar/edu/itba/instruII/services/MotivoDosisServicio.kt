package ar.edu.itba.instruII.services

import android.app.Application
import android.util.Log
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.MotivoDosis

class MotivoDosisServicio {
    fun insertMotivoDosis(motivoDosis: MotivoDosis, applicationContext : Application): MotivoDosis? {
        try{
            var id = InstruIIDatabase.get(application = applicationContext).motivoDosisDao().saveMotivoDosis(motivoDosis)
            // Acá me devuelve el id autogenerado de cosa que le estoy insertando. De esta forma después puedo tomar ese dato
            // y usarlo.
            motivoDosis.id = id
            return motivoDosis
        }catch (e: Exception){
            Log.d("Exception", "Error al insertar nuevo motivo dosis: ${motivoDosis.Tipo}")
            return null
        }
    }

    fun getMotivo(motivo: String, applicationContext: Application): MotivoDosis? {
        try {
            return InstruIIDatabase.get(application = applicationContext).motivoDosisDao().getMotivo(motivo)
        }catch (e: Exception){
            Log.d("Exception", "Error leyendo tabla motivos, motivo: $motivo")
            return null
        }
    }

    fun borrarMotivos(applicationContext: Application) {
        try {
            InstruIIDatabase.get(application = applicationContext).motivoDosisDao().deleteMotivoDosis()
        }catch (e: Exception){
            Log.d("Exception", "Error limpiando tabla motivos")
        }
    }
}