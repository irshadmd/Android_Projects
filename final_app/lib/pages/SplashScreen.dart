import 'package:final_app/models/user.dart';
import 'package:final_app/pages/WalkThrough.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:splashscreen/splashscreen.dart';

import '../Preferences.dart';

class SplashScreenPage extends StatefulWidget {
  @override
  _SplashScreenPageState createState() => _SplashScreenPageState();
}

class _SplashScreenPageState extends State<SplashScreenPage> {
  User user = new User();

  @override
  Widget build(BuildContext context) {
    return SplashScreen(
      seconds: 4,
      photoSize: 150,
      image: Image.asset('assets/splash.png'),
      navigateAfterSeconds: new WalkThrough(),
    );
  }
}
//
//check_if_already_login(BuildContext context) async {
//  await Preferences.getWalkThrough().then((value) {
//    if (value == false) {
//      Navigator.of(context).pushNamed('/WalkThrough');
//    } else {
//      Preferences.getUser().then((value) {
//        User user = new User();
//        user = value;
//
//        if (user.name == "--") {
//          Navigator.of(context).pushNamed('/LoginPage');
//        } else {
//          Navigator.of(context).pushNamed('/MainPage');
//        }
//      });
//    }
//  });
//}
