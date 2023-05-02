package c2078913.healthcareapplicstion;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Nearbyplaces extends AsyncTask<Object,String,String>
{
    InputStream is;
    BufferedReader bufferedreader;
    StringBuilder stringBuilder;
    String data;

    @Override
    protected String doInBackground(Object... params) {

        GoogleMap mMap = (GoogleMap) params[0];
        String url =(String)params[1];
        try
        {

            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) myurl.openConnection();
            httpURLConnection.connect();is = httpURLConnection.getInputStream();
            bufferedreader = new BufferedReader(new InputStreamReader(is));

            String line= "" ;
            stringBuilder = new StringBuilder();
                    while ((line = bufferedreader.readLine())!=null);
            {
                stringBuilder.append(line);
            }

             data = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s){

    }
}
