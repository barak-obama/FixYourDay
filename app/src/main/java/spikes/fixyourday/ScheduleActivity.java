package spikes.fixyourday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import spikes.dynamic_view.DynamicListView;
import spikes.looz.Task;

public class ScheduleActivity extends AppCompatActivity {

    DynamicListView dynamicListView;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent i = getIntent();
        Bundle bundle=i.getExtras();
        List<Task> tasks = (List<Task>)bundle.getSerializable("tasks");
        Log.d("bye", tasks + "");
        linearLayout = (LinearLayout) findViewById(R.id.linear_schedule);
        dynamicListView = new DynamicListView(linearLayout);
        for(Task t : tasks) {
            dynamicListView.add(new TaskLineFinal(getApplicationContext(), t));
        }


    }
}
