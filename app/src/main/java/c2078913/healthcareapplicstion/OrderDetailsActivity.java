package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
    private String[][] order_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    ListView lstv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
       btn = findViewById(R.id.btnBckOD);
        lstv = findViewById(R.id.listviewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity2.class));
           }
       });

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        ArrayList dbData = db.getOrderData(username);

        order_details = new String[dbData.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1];
            if (strData[7].compareTo("medicine") == 0) {
               order_details[i][3] = "Del:" + strData[4];
            } else {
               order_details[i][3] = "Del:" + strData[4] + " " + strData[5];
            }
            order_details[i][2] = "/$" + strData[6];
            order_details[i][4] = strData[7];
        }

            list = new ArrayList();
            for (int i = 0; i < order_details.length; i++) {
                item = new HashMap<String,String>();
                item.put("Line1", order_details[i][0]);
                item.put("Line2", order_details[i][1]);
                item.put("Line3", order_details[i][2]);
                item.put("Line4", order_details[i][3]);
                item.put("Line5", order_details[i][4]);
                list.add(item);
            }

                SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
                        R.layout.multi_lines,
                        new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                        new int[]{R.id.Line_a, R.id.Line_b, R.id.Line_c, R.id.Line_d, R.id.Line_e});
                lstv = findViewById(R.id.listviewOD);
                lstv.setAdapter(simpleAdapter);


            }
        }


