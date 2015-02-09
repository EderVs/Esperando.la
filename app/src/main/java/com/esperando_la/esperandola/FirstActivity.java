package com.esperando_la.esperandola;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.esperando_la.esperandola.Data.SQLHelper;
import com.esperando_la.esperandola.fragments.CodeDrawerFragment;

import org.json.JSONObject;

import java.io.Serializable;


public class FirstActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment
            implements View.OnClickListener {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);
            Button Btn_Play = (Button) rootView.findViewById(R.id.Btn_Play);
            Button Btn_Codes = (Button) rootView.findViewById(R.id.Btn_Codes);

            Btn_Play.setOnClickListener(this);
            Btn_Codes.setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            int idClicked_Button = v.getId();

            switch (idClicked_Button) {
                case R.id.Btn_Play:
                    CallAPI apiRequest = new CallAPI();
                        JSONObject Respuesta;
                    try {
                        SQLHelper DB = new SQLHelper(getApplicationContext());
                        Respuesta = apiRequest.execute().get();
                        DB.InsertIntoDB(Respuesta.getString("_id"), Respuesta.getString("description"));
                        Toast.makeText(getActivity(), Respuesta.getString("description"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.Btn_Codes:
                    Intent CodigosIntent = new Intent(getActivity(), ShowCodesActivity.class);
                    startActivity(CodigosIntent);
            }
        }


    }
}


