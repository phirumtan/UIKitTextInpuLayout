package com.example.tanphirum.uikitapplication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tanphirum.uikitapplication.task.AsyncTaskLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final String TAG = AsyncTaskLoaderActivity.class.getSimpleName();

    private TextInputLayout mTxtInput;
    private TextInputEditText mEdtUsername;
    private Button mBtnGetData;

    private View mViewProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);

        mTxtInput = findViewById(R.id.txt_input_username);
        mEdtUsername = findViewById(R.id.edt_username);
        mBtnGetData = findViewById(R.id.btn_get_data);

        mViewProgress = findViewById(R.id.view_progress);

        if (getSupportLoaderManager().getLoader(0)  != null)
            getSupportLoaderManager().initLoader(0, null, this);

        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connectivityManager != null) {
                    networkInfo = connectivityManager.getActiveNetworkInfo();
                }

                if (networkInfo != null && networkInfo.isConnected()) {
                    getSupportLoaderManager().restartLoader(0, null, AsyncTaskLoaderActivity.this);
                } else
                    Toast.makeText(v.getContext(), "No internet connection!!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        mViewProgress.setVisibility(View.VISIBLE);
        return new AsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        mViewProgress.setVisibility(View.GONE);
        Log.d(TAG, "data return =" + data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.optBoolean("status")) {
                JSONObject objResponse = jsonObject.optJSONObject("response");
                mEdtUsername.setText(objResponse.optString("fullname"));
                Log.d(TAG, "objResponse = " + objResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
