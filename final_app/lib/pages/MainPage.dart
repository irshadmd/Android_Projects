import 'dart:io';

import 'package:bottom_indicator_bar/bottom_indicator_bar.dart';
import 'package:final_app/Helper/Categories.dart';
import 'package:final_app/Helper/FavItem.dart';
import 'package:final_app/Helper/FoodCategories.dart';
import 'package:final_app/Helper/SubCategories.dart';
import 'package:final_app/Helper/SubCategoriesApi.dart';
import 'package:final_app/Helper/TrendingNow.dart';
import 'package:final_app/Helper/TrendingNowApi.dart';
import 'package:flutter/services.dart';
import '../Preferences.dart';
import 'package:final_app/api/Categories.dart';
import 'package:final_app/api/loginApi.dart';
import 'package:final_app/models/Categories.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import '../Constants.dart';

import 'package:final_app/Helper/nav_drawer.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  final items = [
    BottomNavigationBarItem(icon: Icon(Icons.home),title: Text("Home"),backgroundColor: Colors.redAccent),
    BottomNavigationBarItem(icon: Icon(Icons.tv),title: Text("Tv"),backgroundColor: Colors.blue),
    BottomNavigationBarItem(icon: Icon(Icons.favorite_border),title: Text("Fav"),backgroundColor: Colors.green),
  ];

  @override
  void initState() {
    super.initState();
    print("======================== Trending Now Listing ==========");
    CategoriesList.TrendingNowListing();
  }
  int _currentIndex=0;
  final tabs=[
    SingleChildScrollView(
    child: Column(
      children: <Widget>[
        Container(
          margin: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
          padding: EdgeInsets.symmetric(horizontal: 20),
          height: 40,
          width: double.infinity,
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(15),
              color: Colors.grey[300],
              boxShadow: [
                BoxShadow(
                  color: Colors.grey,
                  blurRadius: 7,
                )
              ]),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Icon(Icons.search),
              SizedBox(
                width: 20,
              ),
              Text(
                "SEARCH FOR CATEGORIES",
                style: KsearchBar,
              )
            ],
          ),
        ),
        ProductCategories(),
        Container(
          margin: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
          padding: EdgeInsets.symmetric(horizontal: 20),
          height: 150,
          decoration: BoxDecoration(
              color: Colors.pink[100],
              borderRadius: BorderRadius.circular(15),
              image: DecorationImage(
                  image: AssetImage('assets/ezgif.com-video-to-gif.gif'),
                  fit: BoxFit.cover)),
        ),
        Container(
          margin: EdgeInsets.only(bottom: 5, right: 20, left: 20, top: 15),
          child: Row(
            children: <Widget>[
              Text(
                "Main Categories",
                style: GoogleFonts.roboto(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                    fontSize: 25),
              ),
            ],
          ),
        ),
        ProductCategories(),
        GestureDetector(
          child: Container(
            margin:
            EdgeInsets.only(bottom: 5, right: 20, left: 20, top: 15),
            child: Row(
              children: <Widget>[
                Text(
                  "Sub-Categories",
                  style: GoogleFonts.roboto(
                      color: Colors.red,
                      fontWeight: FontWeight.bold,
                      fontSize: 25),
                ),
              ],
            ),
          ),
        ),
        SubCategoriesApi(),
        Container(
          margin: EdgeInsets.only(bottom: 5, right: 20, left: 20, top: 15),
          child: Row(
            children: <Widget>[
              Text(
                "BestSeller",
                style: GoogleFonts.roboto(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                    fontSize: 25),
              ),
            ],
          ),
        ),
        Container(
          height: 150,
          margin: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
          padding: EdgeInsets.symmetric(horizontal: 20),
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(15),
              image: DecorationImage(
                  image: AssetImage(
                      'assets/WhatsApp Image 2020-07-23 at 12.17.11 AM (1).jpeg'),
                  fit: BoxFit.cover)),
        ),
        Container(
          margin: EdgeInsets.only(bottom: 5, right: 20, left: 20, top: 15),
          child: Row(
            children: <Widget>[
              Text(
                "Trending Now",
                style: GoogleFonts.roboto(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                    fontSize: 25),
              ),
            ],
          ),
        ),
        TrendingNowApi()
      ],
    ),
  ),
  Center(child: Text("TV"),),
    Center(child: Text("Fav"),),
  ];
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onBackPressed,
      child: Scaffold(
        // backgroundColor: Colors.grey.withOpacity(0.8),
        bottomNavigationBar: BottomNavigationBar(
          onTap: (index) => {
            setState((){
              _currentIndex=index;
            })
            },
          type: BottomNavigationBarType.shifting,
          currentIndex: _currentIndex,
          items: items,
        ),
        drawer: DrawerWidget(),
        appBar: AppBar(
          iconTheme: new IconThemeData(color: Colors.black),
          elevation: 0,
          backgroundColor: Colors.white,
          title: Row(
            children: <Widget>[
              Container(width: 50, child: Image.asset("assets/freshodaily.png")),
              Container(
                height: 40,
                width: 70,
                decoration: BoxDecoration(
                    color: Colors.white, borderRadius: BorderRadius.circular(50)),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Icon(
                      Icons.location_on,
                      color: Theme.of(context).accentColor,
                      size: 15,
                    ),
                    Text(
                      "Chennai",
                      style: GoogleFonts.raleway(
                        color: Theme.of(context).accentColor,
                        fontSize: 10,
                      ),
                    )
                  ],
                ),
              ),
            ],
          ),
          actions: <Widget>[
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: CircleAvatar(
                backgroundColor: Colors.grey,
                child: GestureDetector(
                    onTap: () {
                      Navigator.of(context).pushNamed('/Cart');
                    },
                    child: Icon(
                      Icons.shopping_cart,
                      color: Colors.black,
                    )),
              ),
            )
          ],
        ),
        body: tabs[_currentIndex]
      ),
    );
  }
  Future<bool> _onBackPressed() {
    return showDialog(
      context: context,
      builder: (context) => new AlertDialog(
        title: new Text('Are you sure?'),
        content: new Text('Do you want to exit an App'),
        actions: <Widget>[
          new GestureDetector(
            onTap: () => Navigator.of(context).pop(false),
            child: Text("NO"),
          ),
          SizedBox(height: 16),
          new GestureDetector(
            onTap: () => {
              exit(0)
              },
            child: Text("YES"),
          ),
        ],
      ),
    ) ??
        false;
  }
}
