package com.example.checkpoint3.ui.dados

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.checkpoint3.databinding.FragmentFilaBinding
import kotlin.random.Random

class FilaFragment : Fragment() {

    private var _binding: FragmentFilaBinding? = null
    private lateinit var sharedPreferences: SharedPreferences
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilaBinding.inflate(inflater, container, false)
        val root = binding.root

        val numeroFila = Random.nextInt(0, 101)

        sharedPreferences = requireActivity().getSharedPreferences("dados_hospital", Context.MODE_PRIVATE)

        val nomeHospital = sharedPreferences.getString("nome", "")
        val enderecoHospital = sharedPreferences.getString("endereco", "")

        binding.numeroFila.text = numeroFila.toString()
        binding.nomeHospital.text = nomeHospital
        binding.enderecoHospital.text = enderecoHospital
        val cancelarButton = binding.cancelarButton
        cancelarButton.setOnClickListener {
            findNavController().popBackStack(findNavController().graph.startDestinationId, false)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}