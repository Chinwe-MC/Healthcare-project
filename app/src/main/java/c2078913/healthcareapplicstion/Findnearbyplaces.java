package c2078913.healthcareapplicstion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import c2078913.healthcareapplicstion.databinding.ActivityFindnearbyplacesBinding;

public class Findnearbyplaces extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;

    private ActivityFindnearbyplacesBinding binding;
    private Marker currentuserlocationmarker;
    private static final int Request_User_Location_Code = 99;
    private double latitude, longitude;
    private int ProximityRadius = 10000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFindnearbyplacesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkuserLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void checkuserLocationPermission() {
    }

    public void onClick(View v)
    {
        String  hospital = " hospital", Pharmacy = "Pharmacy";
        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();



        switch(v.getId())
        {
            case R.id.btnseacr:
                EditText addressField = (EditText) findViewById(R.id.mapsearchfield);
                String address = addressField.getText().toString();

                List<Address> addressList = null;
                MarkerOptions usermarkerOptions = new MarkerOptions();


                if(!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(address, 6);
                        if(addressList != null) {
                            for (int i = 0; i < addressList.size(); i++) {
                                Address userAddress = addressList.get(i);
                                LatLng latlng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());
                                usermarkerOptions.position(latlng);
                                usermarkerOptions.title("address");
                                usermarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                mMap.addMarker(usermarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                        else{
                            Toast.makeText(this, "LOcation Not Found",Toast.LENGTH_SHORT ).show();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
                else {
                    Toast.makeText( this,"Enter an address in the field", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.searchhospbtn:
                mMap.clear();
                String url= getURL( latitude,  longitude,  hospital);
                transferData[0]= mMap;
                transferData[1]= url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby Hospitals", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing  nearby Hospitals", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mappharm:
                mMap.clear();
                 url= getURL( latitude,  longitude,  Pharmacy);
                transferData[0]= mMap;
                transferData[1]= url;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby Pharmacies", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing  nearby Pharmacies", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getURL(double latitude, double longitude, String nearbyPlace) {
        {
            StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            googleURL.append("Location=" + latitude + "," + longitude);
            googleURL.append("&radius= " + ProximityRadius);
            googleURL.append("&type=" + nearbyPlace);
            googleURL.append("&sensor= true");
            googleURL.append("&Key=" + "AIzaSyAt-OdrNKYZxwdWA17Tg7R1DnFMFLLQeC0");


            Log.d("Findnearbyplaces", "url = " + googleURL.toString());
            return googleURL.toString();

        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }
    }


    public boolean checkselfpermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);

            }

        }
        return false;

        }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if(googleApiClient==null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient(){
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            googleApiClient.connect();
        }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest LocationRequest = new LocationRequest();
        LocationRequest.setInterval(1100);
        LocationRequest.setFastestInterval(1100);
        LocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, LocationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Location lastLocation = location;
        if(currentuserlocationmarker!=null)
        {
            currentuserlocationmarker.remove();
        }

        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());


        MarkerOptions markeroptions = new MarkerOptions();
        markeroptions.position(latlng);
        markeroptions.title("user current location");
        markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        currentuserlocationmarker = mMap.addMarker(markeroptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(14));

        if (googleApiClient!= null);
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }



}