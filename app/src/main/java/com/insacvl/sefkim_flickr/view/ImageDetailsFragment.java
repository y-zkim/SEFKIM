package com.insacvl.sefkim_flickr.view;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.DownloadImageTask;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageDetailsFragment extends Fragment {
    private ImageView imageHolder;
    private TextView imageTitle;
    private ImageButton download;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_details, container, false);
        imageHolder = view.findViewById(R.id.imageView);
        imageTitle = view.findViewById(R.id.title);
        System.out.println("=========================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ getArguments().getString("imageUrl"));
        Picasso.get().load(getArguments().getString("imageUrl")).into(imageHolder);
        imageTitle.setText(getArguments().getString("imageText"));
        download=view.findViewById(R.id.download_button);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadImageTask(getContext(),getArguments()).execute(getArguments().getString("imageUrl"));
//                new DownloadImageTask(view.findViewById(R.id.imageView)).execute(getArguments().getString("imageUrl"));
//                System.out.println("trying to make bitmap");
//                Bitmap downloadable=downloadImage(getArguments().getString("imageUrl"));
////                String filename = getArguments().getString("imageText")+".png"; // The name of the file you want to save the Bitmap as
//                System.out.println("bitmap created");
//                String filename = "image.png"; // The name of the file you want to save the Bitmap as
//
//                try {
//                    File file = new File(getExternalStorageDirectory(), filename);
//                    FileOutputStream fos = new FileOutputStream(file);
//                    downloadable.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                    System.out.println("file compressed");
//                    fos.flush();
//                    System.out.println("file flushed");
//                    fos.close();
//                    System.out.println("file closed");
//
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    System.out.println("intent initiated");
//                    intent.setDataAndType(Uri.fromFile(file), "image/*");
//                    System.out.println("data setted to intent");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    System.out.println("flags setted");
//                    startActivity(intent);
//                    System.out.println("acitivity started");
////                    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
////                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
////                        ActivityCompat.requestPermissions(this,
////                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
////                    } else {
////                        // save the bitmap to local storage
////                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

        return view;
    }

    public Bitmap downloadImage(String urlAddress){
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlAddress);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;


    }
}
