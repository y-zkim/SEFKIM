package com.insacvl.sefkim_flickr.adapters;
/**
* @Author : ZKIM Youssef
*/
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.database.DatabaseHelper;
import com.insacvl.sefkim_flickr.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                        FavoritesAdapter                                         *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * The FavouritesAdapter is called to handle the FavouritesFragment display, it creates the View   *
 * holders and bind the data and display it on Favourites Fragment                                 *
 *                                                                                                 *
 * The class also defines a nested static class called "FavoritesViewHolder" which extends the     *
 * RecyclerView.ViewHolder class. This class is used to hold and display the information for a     *
 * single favorited photo.
 *
 * The adapter handles also the click on the like button -> if it's already in the favourites, the *
 * adapter handle the click as a delete from the database and removal from the favourites screen.  *
 * When clicking the image the handler navigate to the ImageDetailsFragment which is the fragment  *
 * that contains the details of this photo.                                                        *
 *                                                                                                 *
 *=================================================================================================*

 */


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    private List<ImageModel> favoritedPhotos;


    public FavoritesAdapter(List<ImageModel> favoritedPhotos) {
        this.favoritedPhotos = favoritedPhotos;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new FavoritesViewHolder(view, favoritedPhotos, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        ImageModel photo = favoritedPhotos.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return favoritedPhotos.size();
    }

    static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoImageView;
        private ImageButton heartButton;
        private List<ImageModel> favoritedPhotos;
        private FavoritesAdapter adapter;

        // Creates the favourites holder
        public FavoritesViewHolder(@NonNull View itemView,List<ImageModel> favouritePhotos, FavoritesAdapter adapter) {
            super(itemView);
            System.out.println("----------------> in Favourites View Holder");
            photoImageView = itemView.findViewById(R.id.imageView);
            heartButton = itemView.findViewById(R.id.favourite_button);
            this.adapter = adapter;
            this.favoritedPhotos = favouritePhotos;
        }

        public void bind(final ImageModel photo) {
            System.out.println("Im in the bind method");
            DatabaseHelper dbHelper = new DatabaseHelper(heartButton.getContext());
            boolean doesExist = dbHelper.checkPhotoExistenceById(photo.getImageId());
            // On creation if the image exists in the database we colorate the favourite button
            heartButton.setImageResource(doesExist? R.drawable.ic_favourite : R.drawable.heart_insta);
            Picasso.get().load(photo.getImageUrl()).into(photoImageView);
            // if the image is clicked -> navigate to the fragment containing its details
            photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("imageUrl", photo.getImageUrl());
                    bundle.putString("imageId", photo.getImageId());
                    bundle.putString("imageText", photo.getImageTitle());
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_navigation_category_to_imageDetailsFragment,bundle);
                }
            });
            // Listener on the favourites button to remove the concerened image from the fav. screen
            heartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    favoritedPhotos.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, favoritedPhotos.size());
                }
            });
        }

    }
}