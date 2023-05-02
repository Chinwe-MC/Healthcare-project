package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise-03 1000IU Capsule", "", "", "", "50"},
                    {"HealthVit Chronium Picolinate 2000mcg Capsule", "", "", "", "305"},
                    {"Vitamin B Complex Capsules", "", "", "", "448"},
                    {"Inlife Vitamin E wheat Germ oil Capsule", "", "", "", "539"},
                    {"Dole 650 Tablet", "", "", "", "40"},
                    {"Crocin 650 Advance Tablet", "", "", "", "432"},
                    {"Strepsil Medicated Longeles for sore throat", "", "", "", "100"},
                    {"Tata 1mg Calcium + Vitamin D3", "", "", "", "50"},
                    {"Feronia -XT Tablet", "", "", "", "40"},

            };
    private String[] package_details = {
            "Building and Keeping the bones and teeth strong\n" +
                    "Reducing fatique and stress and muscular pain\n" +
                    "Boosting immmunity and increasing resistance against infection",
            "chronium is an essential trace mineral that plays an improtant role in helping insulin regulation",
            "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It Promotes health as well as skin benefits\n" +
                    "It helps reduce skin blemish and pigmentation\n" +
                    "It acts to safeguard the skin from the harsh UVA abd UV sun rays",
            "Dole 650 helps relief pain and fever by blocking the release of certain chemical substances",
            "Helps relief fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial threat condition and soothes the recovery process\n" +
                    "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, rickety and osteoperoasis\n" +
                    "Promotes mobility and flexibilty of joints",
            "Helps to reduce iron deficiency due to chronic blood loss or low intake of iron"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    ListView lst;
    Button btn, btnCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.ListViewBuyMed);
        btn = findViewById(R.id.BuyMedBack);
        btnCart = findViewById(R.id.BuyMedCart);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity2.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedActivity .class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String,String>();
            item.put("Line1", packages[i][0]);
            item.put("Line2", packages[i][1]);
            item.put("Line3", packages[i][2]);
            item.put("Line4", packages[i][3]);
            item.put("Line5", packages[i][4]);
            list.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.Line_a, R.id.Line_b, R.id.Line_c, R.id.Line_d, R.id.Line_e});
        lst = findViewById(R.id.ListViewBuyMed);
        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent intent = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                intent.putExtra("text1", packages[i][0]);
                intent.putExtra("text2", package_details[i]);
                intent.putExtra("text3", packages[i][4]);
                startActivity(intent);
            }


        });
    }
}