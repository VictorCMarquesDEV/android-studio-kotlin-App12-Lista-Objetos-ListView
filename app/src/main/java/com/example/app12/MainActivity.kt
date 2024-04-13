package com.example.app12

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import com.example.app12.databinding.ActivityMainBinding

// App12: Lista de Objetos com ListView: adicionar, editar, limpar e eliminar

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val listaUtizador = ArrayList<Utilizador>()
        listaUtizador.add(Utilizador("admin", "1234"))
        listaUtizador.add(Utilizador("user1", "1234"))
        listaUtizador.add(Utilizador("user2", "1234"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtizador)
        binding.listViewUtilizadores.adapter = adapter

        binding.listViewUtilizadores.setOnItemClickListener { _, _, position, _ ->
            binding.editUsuario.setText(listaUtizador[position].usuario)
            binding.editSenha.setText(listaUtizador[position].senha)
            pos = position
        }

        binding.buttonInserir.setOnClickListener {
            val usuario = binding.editUsuario.text.toString().trim()
            val senha = binding.editSenha.text.toString().trim()

            if(usuario.isNotEmpty() && senha.isNotEmpty()){
                listaUtizador.add(Utilizador(usuario, senha))
                adapter.notifyDataSetChanged()
                binding.editUsuario.setText("")
                binding.editSenha.setText("")
                pos = -1
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val usuario = binding.editUsuario.text.toString().trim()
                val senha = binding.editSenha.text.toString().trim()
                if(usuario.isNotEmpty() && senha.isNotEmpty()) {
                    listaUtizador[pos].usuario = usuario
                    listaUtizador[pos].senha = senha
                    adapter.notifyDataSetChanged()
                    binding.editUsuario.setText("")
                    binding.editSenha.setText("")
                    pos = -1
                }
            }
        }

        binding.buttonLimpar.setOnClickListener {
            listaUtizador.clear()
            adapter.notifyDataSetChanged()
            binding.editUsuario.setText("")
            binding.editSenha.setText("")
            pos = -1
        }

        binding.buttonEliminar.setOnClickListener {
            if (pos >= 0) {
                listaUtizador.removeAt(pos)
                adapter.notifyDataSetChanged()
                binding.editUsuario.setText("")
                binding.editSenha.setText("")
                pos = -1
            }
        }
    }
}