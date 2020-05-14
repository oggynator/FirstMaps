package com.example.firstmaps;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        builder = new AlertDialog.Builder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }


    //This callback is triggered once the map is ready
    //This method lets us add the markers we want to add, with a "pop up" prompting us for the name of the location
    //We also call the addKEA() method, which adds KEAs location to the map
    //
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                // Alert dialog builder
                final EditText editText = new EditText(MapsActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setTitle("New marker");
                builder.setMessage("What is on this location?");
                builder.setView(editText)
                        .setPositiveButton("Add marker", new DialogInterface.OnClickListener() {
                            //onclick event to add the new marker
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                mMap.addMarker(new MarkerOptions().position(latLng).title(editText.getText().toString()));
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
        addKEA();
    }


    //add kea to the Map
    private void addKEA() {
        LatLng kea = new LatLng(55.703930, 12.537540);
        mMap.addMarker(new MarkerOptions().position(kea).title("Marker at KEA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kea));


    }


}
