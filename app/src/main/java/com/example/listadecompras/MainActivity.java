package com.example.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvLista;
    List<Lista> lista;
    AdapterLista adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);

        lvLista = (ListView) findViewById(R.id.lvLista);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopupLista.class);
                startActivity(i);
            }
        });
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

*/
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Lista notaSelecionada = lista.get(position);
                Intent intent = new Intent(

                          MainActivity.this, MostarItens.class);
                intent.putExtra("EXTRA_SESSION_ID", notaSelecionada.getId());
                         startActivity(intent);
            }
            }
        );

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(

                      //  MainActivity.this, FormularioActivity.class);
               // startActivity(intent);
            }
        });

*/

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final Lista notaSelecionada = lista.get(position);
                AlertDialog.Builder alerta =
                        new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Excluir Anotação...");
                alerta.setMessage("Confirma a exclusão da anotação " +
                        notaSelecionada.getTitulo() + "?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListaDAO.excluir(notaSelecionada.getId(),
                                MainActivity.this);
                        carregarLista();

                        //     lista.remove( position);
                        //     adapter.notifyDataSetChanged();

                    }
                });
                alerta.setNeutralButton("Cancelar", null);
                alerta.show();



                return true;
            }
        });

    }

    private void salvar(){
        Lista nota = new Lista();
        nota.setTitulo( "teste" );
        ListaDAO.inserir(nota, this);
        //this.finish();
    }
    private void carregarLista(){

        lista = ListaDAO.mostrar(this);
//        adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, lista );
        adapter = new AdapterLista(this, lista);
        lvLista.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

}
