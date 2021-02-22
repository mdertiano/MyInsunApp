package ar.edu.itba.instruII.daos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class Conversores {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return LocalDateTime.ofInstant(
            value?.let { Instant.ofEpochMilli(it) },
            TimeZone.getDefault().toZoneId())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return ZonedDateTime.of(date, ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}