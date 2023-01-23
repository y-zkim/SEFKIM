package com.insacvl.sefkim_flickr.adapters;
/**
* @Author : ZKIM Youssef
*/
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                    DownloadImageTask                                            *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * The class DownloadImageTask is extending AsyncTask and is used to download an image from a given*
 * URL and save it to the device's external public downloads directory.                            *
 * It takes in a context and bundle arguments as parameters in its constructor, which are used to  *
 * set the file name of the downloaded image. The doInBackground() method is used to perform the   *
 * download of the image in a background thread, while the onPostExecute() method is used to handle*
 * the image once it has been downloaded. The image is saved to the device using the               *
 * DownloadManage                                                                                  *
 * service, and the file name is set to the value of the "imageText" key in the bundle arguments.  *
 * The code also includes a catch block for any IOExceptions that may occur during the download and*
 * save process.                                                                                   *
 *                                                                                                 *
 *=================================================================================================*

*/

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    Bundle arguments;
    String url;
    // constructor to set the context and bundle arguments
    public DownloadImageTask(Context context, Bundle arguments){
        this.context=context;
        this.arguments=arguments;
    }
    // method to download image in background thread
    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            this.url=urls[0];
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // method to handle the image once it has been downloaded
    @SuppressLint("WrongThread")
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            FileOutputStream out = null;
            try {
                // using DownloadManager service to save image to device
                DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(this.url);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                // setting the file name to the value of "imageText" in bundle arguments
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,arguments.getString("imageText")+".png");
                Long reference = downloadManager.enqueue(request);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}