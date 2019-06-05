package com.adrian971029.estruturadados;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tapadoo.alerter.Alerter;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showMessage(String titulo, String msg, int corDeFundo) {
        Alerter.create(this).setTitle(titulo)
                .setBackgroundColorInt(corDeFundo)
                .disableOutsideTouch()
                .setDuration(3000)
                .setText(msg)
                .show();
    }


}
