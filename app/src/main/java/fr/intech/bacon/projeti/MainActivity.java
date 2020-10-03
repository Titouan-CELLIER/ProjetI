package fr.intech.bacon.projeti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("OUECH","COMPREND PAS");
        ManagerRequest request = new ManagerRequest();
        try {
            request.getDevices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}