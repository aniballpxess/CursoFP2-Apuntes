import 'package:flutter/material.dart';
import 'main.dart';

class LongTermTaskScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Long-Term Tasks'),
        backgroundColor: Colors.lightGreen,
      ),
      body: ListView.builder(
        itemCount: longTermTasks.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(longTermTasks[index].name),
          );
        },
      ),
    );
  }
}
