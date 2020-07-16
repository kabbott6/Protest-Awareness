package com.ibm.androidapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    String category;
    EditText latitude;
    EditText longitude;
    EditText description;
    String lat;
    String lon;
    String descript;
    Button submit;
    String time;
    RadioGroup theCategory;

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // on sd card
//            File csv = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/stats.csv");

            // app specific internal storage
            File csv = new File(getApplicationContext().getFilesDir(), "stats.csv");
            try {
                FileWriter filewriter = new FileWriter(csv, true);
                CSVWriter csvwriter = new CSVWriter(filewriter);
                List<String[]> info = new ArrayList<String[]>();
                info.add(new String[]{lat, lon, descript, category});
                csvwriter.writeAll(info);
                csvwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // read the file
            FileInputStream stream = null;
            try {
                stream = getApplicationContext().openFileInput("stats.csv");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            InputStreamReader inputreader =
                    new InputStreamReader(stream, StandardCharsets.UTF_8);
            StringBuilder builder = new StringBuilder();
            try (BufferedReader bufferedreader = new BufferedReader(inputreader)) {
                String line = bufferedreader.readLine();
                while (line != null) {
                    builder.append(line).append('\n');
                    line = bufferedreader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                String info = builder.toString();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        try {
            isStoragePermissionGranted();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        theCategory = (RadioGroup) findViewById(R.id.category);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        description = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.submit);

        theCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.food) {
                    category = "Food";
                } else if(checkedId == R.id.water) {
                    category = "Water";
                } else if(checkedId == R.id.medic){
                    category = "Medic";
                }
                else if (checkedId == R.id.warning) {
                    category = "Warning";
                }
            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                lat = latitude.getText().toString();
                lon = longitude.getText().toString();
                descript = description.getText().toString();
                time = String.valueOf(System.currentTimeMillis());
                if (lat.equals("") || lon.equals("") || descript.equals("") || category == null) {
                    Toast badToast = Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT);
                    badToast.show();
                } else if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // on sd card
//                    File csv = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/stats.csv");

                    // app specific internal storage
                    File csv = new File(getApplicationContext().getFilesDir(), "stats.csv");
                    try {
                        FileWriter filewriter = new FileWriter(csv, true);
                        CSVWriter csvwriter = new CSVWriter(filewriter);
                        List<String[]> info = new ArrayList<String[]>();
                        info.add(new String[]{lat, lon, descript, category, time});
                        csvwriter.writeAll(info);
                        csvwriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // read the file
                    FileInputStream stream = null;
                    try {
                        stream = getApplicationContext().openFileInput("stats.csv");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    InputStreamReader inputreader =
                            new InputStreamReader(stream, StandardCharsets.UTF_8);
                    StringBuilder builder = new StringBuilder();
                    try (BufferedReader bufferedreader = new BufferedReader(inputreader)) {
                        String line = bufferedreader.readLine();
                        while (line != null) {
                            builder.append(line).append('\n');
                            line = bufferedreader.readLine();
                        }
                    } catch (IOException e) {
                        // Error occurred when opening raw file for reading.
                    } finally {
                        String info = builder.toString();
                    }
                    Context context = getApplicationContext();
                    CharSequence text = "Successfully Submitted";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

    }

}
