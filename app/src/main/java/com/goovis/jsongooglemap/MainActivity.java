package com.goovis.jsongooglemap;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.goovis.jsongooglemap.adapter.LocationAdapter;
import com.goovis.jsongooglemap.bean.Location;
import com.goovis.jsongooglemap.bean.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ranjith Subramaniam on 03/28/2017
 */

public class MainActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private JSONArray locations;
    private ListView listView;

    ArrayList<Location> locationList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationList = new ArrayList<>();
        listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Getting values from selected ListItem
                Double longitude = locationList.get(position).getLongitude();
                Double latitude = locationList.get(position).getLatitude();

                // Starting single contact activity
                Intent intent = new Intent(MainActivity.this, SingleLocationActivity.class);
                intent.putExtra(Util.TAG_LONGITUDE, longitude);
                intent.putExtra(Util.TAG_LATITUDE, latitude);
                startActivity(intent);
            }
        });

        // Calling async task to get json
        new GetLocation().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetLocation extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler serviceHandler = new ServiceHandler();
            // Making a request to url and getting response
            String jsonResponseString = serviceHandler.makeServiceCall(Util.JSON_URL, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonResponseString);

            if (jsonResponseString != null) {
                try {
                    // Getting JSON Array node
                    locations = new JSONArray(jsonResponseString);
                    // looping through All Locations
                    for (int count = 0; count < locations.length(); count++) {
                        JSONObject c = locations.getJSONObject(count);

                        String id = c.getString(Util.TAG_ID);
                        String name = c.getString(Util.TAG_NAME);
                        String address = c.getString(Util.TAG_ADDRESS);
                        String time = c.getString(Util.TAG_ARRIVALTIME);
                        String longitude = c.getString(Util.TAG_LONGITUDE);
                        String latitude = c.getString(Util.TAG_LATITUDE);

                        Location newLocation = new Location(Integer.parseInt(id), name, address, Double.parseDouble(longitude), Double.parseDouble(latitude), time);
                        locationList.add(newLocation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Dismiss the progress dialog
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            listView.setAdapter(new LocationAdapter(MainActivity.this, locationList));

            super.onPostExecute(result);
        }
    }
}