package com.example.tanphirum.uikitapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mTxtInputUsername, mTxtInputPass, mTxtInputConfPass;
    private TextInputEditText mEdtUsername, mEdtPass, mEdtConfPass, mEdtPhone;
    private Button mBtnRegister;
    private String pass, confPass;
    private Spinner mSpPnone;

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

        mEdtPhone = findViewById(R.id.edt_phone);
        mSpPnone = findViewById(R.id.sp_phone);

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

        final ArrayAdapter<CharSequence> phoneAdapter = ArrayAdapter.createFromResource(this, R.array.array_phone,
                android.R.layout.simple_spinner_item);
        phoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpPnone.setAdapter(phoneAdapter);

        mSpPnone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "item click =" + phoneAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mEdtPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        if (Patterns.EMAIL_ADDRESS.matcher("").matches()) {

        } else {

        }

    }
}
