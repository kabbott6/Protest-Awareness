package com.ibm.androidapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*File file = new File("/Users/kendall.abbott@ibm.com/Documents/protest-awareness/app/src/main/assets/www/stations.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        // This responce will have Json Format String
        String responce = stringBuilder.toString();
        JSONObject jsonObject  = new JSONObject(responce);
        get*/
        try {
            /*JSONObject obj = new JSONObject(loadJSONFromAsset(getApplicationContext()));
            JSONArray KMAE = obj.getJSONArray("KMAE");
            JSONArray KSJC = obj.getJSONArray("KSJC");
            JSONArray KMCE = obj.getJSONArray("KMCE");
            JSONArray KMER = obj.getJSONArray("KMER");*/

            // LatLng kmae_loc = new LatLng(-122.37,37.62);
            // LatLng ksjc_loc = new LatLng(KSJC.getJSONArray(0), KSJC.getJSONArray(1));
            // LatLng ksjc_loc = new LatLng(KSJC.getJSONArray(0), KSJC.getJSONArray(1));
            // LatLng kmce_loc = new LatLng(KMCE.getJSONArray(0), KMCE.getJSONArray(1));
            // LatLng kmer_loc = new LatLng(KMER.getJSONArray(0), KMER.getJSONArray(1));
            // LatLng kmer_loc = new LatLng(KMER.getJSONArray(0), KMER.getJSONArray(1));

            // mMap.addMarker(new MarkerOptions().position(kmae_loc).title("KMAE"));
            // mMap.addMarker(new MarkerOptions().position(ksjc_loc).title("KSJC").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            // mMap.addMarker(new MarkerOptions().position(kmce_loc).title("KMCE").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            // mMap.addMarker(new MarkerOptions().position(kmer_loc).title("KMER").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            // mMap.moveCamera(CameraUpdateFactory.newLatLng(kmae_loc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("/Users/kendall.abbott@ibm.com/Documents/protest-awareness/app/src/main/assets/www/stations.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}

