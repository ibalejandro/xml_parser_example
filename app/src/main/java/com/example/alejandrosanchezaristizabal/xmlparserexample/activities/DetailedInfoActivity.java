package com.example.alejandrosanchezaristizabal.xmlparserexample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alejandrosanchezaristizabal.xmlparserexample.R;
import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;

/**
 * Created by alejandrosanchezaristizabal on 23/06/16.
 *
 * <h1>DetailedInfoActivity</h1>
 * Extracts the item ID which was sent from the general list activity and shows the detailed
 * information of the corresponding object.
 */
public class DetailedInfoActivity extends AppCompatActivity {

  // The ID of the clicked item in the RecyclerView.
  public static final String ITEM_ID = "xmlparserexample.ITEM_ID";

  private TextView name;
  private TextView price;
  private ImageView imgUrl;
  private RatingBar score;
  private TextView numbOfOpinions;
  private TextView description;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detailed_info);

    if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    int hotelId = getIntent().getIntExtra(ITEM_ID, 0); // Extract the item ID.
    Hotel selectedHotel = Hotel.getItem(hotelId);

    if (selectedHotel != null) {
      initDetailedView();
      populateView(selectedHotel);
    }
    else {
      Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_no_content),
                     Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * Initializes the necessary attributes for the detailed view.
   */
  public void initDetailedView() {
    name = (TextView) findViewById(R.id.tv_name);
    price = (TextView) findViewById(R.id.tv_price);
    imgUrl = (ImageView) findViewById(R.id.iv_hotel);
    score = (RatingBar) findViewById(R.id.rb_score);
    numbOfOpinions = (TextView) findViewById(R.id.tv_numb_of_opinions);
    description = (TextView) findViewById(R.id.tv_description);
  }

  /**
   * Sets the view's attributes with the information of the given Object.
   *
   * @param hotel The Hotel which its detailed information wants to be displayed.
   */
  public void populateView(Hotel hotel) {
    name.setText(hotel.getName());
    price.setText("$" + hotel.getPrice());
    Glide.with(this).load(hotel.getImgUrl()).into(imgUrl);
    score.setRating(hotel.getScore());
    numbOfOpinions.setText(hotel.getNumbOfOpinions() + " " + getResources()
        .getString(R.string.message_opinions));
    description.setText(hotel.getDescription());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_detailed_info, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    switch (id) {
      case android.R.id.home: // Back button on the Toolbar.
        finish();
        break;
      case R.id.action_settings:
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
