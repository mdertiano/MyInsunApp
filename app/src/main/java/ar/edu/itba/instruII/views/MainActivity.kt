package ar.edu.itba.instruII.views

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.instruII.R
import ar.edu.itba.instruII.helpers.PreparadorBD
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.AgregarButton)
        button.setOnClickListener(View.OnClickListener {
            val sexo = getSexo()
            //SexoServicio().insertSexo(sexo, application)
            //SexoServicio().deleteTabla(application)
            SexoServicio().getSexos(application)
        })
        //PacienteServicio().insertPaciente(Paciente(0,"Mili",7), application)
        var paciente = PacienteServicio().getPacientes(application)[0]
        paciente.RIC = 8
        PacienteServicio().updatePacientes(application, paciente)
        PreparadorBD.prepararBD(application)

         */
        // PRIMER MENSAJE:
        var PRIVATE_MODE = 0
        val prefs: SharedPreferences  = getSharedPreferences("prefs", PRIVATE_MODE)
        var firstStart: Boolean  = prefs.getBoolean("firstStart", true)

        if (firstStart){
            showStartDialog()
        }

        // BOTONES:
        Thread.sleep(2000)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PreparadorBD.prepararBD(application)

        buttonCALCULADORA.setOnClickListener {
            val intent: Intent = Intent(this, ActivityCalculadora::class.java)
            startActivity(intent)
        }

        buttonEXTRA.setOnClickListener {
            val intent: Intent = Intent(this, ActivityExtra::class.java)
            startActivity(intent)
        }

        buttonREGISTRO.setOnClickListener {
            val intent: Intent = Intent(this, ActivityRegistro::class.java)
            startActivity(intent)
        }

        buttonSETTINGS.setOnClickListener {
            val intent: Intent = Intent(this, ActivitySettings::class.java)
            startActivity(intent)
        }

        buttonALARM.setOnClickListener {
            val intent: Intent = Intent(this, ActivityNotificaciones::class.java)
            startActivity(intent)
        }

    }
    fun showStartDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this).setTitle("Primera configuración")
            .setMessage("¡¡Bienvenido!! ¿Primera vez en MyInsulineApp? Dirigite a DATOS PERSONALES y completa con tu información")

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
        alertDialogBuilder.show()

        var PRIVATE_MODE = 0
        val prefs: SharedPreferences  = getSharedPreferences("prefs", PRIVATE_MODE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }
    /*
    fun getSexo(): Sexo {
        val editTxtSexo = findViewById<EditText>(R.id.sexoEditText) //Estoy pidiendo el elemento de la vista que tiene este id
        var msg = editTxtSexo.text.toString()
        var sexo = Sexo()
        sexo.sexo = msg
        return sexo
    }
     */

}