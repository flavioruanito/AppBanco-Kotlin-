package com.example.appdeteste.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appdeteste.databinding.ProdutoItemBinding
import com.example.appdeteste.model.Produtos



class AdapterProdutos(
    private val context: Context,
    private val lisProdutos: MutableList<Produtos>
) :
    RecyclerView.Adapter<AdapterProdutos.ProdutosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
        val titulo = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProdutosViewHolder(titulo)
    }

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        holder.tituloProdutos.text = lisProdutos[position].texto


    }

    override fun getItemCount() = lisProdutos.size


    inner class ProdutosViewHolder(binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tituloProdutos = binding.tituloProduto
    }


}










