import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';



class CategoryGrid extends StatefulWidget {
  final String product;
  const CategoryGrid({Key key, this.product}) : super(key: key);
  @override
  _CategoryGridState createState() => _CategoryGridState();
}

class _CategoryGridState extends State<CategoryGrid> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Container(
          padding: EdgeInsets.all(10),
          margin: EdgeInsets.all(10),
          height: 70,
          width: 70,
          decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(15),
              boxShadow: [BoxShadow(
                  color: Colors.black.withOpacity(0.7),
                  blurRadius: 15,
                  spreadRadius: 3,
                  offset: Offset(0, 5)
              )]
          ),
          child: Row(
            children: <Widget>[
              // Image.asset("//.png"),
            ],
          ),
        ),
        Text("Egg",style: GoogleFonts.raleway(
            color: Colors.black,
            fontSize: 18,
            fontWeight: FontWeight.w500
        ),)
      ],
    );
  }
}