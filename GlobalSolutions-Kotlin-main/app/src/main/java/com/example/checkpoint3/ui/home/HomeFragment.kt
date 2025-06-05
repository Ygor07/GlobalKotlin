package com.example.checkpoint3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkpoint3.R
import com.example.checkpoint3.databinding.FragmentHomeBinding
import com.example.checkpoint3.databinding.ItemHospitalBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.hospitalList
        val hospitalList = listOf(
            Hospital("Hospital do Servidor Público Municipal", "Rua Castro Alves 60, São Paulo", "1,9 Km"),
            Hospital("Hospital das Clínicas - Cerqueira César", "Av. Dr. Enéas Carvalho de Aguiar 255, São Paulo", "3,9 Km"),
            Hospital("Hospital Universitário - USP", "Av Professor Lineu Prestes 2565, São Paulo", "10,9 Km"),
        )

        val adapter = HospitalAdapter(binding, hospitalList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

}