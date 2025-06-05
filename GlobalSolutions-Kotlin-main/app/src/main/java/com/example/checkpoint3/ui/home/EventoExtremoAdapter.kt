package com.example.checkpoint3.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpoint3.R
import com.example.checkpoint3.ui.model.EventoExtremo

class EventoExtremoAdapter(private val eventos: MutableList<EventoExtremo>, private val onDeleteClick: (EventoExtremo) -> Unit) : RecyclerView.Adapter<EventoExtremoAdapter.EventoExtremoViewHolder>() {

    class EventoExtremoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeLocal: TextView = itemView.findViewById(R.id.tvNomeLocal)
        val tvTipoEvento: TextView = itemView.findViewById(R.id.tvTipoEvento)
        val tvGrauImpacto: TextView = itemView.findViewById(R.id.tvGrauImpacto)
        val tvDataEvento: TextView = itemView.findViewById(R.id.tvDataEvento)
        val tvPessoasAfetadas: TextView = itemView.findViewById(R.id.tvPessoasAfetadas)
        val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoExtremoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento_extremo, parent, false)
        return EventoExtremoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoExtremoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.tvNomeLocal.text = "Local: ${evento.nomeLocal}"
        holder.tvTipoEvento.text = "Tipo: ${evento.tipoEvento}"
        holder.tvGrauImpacto.text = "Impacto: ${evento.grauImpacto}"
        holder.tvDataEvento.text = "Data: ${evento.dataEvento}"
        holder.tvPessoasAfetadas.text = "Pessoas Afetadas: ${evento.pessoasAfetadas}"
        holder.btnExcluir.setOnClickListener { onDeleteClick(evento) }
    }

    override fun getItemCount(): Int = eventos.size

    fun removerEvento(evento: EventoExtremo) {
        val position = eventos.indexOf(evento)
        if (position != -1) {
            eventos.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}

