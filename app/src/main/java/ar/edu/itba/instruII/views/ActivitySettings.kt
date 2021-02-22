package ar.edu.itba.instruII.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import ar.edu.itba.instruII.R
import ar.edu.itba.instruII.models.Paciente
import ar.edu.itba.instruII.services.PacienteServicio
import kotlinx.android.synthetic.main.activity_calculadora.*
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonGUARDARnombreric.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.textNOMBRE)
            var nombreString = nombre.text.toString()
            val ric = findViewById<EditText>(R.id.textRIC)
            var ricInt = ric.text.toString().toInt()
            val fsi = findViewById<EditText>(R.id.textFSI)
            var fsiInt = fsi.text.toString().toInt()

            //GUARDAR EN DB
            val largoTablaPaciente = PacienteServicio().existenciaPaciente(application)
            if (largoTablaPaciente == 0){
                PacienteServicio().insertPaciente(Paciente(0,nombreString,fsiInt,ricInt), application) // Acá ya guardo al paciente en la base de datos.
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                Log.d("RIC", "$ricInt")
            }else {
                var paciente = PacienteServicio().getPacientes(application)[0]
                paciente.RIC = ricInt
                paciente.FSI = fsiInt
                PacienteServicio().updatePacientes(application, paciente)
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                Log.d("Nueva RIC", "$ricInt")
                // Faltan cosas de a pruba de tontos (A mejorar a futuro )
            }
        }

        //VALIDACIÓN NOMBRE
        textNOMBRE.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(mobileValidateNombre(textNOMBRE.text.toString())){
                    if(!textRIC.text.toString().isEmpty() && !textFSI.text.toString().isEmpty()){
                        buttonGUARDARnombreric.isEnabled = true
                    }
                    else if(textRIC.text.toString().isEmpty() || textFSI.text.toString().isEmpty()){
                        buttonGUARDARnombreric.isEnabled = false
                    }
                }
                else {
                    buttonGUARDARnombreric.isEnabled = false
                    textNOMBRE.setError("Ingrese un nombre correcto")
                }
            }
        })

        //VALIDACIÓN RIC
        textRIC.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!textNOMBRE.text.toString().isEmpty() && !textRIC.text.toString().isEmpty() && !textFSI.text.toString().isEmpty()){
                    buttonGUARDARnombreric.isEnabled = true
                }
                else{
                    buttonGUARDARnombreric.isEnabled = false
                }
            }
        })

        //VALIDACIÓN FSI
        textFSI.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!textNOMBRE.text.toString().isEmpty() && !textRIC.text.toString().isEmpty() && !textFSI.text.toString().isEmpty()){
                    buttonGUARDARnombreric.isEnabled = true
                }
                else{
                    buttonGUARDARnombreric.isEnabled = false
                }
            }
        })
    }

    private fun mobileValidateNombre(text: String?): Boolean {
        var p: Pattern = Pattern.compile("[A-Za-z]+")
        var m: Matcher = p.matcher(text)
        return m.matches()
    }

}