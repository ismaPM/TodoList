//
//  TodoListApp.swift
//  TodoList
//
//  Created by Ismael Paredes on 21/02/23.
//

import SwiftUI

@main
struct TodoListApp: App {
    @StateObject private var _todoViewModel = TodoViewModel()
    var body: some Scene {
        WindowGroup {
            TodoView().environmentObject(_todoViewModel)
        }
    }
}
