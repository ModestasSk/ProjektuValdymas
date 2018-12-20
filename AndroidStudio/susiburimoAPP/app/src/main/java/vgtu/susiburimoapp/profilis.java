package vgtu.susiburimoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class profilis extends AppCompatActivity {
    private String vartotojo_numeris = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilis);
        vartotojo_numeris = this.getIntent().getStringExtra("vartotojo_numeris");
       // Toast.makeText(profilis.this,vartotojo_numeris,Toast.LENGTH_SHORT).show();
        profilis.GautiDuomenis duom = new profilis.GautiDuomenis();
        duom.execute(vartotojo_numeris);
    }
    private class GautiDuomenis extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... nr) {
            String rezultatas = "";
            try {
                HttpURLConnection s = (HttpURLConnection) new URL("http://localhost:8080/susiburimoAPI/kontroleris/vartotojas="+vartotojo_numeris+".html").openConnection();
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
                JSONObject json = new JSONObject(result);
                final ArrayList<String> list = new ArrayList<String>();
                final ArrayList<String> ids = new ArrayList<String>();
                final ArrayList<String> list2 = new ArrayList<String>();
                final ArrayList<String> ids2 = new ArrayList<String>();
                JSONObject c = json;
                String vardas = c.getString("vardas");
                String pavarde = c.getString("pavarde");
                String elpastas = c.getString("elpastas");
                String amzius = c.getString("amzius");
                String lytis = c.getString("lytis");
                JSONArray a = c.getJSONArray("renginiai");

                //Toast.makeText(Uzduotis.this,spavadinimas,Toast.LENGTH_SHORT).show();
                TextView tVardas = (TextView)findViewById(R.id.l_vardas);
                tVardas.setText(vardas);
                TextView tPavarde = (TextView)findViewById(R.id.l_pavarde);
                tPavarde.setText(pavarde);
                TextView tMetai = (TextView)findViewById(R.id.l_metai);
                tMetai.setText(amzius + ", "+lytis);
                TextView tPastas = (TextView)findViewById(R.id.l_pastas);
                tPastas.setText(elpastas);
                for (int i = 0; i < a.length(); i++) {
                        JSONObject obj = a.getJSONObject(i);
                        String rStatusas = obj.getString("statusas");

                        String rPavadinimas = obj.getString("pavadinimas");
                        String rData = obj.getString("data");
                        String rKodas = obj.getString("kodas");
                        String bendras = rPavadinimas + " [" + rData + "]";
                    if(rStatusas.equals("vyksta")) {
                        list.add(bendras);
                        ids.add(rKodas);
                    }
                    if(rStatusas.equals("baigta")) {
                        list2.add(bendras);
                        ids2.add(rKodas);
                    }
                }

                ListView w = (ListView) findViewById(R.id.l_sarasas);
                ArrayAdapter adapter = new ArrayAdapter(profilis.this, android.R.layout.simple_list_item_1, list);
                w.setAdapter(adapter);

                w.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String kodas = ids.get(position);
                        prisijungimas(getWindow().getDecorView().findViewById(android.R.id.content), kodas);
                    }
                });
                ListView w2 = (ListView) findViewById(R.id.l_sarasas2);
                ArrayAdapter adapter2 = new ArrayAdapter(profilis.this, android.R.layout.simple_list_item_1, list2);
                w2.setAdapter(adapter2);

                w2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String kodas = ids.get(position);
                        prisijungimas(getWindow().getDecorView().findViewById(android.R.id.content), kodas);

                    }
                });

            }catch (Exception e) {
            }

        }
    }
    public void prisijungimas(View view, String renginys) {

        Intent naujas = new Intent(view.getContext(),renginioInfo.class);
        naujas.putExtra("vartotojo_numeris", vartotojo_numeris);
        naujas.putExtra("renginio_numeris", renginys);
        startActivity(naujas);
    }
}
