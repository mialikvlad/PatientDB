package com.example.patientdb.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.patientdb.MainActivity
import com.example.patientdb.databinding.FragmentEditBinding
import com.example.patientdb.model.RoomPatient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val openImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        binding.image.setImageURI(uri)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            buttonAddPatient.setOnClickListener {
                 val newPatient = RoomPatient(
                     firstName = editFirstName.text.toString(),
                     secondName = editSecondName.text.toString(),
                     hospitalRoom = editHospitalRoom.text.toString()
                 )

                if(newPatient.firstName == "" || newPatient.secondName == ""
                    || newPatient.hospitalRoom == ""){
                    Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
                }else{
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
                        addPatient(newPatient)
                    }
                    Toast.makeText(context, "Patient accepted", Toast.LENGTH_SHORT).show()
                }
            }
        }

        with(binding){
            buttonLoadImage.setOnClickListener {
                openImageLauncher.launch(MIME_TYPE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun addPatient(patient: RoomPatient){
        (requireActivity() as MainActivity).database.patientDao().insertAll(patient)
    }

    companion object {
        private const val MIME_TYPE = "image/*"
    }
}