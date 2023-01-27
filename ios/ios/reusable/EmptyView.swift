//
//  EmptyView.swift
//  ios
//
//  Created by Sergio Casero Hernández on 27/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct EmptyView: View {
    
    private let message: String
    private let icon: String
    private let textColor: Color
    private let backgroundColor: Color
    private let buttonText: String?
    private let onButtonClick: (() -> Any)?
    
    init(message: String, icon: String, textColor: Color, backgroundColor: Color, buttonText: String? = nil, onButtonClick: (@escaping () -> Any)) {
        self.message = message
        self.icon = icon
        self.textColor = textColor
        self.backgroundColor = backgroundColor
        self.buttonText = buttonText
        self.onButtonClick = onButtonClick
    }
    
    var body: some View {
        VStack {
            Image(systemName: icon)
            Spacer()
            Text(message)
                .foregroundColor(textColor)
            
            if buttonText != nil {
                Button(buttonText ?? "") {
                    onButtonClick?()
                }
            }
        }.background(backgroundColor)
    }
}
