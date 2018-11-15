package dejimarquis.zootv;

/**
 * Created by DejiNerd on 5/28/2016.
 */
public class Channel {
    public String channelName;
    public String title;
    public double channelNo;
    public String image;
    public int index = -1;

    public Channel(double channelNo, String channelName, String image) {

        this.channelName = channelName;
        this.channelNo = channelNo;
        this.image = image;
    }

    public Channel(double channelNo, String channelName, String image, int index) {

        this.channelName = channelName;
        this.channelNo = channelNo;
        this.image = image;
        this.index = index;
    }
}
