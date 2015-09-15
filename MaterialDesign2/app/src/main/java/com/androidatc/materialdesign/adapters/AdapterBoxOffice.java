package com.androidatc.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.androidatc.materialdesign.R;
import com.androidatc.materialdesign.anim.AnimationUtils;
import com.androidatc.materialdesign.extras.Constants;
import com.androidatc.materialdesign.network.VolleySingleton;
import com.androidatc.materialdesign.pojo.Movie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by jorgecasariego on 7/9/15.
 */
public class AdapterBoxOffice extends RecyclerView.Adapter<AdapterBoxOffice.ViewHolderBoxOffice>{

    // 1
    LayoutInflater layoutInflater;

    // 9
    ArrayList<Movie> listMovies = new ArrayList<>();

    // 12
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    // 18
    private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    // 21 Creamoa variable para manejar animaciones
    private int previousPosition = 0;

    // 2
    public AdapterBoxOffice(Context context) {
        // 3
        layoutInflater = LayoutInflater.from(context);

        // 13
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    // 10
    public void setMovieList(ArrayList<Movie> listMovies){
        this.listMovies = listMovies;
        notifyItemRangeChanged(0, listMovies.size());
    }

    @Override
    public ViewHolderBoxOffice onCreateViewHolder(ViewGroup parent, int viewType) {
        // 4
        View view = layoutInflater.inflate(R.layout.custom_movie_box_office, parent, false);

        // 5
        ViewHolderBoxOffice viewHolder = new ViewHolderBoxOffice(view);

        // 6
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderBoxOffice holder, int position) {
        // 11
        //Movie currentMovie = new Movie();
        Movie currentMovie = listMovies.get(position);

        holder.movieTitle.setText(currentMovie.getTitle());

        // 17
        Date movieReleaseDate = currentMovie.getReleaseDateTheater();
        if(movieReleaseDate != null){
            String formattedDate = dateFormatter.format(movieReleaseDate);
            holder.movieRelease.setText(formattedDate);
        } else {
            holder.movieRelease.setText(Constants.NA);
        }

        // 19
        int audienceScore = currentMovie.getAudienceScore();
        if(audienceScore == -1){
            holder.movieAudienceScore.setRating(0.0F);
            holder.movieAudienceScore.setAlpha(0.5F);
        } else {
            holder.movieAudienceScore.setRating(audienceScore / 20.0F);
            holder.movieAudienceScore.setAlpha(1.0F);
        }

        // 20 Si queremos animar nuestra lista lo debemos hacer aqui
        //    Para ello creamos una nueva clase que se encargue de la animacion de cada item de la lista

        //    Scroll down
        if(position > previousPosition){

            //Going down
            AnimationUtils.animate(holder, true);
        } else {
            //Going up
            AnimationUtils.animate(holder, false);
        }

        previousPosition = position;




        // 14
        String urlThumbnail = currentMovie.getUrlThumbnail();

        // 16
        loadImages(urlThumbnail, holder);
    }

    // 17
    private void loadImages(String urlThumbnail, final ViewHolderBoxOffice holder) {
        if(!urlThumbnail.equals(Constants.NA)){
            imageLoader.get(urlThumbnail, new ImageLoader.ImageListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    //En el caso de error cargamos una imagen por defecto
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.movieThumbnail.setImageBitmap(response.getBitmap());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        // 15
        return listMovies.size();
    }

    static class ViewHolderBoxOffice extends RecyclerView.ViewHolder{
        // 7
        private ImageView movieThumbnail;
        private TextView movieTitle;
        private TextView movieRelease;
        private RatingBar movieAudienceScore;

        public ViewHolderBoxOffice(View itemView) {
            super(itemView);

            // 8
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movieThumbnail);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieRelease = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            movieAudienceScore = (RatingBar) itemView.findViewById(R.id.movieAudienceScore);

        }
    }
}
