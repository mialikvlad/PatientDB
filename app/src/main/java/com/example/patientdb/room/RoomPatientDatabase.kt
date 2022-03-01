package com.example.patientdb.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.patientdb.model.RoomPatient

@Database(entities = [RoomPatient::class], version = 1)
abstract class RoomPatientDatabase : RoomDatabase() {
    abstract fun patientDao() : RoomPatientDao
}