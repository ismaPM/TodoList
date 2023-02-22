//
//  TodoView.swift
//  TodoList
//
//  Created by Ismael Paredes on 21/02/23.
//


import SwiftUI

struct TodoView: View {
  @State var editMode: EditMode = .inactive
  
  @EnvironmentObject var todoViewModel: TodoViewModel
  
  var body: some View {
    NavigationView {
      List {
        ForEach(todoViewModel.todos) { item in
          NavigationLink(
            destination: TodoModelDetailView(item: item)
          ) {
            TodoModelRow(item: item)
              .environment(\.editMode, self.$editMode.animation())
          }
        }
        .onDelete { (indices) in
          self.todoViewModel.remove(at: indices.first!)
        }
        TodoModelNewEntry()
      }
      .navigationBarTitle("Todo")
      .navigationBarItems(trailing: EditButton())
      .environment(\.editMode, $editMode.animation())
    }
  }
}

struct TodoView_Previews: PreviewProvider {
  static var previews: some View {
    TodoView()
  }
}

struct TodoModelRow: View {
  var item: TodoModel
  
  @EnvironmentObject var todoViewModel: TodoViewModel
  @Environment(\.editMode) var editMode
  
  var body: some View {
    HStack {
      if editMode?.wrappedValue != .active {
        Image(systemName:
          item.isCompleted ? "checkmark.circle.fill" : "circle")
          .imageScale(.large)
          .foregroundColor(item.isCompleted ? .blue : .primary)
          .onTapGesture {
            self.todoViewModel.toggleComplete(self.item)
        }
      }
      TextField(item.title,
                text: todoViewModel.index(of: item)
                  .map { $todoViewModel.todos[$0].title }
                  ?? .constant(item.title))
      .disabled(editMode?.wrappedValue != .active)
      Spacer()
    }
  }
}

struct TodoModelDetailView: View {
  var item: TodoModel
  
  @State private var isPresentingAlert = false
  
  var body: some View {
    ScrollView {
      Text(item.title)
        .font(.largeTitle)
        .frame(minWidth: 0,
               maxWidth: .infinity,
               alignment: .leading)
        .padding()
        .onTapGesture(count: 2) {
          UIPasteboard.general.string = self.item.title
          self.isPresentingAlert = true
      }
      .alert(isPresented: $isPresentingAlert) {
        Alert(title: Text("Copiado"))
      }
    }.navigationBarTitle("Detalle")
  }
}

struct TodoModelNewEntry: View {
  @EnvironmentObject var todoViewModel: TodoViewModel
  @State private var newTodo: String = ""
  
  var body: some View {
    HStack {
      Image(systemName: "circle")
        .imageScale(.large)
        .foregroundColor(
          newTodo.isEmpty ? Color(.systemBlue) : .primary
      )
        
      TextField("Nuevo", text: $newTodo).onSubmit {
          withAnimation {
              if(!self.newTodo.isEmpty){
                  self.todoViewModel.add(self.newTodo)
                  self.newTodo = ""
              }
          }
      }
        
    }
  }
}

