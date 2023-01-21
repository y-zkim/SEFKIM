package com.insacvl.sefkim_flickr.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageModel {
    private String dbId;
    private String imageId;
    private String imageTitle;
    private String imageUrl;
    private String imageFarm;
    private String imageServer;
    private String imageSecret;
    private boolean isFavourite;

    public ImageModel(String dbId, String imageId, String imageTitle, String imageFarm, String imageServer, String imageSecret) {
        this.dbId = dbId;
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        this.imageUrl = "https://farm"+imageFarm+".staticflickr.com/"+imageServer+"/"+imageId+"_"+imageSecret+".jpg";
        this.imageFarm = imageFarm;
        this.imageServer = imageServer;
        this.imageSecret = imageSecret;
    }

    public ImageModel(JSONObject jsonObject) throws JSONException {
        this.imageId = jsonObject.getString("id");
        this.imageTitle = jsonObject.getString("title");
        this.imageFarm = jsonObject.getString("farm");
        this.imageServer = jsonObject.getString("server");
        this.imageSecret = jsonObject.getString("secret");
        this.imageUrl = "https://farm"+imageFarm+".staticflickr.com/"+imageServer+"/"+imageId+"_"+imageSecret+".jpg";
    }

    public ImageModel(String imageId, String imageTitle, String imageUrl) {
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        this.imageUrl = imageUrl;
    }


    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFarm() {
        return imageFarm;
    }

    public void setImageFarm(String imageFarm) {
        this.imageFarm = imageFarm;
    }

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getImageSecret() {
        return imageSecret;
    }

    public void setImageSecret(String imageSecret) {
        this.imageSecret = imageSecret;
    }

    public void setFavourite(boolean b) {
        this.isFavourite = b;
    }
    public boolean getisFavourite(){
        return this.isFavourite;
    }
}
