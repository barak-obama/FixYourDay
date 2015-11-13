package spikes.dynamic_view;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obama on 11/13/15.
 */
public class DynamicListView {

    private  List<AddableItem> addableItemList;
    private ViewGroup viewGroup;

    public DynamicListView(ViewGroup viewGroup){
        this.viewGroup = viewGroup;
        addableItemList = new ArrayList<>();
    }

    public void add(AddableItem addableItem){
        addableItemList.add(addableItem);
        addableItem.addTo(viewGroup);
    }

    public void remove(AddableItem addableItem){
        addableItemList.remove(addableItem);
        addableItem.remove();
    }

    public int size() {
        return addableItemList.size();
    }

    public AddableItem get(int location) {
        return addableItemList.get(location);
    }

    public boolean isEmpty() {
        return addableItemList.isEmpty();
    }
}
