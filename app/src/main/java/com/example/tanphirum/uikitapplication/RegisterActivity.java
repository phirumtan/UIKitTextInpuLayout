package com.example.tanphirum.uikitapplication;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mTxtInputUsername, mTxtInputPass, mTxtInputConfPass;
    private TextInputEditText mEdtUsername, mEdtPass, mEdtConfPass;
    private Button mBtnRegister;
    private String pass, confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTxtInputUsername = findViewById(R.id.txt_input_username);
        mTxtInputPass = findViewById(R.id.txt_input_pass);
        mTxtInputConfPass = findViewById(R.id.txt_input_conf_pass);

        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPass = findViewById(R.id.edt_pass);
        mEdtConfPass = findViewById(R.id.edt_conf_pass);

        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String username = mEdtUsername.getText().toString().trim();
                pass = mEdtPass.getText().toString().trim();
                confPass = mEdtConfPass.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    mTxtInputUsername.setError("Usename can not null");
                } else if (!pass.contentEquals(confPass)) {
                    mTxtInputPass.setError("pass need same with confirm password!!!");
                    mEdtPass.requestFocus();
                } else {
                    mTxtInputUsername.setError(null);
                    mTxtInputUsername.setError(null);
                    new AlertDialog.Builder(v.getContext())
                            .setIcon(v.getResources().getDrawable(R.mipmap.ic_launcher))
                            .setTitle("Register form")
                            .setCancelable(false)
                            .setMessage("Are you sure to register with this user?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(v.getContext(), "Registered successful.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(v.getContext(), "User cancel register.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });
    }
}
