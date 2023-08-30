package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.listadecompras.Utils.Companion.produtosGlobal

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 101
    var	imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val txt_produto = findViewById<EditText>(R.id.txt_produto)
        val txt_qtd = findViewById<EditText>(R.id.txt_qtd)
        val txt_valor = findViewById<EditText>(R.id.txt_valor)
        val btn_inserir = findViewById<Button>(R.id.btn_inserir)
        val img_foto_produto = findViewById<ImageView>(R.id.img_foto_produto)

        img_foto_produto.setOnClickListener	{
            abrirGaleria()
        }

        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {

                val prod = Produto(produto, qtd.toInt(), valor.toDouble(),imageBitMap)
                produtosGlobal.add(prod)

                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            }
        }
    }


    fun	abrirGaleria(){
        val	intent	=	Intent(Intent.ACTION_GET_CONTENT)
        intent.type	=	"image/*"
        startActivityForResult(Intent.createChooser(intent,	"Selecione uma	imagem"),	COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode:	Int, data:	Intent?){
        super.onActivityResult(requestCode,	resultCode,	data)
        if	(requestCode	==	COD_IMAGE	&&	resultCode	==	Activity.RESULT_OK)	{
            if	(data	!=	null)	{

                val	inputStream	= contentResolver.openInputStream(data.getData()!!)
                imageBitMap	= BitmapFactory.decodeStream(inputStream)
                findViewById<ImageView>(R.id.img_foto_produto).setImageBitmap(imageBitMap)

                }
            }
        }

}