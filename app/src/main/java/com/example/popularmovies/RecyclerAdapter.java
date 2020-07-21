package com.example.popularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView original_title;
        public TextView poster_path;
        public TextView overview;
        public TextView vote_average;
        public TextView release_date;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_details, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
              // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);
holder.release_date.setText(mDataset[position]);
        holder.original_title.setText(mDataset[position]);
        holder.poster_path.setText(mDataset[position]);
        holder.overview.setText(mDataset[position]);
        holder.vote_average.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView original_title;
        public TextView poster_path;
        public TextView overview;
        public TextView vote_average;
        public TextView release_date;

//this should attach the views to the view holder making them visible in the ui
        public ViewHolder(View itemView) {
            super(itemView);
            original_title = (TextView) itemView.findViewById(R.id.original_title);
            overview = (TextView) itemView.findViewById(R.id.overview);
            vote_average = (TextView) itemView.findViewById(R.id.vote_average);
            release_date = (TextView) itemView.findViewById(R.id.release_date)
            ;
        }
    }
}