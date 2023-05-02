package c2078913.healthcareapplicstion;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object, String, String>
{
    private String googleplacedata, url;
    private GoogleMap mMap;
    @Override
    protected String doInBackground(Object... objects)
    {
        mMap= (GoogleMap) objects[0];
        url = (String) objects[1];
        DownloadURL downloadurl = new DownloadURL();
        try {
            googleplacedata = downloadurl.ReadTheUrl(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return googleplacedata;
    }

    @Override
    protected void onPostExecute(String s){
        List<HashMap<String, String>> nearbyplacesList = null;
        dataPaser datapaser = new dataPaser();
        nearbyplacesList = datapaser.parse(s);

        DisplayNearbyPlaces(nearbyplacesList);
    }

    private void DisplayNearbyPlaces(List<HashMap<String, String>> nearbyplacesList)
    {
        for(int i = 0; i <nearbyplacesList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googleNearbyPlaces= nearbyplacesList.get(i);
            String nameOfPlace = googleNearbyPlaces.get("place_name");
            String Vicinity = googleNearbyPlaces.get("Vicinity");
            double lat = Double.parseDouble(googleNearbyPlaces.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlaces.get("lng"));

            LatLng latlng = new LatLng(lat, lng);
            markerOptions.position(latlng);
            markerOptions.title(nameOfPlace + " " + Vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));




        }
    }
}
