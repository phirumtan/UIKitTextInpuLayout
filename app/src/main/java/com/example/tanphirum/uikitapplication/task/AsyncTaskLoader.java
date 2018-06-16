package com.example.tanphirum.uikitapplication.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.tanphirum.uikitapplication.webservice.NetworkUtils;

public class AsyncTaskLoader extends android.support.v4.content.AsyncTaskLoader<String>{

    public AsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getDataFromServer(null);
    }
}
