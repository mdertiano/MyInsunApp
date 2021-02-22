package ar.edu.itba.instruII.views

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import ar.edu.itba.instruII.R
import ar.edu.itba.instruII.daos.InstruIIDatabase
import ar.edu.itba.instruII.models.Dosis
import ar.edu.itba.instruII.services.DosisServicio
import ar.edu.itba.instruII.services.MotivoDosisServicio
import ar.edu.itba.instruII.services.PacienteServicio
import kotlinx.android.synthetic.main.activity_calculadora.*
import kotlinx.android.synthetic.main.activity_extra.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.math.RoundingMode.UP
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.abs

class ActivityExtra : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)

        var dosisExtra = 0.0
        // CALCULO DE LA DOSIS DESEADA
        buttonCALCULARex.setOnClickListener {
            val gluDeseada = findViewById<EditText>(R.id.gluDESEADA)
            var gluDeseadaUsar = gluDeseada.text.toString().toDouble()
            val gluActual = findViewById<EditText>(R.id.gluACTUAL)
            var gluActualUsar = gluActual.text.toString().toDouble()
            dosisExtra = correccionBolo(gluDeseadaUsar, gluActualUsar)
            if (dosisExtra > 4){
                showDialogExtra()
            }
            else {
                val visorCorreccion = findViewById(R.id.visorCORRECCION) as TextView
                visorCorreccion.text = dosisExtra.toString()
            }
        }

        buttonGUARDARex.setOnClickListener {
            val tiempoExtra = findViewById<EditText>(R.id.TiempoExtra)
            var fecha = TimePickerFragment.retrieveFromPickerText(tiempoExtra)

            // Se guarda en la base de datos.
            var paciente = PacienteServicio().getPacientes(application)[0].id
            var motivoDosis = MotivoDosisServicio().getMotivo("Extra", application)
            if (motivoDosis != null) {
                DosisServicio().insertDosis(Dosis(0,paciente,dosisExtra,fecha,"",motivoDosis.id), application)
                Toast.makeText(this, "La dosis se guardo correctamente: $dosisExtra", Toast.LENGTH_SHORT).show()
        }

    }

        //RELOJ
        TiempoExtra.setOnClickListener { showTimePickerDialogExtra() }

        //VALIDACIÓN GLUCEMIA
        gluACTUAL.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (50 >= gluACTUAL.text.toString().toDouble() || gluACTUAL.text.toString().toDouble() >= 400) {
                    buttonCALCULARex.isEnabled = false
                    gluACTUAL.setError("Ingrese un valor de glucemia correcto")
                }
                else {
                    if (!gluDESEADA.text.toString().isEmpty() && !gluACTUAL.text.toString().isEmpty()) {
                        if (gluACTUAL.text.toString().toDouble() != gluDESEADA.text.toString().toDouble()) {
                            buttonCALCULARex.isEnabled = true
                        }
                        else if (gluACTUAL.text.toString().toDouble() == gluDESEADA.text.toString().toDouble()) {
                            buttonCALCULARex.isEnabled = false
                            gluACTUAL.setError("Ingrese un valor de glucemia correcto")
                        }
                    }
                }
                if (gluDESEADA.text.toString().isEmpty() || gluACTUAL.text.toString().isEmpty()) {
                    buttonCALCULARex.isEnabled = false
                }
            }
        })

        gluDESEADA.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (80 >= gluDESEADA.text.toString().toDouble() || gluDESEADA.text.toString().toDouble() >= 180) {
                    buttonCALCULARex.isEnabled = false
                    gluDESEADA.setError("Ingrese un valor de glucemia correcto")
                }
                else {
                    if (!gluDESEADA.text.toString().isEmpty() && !gluACTUAL.text.toString().isEmpty()) {
                        if (gluACTUAL.text.toString().toDouble() != gluDESEADA.text.toString().toDouble()) {
                            buttonCALCULARex.isEnabled = true
                        }
                        else if (gluACTUAL.text.toString().toDouble() == gluDESEADA.text.toString().toDouble()) {
                            buttonCALCULARex.isEnabled = false
                            gluDESEADA.setError("Ingrese un valor de glucemia correcto")
                        }
                    }
                }
                if (gluDESEADA.text.toString().isEmpty() || gluACTUAL.text.toString().isEmpty()) {
                    buttonCALCULARex.isEnabled = false
                }
            }
        })

        TiempoExtra.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TiempoExtra.toString().isEmpty() && !visorCORRECCION.text.toString()
                        .isEmpty()
                ) {
                    buttonGUARDARex.isEnabled = true
                } else {
                    buttonGUARDARex.isEnabled = false
                }
            }
        })

    }

    fun correccionBolo(gluDeseada: Double, gluActual: Double): Double {
        var paciente = PacienteServicio().getPacientes(application)[0]
        var DosisExtra1 = abs(gluActual - gluDeseada) / paciente.FSI
        var DosisExtra2 = BigDecimal(DosisExtra1).setScale(2,UP)
        var DosisExtra = DosisExtra2.toDouble()
        return DosisExtra
    }

    private fun showTimePickerDialogExtra() {
        val timePicker = TimePickerFragment{onTimeSelectedExtra(it)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelectedExtra(time:String){
        TiempoExtra.setText("$time")
    }

    fun showDialogExtra(){
        val alertDialogBuilder = AlertDialog.Builder(this).setTitle("ATENCIÓN")
            .setMessage("La dosis calculada posee un valor superior a 8 unidades de insulina. Asegúrese de que la información ingresada sea correcta. ¿Desea recalcular la dosis?")

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        val negativeButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
            var dosisExtra = 0.0
            val gluDeseada = findViewById<EditText>(R.id.gluDESEADA)
            var gluDeseadaUsar = gluDeseada.text.toString().toDouble()
            val gluActual = findViewById<EditText>(R.id.gluACTUAL)
            var gluActualUsar = gluActual.text.toString().toDouble()
            dosisExtra = correccionBolo(gluDeseadaUsar, gluActualUsar)
            val visorCorreccion = findViewById(R.id.visorCORRECCION) as TextView
            visorCorreccion.text = dosisExtra.toString()
        }
        alertDialogBuilder.setPositiveButton("SI", DialogInterface.OnClickListener(function = positiveButtonClick))
        alertDialogBuilder.setNegativeButton("NO", DialogInterface.OnClickListener(function = negativeButtonClick))
        alertDialogBuilder.show()

        var PRIVATE_MODE = 0
        val prefs: SharedPreferences = getSharedPreferences("prefs", PRIVATE_MODE)
        val editor = prefs.edit()
        editor.apply()
    }

}