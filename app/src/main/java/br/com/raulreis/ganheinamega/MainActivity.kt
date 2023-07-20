package br.com.raulreis.ganheinamega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buscar referência dos objetos
        val edtText = findViewById<EditText>(R.id.edtNumber)
        val txvResult = findViewById<TextView>(R.id.txvResult)
        val btnGenerate = findViewById<Button>(R.id.btnGenerate)

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