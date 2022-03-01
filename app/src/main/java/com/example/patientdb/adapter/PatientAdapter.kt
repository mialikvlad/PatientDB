package com.example.patientdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientdb.databinding.FragmentListBinding
import com.example.patientdb.databinding.ItemPatientBinding
import com.example.patientdb.model.RoomPatient

class PatientAdapter(private val action: (RoomPatient) -> Unit) : RecyclerView.Adapter<PatientViewHolder>(){

    private val patients = mutableListOf<RoomPatient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            binding = ItemPatientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onPatientClick = action
        )
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bind(patients[position])
    }

    override fun getItemCount(): Int {
        return patients.size
    }


    fun updateList(newPatients: List<RoomPatient>){
        patients.clear()
        patients.addAll(newPatients)
        notifyDataSetChanged()
    }


}

class PatientViewHolder(private val binding: ItemPatientBinding, private val onPatientClick: (RoomPatient) -> Unit): RecyclerView.ViewHolder(binding.root) {
    fun bind(patient: RoomPatient){
        with(binding){
            patientFirstNameTextView.text = patient.firstName
            patientSecondNameTextView.text = patient.secondName
            patientRoomTextView.text = patient.hospitalRoom

            deleteButton.setOnClickListener {
                onPatientClick(patient)
            }
        }
    }
}
