package com.example.iniciando_projeto_banco_dados;

import android.database.Cursor;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_consulta_dados extends AppCompatActivity {

    TextView txtnome, txttelefone, txtemail, txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    int indice;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta_dados);

        txtnome = (TextView) findViewById(R.id.txtnome);
        txttelefone = (TextView)  findViewById(R.id.txttelefone);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");

        imgprimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imganterior  = (ImageView) findViewById(R.id.imganterior);
        imgproximo = (ImageView) findViewById(R.id.imgproximo);
        imgultimo = (ImageView) findViewById(R.id.imgultimo);

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]{"nome","telefone","email"},
                    null,null,null,null,null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            indice = 1;

            txtnome.setText(c.getString(0));
            txttelefone.setText(c.getString(1));
            txtemail.setText(c.getString(2));
            txtstatus_registro.setText(indice+"/"+c.getCount());

        }
        else {
            txtstatus_registro.setText("Nenhum Registro");
        }
        imgprimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c.getCount() > 0 );
                {
                    c.moveToFirst();
                    indice = 1;
                    txtnome.setText(c.getString(0));
                    txttelefone.setText(c.getString(1));
                    txtemail.setText(c.getString(2));
                    txtstatus_registro.setText(indice+"/"+c.getCount());
                }

            }
        });
        imganterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c.getCount() > 0 );
                {
                    if (indice > 1) {

                        indice--;
                        c.moveToPrevious();

                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(2));
                        txtstatus_registro.setText(indice + "/" + c.getCount());
                    }

                }
            }
        });
        imgproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c.getCount() > 0 );
                {
                    if (indice != c.getCount()) {

                        indice++;
                        c.moveToNext();

                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(2));

                        txtstatus_registro.setText(indice +"/"+ c.getCount());
                    }
                }
            }
        });
        imgultimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c.getCount() > 0){
                    c.moveToLast();
                    indice = c.getCount();

                    txtnome.setText(c.getString(0));
                    txttelefone.setText(c.getString(1));
                    txtemail.setText(c.getString(2));
                    txtstatus_registro.setText(indice
                            +"/"+c.getCount());

                }
            }
        });
        }catch (Exception e){
            MostraMensagem("Erro:" + e.toString());
        }

    }

    public void  MostraMensagem(String str)
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity_consulta_dados.this);
        dialogo.setTitle("Aviso de Erro!").setMessage(str).setNeutralButton("ok ", null).show();
    }
    }