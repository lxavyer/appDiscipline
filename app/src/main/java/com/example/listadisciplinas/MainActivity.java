package com.example.listadisciplinas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Disciplina> disciplinasModulo1;
    private List<Disciplina> disciplinasModulo2;
    private List<Disciplina> disciplinasModulo3;
    private List<Disciplina> disciplinasModulo4;
    private RecyclerView recyclerView;
    private DisciplinaAdapter adapter;
    private Spinner spinnerModulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura a Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        // Inicializa o Spinner
        spinnerModulos = findViewById(R.id.spinnerModulos);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.modulos_array, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModulos.setAdapter(adapterSpinner);

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa as listas de disciplinas
        inicializaDisciplinas();

        // Configura o Spinner para alternar entre módulos
        spinnerModulos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        adapter = new DisciplinaAdapter(disciplinasModulo1, MainActivity.this);
                        break;
                    case 1:
                        adapter = new DisciplinaAdapter(disciplinasModulo2, MainActivity.this);
                        break;
                    case 2:
                        adapter = new DisciplinaAdapter(disciplinasModulo3, MainActivity.this);
                        break;
                    case 3:
                        adapter = new DisciplinaAdapter(disciplinasModulo4, MainActivity.this);
                        break;
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não fazer nada
            }
        });

        // Define o adapter inicial
        adapter = new DisciplinaAdapter(disciplinasModulo1, this);
        recyclerView.setAdapter(adapter);
    }

    private void inicializaDisciplinas() {
        disciplinasModulo1 = new ArrayList<>();
        disciplinasModulo1.add(new Disciplina("Fundamentos da Computação", "Fundamentos", false));
        disciplinasModulo1.add(new Disciplina("Introdução ao Desenvolvimento de Aplicativos Móveis", "Fundamentos", false));
        disciplinasModulo1.add(new Disciplina("Introdução à Programação", "Programacao", false));
        disciplinasModulo1.add(new Disciplina("Laboratório de Programação", "Programacao", false));
        disciplinasModulo1.add(new Disciplina("Introdução à Informática", "Fundamentos", true));

        disciplinasModulo2 = new ArrayList<>();
        disciplinasModulo2.add(new Disciplina("Programação Orientada a Objetos", "Programacao", false));
        disciplinasModulo2.add(new Disciplina("Introdução a Banco de Dados", "Fundamentos", false));
        disciplinasModulo2.add(new Disciplina("Algoritmos e Estrutura de Dados", "Programacao", false));
        disciplinasModulo2.add(new Disciplina("Laboratório de Programação e Desenvolvimento de Sistemas", "Programacao", false));
        disciplinasModulo2.add(new Disciplina("Engenharia de Softwares: Metodologia", "Fundamentos", true));

        disciplinasModulo3 = new ArrayList<>();
        disciplinasModulo3.add(new Disciplina("Tópicos Especiais em Programação Orientada a Objetos", "Programacao", false));
        disciplinasModulo3.add(new Disciplina("Tópicos Especiais em Banco de Dados", "Programacao", false));
        disciplinasModulo3.add(new Disciplina("Desenvolvimento de Apps Android", "Programacao", false));
        disciplinasModulo3.add(new Disciplina("Desenvolvimento de Apps iOS", "Programacao", false));
        disciplinasModulo3.add(new Disciplina("Engenharia de Software: Técnicas de Desenvolvimento", "Fundamentos", true));

        disciplinasModulo4 = new ArrayList<>();
        disciplinasModulo4.add(new Disciplina("Tópicos Desenvolvimento Android", "Programacao", false));
        disciplinasModulo4.add(new Disciplina("Desenvolvimento de Apps Multiplataforma", "Programacao", false));
        disciplinasModulo4.add(new Disciplina("Empreendedorismo e Orientação Profissional", "Fundamentos", false));
        disciplinasModulo4.add(new Disciplina("Práticas em Tecnologia e Sociedade", "Fundamentos", true));
        disciplinasModulo4.add(new Disciplina("Tópicos Desenvolv iOS", "Programacao", false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            double nota = data.getDoubleExtra("nota", -1);
            int modulo = spinnerModulos.getSelectedItemPosition();

            if (position != -1 && nota != -1) {
                List<Disciplina> currentDisciplinas = getCurrentDisciplinas(modulo);
                currentDisciplinas.get(position).setNota(nota);
                adapter.notifyItemChanged(position);
            }
        }
    }

    private List<Disciplina> getCurrentDisciplinas(int modulo) {
        switch (modulo) {
            case 0:
                return disciplinasModulo1;
            case 1:
                return disciplinasModulo2;
            case 2:
                return disciplinasModulo3;
            case 3:
                return disciplinasModulo4;
            default:
                return new ArrayList<>();
        }
    }
}
