import 'package:flutter/material.dart';

void main() => runApp(new HelloWorldApp());

class HelloWorldApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
       home: Scaffold(
         appBar: AppBar(
           title: Text(
             "First App",
           ),
           centerTitle: true,
         ),
         body: Center(
           child: Text(
             'hello irshad',
             style: TextStyle(
               fontSize: 20,
               color: Colors.red,
             ),
           ),
         ),
       ),
    );
  }
}