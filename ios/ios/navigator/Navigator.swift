//
//  Navigator.swift
//  ios
//
//  Created by Sergio Casero Hernández on 27/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

public class Navigator {
    
    public static var entryPoint: some View {
        return NavigationView {
            SplashRoute()
        }
    }
    
    public static func menu() -> Destination {
        return Destination(tag: 0, view: AnyView(MenuRoute()))
    }
}

public class Destination {
    let tag: Int
    let view: AnyView
    
    init(tag: Int, view: AnyView) {
        self.tag = tag
        self.view = view
    }
}
