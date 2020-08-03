import 'package:final_app/models/user.dart';
import 'package:final_app/pages/LoginPage.dart';
import 'package:final_app/pages/MainPage.dart';
import 'package:flutter/material.dart';
import 'package:sk_onboarding_screen/sk_onboarding_model.dart';
import 'package:sk_onboarding_screen/sk_onboarding_screen.dart';

import '../Preferences.dart';

class WalkThrough extends StatefulWidget {
  @override
  _WalkThroughState createState() => _WalkThroughState();
}

class _WalkThroughState extends State<WalkThrough> {
  User user = new User();
  bool walk;
  bool afterwalk;
  final pages = [
    SkOnboardingModel(
        title: 'Truely Delighful\nMeats & Seafood',
        titleColor: Colors.red,
        description: 'Crafted by Meat Lovers. for meat lovers',
        descripColor: const Color(0xFF929794),
        imagePath:
            'assets/shopping-basket-full-meat-raw-prepared_3446-400.jpg'),
    SkOnboardingModel(
        title: 'Farm-to-Fork',
        titleColor: Colors.red,
        description:
            'Enjoy high - quality meat straight \nfrom source free from antibiotics',
        descripColor: const Color(0xFF929794),
        imagePath:
            'assets/agricultural-man-with-baskets-full-veggies_52683-22992.jpg'),
    SkOnboardingModel(
        title: '150+ Premium Products',
        description: 'Choose from wide range of meat\n&seafood products',
        titleColor: Colors.red,
        descripColor: const Color(0xFF929794),
        imagePath: 'assets/farmer-collection-concept_23-2148533584.jpg'),
    SkOnboardingModel(
        title: 'Certified Fresh',
        description: 'Cleaned,cut & packed with FSSC22000 certification',
        titleColor: Colors.red,
        descripColor: const Color(0xFF929794),
        imagePath:
            'assets/delivery-service-with-masks-concept_23-2148535315.jpg'),
  ];

  @override
  initState() {
    super.initState();
    initPrefs();
  }

  void initPrefs() async {
    await Preferences.getUser().then((value) {
      setState(() {
        user = value;
      });
    });
    await Preferences.getWalkThrough().then((value) {
      walk = value;
    });
    if (this.user.name == '--') {
      afterwalk = false;
    } else {
      afterwalk = true;
    }
  }

  @override
  Widget build(BuildContext context) {
    if (walk == false) {
      return Scaffold(
        body: SKOnboardingScreen(
          bgColor: Colors.white,
          themeColor: const Color(0xFFf74269),
          pages: pages,
          skipClicked: (value) {
            Preferences.setUser(
              "--",
              "--",
              "--",
            );
            Preferences.setWalkThrough().then((value) {
              print(
                  "========================Settting walk through=======================");
            });
            Navigator.of(context).pushReplacementNamed('/LoginPage');
          },
          getStartedClicked: (value) {
            Preferences.setUser(
              "--",
              "--",
              "--",
            );
            Preferences.setWalkThrough().then((value) {
              print(
                  "========================Settting walk through=======================");
            });
            Navigator.of(context).pushReplacementNamed('/LoginPage');
          },
        ),
      );
    }else if(afterwalk==false){
      return new LoginPage();
    }else{
      return new MainPage();
    }
  }
}
