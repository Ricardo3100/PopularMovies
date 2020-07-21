package com.example.popularmovies;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class ParserClass {

    private static final String LOG_TAG = ParserClass.class.getSimpleName();

    private ParserClass() {
    }

    public static List<RecyclerAdapter> fetchRecyclerAdapterData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<RecyclerAdapter> recyclerAdapters  = extractFeatureFromJson(jsonResponse);

        return recyclerAdapters;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List <RecyclerAdapter> extractFeatureFromJson(String movieinfo) {
        if (TextUtils.isEmpty(movieinfo)) {
            return null;
        }

        List<RecyclerAdapter> recyclerinfo = new ArrayList<>();


        try {

            JSONObject baseJson = new JSONObject(movieinfo);
            JSONArray acess = baseJson.getJSONObject("results").getJSONArray("original_title");
            JSONObject object = new JSONObject( movieinfo);

            for (int i = 0; i < acess.length(); i++) {

                JSONObject jsonObject = acess.getJSONObject(i);
                String Title = jsonObject.getString("original_title");
//                JSONArray tag = jsonObject.getJSONArray("tags");

                String ReleaseDate = jsonObject.getString("release_date");

                String MoviePoster = jsonObject.getString("poster_path");

                String VoteAverage = jsonObject.getString("vote_average");
                String Plotsynopsis = jsonObject.getString("overview");



                RecyclerViewModelData recycleinfolist = new ParserClass( VoteAverage, ReleaseDate,Title,MoviePoster, Plotsynopsis);

                    RecyclerViewModelData.add(recycleinfolist);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }

        return recyclerinfo;
    }

}
