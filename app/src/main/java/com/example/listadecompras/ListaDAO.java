package com.example.listadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO {

    public static final void inserir(Lista lista, Context context){
        Banco banco = new Banco(context);
        ContentValues valores = new ContentValues();
        valores.put("titulo", lista.getTitulo() );
        SQLiteDatabase db = banco.getWritableDatabase();
        db.insert("listas", null, valores);
    }

    public static final void excluir(int idLista, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("listas", "id = "+idLista, null);
        db.delete("itens","id_lista = "+idLista, null);
    }


        public static final List<Lista> mostrar(Context context) {
        List<Lista> listar = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        String sql = "SELECT * FROM listas ORDER BY id DESC";
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    Lista lista = new Lista();
                    lista.setId(cursor.getInt(0));
                    lista.setTitulo(cursor.getString(1));
                    listar.add(lista);
                }while(cursor.moveToNext());
            }

        return listar;
        }

}
