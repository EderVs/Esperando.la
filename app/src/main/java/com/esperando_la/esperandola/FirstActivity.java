package com.esperando_la.esperandola;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class FirstActivity extends ActionBarActivity implements Serializable{

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
                        Respuesta = apiRequest.execute().get();
                        String Ser_res = Serializa(Respuesta);
                        System.out.println(Ser_res);
                        Codigos codigoRes = Deserializa();
                        Toast.makeText(getActivity(), codigoRes.getDescription(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.Btn_Codes:
                    Toast.makeText(getActivity(), "¡Tus códigos!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        protected String filename = "Codigos";

        public String Serializa(JSONObject a){
            FileOutputStream fos;
            Context context = getApplicationContext();
            try {
                Codigos codigo = new Codigos(a.getString("_id"), a.getBoolean("status"), a.getString("description"));
                fos = openFileOutput(filename, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(codigo);
                oos.close();
                return "Correctamente";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "Error: archivo no encontrado";
            }
            catch (IOException e) {
                e.printStackTrace();
                return "Error";
            } catch (JSONException e) {
                e.printStackTrace();
                return "Error in JSON";
            }
        }

        public Codigos Deserializa()
        {
            FileInputStream fin;
            Codigos codigo = null;

            try
            {
                fin = openFileInput(filename);
                ObjectInputStream ois = new ObjectInputStream(fin);
                codigo = (Codigos) ois.readObject();
                ois.close();
                return codigo;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

    }
}


