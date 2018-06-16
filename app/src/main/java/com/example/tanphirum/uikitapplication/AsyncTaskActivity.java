package com.example.tanphirum.uikitapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    private static final String EXT_TEXT = "ext_text";
    private TextView mTxtLabel;
    private Button mBtnStartTask;
    private View mViewProgress;

    private StaticAsyncTask mTask;

    private static final String TAG = AsyncTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mViewProgress = findViewById(R.id.view_progress);
        mTxtLabel = findViewById(R.id.txt_label);
        mBtnStartTask = findViewById(R.id.btn_start_task);

        mTask = new StaticAsyncTask(mViewProgress);

        mBtnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.execute("query...");
            }
        });

        if (savedInstanceState != null) {
            mTxtLabel.setText(savedInstanceState.getString(EXT_TEXT));
        }

        //sleepThread();
    }

    private void sleepThread() {
        /*Random r = new Random();
        int n = r.nextInt(11);*/
        int s = 60000;

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class StaticAsyncTask extends AsyncTask<String, Void, String> {

        private View mLocalProgress;

        public StaticAsyncTask(View viewProgress) {
            super();
            this.mLocalProgress = viewProgress;
        }

        @Override
        protected void onPreExecute() {
            mLocalProgress.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello asynctask " + strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLocalProgress.setVisibility(View.GONE);
            mTxtLabel.setText(s);
            Log.d(TAG, "handle string from doInBackgroun = " + s);
        }
    }

    @Override
    protected void onDestroy() {
        mTask.cancel(true);
        Log.d(TAG, "onDestroy called");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXT_TEXT, mTxtLabel.getText().toString());
    }
}
