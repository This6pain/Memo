package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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
        TextView mTime;
        public MemoViewHolder(View v){
            super(v);

            mContent = v.findViewById(R.id.mContent);
            mDate = v.findViewById(R.id.mDate);
            mTime = v.findViewById(R.id.mTime);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = memoList.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(v.getContext(), DetailMemoActivity.class);
                    intent.putExtra("position", position);
                    v.getContext().startActivity(intent);

                }
            });

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

        String date = memoList.get(position).getDate();
        if(memoList.get(position).getMemo().length()>30){
            viewHolder.mContent.setText(memoList.get(position).getMemo().substring(0,30)+"...");
        }else{
            viewHolder.mContent.setText(memoList.get(position).getMemo());
        }
        viewHolder.mDate.setText(date.substring(0,10));
        viewHolder.mTime.setText(date.substring(11));

    }

    @Override
    public int getItemCount() {
        return (null != memoList ? memoList.size() : 0);
    }


}
