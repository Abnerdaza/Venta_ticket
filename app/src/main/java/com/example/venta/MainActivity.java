package com.example.venta;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public  void ingresar(View v){

        EditText codigo = findViewById(R.id.text_codigo);
        String codi=codigo.getText().toString();

        if (codi.equals("1")){

            Intent intent = new Intent (this,menu_alumno.class);
            startActivityForResult(intent, 0);
            finish();

        }else if(codi.equals("2")){

            Intent intent = new Intent (this,menu_secretaria.class);
            startActivityForResult(intent, 0);
            finish();

        }

    }

    @Override
    public void onBackPressed() {

        if (contador==0){
            Toast.makeText(this,"Presione nuevamente para salir",Toast.LENGTH_SHORT).show();
            contador++;
        }else{
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){


            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador=0;
            }
        }.start();


    }



}
