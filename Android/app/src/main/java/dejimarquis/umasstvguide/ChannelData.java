package dejimarquis.umasstvguide;

import java.util.ArrayList;

/**
 * Created by DejiNerd on 7/21/2017.
 */

public class ChannelData {

    private ArrayList<Channel> channels;
    private int[] mapping = new int[85];

    //To understand what's going on here get Deji
    public int[] getChannelMapping(){
        mapping[0] = 0;
        mapping[1] = 1;
        mapping[2] = 2;
        mapping[3] = 3;
        mapping[4] = 4;
        mapping[5] = 5;
        mapping[6] = 6;
        mapping[7] = 7;
        mapping[8] = 79;
        mapping[9] = 8;
        mapping[10] = 9;
        mapping[11] = 10;
        mapping[13] = 13;
        mapping[14] = 14;
        mapping[15] = 15;
        mapping[16] = 16;
        for (int i=20; i<84; i++){
            mapping[i+1] = i;
        }
        mapping[12] = mapping[17] = mapping[18] = mapping[19] = mapping[20] = mapping[32] = mapping[56] = -1;

        return mapping;
    }

    public ArrayList<Channel> getChannels(){
        channels = new ArrayList<>();
        channels.add(new Channel(2.1,"HBO","hbo_color"));
        channels.add(new Channel(3.1,"HBO2","hbo2_color"));
        channels.add(new Channel(4.1,"HBO Signature","hbo_signature_color"));
        channels.add(new Channel(5.1,"HBO Family","hbo_family_color"));
        channels.add(new Channel(6.1 ,"FOX/WGGM-DT3 (Fox6 HD)  ","fox_hd_color"));
        channels.add(new Channel(7.1,"WGBY-DT-PBS","wgby"));
        channels.add(new Channel(8.1,"WTIC FOX","wtic_fox_color"));
        channels.add(new Channel(9.1,"WSBK 38","wsbk_38"));
        channels.add(new Channel(10.1 ,"WWLP-CW","wwlp_cw"));
        channels.add(new Channel(11.1,"Weather Channel","the_weather_channel_color"));
        channels.add(new Channel(12.1,"NECN","necn"));
        channels.add(new Channel(13.1,"FOX News","fox_news_color"));
        channels.add(new Channel(14.1,"HLN-CNN Headline News","hln_color"));
        channels.add(new Channel(15.1,"TLC","tlc_color"));
        channels.add(new Channel(16.1,"CNN","cnn_usa_color"));
        channels.add(new Channel(17.1,"BBC America","bbc_america_color"));
        channels.add(new Channel(18.1,"CCTV4","cctv4"));
        channels.add(new Channel(18.2,"Deutsche Welle?","dw"));
        channels.add(new Channel(19.1,"UVC 19","unavailable"));
        channels.add(new Channel(20.1,"TV Guide","unavailable"));
        channels.add(new Channel(20.2,"Student Life Info Channel","unavailable"));
        channels.add(new Channel(22.1,"CNBC","cnbc_color"));
        channels.add(new Channel(23.1,"MSNBC","msnbc_color"));
        channels.add(new Channel(24.1,"C-SPAN","cspan_color"));
        channels.add(new Channel(25.1,"C-SPAN2","cspan2_color"));
        channels.add(new Channel(26.1,"Discovery Life (Fit TV)","fit_tv"));
        channels.add(new Channel(27.1,"Discovery Channel ","discovery_color"));
        channels.add(new Channel(28.1,"Science Channel","science"));
        channels.add(new Channel(29.1,"National Geographic","national_geographic_channel_color"));
        channels.add(new Channel(30.1,"History","history_color"));
        channels.add(new Channel(31.1,"Travel Channel","travel_ch_color"));
        channels.add(new Channel(32.1,"A & E","ae_color"));
        channels.add(new Channel(33.1,"ABC Family","abc_family_color"));
        channels.add(new Channel(34.1,"Cartoon Network","cartoon_network_color"));
        channels.add(new Channel(35.1,"Disney Channel","disney_channel_color"));
        channels.add(new Channel(36.1,"Nickelodeon","nickelodeon_color"));
        channels.add(new Channel(37.1,"Comedy Central","comedy_central_color"));
        channels.add(new Channel(38.1,"TV Land","tvland"));
        channels.add(new Channel(39.1,"Game Show Network","gsn"));
        channels.add(new Channel(40.1,"Animal Planet","animal_planet_color"));
        channels.add(new Channel(41.1,"!Entertainment TV","e_color"));
        channels.add(new Channel(42.1,"USA","usa_network_color"));
        channels.add(new Channel(43.1,"FX","fx"));
        channels.add(new Channel(44.1,"Spike TV","spike_tv_color"));
        channels.add(new Channel(45.1,"TNT","tnt_color"));
        channels.add(new Channel(46.1,"SyFy","syfy_color"));
        channels.add(new Channel(47.1,"Food Network","food_network_color"));
        channels.add(new Channel(48.1,"Bravo","bravo_color"));
        channels.add(new Channel(49.1,"TBS","tbs"));
        channels.add(new Channel(50.1,"OWN","own"));
        channels.add(new Channel(51.1,"WE WomenU+2019s Entertainment","we_color"));
        channels.add(new Channel(52.1,"Lifetime","lifetime"));
        channels.add(new Channel(53.1,"HGTV Home and Garden TV","hgtv_color"));
        channels.add(new Channel(54.1,"AMC","amc_color"));
        channels.add(new Channel(55.1,"Tru TV","tru_tv"));
        channels.add(new Channel(56.1,"BET","bet_color"));
        channels.add(new Channel(57.1,"cloo","cloo_color"));
        channels.add(new Channel(58.1,"MTV","mtv_color"));
        channels.add(new Channel(59.1,"MTV2","mtv2_color"));
        channels.add(new Channel(60.1,"MTV-U","mtvu"));
        channels.add(new Channel(61.1,"VH-1","vh1"));
        channels.add(new Channel(62.1,"BET VH-1 Soul","vh1_soul_color"));
        channels.add(new Channel(63.1,"CMT","cmt_color"));
        channels.add(new Channel(64.1,"Independent Film Channel","ifc_color"));
        channels.add(new Channel(65.1,"TV Japan","tvjapan"));
        channels.add(new Channel(66.1,"Univision","univision"));
        channels.add(new Channel(67.1,"Telemundo","telemundo_color"));
        channels.add(new Channel(68.1,"Golf Channel","golf_channel"));
        channels.add(new Channel(69.1,"ESPN","espn_color"));
        channels.add(new Channel(70.1,"ESPN 2","espn2_color"));
        channels.add(new Channel(71.1,"NFL Network","nfl_network"));
        channels.add(new Channel(72.1,"Comcast Sports Net","comcast"));
        channels.add(new Channel(73.1,"CBS Sports","cbs_sports_network_color"));
        channels.add(new Channel(74.1,"NESN HD","nesn_hd"));
        channels.add(new Channel(75.1,"ESPN Classic","espn_classic_color"));
        channels.add(new Channel(76.1,"ESPN News","espnews_color"));
        channels.add(new Channel(77.1,"ESPN U","espnu"));
        channels.add(new Channel(78.1,"Fox College Sports","fox_college_sport"));
        channels.add(new Channel(79.1,"NBC Sports Versus","nbc_sport_versus"));
        channels.add(new Channel(80.1,"FOX Sports 1","fox_sports_color"));
        channels.add(new Channel(81.1,"NBC HD","nbc_hd_color"));
        channels.add(new Channel(82.1,"ABC HD","abc_hd_color"));
        channels.add(new Channel(83.1,"CBS HD","cbs_hd_color"));
        channels.add(new Channel(84.1,"Cozi tv","cozi"));
        channels.add(new Channel(85.1,"PBS Kids","unavailable"));

        return channels;
    }
}
