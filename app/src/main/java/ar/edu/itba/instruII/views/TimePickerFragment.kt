package ar.edu.itba.instruII.views

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TimePickerFragment(val listener: (String) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar:Calendar = Calendar.getInstance()
        val hour:Int =  calendar.get(Calendar.HOUR_OF_DAY)
        val minute:Int = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, this, hour, minute, true)
        return dialog
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var fixedHour = hourOfDay.toString()
        var fixedMinute = minute.toString()

        if (hourOfDay < 10) {
            fixedHour = "0" + hourOfDay.toString()
        }
        if (minute < 10) {
            fixedMinute = "0" + minute.toString()
        }
        listener("$fixedHour:$fixedMinute")
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun retrieveFromPickerText(timeText: EditText): LocalDateTime {
            var date = LocalDate.now() //Me devuelve un objeto Date del estilo: "yy-mm-dd"
            var dateString = date.toString()

            val tiempo = timeText.text.toString()
            var fechaHora = "$dateString-$tiempo"
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
            var fecha = LocalDateTime.parse(fechaHora, formatter)

            return fecha
        }
    }

}