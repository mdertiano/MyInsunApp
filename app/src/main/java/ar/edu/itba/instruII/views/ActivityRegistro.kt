package ar.edu.itba.instruII.views

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.instruII.R
import ar.edu.itba.instruII.services.DosisServicio
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class ActivityRegistro : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val ultimoVal = DosisServicio().getDosis(application).last() //Acá tengo mi última dosis
        val horaDosis = ultimoVal.fecha // Estoy llamando a la última hora que tengo en mi registro
        var volumenDosis = ultimoVal.volumen // Estoy llamando al último volumen que tengo en mi registro

        val dosisUltima = findViewById(R.id.dosisultima) as TextView
        dosisUltima.text = volumenDosis.toString() //Me tiene que estar reescribiendo el coso de la dosis en pantalla.

        val tiempoUltimo = findViewById<TextView>(R.id.horarioultima)
        //val instant: Instant = Instant.parse(horaDosis.toString())
        //val formattedIsoString = val formattedIsoString =
         //   Instant.parse(horaDosis.toString())
          //      .atOffset(ZoneOffset.UTC)
           //     .format(DateTimeFormatter
             //       .ofPattern("uuuu-MM-dd'T'HH:mm"))
        val s = horaDosis.toString()
        tiempoUltimo.text = s // Me escribe la hora de la dosis en pantalla
    }
}