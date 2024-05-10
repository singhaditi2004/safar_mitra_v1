package com.example.safarmitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.safarmitra.Model.User;
import com.example.safarmitra.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityRegisterBinding binding;
    private User user;
    private String password;
    private String email;
    private String phoneNo;
    private String name;

// ...
// Initialize Firebase Auth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= String.valueOf(binding.nameN.getText());
                password= String.valueOf(binding.passwordRegister.getText());
                email= String.valueOf(binding.email.getText());
                phoneNo= String.valueOf(binding.phoneNumber.getText());
                if(!name.trim().isEmpty() && !password.trim().isEmpty() && !email.trim().isEmpty() && !password.trim().isEmpty()){
                    user=new User(name,password,phoneNo,email);
                    registerWithEmail(email,password,user);
                }
                else{
                    Toast.makeText(register.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerWithEmail(String email, String password, User user){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user1 = mAuth.getCurrentUser();
                            UploadUsertoDatabase(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register.this, "error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void UploadUsertoDatabase(User user) {
        String Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(Uid)
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(register.this,MapsActivity.class));

                        }
                        else{
                            Toast.makeText(register.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}