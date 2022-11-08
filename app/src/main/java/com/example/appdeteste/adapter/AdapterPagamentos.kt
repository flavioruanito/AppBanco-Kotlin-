package com.example.appdeteste.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appdeteste.databinding.PagamentoItemBinding
import com.example.appdeteste.model.Pagamento

class AdapterPagamentos (private val context: Context,private val lisPagamentos:MutableList<Pagamento>):
    RecyclerView.Adapter<AdapterPagamentos.PagamentoViweHolder>() {


// Criando lista onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagamentoViweHolder {
        val itemLista = PagamentoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PagamentoViweHolder(itemLista)
    }

    // Mostrando a Lista na tela (icone e texto) onBindViewHolder
    override fun onBindViewHolder(holder: PagamentoViweHolder, position: Int) {
        holder.iconePag.setBackgroundResource(lisPagamentos[position].icone!!)
        holder.txtTituloPagamento.text = lisPagamentos[position].titulo


    }
          // Tamanho da lista
    override fun getItemCount() = lisPagamentos.size



    inner class PagamentoViweHolder(binding: PagamentoItemBinding): RecyclerView.ViewHolder(binding.root) {
     val iconePag = binding.iconePagamento
        val txtTituloPagamento = binding.tituloPagamento




    }


}