package ar.edu.itba.instruII.views

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.instruII.R
import ar.edu.itba.instruII.models.Dosis
import ar.edu.itba.instruII.services.DosisServicio
import ar.edu.itba.instruII.services.MotivoDosisServicio
import ar.edu.itba.instruII.services.PacienteServicio
import kotlinx.android.synthetic.main.activity_calculadora.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

class ActivityCalculadora : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        //AUTOCOMPLETE
        val alimentos = resources.getStringArray(R.array.alimentos)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alimentos)
        autoCompleteTextView.setAdapter(arrayAdapter)

        //RELOJ
        Tiempo.setOnClickListener { showTimePickerDialog() }

        //CALCULAR DOSIS
        var dosisCorrespondiente = 0.0
        BotonCALCULAR.setOnClickListener {
            val alimentoauto= findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
            var alimento=alimentoauto.text.toString()
            val gramos = findViewById<EditText>(R.id.cantGramos)
            var gramosPaciente = gramos.text.toString().toInt()
            dosisCorrespondiente = DosisServicio().calcularDosis(alimento,gramosPaciente,application)
            if (dosisCorrespondiente > 10){
                showDialog()
            }
            else {
                val dosisUltima = findViewById<TextView>(R.id.visorcalculo)
                dosisUltima.text = dosisCorrespondiente.toString()
            }
        }

        //GUARDAR DOSIS
        GuardarDosisIngesta.setOnClickListener {
            val tiempoAuto = findViewById<EditText>(R.id.Tiempo)
            var fecha = TimePickerFragment.retrieveFromPickerText(tiempoAuto)
            // Obtengo mi fecha actual
            /*var date = LocalDate.now() //Me devuelve un objeto Date del estilo: "yy-mm-dd"
            var dateString = date.toString()

            //Obtengo el horario dado por el usuario que se va aplicar la dosis.
            var tiempo = tiempoAuto.text.toString()
            var fechaHora = "$dateString-$tiempo"
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
            var fecha = LocalDateTime.parse(fechaHora, formatter) */

            // Se guarda en la base de datos.
            var paciente = PacienteServicio().getPacientes(application)[0].id
            var motivoDosis = MotivoDosisServicio().getMotivo("Ingesta", application)
            if (motivoDosis != null) {
                DosisServicio().insertDosis(Dosis(0,paciente,dosisCorrespondiente,fecha,"",motivoDosis.id), application)
                Toast.makeText(this, "La dosis se guardó correctamente: $dosisCorrespondiente", Toast.LENGTH_SHORT).show()
            } //id motivo ir a buscarlo a la tabla segun string motivo (es fijo).
        }

        //VALIDACIONES
        var alimentosValidos: HashSet<String> = hashSetOf<String>(*alimentos)
        autoCompleteTextView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                autoCompleteTextView.setError(null)
                if(!alimentosValidos.contains(autoCompleteTextView.text.toString())) {
                    //no es un alimento valido
                    BotonCALCULAR.isEnabled = false
                    autoCompleteTextView.setError("Ingrese un alimento correcto")
                }
            }
        })

        cantGramos.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                BotonCALCULAR.isEnabled = !cantGramos.text.toString().isEmpty()
            }
        })

        Tiempo.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!Tiempo.text.toString().isEmpty() && !visorcalculo.text.toString().isEmpty()) {
                    GuardarDosisIngesta.isEnabled = true
                }
                else {
                    GuardarDosisIngesta.isEnabled = false
                }
            }
        })


    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment{onTimeSelected(it)}
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time:String){
        Log.d("onTimeSelected calc", time)
        Tiempo.setText("$time")
    }

    fun showDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this).setTitle("ATENCIÓN")
            .setMessage("La dosis calculada posee un valor superior a 10 unidades de insulina. Asegúrese de que la información ingresada sea correcta. ¿Desea recalcular la dosis?")

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        val negativeButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
            val dosisUltima = findViewById<TextView>(R.id.visorcalculo)
            var dosisCorrespondiente = 0.0
            val alimentoauto= findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
            var alimento=alimentoauto.text.toString()
            val gramos = findViewById<EditText>(R.id.cantGramos)
            var gramosPaciente = gramos.text.toString().toInt()
            dosisCorrespondiente = DosisServicio().calcularDosis(alimento,gramosPaciente,application)
            dosisUltima.text = dosisCorrespondiente.toString()
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


