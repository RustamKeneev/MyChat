package com.example.mychat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    MaterialEditText send_email;
    Button button_reset;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Сброс пароля");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        send_email = findViewById(R.id.send_email);
        button_reset = findViewById(R.id.button_reset);

        firebaseAuth = FirebaseAuth.getInstance();

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  email = send_email.getText().toString();

                if (email.equals("")){
                    Toast.makeText(ResetPasswordActivity.this,"Все поля обязательны для заполнения",Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this,"Пожалуйста, проверьте вашу электронную почту",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }else {
                                String error = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}
