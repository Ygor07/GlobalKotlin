package com.example.checkpoint3.ui.dados

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.checkpoint3.R
import com.example.checkpoint3.databinding.FragmentDadosSintomasBinding

class DadosSintomasFragment : Fragment() {


    private var _binding: FragmentDadosSintomasBinding? = null
    private lateinit var sharedPreferences: SharedPreferences
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadosSintomasBinding.inflate(inflater, container, false)
        val rootView = binding.root

        sharedPreferences = requireActivity().getSharedPreferences("dados_sintomas", Context.MODE_PRIVATE)

        val nextButton = binding.nextButton
        nextButton.setOnClickListener {
            val editor = sharedPreferences.edit()

            val checkBox1 = binding.checkBox1.isChecked
            val checkBox2 = binding.checkBox2.isChecked
            val checkBox3 = binding.checkBox3.isChecked
            val checkBox4 = binding.checkBox4.isChecked

            val outros = binding.outros.text.toString().trim()

            if (checkBox1 || checkBox2 || checkBox3 || checkBox4 || !outros.isNotEmpty()) {
                editor.putBoolean("checkBox1", checkBox1)
                editor.putBoolean("checkBox2", checkBox2)
                editor.putBoolean("checkBox3", checkBox3)
                editor.putBoolean("checkBox4", checkBox4)
                editor.putString("outros", outros)

                editor.apply()
                findNavController().navigate(R.id.nav_fila)
            } else {
                Toast.makeText(requireContext(), "Selecione pelo menos uma informação para ajuda-lo melhor", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}