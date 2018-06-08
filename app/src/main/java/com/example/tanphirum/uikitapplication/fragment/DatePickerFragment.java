package com.example.tanphirum.uikitapplication.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.tanphirum.uikitapplication.RegisterActivity;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (getActivity() == null)
            return null;
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof RegisterActivity) {
            RegisterActivity registerActivity = (RegisterActivity) getActivity();
            registerActivity.handleDatePickerValue(year, month, dayOfMonth);
        }
    }
}
