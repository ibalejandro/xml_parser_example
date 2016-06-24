package com.example.alejandrosanchezaristizabal.xmlparserexample.network;

import android.content.Context;
import android.os.AsyncTask;

import com.example.alejandrosanchezaristizabal.xmlparserexample.interfaces.AsyncTaskCompleteListener;
import com.example.alejandrosanchezaristizabal.xmlparserexample.models.Hotel;
import com.example.alejandrosanchezaristizabal.xmlparserexample.utils.ParsingProcessHelper;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by alejandrosanchezaristizabal on 22/06/16.
 *
 * <h1>HttpConnectionClient</h1>
 * Creates and manages the HTTP Connection in order to receive the input data and be able to start
 * the Parsing process.
 */
public class HttpConnectionClient extends AsyncTask<String, Void, List<Hotel>> {

  private static final String TAG = "HttpConnectionClient";

  // HTTP Connection attributes.
  private static final int READ_TIME_OUT = 10000;
  private static final int CONNECT_TIME_OUT = 15000;
  private static final String GET_REQUEST = "GET";

  private Context context;
  private AsyncTaskCompleteListener<List<Hotel>> listener;

  public HttpConnectionClient(Context context, AsyncTaskCompleteListener<List<Hotel>> listener) {
    this.context = context;
    this.listener = listener;
  }

  @Override
  protected List<Hotel> doInBackground(String... urls) {
    try {
      InputStream inputStream = getXmlFileAsInputStream(urls[0]);
      return analyzeInput(inputStream);
    }
    catch (IOException e) {
      return null; // A Network error has occurred.
    }
    catch (XmlPullParserException e) {
      return null; // An XML Parsing error has occurred.
    }
  }

  /**
   * Makes the connection to get the information contained in the XML input file.
   *
   * @param urlString The URL where the XML input file is located.
   *
   * @return inputStream The connection's result which will be the feed.
   *
   * @throws IOException
   */
  private InputStream getXmlFileAsInputStream(String urlString) throws IOException {
    InputStream inputStream;

    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setReadTimeout(READ_TIME_OUT);
    conn.setConnectTimeout(CONNECT_TIME_OUT);
    conn.setRequestMethod(GET_REQUEST);
    conn.setDoInput(true);
    conn.connect(); // Initializes the request.
    inputStream = conn.getInputStream(); // Gets the result.

    return inputStream;
  }

  /**
   * Retrieves the result of the Parsing process.
   *
   * @param inputStream The input data for the Parser.
   *
   * @return result The list of the main Objects parsed.
   *
   * @throws XmlPullParserException
   * @throws IOException
   */
  private List<Hotel> analyzeInput(InputStream inputStream) throws XmlPullParserException,
      IOException {
    ParsingProcessHelper parsingProcessHelper = new ParsingProcessHelper();
    List<Hotel> result = null;

    try {
      result = parsingProcessHelper.parse(inputStream);
    }
    finally {
      if (inputStream != null) inputStream.close();
    }

    return result;
  }

  @Override
  protected void onPostExecute(List<Hotel> result) {
    super.onPostExecute(result);
    listener.onTaskComplete(result); //It's the callback to notify the AsyncTask completion.
  }
}
