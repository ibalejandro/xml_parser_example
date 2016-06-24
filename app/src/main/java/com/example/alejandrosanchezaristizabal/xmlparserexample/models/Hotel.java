package com.example.alejandrosanchezaristizabal.xmlparserexample.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrosanchezaristizabal on 13/06/16.
 *
 * <h1>Hotel</h1>
 * Represents every main object in the XML input file.
 */
public class Hotel {

  private int id;
  private String name;
  private String price;
  private String imgUrl;
  private float score;
  private int numbOfOpinions;
  private String description;

  // Contains the List of the main objects parsed.
  public static List<Hotel> HOTELS = new ArrayList<>();

  public Hotel(int id, String name, String price, String imgUrl, float score, int numbOfOpinions,
               String description) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imgUrl = imgUrl;
    this.score = score;
    this.numbOfOpinions = numbOfOpinions;
    this.description = description;
  }

  /**
   * Obtains the corresponding hotel for the given ID.
   *
   * @param id ID of the searched hotel.
   *
   * @return item The hotel which its ID is equal to the given one.
   */
  public static Hotel getItem(int id) {
    for (Hotel item : HOTELS) {
      if (item.getId() == id) return item;
    }

    return null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  public int getNumbOfOpinions() {
    return numbOfOpinions;
  }

  public void setNumbOfOpinions(int numbOfOpinions) {
    this.numbOfOpinions = numbOfOpinions;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this); // Converts an Hotel into a JSON String.
  }
}
