import 'package:final_app/Helper/Categories.dart';
import 'package:final_app/pages/CartPage.dart';
import 'package:final_app/pages/Categories.dart';
import 'package:final_app/pages/LoginPage.dart';
import 'package:final_app/pages/MainPage.dart';
import 'package:final_app/pages/ProductDetailPage.dart';
import 'package:final_app/pages/Register.dart';
import 'package:final_app/pages/SplashScreen.dart';
import 'package:final_app/pages/WalkThrough.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class RouteGenerator {
  static Route<dynamic> generateRoute(RouteSettings settings) {
    final args = settings.arguments;
    switch (settings.name) {
      case'/':
        return MaterialPageRoute(builder: (_) => SplashScreenPage());
      case'/RegisterPage':
        return MaterialPageRoute(builder: (_) => RegisterPage());
      case'/ProductDetail':
        return MaterialPageRoute(builder: (_) => PlantDetail());
      case'/Categories':
        return MaterialPageRoute(builder: (_) => CategoryPage());
      case'/MainPage':
        return MaterialPageRoute(builder: (_) => MainPage());
      case'/LoginPage':
        return MaterialPageRoute(builder: (_)=> LoginPage());
      case'/Cart':
        return MaterialPageRoute(builder: (_)=> Cart());
      case'/WalkThrough':
        return MaterialPageRoute(builder: (_)=> WalkThrough());
      default:
        return _errorRoute();
    }
  }

  static Route<dynamic> _errorRoute() {
    return MaterialPageRoute(builder: (_) {
      return Scaffold(
        appBar: AppBar(
          title: Text('Error'),
        ),
        body: Center(
          child: Text('ERROR'),
        ),
      );
    });
  }
}
