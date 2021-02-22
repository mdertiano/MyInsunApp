package ar.edu.itba.instruII.views

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.instruII.R
import kotlinx.android.synthetic.main.activity_notificaciones.*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.TimePickerDialog
import ar.edu.itba.instruII.service.AlarmService
import java.text.SimpleDateFormat
import java.util.*

class ActivityNotificaciones : AppCompatActivity() {

    lateinit var alarmService: AlarmService

    private val CHANNEL_ID="channel_id_example_01"
    private val notificationID=101

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        alarmService = AlarmService(this)

        //BTN1
        btn_11.setOnClickListener {
            setAlarm1 { alarmService.setExactAlarm(it) }
        }
        //BTN2
        btn_22.setOnClickListener {
            setAlarm2 { alarmService.setExactAlarm(it) }
        }
        //BTN3
        btn_33.setOnClickListener {
            setAlarm3 { alarmService.setExactAlarm(it) }
        }
        //BTN4
        btn_44.setOnClickListener {
            setAlarm4 { alarmService.setExactAlarm(it) }
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name="Title"
            val descriptionText="Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel= NotificationChannel(CHANNEL_ID, name, importance).apply{
                description=descriptionText
            }
            val notificationManager: NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title2")
            .setContentText("descrip")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationID, builder.build())
        }
    }

    private fun setAlarm1(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            this.get(Calendar.YEAR)
            this.get(Calendar.MONTH)
            this.get(Calendar.DAY_OF_MONTH)
            TimePickerDialog(
                this@ActivityNotificaciones,
                0,
                { _, hour, minute ->
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, minute)
                    callback(this.timeInMillis)
                    tiempoDesayuno.text = SimpleDateFormat("HH:mm").format(this.time)
                },
                this.get(Calendar.HOUR_OF_DAY),
                this.get(Calendar.MINUTE),
                false
            ).show()
        }
    }

    private fun setAlarm2(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            this.get(Calendar.YEAR)
            this.get(Calendar.MONTH)
            this.get(Calendar.DAY_OF_MONTH)
            TimePickerDialog(
                this@ActivityNotificaciones,
                0,
                { _, hour, minute ->
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, minute)
                    callback(this.timeInMillis)
                    tiempoAlmuerzo.text = SimpleDateFormat("HH:mm").format(this.time)
                },
                this.get(Calendar.HOUR_OF_DAY),
                this.get(Calendar.MINUTE),
                false
            ).show()
        }
    }

    private fun setAlarm3(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            this.get(Calendar.YEAR)
            this.get(Calendar.MONTH)
            this.get(Calendar.DAY_OF_MONTH)
            TimePickerDialog(
                this@ActivityNotificaciones,
                0,
                { _, hour, minute ->
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, minute)
                    callback(this.timeInMillis)
                    tiempoMerienda.text = SimpleDateFormat("HH:mm").format(this.time)
                },
                this.get(Calendar.HOUR_OF_DAY),
                this.get(Calendar.MINUTE),
                false
            ).show()
        }
    }

    private fun setAlarm4(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            this.get(Calendar.YEAR)
            this.get(Calendar.MONTH)
            this.get(Calendar.DAY_OF_MONTH)
            TimePickerDialog(
                this@ActivityNotificaciones,
                0,
                { _, hour, minute ->
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, minute)
                    callback(this.timeInMillis)
                    tiempoCena.text = SimpleDateFormat("HH:mm").format(this.time)
                },
                this.get(Calendar.HOUR_OF_DAY),
                this.get(Calendar.MINUTE),
                false
            ).show()
        }
    }
}

