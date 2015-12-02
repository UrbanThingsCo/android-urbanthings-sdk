package com.example.urbanthings.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.urbanthings.R;
import com.example.urbanthings.network.UrbanThingsApiClient;
import com.example.urbanthings.network.parser.PlacePointHttpResponseParser;
import com.example.urbanthings.network.parser.ResourceStatusHttpResponseParser;
import io.urbanthings.datamodel.PlacePoint;
import io.urbanthings.datamodel.ResourceStatus;
import io.urbanthings.helpers.CoreUtils;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String SEPARATOR = ":  ";
    private static final String STATUS_SEPARATOR = ";  ";

    // Lat Lng bounds for this 'Car Parks' sample
    // min, max Latitude & Longitude values specify bounds within which data is wanted

    // In a real application, these values may be derived from device or map location
    // For example using Google Maps API
    // LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
    // then: MIN_LNG = bounds.southwest.longitude
    //       MAX_LNG = bounds.northeast.longitude
    //       MIN_LAT = bounds.southwest.latitude
    //       MAX_LAT = bounds.northeast.latitude

    // If you make the bounds too large, # results will be capped at a server defined maximum.

    private static final double MIN_LAT = 51.394;
    private static final double MAX_LAT = 51.566;
    private static final double MIN_LNG = -2.71;
    private static final double MAX_LNG = -2.436;

    private View mListContainer;
    private View mProgressBar;
    private ListView mListView;
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListContainer = findViewById(R.id.listContainer);
        mProgressBar = findViewById(R.id.progressBar);
        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);
        mListView = (ListView) findViewById(R.id.listView);
        // EmptyView will be shown when list is visible, but empty.
        mListView.setEmptyView(mEmptyTextView);

        if (TextUtils.isEmpty(UrbanThingsApiClient.API_KEY))  {
            mEmptyTextView.setText(getString(R.string.you_must_get_api_key));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // clear the list when the FAB is pushed...
                mListView.setAdapter(null);

                // make sure any previous error text is reset to default message
                mEmptyTextView.setText(getString(R.string.empty_list_text));

                // hide the entire list container & show progress Indicator
                showProgressBar(true);

                UrbanThingsApiClient.get(
                        UrbanThingsApiClient.GET_STOPS,
                        UrbanThingsApiClient.getCarParkRequestParams(MIN_LAT, MAX_LAT, MIN_LNG, MAX_LNG),
                        new PlacePointHttpResponseParser() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, List<PlacePoint> errorResponse) {
                        showProgressBar(false);
                        StringBuilder sb = new StringBuilder(getString(R.string.failed_to_retrieve_placepoints));
                        if (null != throwable) {
                            sb.append(SEPARATOR);
                            sb.append(throwable.getLocalizedMessage());
                        }
                        String errorStr = sb.toString();
                        Log.e(LOG_TAG, errorStr, throwable);
                        mEmptyTextView.setText(errorStr);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, List<PlacePoint> response) {
                        showProgressBar(false);
                        StringBuilder sb = new StringBuilder(getString(R.string.call_succeeded));
                        if (null != response) {
                            sb.append(SEPARATOR);
                            sb.append(getString(R.string.results_summary_template, response.size()));
                        }
                        String summary = sb.toString();
                        Log.i(LOG_TAG, summary);
                        report(view, summary);

                        // we have our data - populate the list
                        mListView.setAdapter(new PlacePointListAdapter(MainActivity.this, response));
                    }
                });
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                PlacePoint placePoint = CoreUtils.safeCast(
                        parent.getItemAtPosition(position),
                        PlacePoint.class);
                if ((null != placePoint) && !TextUtils.isEmpty(placePoint.primaryCode)) {
                    UrbanThingsApiClient.get(
                            UrbanThingsApiClient.GET_STATUS,
                            UrbanThingsApiClient.getPlacePointStatusRequestParams(placePoint),
                            new ResourceStatusHttpResponseParser() {
                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, List<ResourceStatus> errorResponse) {
                                    StringBuilder sb = new StringBuilder(getString(R.string.failed));
                                    if (null != throwable) {
                                        sb.append(SEPARATOR);
                                        sb.append(throwable.getLocalizedMessage());
                                    }
                                    String errorStr = sb.toString();
                                    Log.e(LOG_TAG, errorStr, throwable);
                                    report(view, errorStr);
                                }

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, List<ResourceStatus> response) {
                                    StringBuilder sb = new StringBuilder(getString(R.string.retrieved_status));
                                    if ((null != response) && (response.size() > 0)) {
                                        // we really only expect a single status report here...
                                        int idx = 0;
                                        for (ResourceStatus status : response) {
                                            if (!TextUtils.isEmpty(status.statusText)) {
                                                if (idx++ > 0) {
                                                    sb.append(STATUS_SEPARATOR);
                                                } else {
                                                    sb.append(SEPARATOR);
                                                }
                                                sb.append(status.statusText);
                                            }
                                        }
                                    } else {
                                        sb.append(SEPARATOR);
                                        sb.append(getString(R.string.nothing_to_report));
                                    }
                                    String summary = sb.toString();
                                    Log.i(LOG_TAG, summary);
                                    report(view, summary);
                                }
                            });
                }
            }
        });
    }

    private void showProgressBar(boolean show) {
        mListContainer.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void report(View view, String info) {
        Snackbar.make(view, info, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
