package com.hub.rish.payfair;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
static String source,destination,parsedDistance,response,jasonResponse,key="AIzaSyDBjK_xom_9U2Lvx6dmwGdPNPYMIeeyJGM";
URL urlmain;
String busType="Ordinary";
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int PLACE_PICKER_REQUEST = 1,flag=1;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=(TextView)findViewById(R.id.display);

        EditText src=(EditText)findViewById(R.id.source);
        EditText dest=(EditText)findViewById(R.id.destination);


        Spinner spinner = (Spinner) findViewById(R.id.bustype);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bustype, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
spinner.setOnItemSelectedListener(MainActivity.this);




        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setBoundsBias(new LatLngBounds(
                                    new LatLng(17.387140,  78.491684),
                                    new LatLng(17.560000, 78.729596)))
                                    .build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });
        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setBoundsBias(new LatLngBounds(
                                    new LatLng(17.387140,  78.491684),
                                    new LatLng(17.560000, 78.729596)))
                                    .build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        Button submit=(Button)findViewById(R.id.submit);
        Button srcbutton=(Button)findViewById(R.id.srcbutton);
        Button destbutton=(Button)findViewById(R.id.destbutton);

        srcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        destbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float price;
                int busprice=5;
            String dist=getDistance();
           try {
               String[] split = dist.split(" ");

           float distance=Float.parseFloat(split[0]);
            distance-=1.6;
            if(distance<=0)
                price=20;
            else
                price =(20 + 11*distance);

               distance+=1.6;
            if(busType.contains("Ordinary")){
                if(distance<4)
                    busprice=5;
                if(distance>=4&&distance<=10)
                    busprice=10;
                if(distance>10&&distance<20)
                    busprice=15;
                if(distance>=20&&distance<30)
                    busprice=20;
                if(distance>=30&&distance<40)
                    busprice=25;
                if(distance>=40)
                    busprice=30;
            }
               if(busType.contains("Express")){
                   if(distance<6)
                       busprice=10;
                   if(distance>=6&&distance<16)
                       busprice=15;
                   if(distance>=16&&distance<26)
                       busprice=20;
                   if(distance>=26&&distance<36)
                       busprice=25;
                   if(distance>=36)
                       busprice=30;
               }
               if(busType.contains("Deluxe")){
                   if(distance<6)
                       busprice=10;
                   if(distance>=6&&distance<14)
                       busprice=15;
                   if(distance>=14&&distance<24)
                       busprice=20;
                   if(distance>=24&&distance<32)
                       busprice=25;
                   if(distance>=32)
                       busprice=30;
               }




display.setText("Distance: "+String.format("%.2f",distance)+" km"+"\nAuto Cost: "+String.format("%.1f",price)+" Rs"
        +"\nBus Cost: "+String.valueOf(busprice)+" Rs");
           }catch (NullPointerException e){}

            }
        });
    }
    public String getDistance(){
        EditText src=(EditText)findViewById(R.id.source);
        EditText dest=(EditText)findViewById(R.id.destination);

        source=src.getText().toString();
        destination=dest.getText().toString();

        source=source.replace(" ","+");
        destination=destination.replace(" ","+");

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {

                        urlmain= new URL("https://maps.googleapis.com/maps/api/directions/json?origin="+source +
                                "+hyderabad+telangana&destination="+destination +
                                "+hyderabad+telangana&departure_time=1541202457&traffic_model=best_guess&key="+key);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    final HttpURLConnection conn = (HttpURLConnection) urlmain.openConnection();
                    conn.setRequestMethod("POST");
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = readFromStream(in);

                    final JSONObject json = new JSONObject(response);
                    JSONArray routeArray = json.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);

                    JSONArray newTempARr = routes.getJSONArray("legs");
                    JSONObject newDisTimeOb = newTempARr.getJSONObject(0);

                    JSONObject distOb = newDisTimeOb.getJSONObject("distance");
                    JSONObject timeOb = newDisTimeOb.getJSONObject("duration");

                    parsedDistance=distOb.getString("text");
                    Log.i("Time :", timeOb.getString("text"));
                   // parsedDistance=distance.getString("text");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return parsedDistance;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        EditText src=(EditText)findViewById(R.id.source);
        EditText dest=(EditText)findViewById(R.id.destination);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
               if(flag==1)
                   src.setText(String.valueOf(place.getName()));
                else
                    dest.setText(String.valueOf(place.getName()));
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if(flag==1)
                    src.setText(place.getName());
                else
                    dest.setText(place.getName());

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
      busType=String.valueOf(parent.getItemAtPosition(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

