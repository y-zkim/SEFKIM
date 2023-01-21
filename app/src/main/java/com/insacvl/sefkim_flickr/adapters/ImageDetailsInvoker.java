package com.insacvl.sefkim_flickr.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.view.ImageDetailsFragment;


public class ImageDetailsInvoker extends Fragment{
    private View imageDetailsView;
    private ImageView imageView;

    @SuppressLint("ResourceType")
    public void invoke(){
        System.err.println("image clicked to enter image Frag");
        Fragment fragment = new ImageDetailsFragment();
//        System.out.println("fragment manager ---->"+ getFragmentManager());
//        System.out.println("fragment manager from activity ---->"+ getActivity().get());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.layout.activity_main, fragment).commit();
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        imageDetailsView=inflater.inflate(R.layout.fragment_image_details, container, false);
//        return imageDetailsView;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        imageView = imageDetailsView.findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
}
