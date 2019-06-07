package com.adrian971029.estruturadados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity {

    @BindView(R.id.tvNomeProcesso)
    TextView tvNomeProcesso;
    @BindView(R.id.tvNumEntradas)
    TextView tvNumEntradas;
    @BindView(R.id.tvTempTotalExec)
    TextView tvTempoTotalExec;
    @BindView(R.id.tvTempoExecProcesso)
    TextView tvTempoExecProcesso;
    @BindView(R.id.tvTempoTotalEspera)
    TextView tvTempoTotalEspera;
    @BindView(R.id.tvTempoTotalResposta)
    TextView tvTempoTotalResposta;
    @BindView(R.id.tvArvoreUtilizada)
    TextView tvArvoreUtilizada;

    private String nomeProcesso;
    private int numEntradas;
    private double tempoTotalExecucao;
    private double tempoExecucaoProcesso;
    private double tempoTotalEspera;
    private double tempoTotalResposta;
    private boolean isRedBlackTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            nomeProcesso = data.getString(MainActivity.DATA_NOME_PROCESSO, "");
            numEntradas = data.getInt(MainActivity.DATA_NUM_ENTRADAS, 0);
            tempoTotalExecucao = data.getDouble(MainActivity.DATA_TEMPO_TOTAL_EXEC, 0);
            tempoExecucaoProcesso = data.getDouble(MainActivity.DATA_TEMPO_EXEC_PROCESSO, 0);
            tempoTotalEspera = data.getDouble(MainActivity.DATA_TEMPO_TOTAL_ESPERA, 0);
            tempoTotalResposta = data.getDouble(MainActivity.DATA_TEMPO_TOTAL_RESPOSTA, 0);
            isRedBlackTree = data.getBoolean(MainActivity.DATA_ARVORE_UTILIZADA, true);
        }

        atualizaCampos();

    }

    @OnClick(R.id.btnVoltar)
    void onActionVoltar() {
        finish();
    }

    private void atualizaCampos() {
        tvNomeProcesso.setText(nomeProcesso);
        tvNumEntradas.setText("" + numEntradas);
        tvTempoTotalExec.setText("" + tempoTotalExecucao + " nanosegundos");
        tvTempoExecProcesso.setText("" + tempoExecucaoProcesso + " nanosegundos");
        tvTempoTotalEspera.setText("" + tempoTotalEspera + " nanosegundos");
        tvTempoTotalResposta.setText("" + tempoTotalResposta + " nanosegundos");
        if (isRedBlackTree) {
            tvArvoreUtilizada.setText("Red Black Tree");
        } else {
            tvArvoreUtilizada.setText("Heap");
        }
    }

}
