package com.example.patientdb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.patientdb.model.RoomPatient

@Dao
interface RoomPatientDao {
  @Query("SELECT * FROM roompatient")
  suspend fun getAll() : List<RoomPatient>

  @Insert
  suspend fun insertAll(vararg patients: RoomPatient)

  @Delete
  suspend fun delete(patient: RoomPatient)
}