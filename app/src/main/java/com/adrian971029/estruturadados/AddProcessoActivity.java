package com.adrian971029.estruturadados;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_processo);

        ButterKnife.bind(this);

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
            showMessage("Sucesso!!!", "Processo criado com sucesso.", Color.GREEN);
        }
    }

}
