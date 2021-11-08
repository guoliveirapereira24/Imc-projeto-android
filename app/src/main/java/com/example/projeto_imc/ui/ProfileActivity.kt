package com.example.projeto_imc.ui

import android.app.DatePickerDialog
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.projeto_imc.R
import com.example.projeto_imc.model.Usuario
import com.example.projeto_imc.utils.convertStringToLocalDate
import kotlinx.android.synthetic.main.activity_profile.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ProfileActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var radioF : RadioButton
    lateinit var  radioM : RadioButton
    lateinit var radioAnother : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        editEmail = findViewById(R.id.et_email)
        editSenha = findViewById(R.id.et_senha)

        editNome = findViewById(R.id.et_nome)
        editProfissao = findViewById(R.id.et_profissao)
        editAltura = findViewById(R.id.et_altura)
        editDataNascimento = findViewById(R.id.et_data)
        radioF = findViewById<RadioButton>(R.id.radioF)
        radioM = findViewById<RadioButton>(R.id.radioM)
        radioAnother = findViewById<RadioButton>(R.id.radioAnother)

        supportActionBar!!.title = "Novo usuário"

        //Criar um calendário
        val calendario = Calendar.getInstance()

        // Determinar os dados (dia, mês e ano)
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker
        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                    etDataNascimento.setText("$_dia/${_mes + 1}/$_ano")
                }, ano, mes, dia
            )
            dp.show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    val min_value = 8

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()){

            val nascimento = convertStringToLocalDate(editDataNascimento.text.toString())

            //Criar o objeto usuario
                val usuario = Usuario(
                    1,
                    editNome.text.toString(),
                    editEmail.text.toString(),
                    editSenha.text.toString(),
                    0.0,
                    editAltura.text.toString().toDouble(),
                    LocalDate.of(
                        nascimento.year,
                        nascimento.monthValue,
                        nascimento.dayOfMonth

                    ),
                    editProfissao.text.toString(),
                    if (radioF.isChecked) 'F' else 'M'







                )

            // Salvar o registro
            // Em um SharedPreference

            //A instrução abaixo irá criar um
            //arquivo sharedPreferences se não existir
            //Se existir ele será aberto para edição

            val dados = getSharedPreferences(
                "usuario", Context.MODE_PRIVATE)

            // Vamos criar um objeto que permitirá a
            // edição dos dados do arquivo SharedPreferences
            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putFloat("peso", usuario.peso.toFloat())
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.apply()
            
        }
        Toast.makeText(this, "Usuário Cadastrado!!", Toast.LENGTH_SHORT).show()
        
        return true
    }


    fun validarCampos(): Boolean {

        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "O e-mail é obrigatório!"
        }
        if (editSenha.text.isEmpty()) {
            editSenha.error = "É obrigatório ter uma senha!"
        }
        if (editNome.text.isEmpty()){
            editNome.error = "É obrigatório colocar um nome!"
        }
        if (editProfissao.text.isEmpty()){
            editProfissao.error = "É obrigatório inserir alguma profissão!"
        }
        if (editAltura.text.isEmpty()){
            editAltura.error = "É obrigatório inserir uma altura!"
        }
        if (editDataNascimento.text.isEmpty()){
            editDataNascimento.error = "É obrigatório inserir uma data de nascimento!"
        }

        return true


    }


}



