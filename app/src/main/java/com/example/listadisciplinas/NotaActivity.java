package com.example.listadisciplinas;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotaActivity extends AppCompatActivity {
    private Disciplina disciplina;
    private EditText notaInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarNota);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obter disciplina
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            disciplina = (Disciplina) intent.getSerializableExtra("disciplina");
            getSupportActionBar().setTitle(disciplina.getNome()); // Definir o nome da disciplina na Toolbar
        }

        final int position = intent.getIntExtra("position", -1);

        // Configurar views
        TextView nomeDisciplina = findViewById(R.id.nomeDisciplinaNota);
        nomeDisciplina.setText(disciplina.getNome());

        notaInput = findViewById(R.id.notaInput);
        notaInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        Button cancelarButton = findViewById(R.id.cancelarButton);
        cancelarButton.setOnClickListener(v -> finish());

        Button concluirButton = findViewById(R.id.concluirButton);
        concluirButton.setOnClickListener(v -> {
            double nota = Double.parseDouble(notaInput.getText().toString());
            disciplina.setNota(nota);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", getIntent().getIntExtra("position", -1));
            resultIntent.putExtra("nota", nota);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}