package dejimarquis.umasstvguide;

/**
 * Created by ayodeji on 9/11/17.
 */

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import static android.R.attr.format;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, ArrayList<String>> listings;
    private java.util.Date d1;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, ArrayList<String>> listings) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.listings = listings;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.listings.get(this.expandableListTitle.get(listPosition))
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
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.showings_description);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.listings.get(this.expandableListTitle.get(listPosition))
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
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.showings);
        TextView times = (TextView) convertView.findViewById(R.id.timez);
        //listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        //time implementation
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
        java.sql.Time initTime = new java.sql.Time(Long.parseLong(listings.get(listTitle).get(listings.get(listTitle).size()-1)));
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
