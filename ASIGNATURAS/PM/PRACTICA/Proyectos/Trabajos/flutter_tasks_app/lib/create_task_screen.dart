import 'package:flutter/material.dart';
import 'main.dart';

class CreateTaskScreen extends StatefulWidget {
  final String taskList;

  const CreateTaskScreen({super.key, required this.taskList});

  @override
  _CreateTaskScreenState createState() => _CreateTaskScreenState();
}

class _CreateTaskScreenState extends State<CreateTaskScreen> {
  String? _taskName = '';
  String? _selectedTaskList;

  @override
  void initState() {
    super.initState();
    _selectedTaskList = widget.taskList;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Create Task'),
        backgroundColor: Colors.orange,
      ),
      body: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            TextField(
              decoration: InputDecoration(
                labelText: 'Enter Task Name',
                hintText: 'Task name...',
                border: OutlineInputBorder(),
              ),
              onChanged: (value) {
                setState(() {
                  _taskName = value;
                });
              },
            ),
            SizedBox(height: 16),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.yellow.shade300,
              ),
              onPressed: () {
                if (_taskName != null && _taskName!.isNotEmpty) {
                  // Add the task to the corresponding list
                  if (_selectedTaskList == 'Priority') {
                    priorityTasks.add(Task(_taskName!));
                  } else if (_selectedTaskList == 'Long-Term') {
                    longTermTasks.add(Task(_taskName!));
                  }
                  Navigator.pop(context); // Return to the previous screen
                }
              },
              child: Text('Create Task'),
            ),
          ],
        ),
      ),
    );
  }
}
