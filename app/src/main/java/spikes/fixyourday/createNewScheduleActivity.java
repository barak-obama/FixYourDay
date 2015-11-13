package spikes.fixyourday;

import android.animation.TimeAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import spikes.dynamic_view.DynamicListView;

public class CreateNewScheduleActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog_start;
    TimePickerDialog timePickerDialog_start, timePickerDialog_end;
    CheckBox end_time_checkbox;
    int start_year, start_month, start_day, start_hour, start_min, end_hour, end_min;
    //TextView textView_startdate = (TextView) findViewById(R.id.textView);;
    Button start_button, end_button, not_active, manly_submit;
    DynamicListView dynamicListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_schedule);

        final Calendar c = Calendar.getInstance();
        start_year = c.get(Calendar.YEAR);
        start_month = c.get(Calendar.MONTH);
        start_day = c.get(Calendar.DAY_OF_MONTH);
        start_hour = c.get(Calendar.HOUR);
        start_min = c.get(Calendar.MINUTE);
        end_hour = c.get(Calendar.HOUR);
        end_min = c.get(Calendar.MINUTE);

        //buttons
        start_button = ((Button) findViewById(R.id.set_start_button));
        end_button = (Button) findViewById(R.id.set_end_button);
        not_active = (Button) findViewById(R.id.not_active_add_button);
        manly_submit = (Button) findViewById(R.id.new_schedule_submit);

        end_time_checkbox = (CheckBox) findViewById(R.id.end_time_checkbox);

        datePickerDialog_start = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        start_year = year;
                        start_month = monthOfYear;
                        start_day = dayOfMonth;
                        timePickerDialog_start.show();


                    }
                }, start_year, start_month, start_day);

        timePickerDialog_start = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                start_hour = hourOfDay;
                start_min = minute;
                String min = start_min < 10 ? "0" + start_min : start_min + "";
                start_button.setText(start_hour + ":" + min + "  " + start_day + "-"
                        + (start_month + 1) + "-" + start_year);
            }
        }, start_hour, start_min, true);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog_start.show();

            }
        });


        timePickerDialog_end = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                end_hour = hourOfDay;
                end_min = minute;
                String min = end_min < 10 ? "0" + end_min : end_min + "";
                end_button.setText(end_hour + ":" + min);
            }
        }, end_hour, end_min, true);

        end_button.setEnabled(false);

        end_time_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_button.setEnabled(!end_button.isEnabled());

            }
        });


        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog_end.show();

            }
        });

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.not_active_hour_layout);

        dynamicListView = new DynamicListView(linearLayout);

        not_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicListView.add(new NotActiveHours(linearLayout.getContext()));
            }
        });

        manly_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateNewScheduleActivity.this.getApplicationContext(), AddTasksActivity.class);
                i.putExtra("isLimitedTime", !end_time_checkbox.isChecked());
                i.putExtra("startYear", start_year);
                i.putExtra("startMonth", start_month);
                i.putExtra("startDay", start_day);
                i.putExtra("startHour", start_hour);
                i.putExtra("startMin", start_min);
                i.putExtra("endHour", end_hour);
                i.putExtra("endMin", end_min);

                for (int j = 0; j < dynamicListView.size(); j++) {
                    i.putExtra("cantWorkStartHour" + j, ((NotActiveHours)dynamicListView.get(j)).start_hour);
                    i.putExtra("cantWorkStartMin" + j, ((NotActiveHours)dynamicListView.get(j)).start_min);
                    i.putExtra("cantWorkEndHour" + j, ((NotActiveHours)dynamicListView.get(j)).end_hour);
                    i.putExtra("cantWorkEndMin" + j, ((NotActiveHours)dynamicListView.get(j)).end_min);
                }
                i.putExtra("numOfCantWork", dynamicListView.size());
                startActivity(i);

            }
        });


    }


}
