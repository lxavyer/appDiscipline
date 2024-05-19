package com.example.listadisciplinas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {
    private List<Disciplina> disciplinas;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> disciplinas, Context context) {
        this.disciplinas = disciplinas;
        this.context = context;
    }

    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_disciplina, parent, false);
        return new DisciplinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = disciplinas.get(position);

        holder.nomeDisciplina.setText(disciplina.getNome());
        if (disciplina.isNotaInserida()) {
            holder.notaDisciplina.setText(String.valueOf(disciplina.getNota()));
            holder.statusDisciplina.setImageResource(disciplina.getNota() >= 60 ? R.drawable.ic_check : R.drawable.ic_x);
            holder.layoutNota.setVisibility(View.VISIBLE);
        } else {
            holder.layoutNota.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (disciplina.isNotaInserida()) {
                new AlertDialog.Builder(context)
                        .setTitle(disciplina.getNome())
                        .setMessage(context.getString(R.string.categoria) + ": " + disciplina.getCategoria() + "\n" +
                                context.getString(R.string.tipo) + ": " + (disciplina.isEad() ? "EaD" : "Presencial"))
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                Intent intent = new Intent(context, NotaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("disciplina", disciplina);
                bundle.putInt("position", position); // Passa a posição da disciplina
                intent.putExtras(bundle);
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeDisciplina, notaDisciplina;
        ImageView statusDisciplina;
        View layoutNota;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeDisciplina = itemView.findViewById(R.id.nomeDisciplina);
            notaDisciplina = itemView.findViewById(R.id.notaDisciplina);
            statusDisciplina = itemView.findViewById(R.id.statusDisciplina);
            layoutNota = itemView.findViewById(R.id.layoutNota);
        }
    }
}
