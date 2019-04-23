package com.moeyeo.argipredictionapp.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moeyeo.argipredictionapp.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class VegAdapter extends RecyclerView.Adapter<VegAdapter.FilmHolder> {

    private List<vegDetails> films;
    public OnFilmClickListener onFilmClickListener;
    private Context mContext;
    List<Double> prediction;
    NumberFormat formatter = new DecimalFormat("#0.00");

    public VegAdapter(Context context,List<vegDetails> films, OnFilmClickListener onFilmClickListener) {
        this.films = films;
        this.onFilmClickListener = onFilmClickListener;
        mContext = context;
    }

    public void setFilms(List<vegDetails> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.veg, parent, false);
        return new FilmHolder(view, onFilmClickListener);
    }

    @Override
    public void onBindViewHolder(FilmHolder holder, int position) {
        vegDetails item = films.get(position);
        holder.film = item;
        holder.title.setText(item.Name);
        holder.avgPrice.setText(formatter.format(item.getAvgPrice())+" THB");
        holder.harvest.setText("harvested in : "+item.getHarvestTime()+" months.");
        Glide.with(mContext).load(item.getImgUrl()).centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (films == null) {
            return 0;
        } else {
            return films.size();
        }
    }

    static class FilmHolder extends RecyclerView.ViewHolder {

        vegDetails film;
        TextView title;
        TextView avgPrice;
        ImageView img;
        TextView harvest;

        FilmHolder(View itemView, final OnFilmClickListener onFilmClickListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView3);
            img = (ImageView) itemView.findViewById(R.id.imageView3);
            avgPrice = (TextView) itemView.findViewById(R.id.priceMonth);
            harvest = (TextView) itemView.findViewById(R.id.harvest1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFilmClickListener.onFilmClick(film);
                }
            });
        }
    }

    public interface OnFilmClickListener {
        void onFilmClick(vegDetails film);
    }
}
