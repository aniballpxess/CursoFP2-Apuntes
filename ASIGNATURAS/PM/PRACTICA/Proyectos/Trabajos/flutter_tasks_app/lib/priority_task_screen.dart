import 'package:flutter/material.dart';
import 'main.dart';

class PriorityTaskScreen extends StatelessWidget {
  const PriorityTaskScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Priority Tasks'),
        backgroundColor: Colors.lightBlue,
      ),
      body: ListView.builder(
        itemCount: priorityTasks.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(priorityTasks[index].name),
          );
        },
      ),
    );
  }
}
