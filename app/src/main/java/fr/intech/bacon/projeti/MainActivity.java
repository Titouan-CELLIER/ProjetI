package fr.intech.bacon.projeti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.i("OUECH","COMPREND PAS");
        ManagerRequest request = new ManagerRequest();
        try {
            request.getDevices();
        } catch (IOException e) {
            e.printStackTrace();
        }


        listView = (ListView) findViewById(R.id.listView);

        String[] testList = new String[] {
          "Lampe du bureau",
          "Suspension",
          "Table de nuit"
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = (String) listView.getItemAtPosition(position);
            }
        });


    }


    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
            //return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu:
                    openActivityOptions();
            }
            return super.onOptionsItemSelected(item);
        }

        public void openActivityOptions() {
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivity(intent);
        }






}