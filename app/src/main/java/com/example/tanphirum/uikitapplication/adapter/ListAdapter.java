package com.example.tanphirum.uikitapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanphirum.uikitapplication.R;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter{

    private LayoutInflater mInflater;

    private LinkedList<String> mListData;

    private final int VIEW_PROG = 0;
    private final int VIEW_LAYOUT = 1;
    /*private static MyItemClickListener mListener;

    public ListAdapter(Context context, LinkedList<String> list, MyItemClickListener listener) {
        super();
        mInflater = LayoutInflater.from(context);
        mListData = list;
        mListener = listener;
    }*/

    public ListAdapter(Context context, LinkedList<String> list) {
        super();
        mInflater = LayoutInflater.from(context);
        mListData = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder v;
        if (viewType == VIEW_PROG) {
            v = new MyProgressViewHolder(mInflater.inflate(R.layout.layout_progres_bar, parent, false));
        } else {
            v = new MyViewHolder(mInflater.inflate(R.layout.layout_list_item, parent, false));
        }
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTxtItemName.setText(mListData.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (mListData.size() - 1)) {
            return VIEW_PROG;
        }
        return VIEW_LAYOUT;
    }

    /*@NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.mTxtItemName.setText(mListData.get(position));
    }*/

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void insertItem(String item) {
        mListData.add(item);
    }

    public void insertItem(LinkedList<String> items) {
        mListData.addAll(items);
    }

    public void updateItem(int pos, String item) {
        mListData.set(pos, item);
    }

    public void clearItem() {
        mListData.clear();
    }

    class MyProgressViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtProgress;
        public MyProgressViewHolder(View itemView) {
            super(itemView);
            mTxtProgress = itemView.findViewById(R.id.txt_progress);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImgIcon;
        private TextView mTxtItemName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImgIcon = itemView.findViewById(R.id.img_icon);
            mTxtItemName = itemView.findViewById(R.id.txt_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.myItemClick(getLayoutPosition(), mTxtItemName.getText().toString());

            Toast.makeText(v.getContext(), "Item click on pos " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    private MyItemClickListener mListener;

    public interface MyItemClickListener {
        public void myItemClick(int pos, String item);
    }

    public void setListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

}
