package com.adrian971029.estruturadados;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.adrian971029.estruturadados.core.Agendador;
import com.adrian971029.estruturadados.db.dto.DtoProcesso;
import com.adrian971029.estruturadados.model.ImplementacaoHeap;
import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.model.RedBlackTree;
import com.adrian971029.estruturadados.view_model.ProcessoViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProcessoActivity extends BaseActivity {

    @BindView(R.id.edtNomeProcesso)
    EditText edtNomeProcesso;
    @BindView(R.id.rbPequeno)
    RadioButton rbPequeno;
    @BindView(R.id.rbMedio)
    RadioButton rbMedio;
    @BindView(R.id.rbGrande)
    RadioButton rbGrande;
    @BindView(R.id.btnCancelar)
    Button btnCancelar;
    @BindView(R.id.btnCriar)
    Button btnCriar;
    @BindView(R.id.rbRedBlackTree)
    RadioButton rbRedBlackTree;
    @BindView(R.id.rbHeap)
    RadioButton rbHeap;

    public static int nEntradas;
    public static int periodo;

    private ProcessoViewModel processoViewModel;
    private RedBlackTree redBlackTree;
    private ImplementacaoHeap heap;
    private ArrayList<Processo> processArrayList = new ArrayList<Processo>();
    private Agendador agendador;
    private double[] dadosASalvar = new double[5];
    private String nomeProcesso;
    private double tempoTotalExecucao;
    private double tempoExecucaoProcesso;
    private double tempoTotalEspera;
    private double tempoTotalResposta;
    private boolean isRedBlackTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_processo);

        ButterKnife.bind(this);

        processoViewModel = new ViewModelProviders().of(this).get(ProcessoViewModel.class);
        redBlackTree = new RedBlackTree();
        heap = new ImplementacaoHeap();
        agendador = new Agendador();

    }

    @OnClick(R.id.btnCancelar)
    void onActionBtnCancelar() {
        finish();
    }

    @OnClick(R.id.btnCriar)
    void onActionBtnCriar() {
        if (edtNomeProcesso.getText().toString().equals("")) {
            showMessage("Nome do Processo Vacio", "Precisa dar um nome ao processo para criar ele.", Color.RED);
        } else {
            nEntradas = tamanhoProcesso();
            periodo = tamanhoProcesso();

            if (rbRedBlackTree.isChecked()) {
                for (int i = 0; i < nEntradas; i++) {
                    processArrayList.add(new Processo(redBlackTree, tamanhoProcesso(), tamanhoProcesso(), tamanhoProcesso()));
                    isRedBlackTree = true;
                }
                dadosASalvar = agendador.agendadorRedBlackTree(redBlackTree, periodo, nEntradas);
            } else {
                for (int i = 0; i < nEntradas; i++) {
                    processArrayList.add(new Processo(heap, tamanhoProcesso(), tamanhoProcesso(), tamanhoProcesso()));
                    isRedBlackTree = false;
                }
                dadosASalvar = agendador.agendadorHeap(heap, periodo, nEntradas);
            }

            nomeProcesso = edtNomeProcesso.getText().toString();
            tempoTotalExecucao = dadosASalvar[1];
            tempoExecucaoProcesso = dadosASalvar[2];
            tempoTotalEspera = dadosASalvar[3];
            tempoTotalResposta = dadosASalvar[4];

            DtoProcesso dtoProcesso = new DtoProcesso(nomeProcesso, nEntradas,
                    tempoTotalExecucao, tempoExecucaoProcesso, tempoTotalEspera,
                    tempoTotalResposta, isRedBlackTree);

            processoViewModel.insert(dtoProcesso);
            showMessageTelaPrincipal();
        }
    }

    private void showMessageTelaPrincipal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("Sucesso!!!");
        builder.setIcon(R.drawable.ic_success_white_24dp);
        builder.setMessage("Processo criado com sucesso.");
        builder.setCancelable(false);
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int tamanhoProcesso() {
        int tamanho = 0;
        if (rbPequeno.isChecked()) {
            tamanho = (int)(Math.random() * 1000 + 1);
        } else if (rbMedio.isChecked()) {
            tamanho = (int)(Math.random() * 10000 + 1001);
        } else if (rbGrande.isChecked()) {
            tamanho = (int)(Math.random() * 100000 + 10001);
        }
        return tamanho;
    }

}
