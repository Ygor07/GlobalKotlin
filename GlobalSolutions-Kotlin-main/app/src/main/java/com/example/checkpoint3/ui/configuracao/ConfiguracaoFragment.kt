package com.example.checkpoint3.ui.configuracao

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.checkpoint3.databinding.FragmentConfiguracaoBinding
import java.util.Locale

class ConfiguracaoFragment : Fragment() {

    private var _binding: FragmentConfiguracaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var isDarkTheme = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ConfiguracaoViewModel::class.java)

        _binding = FragmentConfiguracaoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreferences = requireActivity().getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false)

        binding.buttonChangeTheme.setOnClickListener {
            isDarkTheme = !isDarkTheme

            sharedPreferences.edit().putBoolean("is_dark_theme", isDarkTheme).apply()

            toggleTheme()
        }

        return root
    }

    private fun toggleTheme() {
        if(isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            isDarkTheme = false
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            isDarkTheme = true
        }
        requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}