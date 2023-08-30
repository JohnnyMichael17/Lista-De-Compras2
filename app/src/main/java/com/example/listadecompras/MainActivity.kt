package com.example.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.listadecompras.Utils.Companion.produtosGlobal
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ProdutoAdapter(this)
        val list_view_produtos = findViewById<ListView>(R.id.list_view_produtos)

        list_view_produtos.adapter = produtosAdapter

        list_view_produtos.setOnItemLongClickListener { adapterView, view, position, id ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            true
        }

        findViewById<Button>(R.id.btn_adicionar).setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = findViewById<ListView>(R.id.list_view_produtos).adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        val	soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        val	f =	NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        findViewById<TextView>(R.id.txt_total).text = "TOTAL: ${f.format(soma)}"
    }
}
