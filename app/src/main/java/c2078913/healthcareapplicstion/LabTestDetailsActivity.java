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

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText editpackagedetails;
    Button btnaddtocart, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackageName = findViewById(R.id.LabDetailsTestPackages);
        editpackagedetails = findViewById(R.id.editTextTextMultiline);
        tvTotalCost = findViewById(R.id.TextViewTotalcost);
        btnaddtocart = findViewById(R.id.LabTestDetailsCartBtn);
        btnback = findViewById(R.id.LabTestDetailsBack);

        editpackagedetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        editpackagedetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost :" + intent.getStringExtra("text3") + "/$");

        btnback.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        }));
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sp.getString("username", "").toString();
                    String product = tvPackageName.getText().toString();
                    float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    if(db.checkcart(username, product )==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        db.addCart(username, product, price, "Lab");
                        Toast.makeText(getApplicationContext(), "Package has  been inserted to the cart", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LabTestDetailsActivity.this, CartdetailsActivity.class));
            }



        };
    });
    };
}

