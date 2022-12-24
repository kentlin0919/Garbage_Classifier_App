package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.Visitor.Switch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class RegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    int i = 0;
    String getLocation, machineImage;
    private String selectedLocation;
    ArrayList<String> setLocation = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<>();
    ImageView imageView ;
    float scaleWidth,scaleHeight;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
        imageView = (ImageView)findViewById(R.id.machineImageView);
        //創建矩陣
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //bp = BitmapFactory.decodeResource(getResources(),R.drawable.)
        //int height =
        //to get screen width and height
        int w=dm.widthPixels;
        int h=dm.heightPixels;

        //to create array and get location  , put in the spinner
        new getMachineLocationTask().execute();
    }


    class getMachineLocationTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/machines")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    try {
                        while (i < jsonArray.length()) {
                            getLocation = jsonArray.getJSONObject(i).getString("location");
                            location.add(getLocation);
                            i++;
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                int k = 0;
                while (k < location.size()) {
                    setLocation.add(location.get(k));
                    k++;
                }
                setSpinner();
            }
        }
    }

    private void setSpinner() {
        Spinner mspinner = (Spinner) findViewById(R.id.nonRegistSelectedLocation);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, setLocation);
        mspinner.setAdapter(adapter);
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLocation = (String) mspinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NN");
            }
        });
    }

    class registLocationGetImageTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/machines/location/?location=" + selectedLocation)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JSONArray json = new JSONArray(response.body().string());
                    machineImage = json.getJSONObject(0).getString("machinePicture");
                    return true;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                byte[] decodedString = Base64.decode(machineImage, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);

            }
        }
    }

    public void btnGetLocationImage(View view) {
        new registLocationGetImageTask().execute();
    }


    public void gotoHome(View view) {
        Switch aswitch = new Switch("Home",this);
        aswitch.activity();
    }
    public void gotoLocation(View view) {
        Switch aswitch = new Switch("RegistLocation",this);
        aswitch.activity();
    }
    public void gotoAccount(View view) {
        Switch aswitch = new Switch("RedgistrReviseAccount",this);
        aswitch.activity();
    }

    public void gotoConnect(View view) {
        Switch aswitch = new Switch("ConnectCan",this);
        aswitch.activity();
    }
    public void gotoMoney(View view) {
        Switch aswitch = new Switch("RedgistMoney",this);
        aswitch.activity();
    }
}
