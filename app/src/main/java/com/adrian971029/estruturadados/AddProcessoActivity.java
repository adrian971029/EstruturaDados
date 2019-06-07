package com.adrian971029.estruturadados;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.view_model.ProcessoViewModel;

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

    private ProcessoViewModel processoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_processo);

        ButterKnife.bind(this);

        processoViewModel = new ViewModelProviders().of(this).get(ProcessoViewModel.class);

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

}
