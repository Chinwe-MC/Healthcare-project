package c2078913.healthcareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import c2078913.healthcareapplicstion.R;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.RegisterHere);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Username = edUsername.getText().toString();
                String Password = edPassword.getText().toString();
                if(Username.length()==0 || Password.length()==0){
                Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();

            }else {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }
}