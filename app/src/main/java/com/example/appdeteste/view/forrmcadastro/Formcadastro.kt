package com.example.appdeteste.view.forrmcadastro

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appdeteste.R
import com.example.appdeteste.Tela_Principal
import com.example.appdeteste.databinding.ActivityFormcadastroBinding
import com.example.appdeteste.view.formlogin.FormLogin
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Formcadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormcadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormcadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Limpando nomenclatura do Layout
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.buttoSave.setOnClickListener { view ->

            // Recuperando dados inseridos e convertendo em texto
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()


            // Fazendo validação (isEmpty vazia)
            if (email.isEmpty() || senha.isEmpty()) {

                val snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()

            } else {
                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { cadastro ->
                        if (cadastro.isSuccessful) {

                            val snackbar = Snackbar.make(
                                view,
                                "Cadastro realizado com sucesso!",
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.setBackgroundTint(Color.GREEN)
                            snackbar.show()

                        }
                        // Tratando mensagens de erro:
                        // e-mail que ja existente
                        // senha com menos de 6 caracter
                        // e-mail incoerente
                        // usuário sem internet
                        // Qualquer error generico

                    }.addOnFailureListener { exception ->
                        val msgErro = when (exception) {
                            is FirebaseAuthUserCollisionException -> "E-mail ja dacastrado"
                            is FirebaseAuthWeakPasswordException -> "Digite uma senha com no minimo 6 caracteres"
                            is FirebaseAuthInvalidCredentialsException -> "Digite um e-mail válido!"
                            is FirebaseNetworkException -> "Sem conexão com a internet!"
                            else -> "Erro ao cadastrar usuário"
                        }
                        val snackbar = Snackbar.make(view, msgErro, Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }


            }

        }


    }


}