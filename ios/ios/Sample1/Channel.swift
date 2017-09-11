//
//  Channel.swift
//  UmassTvGuide
//
//  Created by Ayodeji Marquis on 8/13/16.
//  Copyright © 2016 Ayodeji Marquis. All rights reserved.
//

import UIKit

class Channel {
    // MARK: Properties
    
    var name: String
    var photo: UIImage?
    var no: Double
    
    // MARK: Initialization
    
    init?( no: Double, name: String, photo: UIImage?) {
        // Initialize stored properties.
        self.name = name
        self.photo = photo
        self.no = no
        
        // Initialization should fail if there is no name or if the rating is negative.
        if name.isEmpty || no < 0 {
            return nil
        }
    }
    
}
