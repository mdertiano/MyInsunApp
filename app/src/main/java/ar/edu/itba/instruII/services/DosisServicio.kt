package ar.edu.itba.instruII.services

import android.app.Application
import android.util.Log
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.Alimentos
import ar.edu.itba.instruII.models.Dosis
import ar.edu.itba.instruII.models.Paciente
import java.math.BigDecimal
import java.math.RoundingMode

class DosisServicio{

    fun insertDosis(dosis: Dosis, applicationContext : Application): Dosis? {
        try{
            var id = InstruIIDatabase.get(application = applicationContext).dosisDao().saveDosis(dosis)
            dosis.id = id
            return dosis
        }catch (e: Exception){
            Log.d("Exception", "Error ingresando dosis")
            return null
        }
    }
    // Calculo de la dosis de insulina
    fun calcularDosis(alimento: String, gramos: Int, applicationContext: Application): Double {
        var alimento = InstruIIDatabase.get(application = applicationContext).alimentosDao().getAlimento(alimento)
        var paciente = InstruIIDatabase.get(application = applicationContext).pacienteDao().getAllPaciente()
        var DosisADar1 = ((alimento.gHcxPorcion * gramos) / alimento.gxPorcion) / paciente[0].RIC
        var DosisADar2 = BigDecimal(DosisADar1).setScale(2, RoundingMode.UP)
        var DosisADar = DosisADar2.toDouble()
        return DosisADar
    }


    fun getDosis(applicationContext : Application): List<Dosis> {
        try {
            var list: List<Dosis> = emptyList()
            list = InstruIIDatabase.get(application = applicationContext).dosisDao().getAllDosis()
            return list
        }catch (e: java.lang.Exception){
            Log.d("Excepcion", "error levantando pacientes")
            return emptyList()
        }
    }

    fun deleteDosis(applicationContext: Application){
        try {
            InstruIIDatabase.get(application = applicationContext).dosisDao().deleteDosis()
        }catch (e: Exception){
            Log.d("Exception", "Error borrando la tabla de Alimentos")
        }
    }
}

