package com.example.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopupLista extends AppCompatActivity {
    private EditText etTitulo;
    private Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_lista);


        etTitulo = (EditText) findViewById(R.id.etTitulo);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }
    private void salvar(){
        Lista nota = new Lista();
        nota.setTitulo( etTitulo.getText().toString() );
        ListaDAO.inserir(nota, this);
        this.finish();
    }
    /*
    private void salvar(){
        Anotacao nota = new Anotacao();
        nota.setTexto( etTexto.getText().toString() );
        nota.setTitulo( etTitulo.getText().toString() );
        AnotacaoDAO.inserir(nota, this);
        this.finish();
    }
    */
}
