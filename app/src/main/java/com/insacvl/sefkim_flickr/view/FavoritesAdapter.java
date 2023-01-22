package com.insacvl.sefkim_flickr.view;

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
import com.insacvl.sefkim_flickr.db.DatabaseHelper;
import com.insacvl.sefkim_flickr.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    private List<ImageModel> favoritedPhotos;


    public FavoritesAdapter(List<ImageModel> favoritedPhotos) {
        this.favoritedPhotos = favoritedPhotos;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("----------------> On create View");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        System.out.println("----------------> On create View inflater");
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
            heartButton.setImageResource(doesExist? R.drawable.ic_favourite : R.drawable.heart_insta);
            Picasso.get().load(photo.getImageUrl()).into(photoImageView);
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