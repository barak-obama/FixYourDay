package spikes.fixyourday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
>>>>>>> 069c5dea2c84cbc9bc78826475ec49641e2cac2c

public class NewScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);
<<<<<<< HEAD
        Button b = (Button)findViewById(R.id.button);
        b.setText("hi");
=======
        Button r = (Button) findViewById(R.id.new_schedule_button);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, DisplayMessageActivity.class);
                Toast.makeText(null, "Hello", Toast.LENGTH_LONG).show();
            }
        });
>>>>>>> 069c5dea2c84cbc9bc78826475ec49641e2cac2c
    }

}
