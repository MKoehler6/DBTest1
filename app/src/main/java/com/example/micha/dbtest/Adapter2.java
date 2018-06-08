package com.example.micha.dbtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Michael on 25.12.2017.
 */

public class Adapter2 extends RecyclerView.Adapter {

    private String[] mDatasetSumme;

    public Adapter2(String[] sA) {
        mDatasetSumme = sA;;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eine_spalte, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mDatasetSumme.length) {
            ((ListViewHolder) holder).mItemText5.setText(mDatasetSumme[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mDatasetSumme.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemText5;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText5 = (TextView) itemView.findViewById(R.id.textzeile5);
        }
    }
}

