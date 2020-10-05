package fr.intech.bacon.projeti;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import fr.intech.bacon.projeti.model.Device;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ManagerRequest extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient.Builder()
            //.addInterceptor(new StethoInterceptor())
            .build();


    public void getDevices() throws IOException {
        Log.i("jesuisla","jesuisla");
        Request request = new Request.Builder()
                // /json.htm?type=devices&filter=light&used=true&order=Name
                // TODO : adresse serv variable;
                .url("http://10.8.110.219:8080/json.htm?type=devices&filter=light&used=true&order=Name")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
                .build();

       client.newCall(request).enqueue(new Callback() {

           @Override
           public void onFailure(Call call, IOException e) {
               Log.i("larep", "c fo ");
               e.printStackTrace();
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               Log.i("larep", ""+response.code());
               final String myResponse = response.body().string();
               try{
                   //ALED
              } catch (IOError e) {
                   e.printStackTrace();
              }

           }
       });
       //Log.i("TryRequest", "cccccc");
   }

    /*public void switchOnOff(int idx){
        Request request = new Request.Builder()
                .url("http://192.168.1.32:8080/json.htm?type=command&param=switchlight&idx="+idx+"&switchcmd=Toggle")
                .header("Authorization","Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());


                }

            }
        });
    }*/
}