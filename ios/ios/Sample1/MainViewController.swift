//
//  ViewController.swift
//  Sample1
//
//  Created by TheAppGuruz-New-6 on 10/07/14.
//  Copyright (c) 2014 TheAppGuruz-New-6. All rights reserved.
//

import UIKit

class MainViewController: UIViewController
{
    var channels = [Channel]()
    
    @IBOutlet var tvCars : UITableView?
    override func viewDidLoad()
    {
        super.viewDidLoad()
        if UIScreen.main.bounds.size.height>768
        {
            tvCars?.rowHeight=140;
        }
        // Do any additional setup after loading the view, typically from a nib.
        loadSampleChannels()
    }

    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadSampleChannels() {
        //channels[0] = Channel(no:2.1,name: "HBO",photo: UIImage(named: "hbo_color"))!
        
        channels.append(Channel(no:2.1, name: "HBO",photo: UIImage(named: "hbo_color"))!)
        channels.append(Channel(no:3.1, name: "HBO2",photo: UIImage(named: "hbo2_color"))!)
        channels.append(Channel(no:4.1, name: "HBO Signature",photo: UIImage(named:"hbo_signature_color"))!)
        channels.append(Channel(no:5.1, name: "HBO Family", photo: UIImage(named: "hbo_family_color"))!)
        channels.append(Channel(no:6.1, name: "FOX/WGGM-DT3 (Fox6 HD)  ",photo: UIImage(named: "fox_hd_color"))!)
        channels.append(Channel(no:7.1, name: "WGBY-DT-PBS",photo: UIImage(named: "wgby"))!)
        channels.append(Channel(no:8.1, name: "WTIC FOX",photo: UIImage(named: "wtic_fox_color"))!)
        channels.append(Channel(no:9.1, name: "WSBK 38",photo: UIImage(named: "wsbk_38"))!)
        channels.append(Channel(no:10.1, name: "WWLP-CW",photo: UIImage(named: "wwlp_cw"))!)
        channels.append(Channel(no:11.1, name: "Weather Channel", photo: UIImage(named: "the_weather_channel_color"))!)
        channels.append(Channel(no:12.1, name: "NECN", photo: UIImage(named: "necn"))!)
        channels.append(Channel(no:13.1, name: "FOX News",photo: UIImage(named: "fox_news_color"))!)
        channels.append(Channel(no:14.1, name: "HLN-CNN Headline News", photo: UIImage(named: "hln_color"))!)
        channels.append(Channel(no:15.1, name: "TLC", photo: UIImage(named:"tlc_color"))!)
        channels.append(Channel(no:16.1, name: "CNN", photo: UIImage(named: "cnn_usa_color"))!)
        channels.append(Channel(no:17.1, name: "BBC America",photo: UIImage(named: "bbc_america_color"))!)
        channels.append(Channel(no:18.1, name: "CCTV4", photo: UIImage(named: "cctv4"))!)
        channels.append(Channel(no:18.2, name: "Deutsche Welle?", photo: UIImage(named: "dw"))!)
        channels.append(Channel(no:19.1, name: "UVC 19",photo: UIImage(named: "unavailable"))!)
        channels.append(Channel(no:20.1, name: "TV Guide",photo: UIImage(named: "unavailable"))!)
        channels.append(Channel(no:20.2, name: "Student Life Info Channel",photo: UIImage(named: "unavailable"))!)
        channels.append(Channel(no:22.1, name: "CNBC", photo: UIImage(named: "cnbc_color"))!)
        channels.append(Channel(no:23.1, name: "MSNBC",photo: UIImage(named: "msnbc_color"))!)
        channels.append(Channel(no:24.1, name: "C-SPAN",photo: UIImage(named: "cspan_color"))!)
        channels.append(Channel(no:25.1, name: "C-SPAN2", photo: UIImage(named: "cspan2_color"))!)
        channels.append(Channel(no:26.1, name: "Discovery Life (Fit TV)", photo: UIImage(named: "fit_tv"))!)
        channels.append(Channel(no:27.1, name: "Discovery Channel ",photo:UIImage(named: "discovery_color"))!)
        channels.append(Channel(no:28.1, name: "Science Channel",photo: UIImage(named: "science"))!)
        channels.append(Channel(no:29.1, name: "National Geographic",photo: UIImage(named: "national_geographic_channel_color"))!)
        channels.append(Channel(no:30.1, name: "History", photo: UIImage(named: "history_color"))!)
        channels.append(Channel(no:31.1, name: "Travel Channel", photo: UIImage(named: "travel_ch_color"))!)
        channels.append(Channel(no:32.1, name: "A & E", photo: UIImage(named: "ae_color"))!)
        channels.append(Channel(no:33.1, name: "ABC Family", photo: UIImage(named: "abc_family_color"))!)
        channels.append(Channel(no:34.1, name: "Cartoon Network", photo: UIImage(named: "cartoon_network_color"))!)
        channels.append(Channel(no:35.1, name:"Disney Channel", photo: UIImage(named: "disney_channel_color"))!)
        channels.append(Channel(no:36.1, name: "Nickelodeon",photo: UIImage(named: "nickelodeon_color"))!)
        channels.append(Channel(no:37.1, name: "Comedy Central",photo: UIImage(named: "comedy_central_color"))!)
        channels.append(Channel(no:38.1, name: "TV Land", photo: UIImage(named: "tvland"))!)
        channels.append(Channel(no:39.1, name: "Game Show Network",photo: UIImage(named: "gsn"))!)
        channels.append(Channel(no:40.1, name: "Animal Planet", photo: UIImage(named: "animal_planet_color"))!)
        channels.append(Channel(no:41.1, name: "!Entertainment TV",photo: UIImage(named: "e_color"))!)
        channels.append(Channel(no:42.1, name: "USA",photo: UIImage(named: "usa_network_color"))!)
        channels.append(Channel(no:43.1, name: "FX", photo: UIImage(named: "fx"))!)
        channels.append(Channel(no:44.1, name: "Spike TV", photo: UIImage(named: "spike_tv_color"))!)
        channels.append(Channel(no:45.1, name: "TNT",photo: UIImage(named: "tnt_color"))!)
        channels.append(Channel(no:46.1, name: "SyFy", photo: UIImage(named: "syfy_color"))!)
        channels.append(Channel(no:47.1, name: "Food Network", photo: UIImage(named: "food_network_color"))!)
        channels.append(Channel(no:48.1, name: "Bravo", photo: UIImage(named: "bravo_color"))!)
        channels.append(Channel(no:49.1, name: "TBS",photo: UIImage(named: "tbs"))!)
        channels.append(Channel(no:50.1, name: "OWN", photo: UIImage(named: "own"))!)
        channels.append(Channel(no:51.1, name: "WE WomenU+2019s Entertainment",photo: UIImage(named: "we_color"))!)
        channels.append(Channel(no:52.1, name: "Lifetime",photo: UIImage(named: "lifetime"))!)
        channels.append(Channel(no:53.1, name: "HGTV Home and Garden TV",photo: UIImage(named: "hgtv_color"))!)
        channels.append(Channel(no:54.1, name: "AMC", photo: UIImage(named:  "amc_color"))!)
        channels.append(Channel(no:55.1, name: "Tru TV", photo: UIImage(named: "tru_tv"))!)
        channels.append(Channel(no:56.1, name: "BET",photo: UIImage(named: "bet_color"))!)
        channels.append(Channel(no:57.1, name: "cloo", photo: UIImage(named: "cloo_color"))!)
        channels.append(Channel(no:58.1, name: "MTV", photo: UIImage(named: "mtv_color"))!)
        channels.append(Channel(no:59.1, name: "MTV2", photo: UIImage(named: "mtv2_color"))!)
        channels.append(Channel(no:60.1, name: "MTV-U", photo: UIImage(named: "mtvu"))!)
        channels.append(Channel(no:61.1, name: "VH-1", photo: UIImage(named: "vh1"))!)
        channels.append(Channel(no:62.1, name: "BET VH-1 Soul", photo: UIImage(named: "vh1_soul_color"))!)
        channels.append(Channel(no:63.1, name: "CMT", photo: UIImage(named: "cmt_color"))!)
        channels.append(Channel(no:64.1, name: "Independent Film Channel", photo: UIImage(named: "ifc_color"))!)
        channels.append(Channel(no:65.1, name: "TV Japan", photo: UIImage(named: "tvjapan"))!)
        channels.append(Channel(no:66.1, name: "Univision", photo: UIImage(named: "univision"))!)
        channels.append(Channel(no:67.1, name: "Telemundo", photo: UIImage(named: "telemundo_color"))!)
        channels.append(Channel(no:68.1, name: "Golf Channel", photo: UIImage(named: "golf_channel"))!)
        channels.append(Channel(no:70.1, name: "ESPN 2", photo: UIImage(named: "espn2_color"))!)
        channels.append(Channel(no:71.1, name: "NFL Network", photo: UIImage(named: "nfl_network"))!)
        channels.append(Channel(no:72.1, name: "Comcast Sports Net", photo: UIImage(named: "comcast"))!)
        channels.append(Channel(no:73.1, name: "CBS Sports", photo: UIImage(named: "cbs_sports_network_color"))!)
        channels.append(Channel(no:74.1, name: "NESN HD", photo:UIImage(named:  "nesn_hd"))!)
        channels.append(Channel(no:75.1, name: "ESPN Classic", photo: UIImage(named: "espn_classic_color"))!)
        channels.append(Channel(no:76.1, name: "ESPN News",photo: UIImage(named: "espnews_color"))!)
        channels.append(Channel(no:77.1, name: "ESPN U",photo: UIImage(named: "espnu"))!)
        channels.append(Channel(no:78.1, name: "Fox College Sports", photo: UIImage(named: "fox_college_sport"))!)
        channels.append(Channel(no:79.1, name: "NBC Sports Versus", photo: UIImage(named: "nbc_sport_versus"))!)
        channels.append(Channel(no:80.1, name: "FOX Sports 1",photo: UIImage(named: "fox_sports_color"))!)
        channels.append(Channel(no:81.1, name: "NBC HD", photo: UIImage(named: "nbc_hd_color"))!)
        channels.append(Channel(no:82.1, name: "ABC HD", photo: UIImage(named: "abc_hd_color"))!)
        channels.append(Channel(no:83.1, name: "CBS HD",photo: UIImage(named: "cbs_hd_color"))!)
        channels.append(Channel(no:84.1, name: "Cozi tv", photo: UIImage(named: "cozi"))!)
    }
    //tableview delegate
    
    func numberOfSectionsInTableView(_ tableView: UITableView) -> Int
    {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        //make sure you use the relevant array sizes
        return channels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAtIndexPath indexPath: IndexPath) -> UITableViewCell
    {
        var cell : SampleTableViewCell! = tableView.dequeueReusableCell(withIdentifier: "Cell") as! SampleTableViewCell
        if(cell == nil)
        {
            cell = Bundle.main.loadNibNamed("Cell", owner: self, options: nil)?[0] as! SampleTableViewCell;
        }
        let channelName = channels[indexPath.row].name //NOT NSString
        let channelPhoto = channels[indexPath.row].photo
        let channelNo = "\(channels[indexPath.row].no)"
        cell.lblTitle.text=channelName
        cell.channelNo.text = channelNo
        cell.ivPhoto.image = channelPhoto
        return cell as SampleTableViewCell
    }
    
//    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?)
//    {
//        if segue.identifier == "DetailSegue"
//        {
//            let detailViewController = ((segue.destinationViewController) as! DetailViewController)
//            let indexPath = self.tvCars!.indexPathForSelectedRow!
//            let strImageName = car[indexPath.row]
//            detailViewController.strImageName = strImageName
//            detailViewController.title = strImageName
//        }
//    }
    
}

