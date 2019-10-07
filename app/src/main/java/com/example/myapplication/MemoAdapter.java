package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private ArrayList<Memo> memoList;

    public class MemoViewHolder extends RecyclerView.ViewHolder{

        TextView mContent;
        TextView mDate;
        public MemoViewHolder(View v){
            super(v);

            mContent = v.findViewById(R.id.mContent);
            mDate = v.findViewById(R.id.mDate);
        }

    }

    public MemoAdapter(ArrayList<Memo> memoL){
        this.memoList = memoL;
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        MemoViewHolder viewHolder = new MemoViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder viewHolder, int position) {

        String date = memoList.get(position).getDate().toString();
        viewHolder.mContent.setText(memoList.get(position).getMemo());
        viewHolder.mDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return (null != memoList ? memoList.size() : 0);
    }


}
