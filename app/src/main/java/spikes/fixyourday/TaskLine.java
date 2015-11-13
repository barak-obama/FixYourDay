package spikes.fixyourday;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import spikes.dynamic_view.AddableItem;

/**
 * Created by obama on 11/13/15.
 */
public class TaskLine extends AddableItem {

    LinearLayout linearLayout;
    EditText name, time, importance;
    Button delete;

    public TaskLine(Context context){

        linearLayout = new LinearLayout(context);

        delete = new Button(linearLayout.getContext());
        delete.setText("delete");

        name = new EditText(linearLayout.getContext());
        name.setHint("name");
        time = new EditText(linearLayout.getContext());
        time.setHint("time");
        importance = new EditText(linearLayout.getContext());
        importance.setHint("importance");

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(10, 25, 10, 25);
        linearLayout.addView(delete);
        linearLayout.addView(importance);
        linearLayout.addView(time);
        linearLayout.addView(name);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskLine.this.remove();
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

