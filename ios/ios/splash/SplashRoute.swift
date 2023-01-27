//
//  ScreenRoute.swift
//  ios
//
//  Created by Sergio Casero Hernández on 21/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SplashRoute: View {
    
    @StateObject var splashViewModelObserver = ViewModelObserver(
        viewModel: SplashViewModel(currentAppVersion: 7, initialState: SplashState.InProgress()),
        state: SplashState.InProgress()
    )
    
    var body: some View {
        Text("hello")
    }
}
