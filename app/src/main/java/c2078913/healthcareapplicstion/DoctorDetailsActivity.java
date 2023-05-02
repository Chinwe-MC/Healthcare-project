package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] Doctor_details1 =
            {
                    {"Doctor Name: Chuma Chukwu", " Hospital address: 22 Ib strret ", "Experience: 5years", "Mobile number: 009876543", " 500.00"},
                    {"Doctor Name: Chinwe David ", " Hospital address: 33 kurudu ", "Experience: 10years", "Mobile number: 098765432", "500.00"},
                    {"Doctor Name: Victor Vice ", " Hospital address: 66 Ibefu ", "Experience: 29 years", "Mobile number: 098754126", "500.00"},
                    {"Doctor Name: bello bike ", " Hospital address: 9 occupy streer", "Experience: 6 years", "Mobile number 08765432", "500.00"},
                    {"Doctor Name: nkem kanma ", " Hospital address: 22 kuje", "Experience: 12years", "Mobile number: 0789765789", "500.00"},

            };
    private String[][] Doctor_details2 =
            {
                    {"Doctor Name: Chuma Chukwu", " Hospital address: 22 Ib strret ", "Experience: 5years", "Mobile number: 009876543", "500.00"},
                    {"Doctor Name: Chinwe David ", " Hospital address: 33 kurudu ", "Experience: 10years", "Mobile number: 098765432", "500.00"},
                    {"Doctor Name: Victor Vice ", " Hospital address: 66 Ibefu ", "Experience: 29 years", "Mobile number: 098754126", "500.00"},
                    {"Doctor Name: bello bike ", " Hospital address: 9 occupy streer", "Experience: 6 years", "Mobile number 08765432", "500.00"},
                    {"Doctor Name: nkem kanma ", " Hospital address: 22 kuje", "Experience: 12years", "Mobile number: 0789765789", "500.00"},

            };
    private String[][] Doctor_details3 =
            {
                    {"Doctor Name: Chuma Chukwu", " Hospital address: 22 Ib strret ", "Experience: 5years", "Mobile number: 009876543", "500.00"},
                    {"Doctor Name: Chinwe David ", " Hospital address: 33 kurudu ", "Experience: 10years", "Mobile number: 098765432", "500.00"},
                    {"Doctor Name: Victor Vice ", " Hospital address: 66 Ibefu ", "Experience: 29 years", "Mobile number: 098754126", "500.00"},
                    {"Doctor Name: bello bike ", " Hospital address: 9 occupy streer", "Experience: 6 years", "Mobile number 08765432", "500.00"},
                    {"Doctor Name: nkem kanma ", " Hospital address: 22 kuje", "Experience: 12years", "Mobile number: 0789765789", "500.00"},
            };
    private String[][] Doctor_details4 =
            {
                    {"Doctor Name: Chuma Chukwu", " Hospital address: 22 Ib strret ", "Experience: 5years", "Mobile number: 009876543", "500.00"},
                    {"Doctor Name: Chinwe David ", " Hospital address: 33 kurudu ", "Experience: 10years", "Mobile number: 098765432", "500.00"},
                    {"Doctor Name: Victor Vice ", " Hospital address: 66 Ibefu ", "Experience: 29 years", "Mobile number: 098754126", "500.00"},
                    {"Doctor Name: bello bike ", " Hospital address: 9 occupy streer", "Experience: 6 years", "Mobile number 08765432", "500.00"},
                    {"Doctor Name: nkem kanma ", " Hospital address: 22 kuje", "Experience: 12years", "Mobile number: 0789765789", "500.00"},
            };
    private String[][] Doctor_details5 =
            {
                    {"Doctor Name: Chuma Chukwu", " Hospital address: 22 Ib strret ", "Experience: 5years", "Mobile number: 009876543", " 500.00"},
                    {"Doctor Name: Chinwe David ", " Hospital address: 33 kurudu ", "Experience: 10years", "Mobile number: 098765432", " 500.00"},
                    {"Doctor Name: Victor Vice ", " Hospital address: 66 Ibefu ", "Experience: 29 years", "Mobile number: 098754126", " 500.00"},
                    {"Doctor Name: bello bike ", " Hospital address: 9 occupy streer", "Experience: 6 years", "Mobile number 08765432", " 500.00"},
                    {"Doctor Name: nkem kanma ", " Hospital address: 22 kuje", "Experience: 12years", "Mobile number: 0789765789", "500.00"},

            };

    TextView tv;
    Button btn;
    String[][] Doctor_details = {};
    ArrayList List;
    HashMap<String, String> item;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.CartItems);
        btn = findViewById(R.id.LabTestBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Family Physician") == 0)
            Doctor_details = Doctor_details1;
        else if (title.compareTo("Deitician ") == 0)
            Doctor_details = Doctor_details2;

        else if (title.compareTo("Dentist ") == 0)
            Doctor_details = Doctor_details3;

        else if (title.compareTo("Surgeon ") == 0)
            Doctor_details = Doctor_details4;

        else
            Doctor_details = Doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, findDoctor.class));
            }
        });

        List = new ArrayList();
        ListView lst;
        for (int i = 0; i < Doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("Line1", Doctor_details[i][0]);
            item.put("Line2", Doctor_details[i][1]);
            item.put("Line3", Doctor_details[i][2]);
            item.put("Line4", Doctor_details[i][3]);
            item.put("Line5", "consultant_fee:" + Doctor_details[i][4] + "$");
            List.add(item);

            simpleAdapter = new SimpleAdapter(this, List, R.layout.multi_lines, new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                    new int[]{R.id.Line_a, R.id.Line_b, R.id.Line_c, R.id.Line_d, R.id.Line_e});
            lst = findViewById(R.id.ListViewDoctorDetails);
            lst.setAdapter(simpleAdapter);


            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it = new Intent(DoctorDetailsActivity.this, AppointmentBookingActivity.class);
                    it.putExtra("text1", title);
                    it.putExtra("text2", Doctor_details[i][0]);
                    it.putExtra("text3", Doctor_details[i][1]);
                    it.putExtra("text4", Doctor_details[i][3]);
                    it.putExtra("text5", Doctor_details[i][4]);
                    startActivity(it);
                }


            });
        }
    }
}