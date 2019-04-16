package com.example.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class FormularioActivity extends AppCompatActivity {
    private EditText etTitulo;
    private TextView textView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        int sessionId= getIntent().getIntExtra("EXTRA_SESSION_ID",0);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        EditText etTitulo = (EditText)findViewById(R.id.etTitulo);

        etTitulo.setText(String.valueOf(sessionId));

    }
}
