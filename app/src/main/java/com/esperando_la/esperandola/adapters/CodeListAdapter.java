package com.esperando_la.esperandola.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esperando_la.esperandola.R;
import com.esperando_la.esperandola.model.Codigos;

import java.util.Collections;
import java.util.List;

/**
 * Created by eder on 08/02/2015.
 */
public class CodeListAdapter extends  RecyclerView.Adapter<CodeListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<Codigos> codes = Collections.emptyList();

    public CodeListAdapter(Context context, List<Codigos> codes) {
        this.codes = codes;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_codes,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        System.out.println("Adios");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Codigos current = codes.get(position);
        System.out.println("Holaaaaaaaa");
        holder.descripcion.setText(current.getDescription());
        holder.id.setText(current.getId());
    }

    @Override
    public int getItemCount() {

        return codes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView descripcion;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.code_id);
            descripcion = (TextView) itemView.findViewById(R.id.code_descripcion);
        }


    }
}

