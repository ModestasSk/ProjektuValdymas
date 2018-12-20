package vgtu.susiburimoapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String stud_nr = "";
    private String stud_psw = "";
    private boolean pass = false;
    private Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void prisijungimas(View view) {
        EditText vartotojo_nr = (EditText) findViewById(R.id.l_el_nr);
        stud_nr = vartotojo_nr.getText().toString();
        EditText vartotojo_psw = (EditText) findViewById(R.id.l_psw);
        stud_psw = vartotojo_psw.getText().toString();
        MainActivity.GautiDuomenis duom = new MainActivity.GautiDuomenis();
        duom.execute();
        thisContext = this;

    }

    private class GautiDuomenis extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... nr) {
            String rezultatas = "";
            try {
                HttpURLConnection s = (HttpURLConnection) new URL("http://localhost:8080/susiburimoAPI/kontroleris/vartotojai.html").openConnection();
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
                for (int i = 0; i < json.length(); i++) {
                    JSONObject c = json.getJSONObject(i);
                    String kodas = c.getString("kodas");
                    String slaptazodis = c.getString("slaptazodis");
                    if (kodas.equals(stud_nr) && slaptazodis.equals(stud_psw)) {
                        pass = true;
                    }
                }
                if (pass) {
                    Intent naujas = new Intent(thisContext, renginiuSarasas.class);
                    naujas.putExtra("nr", stud_nr);
                    startActivity(naujas);
                    pass = false;
                } else
                    Toast.makeText(MainActivity.this, "Netinkami duomenys", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
            }
        }
    }
}
