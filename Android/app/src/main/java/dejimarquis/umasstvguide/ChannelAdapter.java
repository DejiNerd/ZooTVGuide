package dejimarquis.umasstvguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DejiNerd on 5/28/2016.
 */
public class ChannelAdapter extends ArrayAdapter<Channel> implements Filterable {
    private Context context;
    private int resource;
    private ArrayList<Channel> objects;
    private ArrayList<Channel> filter;
    private CustomFilter customFilter;

    public ChannelAdapter(Context context, int resource, ArrayList<Channel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        filter = objects;
    }

    @Override
    public Channel getItem(int position) {
        return objects.get(position);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ChannelHolder channelHolder;

        if (row == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            row = inflate.inflate(resource, parent, false);

            channelHolder = new ChannelHolder();
            channelHolder.textView = (TextView) row.findViewById(R.id.channelName);
            channelHolder.noView = (TextView) row.findViewById(R.id.channelNumber);
            channelHolder.titleView = (TextView) row.findViewById(R.id.nowShowing);
            channelHolder.imageView = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(channelHolder);
        } else {
            channelHolder = (ChannelHolder) row.getTag();
        }
        if (objects != null && objects.get(position) != null) {
            Channel data = objects.get(position);
            channelHolder.textView.setText(data.channelName);
            channelHolder.noView.setText(data.channelNo + "");
            String dTitle = data.title;
            if (dTitle != null || dTitle.length() != 0) {
                channelHolder.titleView.setText("Live: " + dTitle);
            }
            int resid = context.getResources().getIdentifier(data.image, "drawable",
                    context.getPackageName());
            channelHolder.imageView.setImageResource(resid);
        }

        return row;
    }

    @Override
    public Filter getFilter() {

        if (customFilter == null) {
            customFilter = new CustomFilter();
        }

        return customFilter;

    }

    class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            ArrayList<Channel> filteredChannelNames = new ArrayList<>();
            if (charSequence != null && charSequence.length() > 0) {
                charSequence = charSequence.toString().toLowerCase();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).channelName.toLowerCase().contains(charSequence) || filter.get(i).title.toLowerCase().contains(charSequence)) {
                        Channel ch = new Channel(filter.get(i).channelNo, filter.get(i).channelName, filter.get(i).image, i);
                        ch.title = filter.get(i).title;
                        filteredChannelNames.add(ch);
                    }
                }

                filterResults.count = filteredChannelNames.size();
                filterResults.values = filteredChannelNames;
            } else {
                filterResults.values = filter;
                filterResults.count = filter.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            objects = (ArrayList<Channel>) results.values;
            notifyDataSetChanged();
        }
    }

    private class ChannelHolder {
        private TextView textView;
        private TextView noView;
        private ImageView imageView;
        private TextView titleView;
    }
}
