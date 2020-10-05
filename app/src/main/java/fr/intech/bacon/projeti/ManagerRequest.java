package fr.intech.bacon.projeti;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOError;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ManagerRequest extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient.Builder()
            //.addInterceptor(new StethoInterceptor())
            .build();


    public void getDevices() throws IOException {
        Request request = new Request.Builder()
                // /json.htm?type=devices&filter=light&used=true&order=Name
                // TODO : adresse serv variable;
                //192.168.43.151
                //192.168.1.32
                .url("http://192.168.1.32:8080/json.htm?type=devices&filter=light&used=true&order=Name")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
                .build();

       client.newCall(request).enqueue(new Callback() {

           @Override
           public void onFailure(Call call, IOException e) {
               e.printStackTrace();
               Log.e("MANAGER", "onFailure"+e);
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               Log.i("MANAGER", ""+response.code());
               String responseBody = response.body().string();
               try{
                   Log.i("MANAGER","onResponse Try");
                   Log.i("MANAGER","responseBody "+responseBody);
                   ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                   Map<String, Object> map = mapper.readValue(responseBody, new TypeReference<Map<String,Object>>(){});
                   Log.i("MANAGER","map "+map);

                   for (Map.Entry mapentry :map.entrySet()
                        ) {
                       if(mapentry.getKey() == "result"){

                           Log.i("MANAGER","value "+mapentry.getValue());
                           List<Object> devices = (List<Object>) mapentry.getValue();
                           Log.i("MANAGER","devices "+ devices);
                           for (int i =0; i < devices.size();i++){
                               Log.i("MANAGER","DEVICES"+i+""+ devices.get(i));
                               }

                           }
                       }

               } catch (IOError e) {
                   e.printStackTrace();
                   Log.e("MANAGER", "onResponse Error"+e);
              }

           }
       });
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