package com.example.alejandrosanchezaristizabal.xmlparserexample.utils;

import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by alejandrosanchezaristizabal on 15/06/16.
 *
 * <h1>ObjectBuilderHelper</h1>
 * Analyzes an entry and converts it into an Object.
 */
public class ObjectBuilderHelper {

  /**
   * Converts an <entry> tag into an Object.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return createdObject The created object from the parser.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static Hotel analyzeEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.ENTRY_TAG);
    int id = 0;
    String name = null;
    String price = null;
    String imgUrl = null;
    HashMap<String, String> appreciation;
    float score = 0;
    int numbOfOpinions = 0;
    String description = null;

    while (parser.next() != XmlPullParser.END_TAG) {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }

      String tag = parser.getName();
      switch (tag) {
        case TagsDefinerHelper.ID_TAG:
          id = TagsAnalyzerHelper.analyzeId(parser);
          break;
        case TagsDefinerHelper.NAME_TAG:
          name = TagsAnalyzerHelper.analyzeName(parser);
          break;
        case TagsDefinerHelper.PRICE_TAG:
          price = TagsAnalyzerHelper.analyzePrice(parser);
          break;
        case TagsDefinerHelper.IMG_URL_TAG:
          imgUrl = TagsAnalyzerHelper.analyzeImgUrl(parser);
          break;
        case TagsDefinerHelper.APPRECIATION_TAG:
          appreciation = TagsAnalyzerHelper.analyzeAppreciation(parser);
          // The appreciation tag contains the values for the attributes 'score' and
          // 'numbOfOpinions'.
          score = Float.parseFloat(appreciation.get(TagsDefinerHelper.SCORE_ATTR));
          numbOfOpinions = Integer.parseInt(appreciation.get(TagsDefinerHelper
              .NUMB_OF_OPINIONS_ATTR));
          break;
        case TagsDefinerHelper.DESCRIPTION_TAG:
          description = TagsAnalyzerHelper.analyzeDescription(parser);
          break;
        default:
          DefaultParsingFunctionsHelper.skipTag(parser);
          break;
      }
    }

    return new Hotel(id, name, price, imgUrl, score, numbOfOpinions, description);
  }
}
