package br.com.raulreis.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Buscar referência dos objetos
        val edtText = findViewById<EditText>(R.id.edtNumber)
        val txvResult = findViewById<TextView>(R.id.txvResult)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)

        // Obtendo dados locais
        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val result = prefs.getString("result", null)

        // if --> let
        /*
        if (result != null)
            txvResult.text = "Ultima aposta: $result"

         */

        result?.let {
            txvResult.text = "Ultima aposta: $it"
        }

        // Opção 1: Evento de touch por XML

        // Opção 2: variavel que seja do tipo View.OnClickListener (interface)

        // btnGenerate.setOnClickListener(buttonClickListeber)

        // !Opção 3: Mais simples: bloco de código que será disparado pleo onClick Listener

        btnGenerate.setOnClickListener {

            val text = edtText.text.toString()

            numberGenerator(text, txvResult)
        }
    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        // Validar quando o campo é vazio

        if (text.isEmpty()) {
            Toast.makeText(this, R.string.number_hint, Toast.LENGTH_LONG).show()
            return
        }

        val qtd = text.toInt()

        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, R.string.number_hint, Toast.LENGTH_LONG).show()
            return
        }

        val numbers = mutableSetOf<Int>()
        val random = Random()

        while (true) {
            val number = random.nextInt(60) // 0 .. 59
            numbers.add(number + 1)

            if (numbers.size == qtd)
                break
        }

        txtResult.text = numbers.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("result", txtResult.text.toString())
        // editor.commit() --> Salvar de forma síncrona (bloquear a interface) e
        // retorna um booleano dizendo que foi gravado com sucesso ou não (dados simples)
        // editot.apply() --> Salvar de forma asssíncrona (não bloqueia a interface) e
        // mas não informa se foi gravado com sucesso ou não
        editor.apply() // assimcrona

        // Alternativa: Escrita resumida, quando estamos agindo várias vezes sobre o mesmo objeto
        /*
        prefs.edit().apply {
            putString("result, txtResult.text.toString())
            apply()
        }
         */


        // validar se o campo informado é entre 6 e 15
    }

    //val buttonClickListeber = object :View.OnClickListener {
    //  override fun onClick(p0: View?) {
    //Ação
    //}
    //}

//    fun buttonClicked(view: View) {
    //Ação
//    }
}