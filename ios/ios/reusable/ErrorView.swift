//
//  ErrorView.swift
//  ios
//
//  Created by Sergio Casero Hernández on 27/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct ErrorView: View {
    private let error: AppError
    private let onRetry: () -> Any
    
    init(error: AppError, onRetry: @escaping () -> Any) {
        self.error = error
        self.onRetry = onRetry
    }
    
    private func appErrorToText() -> String {
        switch error {
        case AppError.LocalError():
            return "Local Error"
        case AppError.NotFound():
            return "Not found"
        case AppError.Unknown():
            return "Unknown"
        case AppError.NoInternet():
            return "No Internet"
        case AppError.ServerError():
            return "ServerError"
        case AppError.AppConfig():
            return "AppConfig"
        default:
            return "Unknown"
        }
    }
    
    private func appErrorToIcon() -> String {
        switch error {
        case AppError.LocalError():
            return "globe"
        case AppError.NotFound():
            return "globe"
        case AppError.Unknown():
            return "globe"
        case AppError.NoInternet():
            return "globe"
        case AppError.ServerError():
            return "globe"
        case AppError.AppConfig():
            return "globe"
        default:
            return "globe"
        }
    }
    
    var body: some View {
        EmptyView(
            message: appErrorToText(),
            icon: appErrorToIcon(),
            textColor: Color.white,
            backgroundColor: Color.red) {
                onRetry()
            }
    }
}

