//
//  TodoViewModel.swift
//  TodoList
//
//  Created by Ismael Paredes on 21/02/23.
//

import Foundation
import SwiftUI

class TodoViewModel: ObservableObject {
    
    @Published var todos: [TodoModel] {
        didSet {
          UserDefaults.standard.set(try? JSONEncoder().encode(todos), forKey: "TODO")
        }
    }

    init() {
        todos = UserDefaults.standard.data(forKey: "TODO").flatMap {
            try? JSONDecoder().decode([TodoModel].self, from: $0)
        } ?? []
    }

    func add(_ title: String) {
        let item = TodoModel(title: title)
        todos.append(item)
    }

    func remove(at index: Int) {
        todos.remove(at: index)
    }

    func toggleComplete(_ item: TodoModel) {
        index(of: item).map { todos[$0].isCompleted.toggle() }
    }

    func index(of item: TodoModel) -> Int? {
        return todos.firstIndex(of: item)
    }

}
