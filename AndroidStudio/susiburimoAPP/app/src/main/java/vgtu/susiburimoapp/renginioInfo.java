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

public class renginioInfo extends AppCompatActivity {
    private String vartotojo_numeris = "";
    private String renginio_numeris = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renginio_info);
        vartotojo_numeris = this.getIntent().getStringExtra("vartotojo_numeris");
        renginio_numeris = this.getIntent().getStringExtra("renginio_numeris");
       //Toast.makeText(renginioInfo.this,vartotojo_numeris,Toast.LENGTH_SHORT).show();
        renginioInfo.GautiDuomenis duom = new GautiDuomenis();
        duom.execute(vartotojo_numeris);
    }
    private class GautiDuomenis extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... nr) {
            String rezultatas = "";
            try {
                HttpURLConnection s = (HttpURLConnection) new URL("http://localhost:8080/susiburimoAPI/kontroleris/renginys="+renginio_numeris+".html").openConnection();
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
                final ArrayList<Integer> ids = new ArrayList<Integer>();
                JSONObject c = json;
                String pavadinimas = c.getString("pavadinimas");
                String aprasymas = c.getString("aprasymas");
                String data = c.getString("data");
                String kaina = c.getString("kaina");
                //Toast.makeText(Uzduotis.this,spavadinimas,Toast.LENGTH_SHORT).show();
                TextView tPavadinimas = (TextView)findViewById(R.id.t_pavadinimas);
                tPavadinimas.setText(pavadinimas);
                TextView tAprasymas = (TextView)findViewById(R.id.t_aprasymas);
                tAprasymas.setText(aprasymas);
                TextView tData = (TextView)findViewById(R.id.t_data);
                tData.setText("Renginio laikas- " + data);
                TextView tKaina = (TextView)findViewById(R.id.t_kaina);
                tKaina.setText("Bilieto kaina- "+ kaina + "€");

                }catch (Exception e) {
            }

        }
    }
    public void dalyvauti(View view) {
        renginioInfo.DuotiDuomenis duom = new DuotiDuomenis();
        duom.execute();
    }
    private class DuotiDuomenis extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... nr) {
            String rezultatas = "";
            try {
                HttpURLConnection s = (HttpURLConnection) new URL("http://localhost:8080/susiburimoAPI/kontroleris/priskirti="+vartotojo_numeris+"&renginys="+renginio_numeris+".html").openConnection();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String outStr;
                while ((outStr = bufRead.readLine()) != null) {
                    rezultatas += outStr;
                }
                bufRead.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(renginioInfo.this,"To padaryti jau negalima",Toast.LENGTH_SHORT).show();
            }
            return rezultatas;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(renginioInfo.this,result,Toast.LENGTH_SHORT).show();
        }
    }
}
