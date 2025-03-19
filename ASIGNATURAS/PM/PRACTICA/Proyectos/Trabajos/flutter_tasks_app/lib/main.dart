import 'package:flutter/material.dart';
import 'create_task_screen.dart'; // Import the CreateTaskScreen
import 'priority_task_screen.dart'; // Import the Priority Task Screen
import 'long_term_task_screen.dart'; // Import the Long-Term Task Screen

// Task Model
class Task {
  String name;

  Task(this.name);
}

// Global lists to store tasks
List<Task> priorityTasks = [];
List<Task> longTermTasks = [];

void main() {
  runApp(TaskManagerApp());
}

class TaskManagerApp extends StatelessWidget {
  const TaskManagerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Task Manager',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Task Manager'),
        backgroundColor: Colors.purple.shade400,
      ),
      body: Container(
        color: Colors.lightBlue.shade50, // Home Screen background color
        child: Center(
          child: Padding(
            padding: EdgeInsets.all(16.0),
            child: Column(
              children: [
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange, // button background color
                    foregroundColor:
                        Colors.white, // text color inside the button
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) =>
                              CreateTaskScreen(taskList: 'Priority')),
                    );
                  },
                  child: Text('Create Priority Tasks'),
                ),
                SizedBox(height: 16),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.orange,
                    foregroundColor: Colors.white,
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) =>
                              CreateTaskScreen(taskList: 'Long-Term')),
                    );
                  },
                  child: Text('Create Long-Term Tasks'),
                ),
                SizedBox(height: 48),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.lightBlue,
                    foregroundColor: Colors.white,
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => PriorityTaskScreen()),
                    );
                  },
                  child: Text('View Priority Tasks'),
                ),
                SizedBox(height: 16),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.lightGreen,
                    foregroundColor: Colors.white,
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => LongTermTaskScreen()),
                    );
                  },
                  child: Text('View Long-Term Tasks'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
