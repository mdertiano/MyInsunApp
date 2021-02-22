package ar.edu.itba.instruII.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import ar.edu.itba.instruII.service.AlarmService
import ar.edu.itba.instruII.util.Constants
import io.karn.notify.Notify
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReciever: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)
        when (intent.action) {
            Constants.ACTION_SET_EXACT -> {
                buildNotification(context, "Set Exact Time", convertDate(timeInMillis))
            }

            Constants.ACTION_SET_REPETITIVE_EXACT -> {
                setRepetitiveAlarm(AlarmService(context))
                buildNotification(context, "Set Repetitive Exact Time", convertDate(timeInMillis))
            }
        }
    }

    private fun buildNotification(context: Context, title: String, message: String) {
        Notify
            .with(context)
            .content {
                this.title ="RECORDATORIO"
                text = "No te olvides de calcular tu dosis de insulina."
            }
            .show()
    }

    private fun setRepetitiveAlarm(alarmService: AlarmService) {
        val cal = Calendar.getInstance().apply {
            this.timeInMillis = timeInMillis + TimeUnit.DAYS.toMillis(7)
            Timber.d("Set alarm for next week same time - ${convertDate(this.timeInMillis)}")
        }
        alarmService.setRepetitiveAlarm(cal.timeInMillis)
    }

    private fun setDailyAlarm(alarmService: AlarmService) {
        val cal = Calendar.getInstance().apply {
            this.timeInMillis = timeInMillis + TimeUnit.DAYS.toMillis(1)
            Timber.d("Set alarm for next week same time - ${convertDate(this.timeInMillis)}")
        }
        alarmService.setRepetitiveAlarm(cal.timeInMillis)
    }

    private fun convertDate(timeInMillis: Long): String =
        DateFormat.format("dd/MM/yyyy hh:mm:ss", timeInMillis).toString()


}