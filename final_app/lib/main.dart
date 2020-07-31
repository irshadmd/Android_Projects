import 'package:final_app/pages/Categories.dart';
import 'package:final_app/pages/LoginPage.dart';
import 'package:final_app/pages/MainPage.dart';
import 'package:final_app/pages/ProductDetailPage.dart';
import 'package:final_app/pages/SplashScreen.dart';
import 'package:flutter/material.dart';

import 'RouteGen/route_generator.dart';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      onGenerateRoute: RouteGenerator.generateRoute,
      themeMode: ThemeMode.light,
      color: Color(0xFFf74269),
      theme: ThemeData(
        primaryColor: Color(0xFFDE3C26),
        accentColor: Color(0xFFDE3C26)
      ),
      debugShowCheckedModeBanner: false,
      title: "Chicken App",
      home: SplashScreenPage(),
    );
  }
}
