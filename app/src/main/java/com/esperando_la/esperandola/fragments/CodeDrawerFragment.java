package com.esperando_la.esperandola.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esperando_la.esperandola.Data.SQLHelper;
import com.esperando_la.esperandola.R;
import com.esperando_la.esperandola.adapters.CodeListAdapter;
import com.esperando_la.esperandola.model.Codigos;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eder on 08/02/2015.
 */
public class CodeDrawerFragment extends Fragment {
    private RecyclerView recyclerview;

    private CodeListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_codes, container, false);

        recyclerview = (RecyclerView) rootView.findViewById(R.id.codes_list);
        adapter = new CodeListAdapter(getActivity(), getData());
        System.out.println(adapter);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return rootView;
    }

    public List<Codigos> getData() {
        List<Codigos> codes = new ArrayList<>();

        SQLHelper Helper = new SQLHelper(getActivity());
        Cursor cr = Helper.SelectAll();

        while(cr.isAfterLast() == false)
        {
            Codigos current = new Codigos();
            current.setId(cr.getString(0));
            current.setDescription(cr.getString(2));
            codes.add(current);

            cr.moveToNext();
        }

        System.out.println(codes);
        return codes;
    }
}
