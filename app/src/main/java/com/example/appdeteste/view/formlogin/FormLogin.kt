package com.example.appdeteste.view.formlogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdeteste.Tela_Principal
import com.example.appdeteste.databinding.ActivityFormLoginBinding
import com.example.appdeteste.view.forrmcadastro.Formcadastro
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormLogin : AppCompatActivity() {

    private lateinit var binding:ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()

        }

        binding.buttonEntrar.setOnClickListener { view ->

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {

                val snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener() { autenticacao ->
                        if (autenticacao.isSuccessful) {
                            navegarTelaPrincipal()

                        }


                    }.addOnFailureListener {


                        val snackbar = Snackbar.make(view, "Erro ao cadastrar usuário", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()

                    }
            }

        }
        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, Formcadastro::class.java)
            startActivity(intent)

        }

    }
    private fun navegarTelaPrincipal() {
        val intent = Intent(this,Tela_Principal::class.java)
        startActivity(intent)
        finish()

    }

    override fun onStart() {
        super.onStart()

         //Validando se usuário está logado(autenticado)
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
           navegarTelaPrincipal()

        }
    }

}






