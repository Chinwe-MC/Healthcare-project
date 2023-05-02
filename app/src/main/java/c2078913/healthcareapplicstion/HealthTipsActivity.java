package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthTipsActivity extends AppCompatActivity {
    private String[][] health=
            {
                    {"Covid-19 is an infectous disease caused by the SARS CoV-2 Virus Most people infected with the virus will experience mild to moderate respiratory illness and recover without requiring special treatment. However, some will become seriously ill and require medical attention.", "Click above titled Covid button to know how to avoid Covid-19", ""},
                    {"The Benefits and Efects of Walking briskly Daily can help you to Maintain a healthy weight and lose body fat\n" +
                            "Prevent or manage various conditions, including heart disease, stroke, high blood pressure, cancer and type 2 diabetes\n" +
                            "Improve cardiovascular fitness\n" +
                            "Strengthen your bones and muscles\n" +
                            "Improve muscle endurance\n" +
                            "Increase energy levels\n" +
                            "Improve your mood, cognition, memory and sleep\n" +
                            "Improve your balance and coordination\n" +
                            "Strengthen immune system\n" +
                            "Reduce stress and tension ", "Click the above button titled walking to know more", ""}

                    };

    HashMap<String, String> item;
    ArrayList lst;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button btncovid19 = findViewById(R.id.btncovid);
        btncovid19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.framelayoutfrag, Covid19Tips.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

        Button btnwater = findViewById(R.id.btnwater);
        btnwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayoutfrag, WaterFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });





       ListView list = findViewById(R.id.listviewH);

        lst = new ArrayList();
        for (int i = 0; i < health.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health[i][0]);
            item.put("line2", health[i][1]);
            lst.add(item);
        }

        sa = new SimpleAdapter(this, lst,
                R.layout.many_lines,
                new String[]{"line1", "line2", },
                new int[]{R.id.Line_a, R.id.Line_b});
        list.setAdapter(sa);

    }
}
