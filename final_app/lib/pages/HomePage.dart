import 'package:final_app/Helper/FoodCategories.dart';
import 'package:final_app/Helper/SubCategoriesApi.dart';
import 'package:final_app/Helper/TrendingNowApi.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import '../Constants.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: <Widget>[
          Container(
            margin: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
            padding: EdgeInsets.symmetric(horizontal: 20),
            height: 40,
            width: double.infinity,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(15),
              color: Colors.white,
              border: Border.all(
                color: Colors.black,
                width: 1,
              ),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Icon(Icons.search),
                SizedBox(
                  width: 20,
                ),
                Text(
                  "Search for products...",
                  style: KsearchBar,
                )
              ],
            ),
          ),
          Container(
            margin: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
            padding: EdgeInsets.symmetric(horizontal: 20),
            height: 150,
            decoration: BoxDecoration(
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
              margin: EdgeInsets.only(bottom: 5, right: 20, left: 20, top: 15),
              child: Row(
                children: <Widget>[
                  Text(
                    "Sub Categories",
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
    );
  }
}
