//
//  ViewModelObserver.swift
//  ios
//
//  Created by Sergio Casero Hernández on 27/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

open class ViewModelObserver<S: ViewState, E: ViewEvent>: ObservableObject {
    
    var viewModel: RootViewModel<S, E>
    
    @Published var state: S
    
    init(viewModel: RootViewModel<S, E>, state: S) {
        self.viewModel = viewModel
        self.state = state
        
        self.viewModel.observe(self.viewModel.state) { newState in
            guard let state = newState as? S else {
                return
            }
            self.state = state
        }
        
        self.viewModel.attach()
    }
}
