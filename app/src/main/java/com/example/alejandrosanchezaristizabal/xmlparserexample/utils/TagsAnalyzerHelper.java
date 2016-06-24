package com.example.alejandrosanchezaristizabal.xmlparserexample.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by alejandrosanchezaristizabal on 21/06/16.
 *
 * <h1>TagsAnalyzerHelper</h1>
 * Analyzes the content of every single tag using individual methods.
 */
public class TagsAnalyzerHelper {

  /**
   * Process the <id> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return id The content inside the <id> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static int analyzeId(XmlPullParser parser) throws XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.ID_TAG);
    int id = Integer.parseInt(DefaultParsingFunctionsHelper.obtainText(parser));
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE, TagsDefinerHelper.ID_TAG);

    return id;
  }

  /**
   * Process the <name> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return name The content inside the <name> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static String analyzeName(XmlPullParser parser) throws XmlPullParserException, IOException
  {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.NAME_TAG);
    String name = DefaultParsingFunctionsHelper.obtainText(parser);
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.NAME_TAG);

    return name;
  }

  /**
   * Process the <price> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return price The content inside the <price> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static String analyzePrice(XmlPullParser parser) throws XmlPullParserException, IOException
  {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.PRICE_TAG);
    String price = DefaultParsingFunctionsHelper.obtainText(parser);
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.PRICE_TAG);

    return price;
  }

  /**
   * Process the <imgUrl> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return imgUrl The content inside the <imgUrl> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static String analyzeImgUrl(XmlPullParser parser) throws IOException,
      XmlPullParserException {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.IMG_URL_TAG);
    String imgUrl = DefaultParsingFunctionsHelper.obtainText(parser);
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.IMG_URL_TAG);

    return imgUrl;
  }

  /**
   * Process the <appreciation> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return appreciation The content inside the <appreciation> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static HashMap<String, String> analyzeAppreciation(XmlPullParser parser) throws
      XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.APPRECIATION_TAG);

    // The appreciation tag contains the values for the attributes 'score' and 'numbOfOpinions'.
    String score = parser.getAttributeValue(ParsingProcessHelper.NAMESPACE,
                                            TagsDefinerHelper.SCORE_ATTR);
    String numbOfOpinions = parser.getAttributeValue(ParsingProcessHelper.NAMESPACE,
                                                     TagsDefinerHelper.NUMB_OF_OPINIONS_ATTR);
    parser.nextTag();
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.APPRECIATION_TAG);

    HashMap<String, String> appreciation = new HashMap<>();
    appreciation.put(TagsDefinerHelper.SCORE_ATTR, score);
    appreciation.put(TagsDefinerHelper.NUMB_OF_OPINIONS_ATTR, numbOfOpinions);

    return appreciation;
  }

  /**
   * Process the <description> tag while iterating through the input data.
   *
   * @param parser The XmlPullParser for the input data.
   *
   * @return description The content inside the <description> tag.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  public static String analyzeDescription(XmlPullParser parser) throws IOException,
      XmlPullParserException {
    String description = "";
    parser.require(XmlPullParser.START_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.DESCRIPTION_TAG);
    // The description of the entry has a prefix which needs to be used in order to access the
    // content.
    String prefix = parser.getPrefix();
    if (prefix.equals(TagsDefinerHelper.PREFIX)) {
      description = DefaultParsingFunctionsHelper.obtainText(parser);
    }
    parser.require(XmlPullParser.END_TAG, ParsingProcessHelper.NAMESPACE,
                   TagsDefinerHelper.DESCRIPTION_TAG);

    return description;
  }
}
