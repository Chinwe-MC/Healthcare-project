package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
            public void onClick(View view) {
                //startActivity(new Intent(LoginActivity.this, HomeActivity2.class));
                String Username = edUsername.getText().toString();
                String Password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (Username.length() == 0 || Password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();

                } else {
                    if (db.Login(Username, Password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username", Username);
                        //to save date with a key and value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity2.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
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