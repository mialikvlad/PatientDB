package com.example.patientdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.patientdb.Fragment.TabFragment
import com.example.patientdb.room.RoomPatientDao
import com.example.patientdb.room.RoomPatientDatabase

class MainActivity : AppCompatActivity() {

    val database: RoomPatientDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            RoomPatientDatabase::class.java,
            "patient-db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, TabFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}