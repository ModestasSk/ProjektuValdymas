package vgtu.susiburimoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class renginiuSarasas extends AppCompatActivity {
    private String vartotojo_numeris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renginiu_sarasas);
        vartotojo_numeris = this.getIntent().getStringExtra("nr");
        //Toast.makeText(renginiuSarasas.this,vartotojo_numeris,Toast.LENGTH_SHORT).show();
        GautiDuomenis duom = new GautiDuomenis();
        duom.execute(vartotojo_numeris);

    }

    private class GautiDuomenis extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... nr) {
            String rezultatas = "";
            try {
                HttpURLConnection s = (HttpURLConnection) new URL("http://localhost:8080/susiburimoAPI/kontroleris/renginiai.html").openConnection();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String outStr;
                while ((outStr = bufRead.readLine()) != null) {
                    rezultatas += outStr;
                }
                bufRead.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rezultatas;
        }


        protected void onPostExecute(String result) {
            try {
                JSONArray json = new JSONArray(result);
                final ArrayList<String> list = new ArrayList<String>();
                final ArrayList<String> ids = new ArrayList();
                for (int i = 0; i < json.length(); i++) {
                    JSONObject c = json.getJSONObject(i);
                    String statusas = c.getString("statusas");
                    String id = c.getString("kodas");
                    String pavadinimas = c.getString("pavadinimas");
                    String data = c.getString("data");
                    String bendras = pavadinimas + " [" + data + "] ";
                    if(statusas.equals("vyksta")) {
                        list.add(bendras);
                        ids.add(id);
                    }
                }
                ListView w = (ListView) findViewById(R.id.l_sarasas);
                ArrayAdapter adapter = new ArrayAdapter(renginiuSarasas.this, android.R.layout.simple_list_item_1, list);
                w.setAdapter(adapter);

                w.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String kodas = ids.get(position);
                        prisijungimas(getWindow().getDecorView().findViewById(android.R.id.content), kodas);

                    }
                });
            } catch (Exception e) {
            }
        }

        public void prisijungimas(View view, String renginys) {

              Intent naujas = new Intent(view.getContext(),renginioInfo.class);
              naujas.putExtra("vartotojo_numeris", vartotojo_numeris);
              naujas.putExtra("renginio_numeris", renginys);
              startActivity(naujas);
        }
    }
    public void profilis(View view){
        Intent naujas = new Intent(this, profilis.class);
        naujas.putExtra("vartotojo_numeris", vartotojo_numeris);
        startActivity(naujas);
    }

}
