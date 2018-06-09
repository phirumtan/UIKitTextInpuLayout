package com.example.tanphirum.uikitapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.tanphirum.uikitapplication.adapter.ListAdapter;
import com.example.tanphirum.uikitapplication.callback.EndlessRecyclerViewScrollListener;

import java.util.LinkedList;

public class RecyclerViewActivity extends AppCompatActivity /*implements MyItemClickListener*/{

    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    private EndlessRecyclerViewScrollListener mListener;

    private RecyclerView mRcv;
    private ListAdapter adapter;
    private LinearLayoutManager manager;
    private LinkedList<String> mListData;

    private Button mBtnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mListData = new LinkedList<>();

        mBtnTest = findViewById(R.id.btn_test);

        mRcv = findViewById(R.id.rcv);
        //adapter = new ListAdapter(this, createList(20), this);
        adapter = new ListAdapter(this, createList(20));
        manager = new LinearLayoutManager(this);
        //manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //manager = new GridLayoutManager(this, 3);
        //manager = new GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false);
        //manager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mRcv.setLayoutManager(manager);

        adapter.setListener(new ListAdapter.MyItemClickListener() {
            @Override
            public void myItemClick(int pos, String item) {
                mBtnTest.setText("new callback : Pos " + pos + " item " + item);
            }
        });

        mListener = new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(final int page, int totalItemsCount, RecyclerView view) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int totalCurrentSize = adapter.getItemCount();
                        adapter.insertItem(createList(10));
                        adapter.notifyItemRangeChanged(totalCurrentSize, adapter.getItemCount());
                        Log.d(TAG, "Page " + page);
                    }
                }, 500);

            }
        };

        mRcv.addOnScrollListener(mListener);

        mRcv.setAdapter(adapter);
    }

    private Handler mHandler = new Handler();

    private LinkedList<String> createList(int amount) {
        for (int i=0; i < amount; i++) {
            mListData.add("item " + i);
        }
        return mListData;
    }

    /*@Override
    public void MyItemClick(int pos, String item) {
        Log.d(TAG, "Pos " + pos + " item " + item);
        ((Button)findViewById(R.id.btn_test)).setText("Pos " + pos + " item " + item);
    }*/
}
