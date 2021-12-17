package com.example.shipperonly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.shipperonly.Client.Model.ApiClient;
import com.example.shipperonly.Client.Model.User;
import com.example.shipperonly.Utils.HashMD5;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    private EditText edtName , edtEmail , edtpass , edtsdt ,edtpassagain;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    public static final String TAG = DangKy.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dang_ky);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setTitleToolbar();
        mAuth = FirebaseAuth.getInstance();
        edtName = findViewById(R.id.inputUsername);
        edtpass = findViewById(R.id.inputPassword);
        edtpassagain = findViewById(R.id.inputConformPassword);
        edtEmail = findViewById(R.id.inputEmail);
        edtsdt = findViewById(R.id.inputSDT);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickRegister();
//                insert();
                String strPhoneNumber = edtsdt.getText().toString().trim();
                onClickVerifyPhoneNumber(strPhoneNumber);
            }
        });
    }
    private void OnClickRegister() {
        String name = edtName.getText().toString().trim();
        String pass = HashMD5.MD5(edtpass.getText().toString().trim());
        String email = edtEmail.getText().toString().trim();
        String strPhoneNumber = edtsdt.getText().toString().trim();

        if(name.isEmpty())
        {
            edtName.setError("Nhap ten");
            edtName.requestFocus();
            return;
        }
        if(name.length()<3)
        {
            edtName.setError("Ten Qua Ngan");
            edtName.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            edtpass.setError("Password is Required");
            edtpass.requestFocus();
            return;
        }
        if(pass.length()<8)
        {
            edtpass.setError("Password must be >=8");
            edtpass.requestFocus();
            return;
        }

        if(email.isEmpty() )
        {
            edtEmail.setError("Email is Required");
            edtEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edtEmail.setError("Mai Khong Co That");
            edtEmail.requestFocus();
            return;
        }
        if(strPhoneNumber.isEmpty())
        {
            edtsdt.setError("Nhap So Dien Thoai");
            edtsdt.requestFocus();
            return;
        }
        if(strPhoneNumber.length()<3)
        {
            edtsdt.setError("So Dien Thoai Qua Ngan");
            edtsdt.requestFocus();
            return;
        }
        {
            doRegitster(name,pass,strPhoneNumber,email);
        }
    }

    private void doRegitster(String name, String pass, String strPhoneNumber, String email) {
//        User user = new User("",pass,strPhoneNumber,name,email);
        User user = new User("",name,strPhoneNumber,"",email,"",pass,"","","driver");
        ApiClient.apiclient.getregister(user).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Toast.makeText(DangKy.this, "Dang Ky Thanh Cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(DangKy.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setTitleToolbar(){
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Verify Phone Number");
        }
    }
    private void onClickVerifyPhoneNumber(String strPhoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(DangKy.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                goToEnterOtpActivity(strPhoneNumber,verificationId);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            goToMainActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(DangKy.this, "The verification", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void goToEnterOtpActivity(String strPhoneNumber, String verificationId) {
        Intent intent = new Intent(this,OtpActivity.class);
        intent.putExtra("phone number",strPhoneNumber);
        intent.putExtra("verification",verificationId);
        startActivity(intent);
    }
    private void goToMainActivity(String phoneNumber) {
        Intent intent = new Intent(this,DangNhap.class);
        intent.putExtra("phone number",phoneNumber);
        startActivity(intent);

    }
}