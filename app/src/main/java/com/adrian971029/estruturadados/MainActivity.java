package com.adrian971029.estruturadados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.adrian971029.estruturadados.adapter.ProcessoAdapter;
import com.adrian971029.estruturadados.db.dto.DtoProcesso;
import com.adrian971029.estruturadados.view_model.ProcessoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static final String DATA_NOME_PROCESSO        = "nomeProcesso";
    public static final String DATA_NUM_ENTRADAS         = "numEntradas";
    public static final String DATA_TEMPO_TOTAL_EXEC     = "tempoTotalExec";
    public static final String DATA_TEMPO_EXEC_PROCESSO  = "tempoExecProcesso";
    public static final String DATA_TEMPO_TOTAL_ESPERA   = "tempoTotalEspera";
    public static final String DATA_TEMPO_TOTAL_RESPOSTA = "tempoTotalResposta";
    public static final String DATA_ARVORE_UTILIZADA     = "arvoreUtilizada";

    private ProcessoViewModel processoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ProcessoAdapter adapter = new ProcessoAdapter();
        recyclerView.setAdapter(adapter);

        processoViewModel = ViewModelProviders.of(this).get(ProcessoViewModel.class);
        processoViewModel.getAllNotes().observe(this, new Observer<List<DtoProcesso>>() {
            @Override
            public void onChanged(List<DtoProcesso> processos) {
                //Actualiza nuestro RecyclerView
                adapter.submitList(processos);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                processoViewModel.delete(adapter.getProcessoAt(viewHolder.getAdapterPosition()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    showMessage("Sucesso!","Processo iliminado com sucesso", getColor(R.color.colorPrimary));
                }
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ProcessoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DtoProcesso processo) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra(DATA_NOME_PROCESSO, processo.getNomeProcesso());
                intent.putExtra(DATA_NUM_ENTRADAS, processo.getNumTotalEntradas());
                intent.putExtra(DATA_TEMPO_TOTAL_EXEC, processo.getTempoTotalExecucao());
                intent.putExtra(DATA_TEMPO_EXEC_PROCESSO, processo.getTempoExecucaoProcesso());
                intent.putExtra(DATA_TEMPO_TOTAL_ESPERA, processo.getTempoTotalEspera());
                intent.putExtra(DATA_TEMPO_TOTAL_RESPOSTA, processo.getTempoTotalResposta());
                intent.putExtra(DATA_ARVORE_UTILIZADA, processo.isRedBlackTree());
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.button_add_processos)
    void onActionAddButtonProcessos() {
        Intent intent = new Intent(this, AddProcessoActivity.class);
        startActivity(intent);
    }

}
