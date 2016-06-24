package com.example.alejandrosanchezaristizabal.xmlparserexample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alejandrosanchezaristizabal.xmlparserexample.R;
import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;

/**
 * Created by alejandrosanchezaristizabal on 22/06/16.
 *
 * <h1>HotelsListAdapter</h1>
 * Captures the click events on the list items and communicates them to the listener activity.
 */
public class HotelsListAdapter extends RecyclerView.Adapter<HotelsListAdapter.ViewHolder> {

  /**
   * Communication interface necessary to capture the click events and notify the listener activity.
   */
  public interface OnItemClickListener {
    void onItemClick(ViewHolder item, int position);
  }

  private OnItemClickListener listener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    this.listener = listener;
  }

  public OnItemClickListener getOnItemClickListener() {
    return listener;
  }

  /**
   * <h1>ViewHolder</h1>
   *
   * Creates the cards with the corresponding information and populates the RecyclerView with them.
   * It also communicates the click events to the parent activity.
   */
  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public TextView price;
    public ImageView imgUrl;
    public RatingBar score;

    private HotelsListAdapter parent = null;

    public ViewHolder(View v, HotelsListAdapter parent) {
      super(v);

      v.setOnClickListener(this);
      // The parent will be responsible to listen to the click events and communicate them to the
      // current listener activity.
      this.parent = parent;

      name = (TextView) v.findViewById(R.id.tv_name);
      price = (TextView) v.findViewById(R.id.tv_price);
      imgUrl = (ImageView) v.findViewById(R.id.iv_min_img_url);
      score = (RatingBar) v.findViewById(R.id.rb_score);
    }

    @Override
    public void onClick(View v) {
      final OnItemClickListener listener = parent.getOnItemClickListener();
      if (listener != null) {
        listener.onItemClick(this, getAdapterPosition()); // Communicating which item was clicked.
      }
    }
  }

  @Override
  public long getItemId(int position) {
    return Hotel.HOTELS.get(position).getId();
  }

  @Override
  public int getItemCount() {
    return (Hotel.HOTELS != null ? Hotel.HOTELS.size() : 0);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    // Inflating a new view hierarchy from the item_entry.
    View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_entry, viewGroup, false);
    return new ViewHolder(v, this);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    Hotel item = Hotel.HOTELS.get(i); // Every object in the list is used to fill the information.

    viewHolder.name.setText(item.getName());
    viewHolder.price.setText("$" + item.getPrice());
    // Managing the download of the images with Glide avoids problems with the main thread.
    Glide.with(viewHolder.itemView.getContext()).load(item.getImgUrl()).centerCrop()
        .into(viewHolder.imgUrl);
    viewHolder.score.setRating(item.getScore());
  }
}
