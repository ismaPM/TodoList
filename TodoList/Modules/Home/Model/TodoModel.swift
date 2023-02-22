//
//  TodoModel.swift
//  TodoList
//
//  Created by Ismael Paredes on 21/02/23.
//

import Foundation

struct TodoModel: Equatable, Identifiable, Codable {
    private(set) var id = UUID()
    var title: String
    var isCompleted: Bool = false
}
