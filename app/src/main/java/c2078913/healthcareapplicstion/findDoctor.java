package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class findDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        CardView Exit = findViewById(R.id.FDBack);
        Exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(findDoctor.this, HomeActivity2.class));

            }
        });
        CardView familyPhysician = findViewById(R.id.FDFamilyPhysician);
        familyPhysician.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fp = new Intent(findDoctor.this, DoctorDetailsActivity.class);
                fp.putExtra("title", "family physician");

                startActivity(fp);
            }
        });
        CardView Deitician = findViewById(R.id.FDDeitician);
        Deitician.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fp = new Intent(findDoctor.this, DoctorDetailsActivity.class);
                fp.putExtra("title", "Deitician");

                startActivity(fp);
            }
        });
        CardView Dentist = findViewById(R.id.FDDentist);
        Dentist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fp = new Intent(findDoctor.this, DoctorDetailsActivity.class);
                fp.putExtra("title", "Dentist");

                startActivity(fp);
            }
        });
        CardView Surgeon = findViewById(R.id.FDSurgeon);
        Surgeon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fp = new Intent(findDoctor.this, DoctorDetailsActivity.class);
                fp.putExtra("title", "Surgeon ");

                startActivity(fp);
            }
        });
        CardView Cardiologist = findViewById(R.id.FDCardioligist);
        Cardiologist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent fp = new Intent(findDoctor.this, DoctorDetailsActivity.class);
                fp.putExtra("title", "Cardiologist ");

                startActivity(fp);
            }
        });

    }
}