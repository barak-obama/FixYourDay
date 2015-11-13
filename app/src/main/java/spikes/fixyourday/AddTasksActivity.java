package spikes.fixyourday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import spikes.dynamic_view.DynamicListView;
import spikes.looz.Looz;
import spikes.looz.Task;
import spikes.looz.time.Resolution;
import spikes.looz.time.Time;

public class AddTasksActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button addTaskButton, girly_submit;
    DynamicListView dynamicListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        linearLayout = (LinearLayout) findViewById(R.id.linear_tasks);
        addTaskButton = (Button) findViewById(R.id.add_task_button);
        girly_submit = (Button) findViewById(R.id.girly_submit);

        dynamicListView = new DynamicListView(linearLayout);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicListView.add(new TaskLine(linearLayout.getContext()));
            }
        });

        girly_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddTasksActivity.this.getApplicationContext(), ScheduleActivity.class);

                boolean isLimitedTime = i.getBooleanExtra("isLimitedTime", true);
                int start_year = i.getIntExtra("startYear", 0);
                int start_month = i.getIntExtra("startMonth", 0);
                int start_day = i.getIntExtra("startDay", 0);
                int start_hour = i.getIntExtra("startHour", 0);
                int start_min = i.getIntExtra("startMin", 0);

                int end_hour = i.getIntExtra("endHour", 0);
                int end_min = i.getIntExtra("endMin", 0);

                int numOfCantWork = i.getIntExtra("numOfCantWork", 0);
                List<Integer> cantWork = new ArrayList<>();
                for (int j = 0; j < numOfCantWork; j++) {
                    cantWork.add(i.getIntExtra("cantWorkStartHour" + j, 0));
                    cantWork.add(i.getIntExtra("cantWorkStartMin" + j, 0));
                    cantWork.add(i.getIntExtra("cantWorkEndHour" + j, 0));
                    cantWork.add(i.getIntExtra("cantWorkEndMin" + j, 0));
                }

                Looz looz = new Looz(start_year, start_month, start_day, start_hour, start_min, cantWork);

                for (int j = 0; j < dynamicListView.size(); j++) {
                    int importance = Integer.parseInt(((TaskLine) dynamicListView.get(j)).importance.getText().toString());
                    Time time = new Time(Resolution.HOURS, Integer.parseInt(((TaskLine) dynamicListView.get(j)).time.getText().toString()));
                    String name = ((TaskLine) dynamicListView.get(j)).name.getText().toString();
                    looz.addTask(new Task(importance, time, name));
                }

                List<Task> tasks;
                if (isLimitedTime)
                    tasks = looz.getLooz();
                else
                    tasks = looz.getLooz(end_hour, end_min);
                Bundle bundle = new Bundle();
                bundle.putSerializable("tasks", (ArrayList) tasks);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
}
