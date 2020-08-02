import 'package:final_app/models/user.dart';
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
      seconds: 6,
      photoSize: 150,
      image: Image.asset('assets/freshodaily.png'),
      navigateAfterSeconds: check_if_already_login(context),
    );
  }
}

check_if_already_login(BuildContext context) async {
  await Preferences.getWalkThrough().then((value) {
    if (value == false) {
      Navigator.of(context).pushNamed('/WalkThrough');
    } else {
      Preferences.getUser().then((value) {
        User user = new User();
        user = value;

        if (user.name == "--") {
          Navigator.of(context).pushNamed('/LoginPage');
        } else {
          Navigator.of(context).pushNamed('/MainPage');
        }
      });
    }
  });
}
