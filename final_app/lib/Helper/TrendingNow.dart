import 'package:final_app/api/cart.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:google_fonts/google_fonts.dart';

class TrendingNow extends StatefulWidget {
  final String title;
  final String pices;
  final String mrp;
  final String qty;
  final String imgPath;
  const TrendingNow({Key key,this.imgPath, this.title, this.pices, this.mrp, this.qty}) : super(key: key);
  
  @override
  _TrendingNowState createState() => _TrendingNowState();
}

class _TrendingNowState extends State<TrendingNow> {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 330,
      margin: EdgeInsets.only(left: 20, right: 20, top: 15, bottom: 10),
      decoration: BoxDecoration(
        color: Colors.grey,
        borderRadius: BorderRadius.all(Radius.circular(10)),
        boxShadow: [
          BoxShadow(color: Theme.of(context).focusColor.withOpacity(1.0), blurRadius: 15, offset: Offset(0, 5)),
          BoxShadow(color: Theme.of(context).accentColor.withOpacity(0.3), blurRadius: 15, offset: Offset(0, 5)),
        ],
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          Container(
            width: 360,
            height: 90,
            decoration: BoxDecoration(
              color: Colors.white,
              image: DecorationImage(
                image:NetworkImage(this.widget.imgPath),
                fit: BoxFit.contain,
              ),
              boxShadow: [
          BoxShadow(color: Theme.of(context).focusColor.withOpacity(0.3), blurRadius: 15, offset: Offset(0, 5)),
        ],
              borderRadius: BorderRadius.only(topLeft: Radius.circular(10), topRight: Radius.circular(10),bottomRight: Radius.circular(30)),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 5),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              mainAxisSize: MainAxisSize.max,
              children: <Widget>[
                Expanded(
                  flex: 4,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Text(
                        this.widget.title,
                        overflow: TextOverflow.fade,
                        style: GoogleFonts.raleway(
                          color: Colors.black,
                          fontSize: 25,
                          fontWeight: FontWeight.bold
                        ),
                      ),
                      SizedBox(height: 10,),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text(
                            "PIECES: "+this.widget.pices,
                            overflow: TextOverflow.ellipsis,
                            style: GoogleFonts.raleway(
                              color: Colors.black,
                              fontWeight: FontWeight.w600,
                              fontSize: 16
                            ),
                          ),
                          
                        ],
                      ),
                      SizedBox(height: 10,),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text(
                            "M.R.P."+"  "+this.widget.mrp,
                            overflow: TextOverflow.fade,
                            style: GoogleFonts.raleway(
                              color: Colors.black,
                              fontWeight: FontWeight.w600,
                              fontSize: 16
                            ),
                          ),
                          FlatButton(
                            onPressed: (){
                               CartApi.addToCart(this.widget.pices).then(
                                (value){
                                  if(value=="Item Added to Cart"){
                                   Scaffold.of(context).showSnackBar(SnackBar(
                                    content: Text("Item Added to Cart"),
                                  ));
                                  }
                                  else if(value=="Item already in cart"){
                                       Scaffold.of(context).showSnackBar(SnackBar(
                                    content: Text("Item Already In Cart"),
                                  ));
                                  }
                                }
                              );
                            },
                            child: Container(
                            padding: EdgeInsets.symmetric(horizontal: 10),
                            height: 40,
                            width: 120,
                            decoration: BoxDecoration(
                              color: Colors.red,
                              borderRadius: BorderRadius.circular(15),
                            ),
                            child: Center(
                              child: Text("ADD TO CART",style: TextStyle(
                                color: Colors.white
                              ),),
                            ),
                              ),
                          )
                        ],
                      ),
                    ],
                  ),
                ),
                SizedBox(width: 10),
              ],
            ),
          )
        ],
      ),
    );
  }
}