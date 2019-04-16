package com.example.listadecompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterItens extends BaseAdapter {

    private Context context;
    private List<Itens> itens;
    private LayoutInflater inflater;

    public AdapterItens(Context context, List<Itens> itens){
        this.context = context;
        this.itens = itens;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Suporte item;

        if( convertView == null ){
            convertView = inflater.inflate
                    (R.layout.listar_itens, null);
            item = new Suporte();
            item.tvCodigo = (TextView)
                    convertView.findViewById(R.id.tvItemCodigo);
            item.tvTitulo = (TextView)
                    convertView.findViewById(R.id.tvItemTitulo);
            item.tvQuantidade = (TextView)
                    convertView.findViewById(R.id.tvQuantidade);
            item.tvPreco = (TextView)
                    convertView.findViewById(R.id.tvPreco);

            convertView.setTag(item);
        }else {
            item = (Suporte) convertView.getTag();
        }

        Itens nota = itens.get( position );
        item.tvCodigo.setText( String.valueOf( nota.getId() ) );
        item.tvTitulo.setText(  nota.getTitulo()  );
        item.tvQuantidade.setText(String.valueOf(nota.getQuantidade()));
        item.tvPreco.setText(String.valueOf(nota.getPreco()));



        return convertView;
    }
    private class Suporte{
        TextView tvCodigo, tvTitulo,tvQuantidade,tvPreco;
    }
}
