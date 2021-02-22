package ar.edu.itba.instruII.daos

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ar.edu.itba.instruII.models.*

// Acá se esta creando la clase de Room. Se esta instanciando la base de datos, es un objeto que se crea una vez y de ahí nunca
// más. Estoy armando la base de datos como si fuera un objeto.
@Database(version = 1, entities = [Dosis::class,Paciente::class,Sexo::class,Tipo_diabetes::class,Unidad::class, Alimentos::class,MotivoDosis::class])
// Hasta acá se están ingresando las tablas con las que se va a laburar. Y que número de Database es.
@TypeConverters(Conversores::class)
abstract class InstruIIDatabase : RoomDatabase(){
    companion object{ // Se crea la base de datos propiamente dicha como un objeto.
        fun get(application: Application) : InstruIIDatabase { //Se establecen los parametros y el contexto.
            // A continuación se devuelve esa base de datos.
            return Room.databaseBuilder(application,
                InstruIIDatabase::class.java,"InstruII").allowMainThreadQueries()
                .build()
        }
    }
    abstract fun dosisDao(): DosisDAO
    abstract fun pacienteDao(): PacienteDAO
    abstract fun sexoDao(): SexoDAO
    abstract fun tipoDiabetesDao(): Tipo_diabetesDAO
    abstract fun unidadDao(): UnidadDAO
    abstract fun alimentosDao(): AlimentosDAO
    abstract fun motivoDosisDao(): MotivoDosisDAO
}