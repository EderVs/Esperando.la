package com.esperando_la.esperandola;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by eder on 06/02/2015.
 */
public class CallAPI extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            //Para el POST
            String url = "http://192.168.1.207:8000/codigos/newCode/10";

            HttpClient client = new DefaultHttpClient();

            HttpPost request = new HttpPost(url);

            HttpResponse response;

            //Parametros
            /*
            JSONObject parametros = new JSONObject();

            parametros.put("usuario","usuario");
            parametros.put("clave","top_secret");
            */

            JSONObject dataJson = new JSONObject();

            try
            {
                StringEntity jsonEntity = new StringEntity(dataJson.toString());

                request.addHeader("Content-Type", "application/json");

                request.setEntity(jsonEntity);

                response = client.execute(request);

                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {
                    InputStream in = response.getEntity().getContent();

                    BufferedReader buffered = new BufferedReader(new InputStreamReader(in));
                    StringBuilder fullLines = new StringBuilder();

                    String line;

                    while ((line = buffered.readLine()) != null)
                    {
                        fullLines.append(line);
                    }

                    String result = fullLines.toString();

                    JSONObject objeto = new JSONObject(result);

                    System.out.println(objeto.getString("description"));
                    return "";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return e.getMessage();
            }

            //Para un GET
            /*
            String url = "http://192.168.1.207:8000/codigos";

            HttpClient client = new DefaultHttpClient();

            // Creamos el request
            HttpGet request = new HttpGet(url);

            HttpResponse response;
            List entityResult;

            try
            {
                // Ejecutar el request
                response = client.execute(request);
                System.out.println("HOLA");
                // Obtener la respuesta
                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {
                    InputStream in = response.getEntity().getContent();

                    BufferedReader buffered = new BufferedReader(new InputStreamReader(in));
                    StringBuilder fullLines = new StringBuilder();

                    String line;

                    while ((line = buffered.readLine()) != null)
                    {
                        fullLines.append(line);
                    }

                    String result = fullLines.toString();
                    System.out.println("HOLAAAAAAAAAAAAAA");
                    return result;
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("No entro");
                return e.getMessage();
            }
        */
        return "";

        }

        protected void onPostExecute(String result) {
            System.out.println(result);
            super.onPostExecute(result);
        }

    }
