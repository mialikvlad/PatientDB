package com.example.patientdb.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientdb.MainActivity
import com.example.patientdb.adapter.PatientAdapter
import com.example.patientdb.databinding.FragmentListBinding
import com.example.patientdb.databinding.ItemPatientBinding
import com.example.patientdb.model.RoomPatient
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    val adapter = PatientAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            (requireActivity() as MainActivity).database.patientDao().delete(it)
            updateIU()
        }
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner.lifecycleScope.launch {
            updateIU()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(
                view.context
            )
            recycleView.adapter = adapter

        }
        viewLifecycleOwner.lifecycleScope.launch {
            updateIU()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun loadPatients(): List<RoomPatient> {
        return (requireActivity() as MainActivity).database.patientDao().getAll()
    }

    private suspend fun updateIU() {
        adapter.updateList(loadPatients())
    }
}