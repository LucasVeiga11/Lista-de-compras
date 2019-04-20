package com.example.listadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public static final void inserir(Itens iten, Context context){

        Banco banco = new Banco (context);
        ContentValues valores = new ContentValues();

        valores.put("id_lista", iten.getId_lista());
        valores.put("titulo", iten.getTitulo() );
        valores.put("preco", iten.getPreco() );
        valores.put("quantidade", iten.getQuantidade() );
        SQLiteDatabase db = banco.getWritableDatabase();
        db.insert("itens", null, valores);
    }

    public static final List<Itens> mostrar(Context context,int idLista) {
        List<Itens> listar = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        String sql = "SELECT * FROM itens WHERE id_lista = '"+idLista +"'ORDER BY id DESC";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Itens itens = new Itens();
                itens.setId(cursor.getInt(0));
                itens.setTitulo(cursor.getString(2));
                itens.setQuantidade(cursor.getInt(4));
                itens.setPreco(cursor.getDouble(3));
                listar.add(itens);
            }while(cursor.moveToNext());
        }

        return listar;
    }

    public static final void excluir(int idItem, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("itens", "id = "+idItem, null);

    }
}
