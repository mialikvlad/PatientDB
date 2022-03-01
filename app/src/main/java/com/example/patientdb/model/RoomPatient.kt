package com.example.patientdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomPatient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "second_name") val secondName: String?,
    @ColumnInfo(name = "hospital_room") val hospitalRoom: String?
)
