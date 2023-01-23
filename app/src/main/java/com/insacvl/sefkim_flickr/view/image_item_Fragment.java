package com.insacvl.sefkim_flickr.view;
/**
* @Author : ZKIM Youssef
*/
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.R;

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
 * This class is called to inflate the image item fragment witch is the card containing the image  *
 *                                                                                                 *
 *=================================================================================================*

 */
public class image_item_Fragment extends Fragment {
    View image_item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_details, container, false);
        image_item = view.findViewById(R.id.imageView);
        image_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("imageUrl", image.getImageUrl());
//                bundle.putString("imageText", image.getImageTitle());
////                Navigation.findNavController(view).navigate(R.id.confirmationAction, bundle);
//                NavController navController = Navigation.findNavController(view);
//                navController.navigate(R.id.action_navigation_category_to_imageDetailsFragment,bundle);
            }
        });
        return view;
    }
}
