package com.example.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopupItem extends AppCompatActivity {

    private EditText etNumero;
    private EditText etNome;
    private EditText etQuantidade;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_item);

        final int sessionId= getIntent().getIntExtra("EXTRA_SESSION_ID",0);

        etNumero = (EditText) findViewById(R.id.etNumero);
        etNome = (EditText) findViewById(R.id.etNome);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);


        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar(sessionId);
            }
        });
    }
    private void salvar(int sessionid){
        Itens nota = new Itens();
        nota.setTitulo( etNome.getText().toString() );
        nota.setPreco(Double.parseDouble(etNumero.getText().toString()));
        nota.setQuantidade( Integer.parseInt( etQuantidade.getText().toString() ));
        nota.setId_lista(sessionid);
        ItemDAO.inserir(nota, this);
        this.finish();
    }
}
