package c2078913.healthcareapplicstion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AppointmentBookingActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerdialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton, timebutton, btnbook, btnback;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booking);

        tv = findViewById(R.id.Appointment);
        ed1 = findViewById(R.id.BkAppName);
        ed2 = findViewById(R.id.BkAppAdress);
        ed3 = findViewById(R.id.BKAppContactNum);
        ed4 = findViewById(R.id.Fees);
        datebutton = findViewById(R.id.buttonSelectDate);
        timebutton = findViewById(R.id.buttonAppTime);
        btnbook = findViewById(R.id.Book);
        btnback = findViewById(R.id.AppBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String Appointment = it.getStringExtra("text1");
        String FullName = it.getStringExtra("text2");
        String Address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(Appointment);
        ed1.setText(FullName);
        ed2.setText(Address);
        ed3.setText(contact);
        ed4.setText("Fees" + fees + "/$/");

        //datepicker
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerdialog.show();

            }

        });
        //timepicker
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentBookingActivity.this, findDoctor.class));

            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              Database db = new Database(getApplicationContext(), "healthcare", null, 1);
              SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
               String username = sp.getString("username", "").toString();
                if(db.checkappointmentExist(username, Appointment+"==" +FullName,Address,contact,datebutton.getText().toString(),timebutton.getText().toString())==1) {
                   Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                }else{
                   db.addOrder(username, Appointment+"=="+FullName, Address, "",contact, datebutton.getText().toString(), timebutton.getText().toString(),Float.parseFloat(fees),"Appointment");
                    Toast.makeText(getApplicationContext(),"Your appointment was booked successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AppointmentBookingActivity.this, HomeActivity2.class));
                }
                


           }
        });
    }

        private void initDatePicker(){
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    i1 = i1 + 1;
                    datebutton.setText(i2 + "/" + i1 + "/' +i");

                }

            };
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_DARK;
            datePickerdialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
            datePickerdialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

        }




        private void initTimePicker() {
            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    timebutton.setText(i+ ":" +i1);

                }
            };

            Calendar cal = Calendar.getInstance();
            int hrs = cal.get(Calendar.HOUR);
            int mins = cal.get(Calendar.MINUTE);


            int style = AlertDialog.THEME_HOLO_DARK;
            timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
            ;
        }
    }

