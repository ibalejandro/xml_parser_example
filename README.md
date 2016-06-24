# xml_parser_example
This app is the first approach to a parser which converts an XML input file with information about the best hotels in Rome into a user-friendly list which presents the content. The parser is limited to a certain number of predefined tags. The content of the XML file can be modified and adding or removing <hotel> entries is also possible.

### Instructions
* Download this project.

* Import the downloaded project into your Android Studio.

* In this project, you will find the attribute 'URL' where the XML input file is located under ${PROJECT_HOME}/app/src/main/java/<package>/activites/MainActivity.java. If you open that URL you will see the feed for the parser. If you want, you can copy the content, paste it in your preferred  editor and modify it, add other entries or remove the existing ones. When you are done with the modifications, copy the new content.

* Go to [Mocky]. Paste the content you copied into the 'Body' section and change the 'Content Type' option as following:

    ```sh
    Content Type = application/xhtml+xml
    ```
* Press the 'Generate my HTTP Response' button and you will see something like this:

    ```sh
    Your link is ready: http://www.mocky.io/v2/...
    ```

* Copy the link and replace the 'URL' attribute on the MainActivity.java file with it.

* Connect an Android device to your computer and make sure that it is recognized successfully.

* You need a stable connection to the Internet in order to allow the download of the XML input file. Otherwise you won't see anything on the screen.

* Now you are able to run the app and see the result of the Parsing process.
 
### References
* [Android Developers] - Parsing XML Data  (Visited on June 2016).
* [Hermosa Programación] - Tutorial De Parsing Xml En Android Con XmlPullParser (Visited on June 2016).
* [Android Developers 2] - Librería XmlPullParser (Visited on April 2016).
* [James Elsey] - Extracting out your AsyncTasks into separate classes makes your code cleaner (Visited on June 2016).
* [Mocky] - Mock your HTTP responses to test your REST API (Visited on June 2016).


[//]: # (These are reference links used in the body of this note)
   [Android Developers]: <https://developer.android.com/training/basics/network-ops/xml.html?hl=es#analyze>
   [Hermosa Programación]: <http://www.hermosaprogramacion.com/2015/07/tutorial-parsing-xml-con-xmlpullparser-android/>
   [Android Developers 2]: <https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html?hl=es>
   [James Elsey]: <http://www.jameselsey.co.uk/blogs/techblog/extracting-out-your-asynctasks-into-separate-classes-makes-your-code-cleaner/>
   [Mocky]: <http://www.mocky.io/>
