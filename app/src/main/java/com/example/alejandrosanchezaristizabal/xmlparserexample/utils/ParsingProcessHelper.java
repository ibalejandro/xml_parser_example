package com.example.alejandrosanchezaristizabal.xmlparserexample.utils;

import android.util.Xml;

import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandrosanchezaristizabal on 13/06/16.
 *
 * <h1>ParsingProcessHelper</h1>
 * Coordinates the Parsing process from the input data until the final list of main objects parsed.
 */
public class ParsingProcessHelper {

  public static final String NAMESPACE = null;

  /**
   * Parses the XML input file into a list of objects.
   *
   * @param in Input data.
   *
   * @return hotels List of the main objects.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public List<Hotel> parse(InputStream in) throws XmlPullParserException, IOException {
    try {
      XmlPullParser parser = Xml.newPullParser();
      parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
      parser.setInput(in, "utf-8");
      parser.nextTag();
      return analyzeFeed(parser);
    }
    finally {
      in.close();
    }
  }

  /**
   * Converts a set of <entry> tags into a list of Objects.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return hotels List of the main objects.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public List<Hotel> analyzeFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
    List<Hotel> hotels = new ArrayList<>();

    parser.require(XmlPullParser.START_TAG, NAMESPACE, TagsDefinerHelper.FEED_TAG);
    while (parser.next() != XmlPullParser.END_TAG) {
      if (parser.getEventType() != XmlPullParser.START_TAG) continue;
      String currentTag = parser.getName();
      // The <entry> tag is being searched.
      if (currentTag.equals(TagsDefinerHelper.ENTRY_TAG)) {
        Hotel hotel = ObjectBuilderHelper.analyzeEntry(parser);
        hotels.add(hotel);
      }
      else DefaultParsingFunctionsHelper.skipTag(parser);
    }

    return hotels;
  }
}
