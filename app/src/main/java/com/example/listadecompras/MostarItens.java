package com.example.listadecompras;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);
        lvIten = (ListView) findViewById(R.id.lvItens);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        getApplicationContext(), PopupItem.class
                );
                i.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(i);


            }
        });

        lvIten.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Itens ItenSelecionado = itens.get(position);
                AlertDialog.Builder alerta =
                        new AlertDialog.Builder(MostarItens.this);
                alerta.setTitle("Excluir Anotação...");
                alerta.setMessage("Confirma a exclusão da anotação " +
                        ItenSelecionado.getTitulo() + "?");
                alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ItemDAO.excluir(ItenSelecionado.getId(), MostarItens.this);
                        carregarLista(sessionId);

                    }
                });
                alerta.setNeutralButton("Cancelar", null);
                alerta.show();

                return true;
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

