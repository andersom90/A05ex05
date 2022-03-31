package com.example.a05ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var resultado = 0
    var contador = 1
    var score = 0
    lateinit var txtResultado: TextView
    lateinit var btnNovo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResultado = findViewById(R.id.txtResultado)
        btnNovo = findViewById(R.id.btnNovo)
        score = getPreferences(MODE_PRIVATE).getInt("SCORE", 0)
        contador = getPreferences(MODE_PRIVATE).getInt("CONTADOR", 1)
        jogar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPreferences(MODE_PRIVATE).edit().putInt("CONTADOR", contador).commit()
    }

    fun jogar(){
        txtResultado.text = "Par ou Ímpar??"
        resultado = Random.nextInt(0,10)
        btnNovo.visibility = View.INVISIBLE
    }

    fun novoJogo(view: View){
        contador++
//        getPreferences(MODE_PRIVATE).edit().putInt("CONTADOR", contador).commit()
        jogar()
    }

    fun jogada(view: View){
        // verificando se o botão clicado corresponde ao valor sorteado
        if (resultado % 2 == view.tag.toString().toInt())
            if (btnNovo.visibility == View.INVISIBLE) { // se o botão estiver ativo
                score++ // soma a pontuação
                getPreferences(MODE_PRIVATE).edit().putInt("SCORE", score).commit()
            }
        title = "Score: $score/$contador" // atualizando o score no título da janela
        txtResultado.text = "$resultado" // mostrando o número sorteado
        btnNovo.visibility = View.VISIBLE // habilita o botão 'btnNovo'
    }
}