package spikes.dynamic_view;

import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Created by obama on 11/13/15.
 */
public abstract class AddableItem {

    ViewGroup viewGroup;

    public void addTo(ViewGroup viewGroup){
//        if (viewGroup != null)
//            throw  new IllegalStateException("The Item can't be added to more then one ViewGroup!");
        this.viewGroup = viewGroup;
        add(viewGroup);
    }

    protected abstract void add(ViewGroup group);

    public void remove(){
        removeFrom(viewGroup);
    }

    protected abstract void removeFrom(ViewGroup viewGroup);

}
