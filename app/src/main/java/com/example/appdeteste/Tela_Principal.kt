package com.example.appdeteste
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdeteste.adapter.AdapterPagamentos
import com.example.appdeteste.adapter.AdapterProdutos
import com.example.appdeteste.databinding.ActivityTelaPrincipalBinding
import com.example.appdeteste.model.Pagamento
import com.example.appdeteste.model.Produtos
import com.example.appdeteste.view.formlogin.FormLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Tela_Principal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapterPagamentos: AdapterPagamentos
    private val listPagamento: MutableList<Pagamento> = mutableListOf()
    private lateinit var adapterProdutos: AdapterProdutos
    private val listProdutos: MutableList<Produtos> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)




            if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val recycleIcones = binding.recyclePagamento
        recycleIcones.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleIcones.setHasFixedSize(true)
        adapterPagamentos = AdapterPagamentos(this, listPagamento)
        recycleIcones.adapter = adapterPagamentos
        ListaIconesPagamentos()


        val recycleProdutos = binding.recycleProdutos
        recycleProdutos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleProdutos.setHasFixedSize(true)
        adapterProdutos = AdapterProdutos(this, listProdutos)
        recycleProdutos.adapter = adapterProdutos
        listTitulos1()


        // Deslogando e voltado para a tela login
        binding.deslog.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()

        }



        //Passando os paramentros e salvando no banco (FirebaseFirestore)
        //binding.btGravaBD.setOnClickListener {

        val usuariosMap = hashMapOf(
            "nome" to "Maria",
            "sobrenome" to "Mota Silva",
            "idade" to 23
        )

        db.collection("Usuários").document("Maria")
            .set(usuariosMap).addOnCompleteListener {
                Log.d("db", "Usuário salvo com sucesso!")

            }.addOnFailureListener {

            }

    }

    private fun ListaIconesPagamentos() {
        val icone1 = Pagamento(R.drawable.ic_pix, "Área PIX")
        listPagamento.add(icone1)

        val icone2 = Pagamento(R.drawable.emprestimo, "Empréstimos")
        listPagamento.add(icone2)

        val icone3 = Pagamento(R.drawable.ic_doar, "Doação de valor")
        listPagamento.add(icone3)

        val icone4 = Pagamento(R.drawable.depositar, "Depositar")
        listPagamento.add(icone4)

        val icone5 = Pagamento(R.drawable.transferencia, "transferência")
        listPagamento.add(icone5)


    }


        private fun listTitulos1() {
        val titulo1 = Produtos("Cresça junto com o Android bank! ")
        listProdutos.add(titulo1)

        val titulo2 = Produtos("Acreditamos em vc, fidelize e venha com agente! ")
        listProdutos.add(titulo2)

        val titulo3 = Produtos("As melhores ofertas na palma da mão! ")
        listProdutos.add(titulo3)

        val titulo4 = Produtos("Juros que cabe no seu bolso e orçamento!")
        listProdutos.add(titulo4)

        val titulo5 = Produtos("Se conecte com o mundo de soluções!")
        listProdutos.add(titulo5)


    }





}


//
//         binding.btAtualizarBD.setOnClickListener {
//            db.collection("Usuários").document("Maria")
//               .update("sobrenome","Ruanito","idade",30).addOnCompleteListener {
//               Log.d("db_update", "Sucesso ao atualizar os dados do usuário!")
//               }
//       }

//   }


// }
//}


//}









