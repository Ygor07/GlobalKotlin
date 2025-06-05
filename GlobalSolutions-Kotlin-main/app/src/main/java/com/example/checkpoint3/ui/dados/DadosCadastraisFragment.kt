package com.example.checkpoint3.ui.dados

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.checkpoint3.R
import com.example.checkpoint3.databinding.FragmentDadosCadastraisBinding

class DadosCadastraisFragment : Fragment() {

    private var _binding: FragmentDadosCadastraisBinding? = null
    private lateinit var sharedPreferences: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDadosCadastraisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreferences = requireActivity().getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val nextButton : Button = binding.nextButton
        nextButton.setOnClickListener{
            val nome = binding.itemNameInput.text.toString().trim()
            val sobrenome = binding.itemSobrenomeInput.text.toString().trim()
            val email = binding.itemEmailInput.text.toString().trim()
            val celular = binding.itemCelularInput.text.toString().trim()
            val rg = binding.itemRgInput.text.toString().trim()
            val dataNascimento = binding.itemDtNascimentoInput.text.toString().trim()
            if (nome.isNotEmpty() && sobrenome.isNotEmpty() && email.isNotEmpty()
                && celular.isNotEmpty() && rg.isNotEmpty() && dataNascimento.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("nome", nome)
                editor.putString("sobrenome", sobrenome)
                editor.putString("email", email)
                editor.putString("celular", celular)
                editor.putString("rg", rg)
                editor.putString("dataNascimento", dataNascimento)
                editor.apply()
                findNavController().navigate(R.id.nav_dados_saude)
            } else {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}