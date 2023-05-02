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

public class LabTestActivity extends AppCompatActivity {
    private String[][] Test_packages =
            {
                    {"Package 1 : General checkup", "", "", "", "889"},
                    {"Package 2 : Genotype Test", "", "", "", "234"},
                    {"Package 3 : Covid- 19 Screening ", "", "", "", "199"},
                    {"Package 4 : Blood count ", "", "", "", "344"},
                    {"Package 5 :  Thyroid Check", "", "", "", "845"},
            };
    private String[] package_details =
            {"Blood Glucose Fasting\n",
                    "Complete Hemogram\n" +
                            "Hb1c\n" +
                            "Iron Studies\n" +
                            "Kidney function Test\n" +
                            "LOH Lactate Dehydration, Serum\n" +
                            "Lipid Profile\n" +
                            "Liver function Test",
                    "Blood Glucose Fasting",
                    "Covid_19 antibody - IgG",
                    "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
                    "Complete Heogram\n",
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                            "Iron Studies\n" +
                            "Kidney FunctionTest\n" +
                            "Vitamin D Total-25 Hydroxy\n" +
                            "liver function test\n" +
                            "Lipid Profile"
            };
    HashMap<String, String> item;
    ArrayList list;
    Button GoToCartbtn, BtnBack;
    ListView listView;
    SimpleAdapter simpleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        GoToCartbtn = findViewById(R.id.MoveToCart);
        BtnBack = findViewById(R.id.LabTestBack);
        listView = findViewById(R.id.ListViewLabtest);

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity2.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < Test_packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", Test_packages[i][0]);
            item.put("line2", Test_packages[i][1]);
            item.put("line3", Test_packages[i][2]);
            item.put("line4", Test_packages[i][3]);
            item.put("line5", "Total Cost " + Test_packages[i][4] + "$");
            list.add(item);


            simpleAdapter = new SimpleAdapter(this, list, R.layout.multi_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                    new int[]{R.id.Line_a, R.id.Line_b, R.id.Line_c, R.id.Line_d, R.id.Line_e});
            listView.setAdapter(simpleAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                    it.putExtra("text1", Test_packages[i][0]);
                    it.putExtra("text2", package_details[i]);
                    it.putExtra("text3", Test_packages[i][4]);
                    startActivity(it);


                }
            });
            GoToCartbtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(LabTestActivity.this, CartdetailsActivity.class ));
                }
            });
        }
    }
}
