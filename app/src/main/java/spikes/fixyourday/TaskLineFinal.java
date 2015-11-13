package spikes.fixyourday;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.logging.Logger;

import spikes.dynamic_view.AddableItem;
import spikes.looz.Task;

import static spikes.fixyourday.R.color.green;

/**
 * Created by obama on 11/13/15.
 */
public class TaskLineFinal extends AddableItem {

    LinearLayout linearLayout;
    TextView start, name, duration, importance;


    public TaskLineFinal(Context context, Task t){
        Log.d("try", t.toString());
        linearLayout = new LinearLayout(context);
        start = new TextView(linearLayout.getContext());
        name = new TextView(linearLayout.getContext());
        duration = new TextView(linearLayout.getContext());
        importance = new TextView(linearLayout.getContext());

        String start_string = t.getStart_hour() + ":" + (t.getStart_min() < 10?  "0" + t.getStart_min(): t.getStart_min() + "");
        start.setText(start_string);

        name.setText(t.getName());

        duration.setText(t.getTime() + "");

        importance.setText(t.getImportance() + "");
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        Log.d("bye", start.getText() + " " + name.getText() + " " + duration.getText() + " " + importance.getText());


        start.setTextSize(40);
        start.setTextColor(linearLayout.getResources().getColor(green));
        start.setPadding(40, 10, 40, 10);
        importance.setTextSize(40);
        importance.setTextColor(0);
        importance.setTextColor(linearLayout.getResources().getColor(green));
        importance.setPadding(40, 10, 40, 10);
        name.setTextSize(40);
        name.setTextColor(0);
        name.setTextColor(linearLayout.getResources().getColor(green));
        name.setPadding(40, 10, 40, 10);
        duration.setTextSize(40);
        duration.setTextColor(0);
        duration.setTextColor(linearLayout.getResources().getColor(green));
        duration.setPadding(40, 10, 40, 10);

        linearLayout.addView(importance);
        linearLayout.addView(duration);
        linearLayout.addView(name);
        linearLayout.addView(start);
        linearLayout.setPadding(10, 25, 10, 25);

    }


    protected  void add(ViewGroup group){
        group.addView(linearLayout);
    }

    protected  void removeFrom(ViewGroup viewGroup){
        viewGroup.removeView(linearLayout);
    }
}
