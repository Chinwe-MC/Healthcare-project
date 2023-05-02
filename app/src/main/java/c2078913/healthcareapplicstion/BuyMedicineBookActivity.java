package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnbooking, btnbck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.BookingNameBM);
        edaddress = findViewById(R.id.BookingAddressBM);
        edcontact = findViewById(R.id.ContactNumberBM);
        edpincode = findViewById(R.id.CodeBM);
        btnbooking = findViewById(R.id.EnterBookingBM);
        btnbck = findViewById(R.id.BookBckBtnBM);
        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        //String time = intent.getStringExtra("time");

        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sp.getString("username", "").toString();

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), edpincode.getText().toString(), date.toString(), "", Float.parseFloat(price[1].toString()), "Medicine");
                db.removeCart(username, "Medicine");
                Toast.makeText(getApplicationContext(), "Your booking has been done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity2.class));
            }
        });
    }
}