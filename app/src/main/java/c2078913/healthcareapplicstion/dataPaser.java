package c2078913.healthcareapplicstion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dataPaser {
    private HashMap<String, String> getSingleNearbyPlace(JSONObject googleplacejson) {
        HashMap<String, String> googleplaceMap = new HashMap<>();
        String NameOfPlace = "-NA-";
        String Vicinity = "-NA-";
        String Latitude ="";
        String Longitude = "";
        String reference = "";

        try {
            if(googleplacejson.isNull("name"))
            {
                NameOfPlace = googleplacejson.getString("name");
            }
            if(googleplacejson.isNull("Vicinity"))
            {
                Vicinity = googleplacejson.getString("Vicinity");
            }
            Latitude = googleplacejson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            Longitude = googleplacejson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googleplacejson.getString("reference");
            googleplaceMap.put("place_name", NameOfPlace);
            googleplaceMap.put("vicinity", Vicinity);
            googleplaceMap.put("lat", Latitude);
            googleplaceMap.put("lng", Longitude);
            googleplaceMap.put("Reference", reference);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return googleplaceMap;

    }

    private List<HashMap<String, String>> getAllNearbyPlaces(JSONArray jsonArray)
    {
        int counter = jsonArray.length();
        List<HashMap<String, String>> nearbyplacesList = new ArrayList<>();
        HashMap<String, String> nearbyplacemap = null;
        for (int i = 0; i<counter; i++)
        {
            try {
                nearbyplacemap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                nearbyplacesList.add(nearbyplacemap);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return nearbyplacesList;


    }

    public List<HashMap<String, String>>parse(String JSONdata)
    {
        JSONArray jsonArray =null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(JSONdata);
            jsonArray= jsonObject.getJSONArray("result");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return getAllNearbyPlaces(jsonArray);
    }

}
