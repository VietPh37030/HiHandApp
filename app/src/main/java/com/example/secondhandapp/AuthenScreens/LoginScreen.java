package com.example.secondhandapp.AuthenScreens;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondhandapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {

    private TextInputEditText phoneNumberEditText;
    private TextInputLayout phoneNumberInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        phoneNumberEditText = findViewById(R.id.signup_edit_phonenumber);
        phoneNumberInputLayout = findViewById(R.id.textInputLayout_phone);

        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    phoneNumberInputLayout.setError("Phone number cannot be empty");
                }
                 else  if (s.length() < 10) {
                    phoneNumberInputLayout.setError("Phone number must have at least 10 digits");
                } else if (s.length() > 10) {
                    phoneNumberInputLayout.setError("");
                }else {
                    phoneNumberInputLayout.setError(null);
                }
            }
        });

        // Đoạn mã khác của onCreate()...
    }
}
