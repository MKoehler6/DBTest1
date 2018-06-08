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

public class Adapter extends RecyclerView.Adapter {

    private String[] mDatasetA;
    private String[] mDatasetB;

    public Adapter(String[] sA, String[] sB) {
        mDatasetA = sA;
        mDatasetB = sB;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vier_spalten, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position * 2 + 1 < mDatasetA.length) {
            ((ListViewHolder) holder).mItemText1.setText(mDatasetA[position * 2]);
            ((ListViewHolder) holder).mItemText2.setText(mDatasetA[position * 2 + 1]);
            ((ListViewHolder) holder).mItemText3.setText(mDatasetB[position * 2]);
            ((ListViewHolder) holder).mItemText4.setText(mDatasetB[position * 2 + 1]);
        }
    }

    @Override
    public int getItemCount() {
        return mDatasetA.length / 2;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemText1;
        public TextView mItemText2;
        public TextView mItemText3;
        public TextView mItemText4;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText1 = (TextView) itemView.findViewById(R.id.textzeile1);
            mItemText2 = (TextView) itemView.findViewById(R.id.textzeile2);
            mItemText3 = (TextView) itemView.findViewById(R.id.textzeile3);
            mItemText4 = (TextView) itemView.findViewById(R.id.textzeile4);

        }
    }
}

