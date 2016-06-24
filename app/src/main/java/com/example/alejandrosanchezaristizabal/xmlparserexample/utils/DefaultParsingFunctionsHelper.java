package com.example.alejandrosanchezaristizabal.xmlparserexample.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by alejandrosanchezaristizabal on 21/06/16.
 *
 * <h1>DefaultParsingFunctionsHelper</h1>
 * Provides the default methods for the Parsing process in order to be able to go on with the
 * transformation.
 */
public class DefaultParsingFunctionsHelper {

  /**
   * Obtains the text from the tags.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return text The text contained inside the tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static String obtainText(XmlPullParser parser) throws XmlPullParserException, IOException {
    String text = "";
    if (parser.next() == XmlPullParser.TEXT) {
      text = parser.getText();
      parser.nextTag();
    }

    return text;
  }

  /**
   * Skips those blocks, which are irrelevant for the Parsing process.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static void skipTag(XmlPullParser parser) throws XmlPullParserException, IOException {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
      throw new IllegalStateException();
    }

    // When the depth is equal to 0, it means that the complete block was skipped.
    int depth = 1;
    while (depth != 0) {
      switch (parser.next()) {
        case XmlPullParser.START_TAG:
          depth++;
          break;
        case XmlPullParser.END_TAG:
          depth--;
          break;
        default:
      }
    }
  }
}
