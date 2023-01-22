package com.insacvl.sefkim_flickr.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.insacvl.sefkim_flickr.adapters.ImageListAdapter;
import com.insacvl.sefkim_flickr.model.ImageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrImages extends AsyncTask<String, Void, JSONArray> {
    private static final String API_KEY = "3b7065148e6472b239e13f9ecde2b260";
    private static final String ENDPOINT = "https://www.flickr.com/services/rest/";
    private static final String SEARCH_METHOD = "?method=flickr.photos.search&api_key=";
    private static final String RECENT_METHOD = "?method=flickr.photos.getRecent&api_key=";

    private ListView listView;
    private Context context;

    public FlickrImages(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }
    @Override
    protected JSONArray doInBackground(String... strings) {
        String query = strings[0];
        if(query.equals("")) System.out.println("La recherche est vide");
        JSONObject data;
        try{
            data =  fetchImagesFromAPI(SEARCH_METHOD, query);
            if (data.getString("stat").equals("fail")){
                data =  fetchImagesFromAPI(RECENT_METHOD, "");
            }
            System.out.println("data = ------>" + data);
            JSONObject photos = data.getJSONObject("photos");
            return photos.getJSONArray("photo");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject fetchImagesFromAPI(String searchMethod, String query) throws IOException, JSONException {
        String url = ENDPOINT + searchMethod + API_KEY + "&format=json&nojsoncallback=1"+(searchMethod.equals(SEARCH_METHOD)? "&text=" + query : "");
        System.out.println("url-----------------------------> = " + url);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        return new JSONObject(json.toString());
    }

    @Override
    protected void onPostExecute(JSONArray jsonImages) {
        try {
            List<ImageModel> images = convertJSONArrayToList(jsonImages);
            if (images != null && images.size()!=0) {
                System.out.println("result -->" + images.toString());
                ImageListAdapter adapter = new ImageListAdapter(context, images);
                listView.setAdapter(adapter);
                listView.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e){
            System.out.println("Exception thrown from FlickrImages due to error in reading JSON images");
            e.printStackTrace();
        }
    }
    private List<ImageModel> convertJSONArrayToList(JSONArray jsonImages) throws JSONException{
        List<ImageModel> images = new ArrayList<>();
        if(jsonImages==null){
            return null;
        }
        for (int i = 0; i < jsonImages.length(); i++) {
            JSONObject jsonObject = jsonImages.getJSONObject(i);
            ImageModel imageModel = new ImageModel(jsonObject);
            images.add(imageModel);
        }
        return images;
    }
}
