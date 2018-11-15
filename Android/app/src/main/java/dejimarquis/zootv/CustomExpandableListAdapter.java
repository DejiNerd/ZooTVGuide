package dejimarquis.zootv;

/**
 * Created by ayodeji on 9/11/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<Integer, ArrayList<String>> listings;
    private HashMap<Integer, String> showTimes;
    private java.util.Date d1;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<Integer, ArrayList<String>> listings, HashMap<Integer, String> showTimes) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.listings = listings;
        this.showTimes = showTimes;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.listings.get(listPosition)
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.showings_description, null);
        }
        TextView expandedListTextView = convertView
                .findViewById(R.id.showings_description);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.listings.get(listPosition)
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.showings, null);
        }
        TextView listTitleTextView = convertView.findViewById(R.id.showings);
        TextView times = convertView.findViewById(R.id.timez);
        listTitleTextView.setText(listTitle);

        //time implementation
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
        java.sql.Time initTime = new java.sql.Time(Long.parseLong(showTimes.get(listPosition)));
        String time = format.format(initTime);
        times.setText(time);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
