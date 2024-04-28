package com.example.secondhandapp.AuthenScreens;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondhandapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpScreen extends AppCompatActivity {
    private TextInputEditText phoneNumberEditText;
    private TextInputEditText fullNameEditText;
    private TextInputEditText passWordEditText;
    private TextInputLayout phoneNumberInputLayout;
    private TextInputLayout fullNameInputLayout;
    private TextInputLayout passWordInputLayout;
    private CheckBox checkbox_chinhsach;
    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            phoneNumberEditText = findViewById(R.id.signup_edit_phonenumber);
            phoneNumberInputLayout = findViewById(R.id.textInputLayout_phone);
            fullNameEditText = findViewById(R.id.signup_edit_username);
            fullNameInputLayout = findViewById(R.id.textInputLayout_user);
            passWordEditText = findViewById(R.id.signup_edit_password);
            passWordInputLayout = findViewById(R.id.textInputLayout_pass);
            phoneNumberEditText = findViewById(R.id.signup_edit_phonenumber);
            btn_signup = findViewById(R.id.signup_btn_Signup);
            //checkbox
            checkbox_chinhsach = findViewById(R.id.signup_checkbox_terms);

            //
            /// validate soóđiện thoại
            phoneNumberEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s)) {
                        phoneNumberInputLayout.setError("Phone number cannot be empty");
                    } else if (s.length() < 10) {
                        phoneNumberInputLayout.setError("Phone number must have at least 10 digits");
                    } else if (s.length() > 10) {
                        phoneNumberInputLayout.setError("");
                    } else {
                        phoneNumberInputLayout.setError(null);
                    }
                }
            });
            /// close validate số điện thoại


            /// validate họ và tên
            fullNameEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String fullName = s.toString().trim();
                    // Kiểm tra xem có đủ cả họ, tên và tên đệm không
                    String[] parts = fullName.split("\\s+");
                    if (parts.length < 1 || parts.length > 5) {
                        fullNameInputLayout.setError("Full name should include first name, middle name, and last name");
                        return;
                    }
                    if (fullName.matches(".*\\d.*")) {
                        // Họ tên không được chứa số
                        fullNameInputLayout.setError("Full name should not contain numbers");
                    } else if (fullName.matches(".*[!@#$%^&*()_+*].*")) {
                        // Họ tên không được chứa ký tự đặc biệt
                        fullNameInputLayout.setError("Full name should not contain special characters");
                    } else if (fullName.matches(".*\\b(Lồn|lồn|nồn|Địt|địt|djt|cặc|cak|mẹ mày|con chó)\\b.*")) {
                        // Họ tên không được chứa từ ngữ không phù hợp
                        fullNameInputLayout.setError("Full name should not contain inappropriate words");
                    } else {
                        fullNameInputLayout.setError(null);
                    }
                }
            });

            /// close validate họ và tên
//valiedate password
            passWordEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                public void afterTextChanged(Editable s) {
                    String password = s.toString().trim();
                    String fullName = fullNameEditText.getText().toString().trim();
                    // Kiểm tra xem mật khẩu có chứa ký tự đặc biệt không
                    if (password.matches(".*[!#$%^&*()_+].*")) {
                        passWordInputLayout.setError("Password should not contain special characters !#$%^&*()_+");
                    } else {
                        // Kiểm tra xem mật khẩu có viết có dấu không
                        if (isContainVietnameseAccent(password)) {
                            passWordInputLayout.setError("Password should not contain Vietnamese accents");
                        } else if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                            passWordInputLayout.setError("Password must contain both letters and numbers");
                        } else if (password.matches(".*[A-Z].*") && password.contains("@")) {
                            // Nếu mật khẩu hợp lệ và mạnh, không có lỗi và hiển thị dòng thông báo màu xanh lá cây
                            passWordInputLayout.setError(null);
                            passWordInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
                            passWordInputLayout.setHelperText("Strong password");
                        } else if (isPasswordContainsFullName(password, fullName)) {
                            // Kiểm tra xem mật khẩu có trùng quá 3 ký tự với họ tên không
                            passWordInputLayout.setError("Password should not contain more than 3 characters from full name");

                        } else {
                            // Nếu mật khẩu hợp lệ, xóa lỗi
                            passWordInputLayout.setError(null);
                        }
                    }
                }
            });
            //close validate

            /// su li nut button signup
            btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!checkbox_chinhsach.isChecked()){
                        // hien thi thong bao dialog
                        showCheckBoxDialog();
                    }else {
                        // su li tiep nut dang ki
                    }
                }
            });

            // close button

            return insets;
        });
    }

    private void showCheckBoxDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.notification);
        builder.setMessage("You need to agree to the terms of use and privacy policy set forth by the application");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Phương thức để kiểm tra xem mật khẩu có chứa dấu tiếng Việt không
    private boolean isContainVietnameseAccent(String input) {
        String regex = "[À-ỹ]";
        return input.matches(".*" + regex + ".*");
    }

    //Để kiểm tra xem mật khẩu có trùng quá 3 ký tự với họ tên, bạn có thể sử dụng một phương thức như sau:
    private boolean isPasswordContainsFullName(String password, String fullName) {
        int count = 0;
        for (int i = 0; i < fullName.length() - 2; i++) {
            String substring = fullName.substring(i, i + 3);
            if (password.contains(substring)) {
                count++;
            }
        }
        return count > 3;
    }

}
