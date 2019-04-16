package com.example.listadecompras;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class MostarItens extends AppCompatActivity {
    ListView lvIten;
    List<Itens> itens;
    AdapterItens adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostraritenss);
        final int sessionId= getIntent().getIntExtra("EXTRA_SESSION_ID",0);
        lvIten = (ListView) findViewById(R.id.lvLista);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            salvar(sessionId);

            }
        });

    }

    private void salvar(int idlista){
        Itens nota = new Itens();
        nota.setTitulo( "teste item" );
        nota.setId_lista(idlista);
        nota.setPreco(5);
        nota.setQuantidade(2);
        ItemDAO.inserir(nota, this);
        this.finish();
    }
    private void carregarLista(int idlista){

        itens = ItemDAO.mostrar(this,idlista);
//        adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, lista );
        adapter = new AdapterItens(this, itens);
        lvIten.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        final int sessionId= getIntent().getIntExtra("EXTRA_SESSION_ID",0);
        carregarLista(sessionId);
    }
}
