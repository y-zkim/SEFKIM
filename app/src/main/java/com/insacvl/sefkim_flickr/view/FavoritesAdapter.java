package com.insacvl.sefkim_flickr.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
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

        public FavoritesViewHolder(@NonNull View itemView,List<ImageModel> favouritePhotos, FavoritesAdapter adapter) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.imageView);
            heartButton = itemView.findViewById(R.id.favourite_button);
            this.adapter = adapter;
            this.favoritedPhotos = favouritePhotos;
        }

        public void bind(final ImageModel photo) {
            Picasso.get().load(photo.getImageUrl()).into(photoImageView);
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