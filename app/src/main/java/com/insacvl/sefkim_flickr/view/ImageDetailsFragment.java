package com.insacvl.sefkim_flickr.view;
/**
* @Author : ZKIM Youssef
*/
import static android.os.Environment.getExternalStorageDirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.DownloadImageTask;
import com.insacvl.sefkim_flickr.db.DatabaseHelper;
import com.insacvl.sefkim_flickr.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                      image_item_Fragment                                        *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * The details have the image, it's description, as well as the like button and the download.      *
 * Those features are handled by this class by calling the necessary adapter and putting listeners.*
 *                                                                                                 *
 *=================================================================================================*

 */

public class ImageDetailsFragment extends Fragment {
    private ImageView imageHolder;
    private TextView imageTitle;
    private ImageButton download;
    private ImageButton heartButton;
    DatabaseHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_details, container, false);
        dbHelper =  new DatabaseHelper(view.getContext());
        boolean exists = dbHelper.checkPhotoExistenceById(getArguments().getString("imageId"));
        imageHolder = view.findViewById(R.id.imageView);
        imageTitle = view.findViewById(R.id.title);
        Picasso.get().load(getArguments().getString("imageUrl")).into(imageHolder);
        imageTitle.setText(getArguments().getString("imageText"));
        download=view.findViewById(R.id.download_button);
        heartButton = view.findViewById(R.id.favourite_button);
        heartButton.setImageResource(exists?
                R.drawable.ic_favourite :
                R.drawable.heart_insta);
//        Handles the download
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadImageTask(getContext(),getArguments()).execute(getArguments().getString("imageUrl"));
            }
        });
        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the image exists in the database
                boolean exists = dbHelper.checkPhotoExistenceById(getArguments().getString("imageId"));
                if(exists){
//                    if it's already in the database -> the user want's to unlike it -> delete from the database
                    dbHelper.deleteIfExistsById(getArguments().getString("imageId"));
//                    changing the favourite icon back to normal (unliked)
                    heartButton.setImageResource(R.drawable.heart_insta);
                }else{
//                    if not then we do the inverse
                    dbHelper.insertImageInDB(new ImageModel(getArguments().getString("imageId"),getArguments().getString("imageTitle"), getArguments().getString("imageUrl")));
                    heartButton.setImageResource(R.drawable.ic_favourite);
                }
            }
        });

        return view;
    }
}
