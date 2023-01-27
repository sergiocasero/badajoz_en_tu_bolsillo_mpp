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
    
    @StateObject var observer = ViewModelObserver(
        viewModel: SplashViewModel(currentAppVersion: 7, initialState: SplashState.InProgress()),
        state: SplashState.InProgress()
    )
    
    @State var tag:Int? = nil
    
    var body: some View {
        
        ZStack {
            NavigationLink(destination: Navigator.menu.view, tag: Navigator.menu.tag, selection: $tag) {}
            
            if observer.state is SplashState.InProgress {
                LoadingView()
            } else if observer.state is SplashState.Error {
                if let state = observer.state as? SplashState.Error {
                    ErrorView(error: state.error) {
                        observer.viewModel.attach()
                    }
                }
            } else if observer.state is SplashState.UpdateNeeded {
                Text("Update needed")
            } else if observer.state is SplashState.NoUpdateNeeded {
                Text("Update not needed")
                    .onAppear {
                        tag = Navigator.menu.tag
                    }
                //
            }
        }
    }
}

