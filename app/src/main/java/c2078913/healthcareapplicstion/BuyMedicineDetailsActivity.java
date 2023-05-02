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

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button Btnbck, btnaddtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.BuyMedDetails);
        tvTotalCost=findViewById(R.id.BMTotalcost);
        edDetails=findViewById(R.id.editTextTextMultiline);
        Btnbck=findViewById(R.id.BMDetailsBack);
        btnaddtoCart=findViewById(R.id.BMDetailsCartBtn);

        Intent it=getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        edDetails.setText(it.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+it.getStringExtra("text3")+"/-");

        Btnbck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        btnaddtoCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sp.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if(db.checkcart(username, product )==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else{

                    db.addCart(username, product, price, "medicine");
                    Toast.makeText(getApplicationContext(), "Package has  been inserted to the cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }

            }
    });
}
    }