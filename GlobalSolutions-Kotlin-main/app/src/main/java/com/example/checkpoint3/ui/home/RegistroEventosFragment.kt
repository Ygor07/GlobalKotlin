package com.example.checkpoint3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkpoint3.databinding.FragmentRegistroEventosBinding
import com.example.checkpoint3.ui.model.EventoExtremo

class RegistroEventosFragment : Fragment() {

    private var _binding: FragmentRegistroEventosBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventoExtremoAdapter: EventoExtremoAdapter
    private val listaEventos = mutableListOf<EventoExtremo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroEventosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupListeners()

        return root
    }

    private fun setupRecyclerView() {
        eventoExtremoAdapter = EventoExtremoAdapter(listaEventos) { evento ->
            // Lógica para excluir o evento
            eventoExtremoAdapter.removerEvento(evento)
            Toast.makeText(context, "Evento excluído!", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerViewEventos.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEventos.adapter = eventoExtremoAdapter
    }

    private fun setupListeners() {
        binding.btnIncluir.setOnClickListener { 
            adicionarEvento()
        }
    }

    private fun adicionarEvento() {
        val nomeLocal = binding.etNomeLocal.text.toString().trim()
        val tipoEvento = binding.etTipoEvento.text.toString().trim()
        val grauImpacto = binding.etGrauImpacto.text.toString().trim()
        val dataEvento = binding.etDataEvento.text.toString().trim()
        val pessoasAfetadasStr = binding.etPessoasAfetadas.text.toString().trim()

        if (nomeLocal.isEmpty() || tipoEvento.isEmpty() || grauImpacto.isEmpty() || dataEvento.isEmpty() || pessoasAfetadasStr.isEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val pessoasAfetadas = pessoasAfetadasStr.toIntOrNull()
        if (pessoasAfetadas == null || pessoasAfetadas <= 0) {
            Toast.makeText(context, "Número de pessoas afetadas deve ser maior que zero!", Toast.LENGTH_SHORT).show()
            return
        }

        val novoEvento = EventoExtremo(nomeLocal, tipoEvento, grauImpacto, dataEvento, pessoasAfetadas)
        listaEventos.add(novoEvento)
        eventoExtremoAdapter.notifyItemInserted(listaEventos.size - 1)

        // Limpar campos
        binding.etNomeLocal.text.clear()
        binding.etTipoEvento.text.clear()
        binding.etGrauImpacto.text.clear()
        binding.etDataEvento.text.clear()
        binding.etPessoasAfetadas.text.clear()

        Toast.makeText(context, "Evento adicionado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

