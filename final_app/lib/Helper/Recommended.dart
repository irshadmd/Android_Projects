import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
class Recommended extends StatelessWidget {
  final String imagePath;
  final String title;
  final String pieces;
  final String orderDay;

  const Recommended({Key key, this.imagePath, this.title, this.pieces, this.orderDay}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(10),
      height: 100,
      width: 350,
//      padding: EdgeInsets.symmetric(horizontal: 20, vertical: 8),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(10),
        boxShadow: [
          BoxShadow(
              color:Colors.grey.withOpacity(0.5),
              blurRadius: 5,
              offset: Offset(0, 4)),
        ],
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          Container(
            padding: EdgeInsets.symmetric(horizontal: 10),
            margin: EdgeInsets.symmetric(horizontal: 10),
            height: 60,
            width: 100,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(15)),
                color: Colors.red,
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++insert+++++
                image: DecorationImage(image: AssetImage(imagePath))
            ),
          ),
          SizedBox(width: 15),
          Flexible(
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Expanded(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Text(
                          title,
                          overflow: TextOverflow.ellipsis,
                          maxLines: 2,
                          style: GoogleFonts.raleway(
                              color: Colors.black,
                              fontWeight: FontWeight.w600,
                              fontSize: 15
                          )
                      ),
                      Text(
                          "Net wt. ${pieces}gms",
                          overflow: TextOverflow.fade,
                          softWrap: false,
                          style:  GoogleFonts.raleway(
                              color: Colors.black,
                              fontWeight: FontWeight.w400,
                              fontSize: 12
                          )
                      ),

                      Text(
                          "$orderDay",
                          overflow: TextOverflow.fade,
                          softWrap: false,
                          style:  GoogleFonts.raleway(
                              color: Colors.grey,
                              fontWeight: FontWeight.w400,
                              fontSize: 13
                          )
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          Container(
//            padding: EdgeInsets.symmetric(horizontal: 10),
            margin: EdgeInsets.symmetric(horizontal: 10),
            height: 30,
            width: 30,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++insert+++++
                color: Colors.red[100]
            ),
            child: Center(
                child: Icon(Icons.add,color: Colors.red,)),
          ),
        ],
      ),
    );
  }
}
