package com.example.alejandrosanchezaristizabal.xmlparserexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alejandrosanchezaristizabal.xmlparserexample.R;
import com.example.alejandrosanchezaristizabal.xmlparserexample.adapters.HotelsListAdapter;
import com.example.alejandrosanchezaristizabal.xmlparserexample.interfaces.AsyncTaskCompleteListener;
import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;
import com.example.alejandrosanchezaristizabal.xmlparserexample.network.HttpConnectionClient;

import java.util.List;

/**
 * Created by alejandrosanchezaristizabal on 13/06/16.
 *
 * <h1>MainActivity</h1>
 * Shows the result of the Parsing process in a clickable List.
 */
public class MainActivity extends AppCompatActivity implements
    AsyncTaskCompleteListener<List<Hotel>>, HotelsListAdapter.OnItemClickListener{

  private static final String TAG = "MainActivity";

  // The URL to get the XML input data.
  private static final String URL = "http://www.mocky.io/v2/576bf4df110000030a6667a1";

  private HotelsListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initRecycler();

    // The AsyncTask to download the input data and start the Parsing process.
    new HttpConnectionClient(getApplicationContext(), this).execute(URL);
  }

  /**
   * Initializes the RecyclerView and sets the corresponding characteristics. It also initializes
   * the adapter and sets it to the RecyclerView.
   */
  public void initRecycler() {
    RecyclerView recycler = (RecyclerView) findViewById(R.id.rv_recycler);
    if (recycler != null) {
      recycler.setHasFixedSize(true);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      recycler.setLayoutManager(linearLayoutManager);

      adapter = new HotelsListAdapter();
      // Enables the procurement of the items IDs using the adapter's getItemId() method.
      adapter.setHasStableIds(true);
      adapter.setOnItemClickListener(this);

      recycler.setAdapter(adapter);
    }
  }

  @Override
  public void onTaskComplete(List<Hotel> result) {
    Hotel.HOTELS = result;
    int listSize = (Hotel.HOTELS != null ? Hotel.HOTELS.size() : 0);
    Log.i(TAG, "Size of Hotels: " + listSize);
    for (int i = 0; i < listSize; ++i) {
      Log.i(TAG, "Hotel " + (i + 1) + ": " + Hotel.HOTELS.get(i).toString());
    }

    adapter.notifyDataSetChanged(); // Updates the list of the main Objects.
  }

  @Override
  public void onItemClick(HotelsListAdapter.ViewHolder item, int position) {
    Log.i(TAG, "Item at position " + (position + 1) + " was pressed.");

    Intent intent = new Intent(this, DetailedInfoActivity.class);
    int itemId = (int) item.getItemId();
    intent.putExtra(DetailedInfoActivity.ITEM_ID, itemId);
    startActivity(intent);
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
}
