package spikes.fixyourday;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;

import spikes.dynamic_view.AddableItem;

/**
 * Created by obama on 11/13/15.
 */
public class NotActiveHours extends AddableItem {


    LinearLayout linearLayout;
    Button start, stop, delete;
    TextView text;
    TimePickerDialog timePickerDialog_start, timePickerDialog_end;
    int start_hour, start_min, end_hour, end_min;

    public NotActiveHours(Context context){
        final Calendar c = Calendar.getInstance();
        start_hour = c.get(Calendar.HOUR);
        start_min = c.get(Calendar.MINUTE);
        end_hour = c.get(Calendar.HOUR);
        end_min = c.get(Calendar.MINUTE);

        linearLayout = new LinearLayout(context);
        start = new Button(linearLayout.getContext());
        stop = new Button(linearLayout.getContext());
        delete = new Button(linearLayout.getContext());
        delete.setText("delete");
        stop.setText("stop");
        start.setText("start");
        text = new TextView(context);
        text.setText("-");
//        XmlPullParser parser = context.getResources().getXml(context.getResources());
//        AttributeSet attributes = Xml.asAttributeSet(parser);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(10, 25, 10, 25);
        linearLayout.addView(delete);
        linearLayout.addView(stop);
        linearLayout.addView(text);
        linearLayout.addView(start);







        timePickerDialog_start = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                start_hour = hourOfDay;
                start_min = minute;
                String min = start_min < 10 ? "0" + start_min : start_min + "";
                start.setText(start_hour + ":" + min);
            }

        }, start_hour, start_min, true);

        timePickerDialog_end = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                end_hour = hourOfDay;
                end_min = minute;
                String min = end_min < 10 ? "0" + end_min : end_min + "";
                stop.setText(end_hour + ":" + min);
            }
        }, end_hour, end_min, true);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog_start.show();

            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog_end.show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
                NotActiveHours.this.remove();
            }
        });


    }



    @Override
    protected void add(ViewGroup group) {
        group.addView(linearLayout);
    }

    @Override
    public void removeFrom(ViewGroup viewGroup) {
        viewGroup.removeView(linearLayout);
    }
}
