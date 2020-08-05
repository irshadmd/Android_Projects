import 'package:final_app/Constants.dart';
import 'package:final_app/api/cart.dart';
import 'package:final_app/models/Cart.dart';
import 'package:final_app/models/Details.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:google_fonts/google_fonts.dart';

class PlantDetail extends StatefulWidget {
  final Detail details;

  const PlantDetail({Key key, this.details}) : super(key: key);

  @override
  _PlantDetailState createState() => _PlantDetailState();
}

class _PlantDetailState extends State<PlantDetail> {
  final globalKey = GlobalKey<ScaffoldState>();
  String message = "";
  bool _emptyCart = false;

  List<CartData> productApi = new List<CartData>();

  void getCouponList() async {
    if (mounted) {
      await CartApi.cartList().then((value) {
        if (value is List) {
          setState(() {
            productApi = value;
            print("=+++++++++++++++++++++++++++======");
            print(productApi.length);
            print("=========== Product Api=========");
            print(productApi.toString());
            if (value.length == 0) {
              setState(() {
                this._emptyCart = true;
              });
            } else {
              setState(() {
                productApi = value;
              });
            }
          });
        } else if (value is String) {
          setState(() {
            message = value;
            print(message);
          });
        }
      });
    }
  }

  @override
  void initState() {
    super.initState();
    print("====Trending Now==============");
    getCouponList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: globalKey,
      body: Material(
        child: ListView(
          children: <Widget>[
            Stack(
              children: <Widget>[
                Container(
                  height: MediaQuery.of(context).size.height,
                  width: MediaQuery.of(context).size.width,
                  decoration:
                      BoxDecoration(color: Theme.of(context).accentColor),
                ),
                Positioned(
                  top: MediaQuery.of(context).size.height / 2,
                  child: Container(
                    height: MediaQuery.of(context).size.height / 2,
                    width: MediaQuery.of(context).size.width,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.only(
                          topLeft: Radius.circular(20.0),
                          topRight: Radius.circular(20.0),
                        ),
                        color: Colors.white),
                  ),
                ),
                Align(
                  alignment: Alignment.topLeft,
                  child: IconButton(
                    icon: Icon(Icons.arrow_back, color: Colors.white),
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(
                      top: 10.0,
                      left: MediaQuery.of(context).size.width - 60.0),
                  child: FloatingActionButton(
                    onPressed: () {
                      Navigator.of(context).pushNamed('/Cart');
                    },
                    backgroundColor: Colors.white,
                    mini: true,
                    elevation: 0.0,
                    child: Icon(Icons.shopping_cart,
                        color: Colors.redAccent, size: 15.0),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 25.0, top: 60.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Text(
                        'NAME',
                        style: GoogleFonts.montserrat(
                            // fontFamily: 'Montserrat',
                            fontSize: 12.0,
                            fontWeight: FontWeight.w600,
                            color: Colors.black),
                      ),
                      Text(
                        this.widget.details.productName,
                        style: GoogleFonts.montserrat(
                            // fontFamily: 'Montserrat',
                            fontSize: 35.0,
                            fontWeight: FontWeight.w600,
                            color: Colors.white),
                      ),
                      SizedBox(height: 15.0),
                      Text(
                        'PRICE',
                        style: GoogleFonts.montserrat(
                            // fontFamily: 'Montserrat',
                            fontSize: 12.0,
                            fontWeight: FontWeight.w600,
                            color: Colors.black),
                      ),
                      Text(
                        '\$' + this.widget.details.rates,
                        style: GoogleFonts.montserrat(
                            // fontFamily: 'Montserrat',
                            fontSize: 25.0,
                            fontWeight: FontWeight.w300,
                            color: Colors.white),
                      ),
                      SizedBox(height: 15.0),
                      Text(
                        'MEASURMENT UNIT',
                        style: GoogleFonts.montserrat(
                            fontSize: 12.0,
                            fontWeight: FontWeight.w600,
                            color: Colors.black),
                      ),
                      Text(
                        this.widget.details.measurementUnit,
                        style: GoogleFonts.montserrat(
                            // fontFamily: 'Montserrat',
                            fontSize: 25.0,
                            fontWeight: FontWeight.w300,
                            color: Colors.white),
                      ),
                    ],
                  ),
                ),
                Positioned(
                  top: (MediaQuery.of(context).size.height / 2) - 185.0,
                  left: (MediaQuery.of(context).size.width / 2) - 80.0,
                  child: Image(
                    image: NetworkImage(
                        "https://freshodaily.com/${this.widget.details.image}"),
                    fit: BoxFit.cover,
                    height: 250.0,
                  ),
                ),
                Padding(
                    padding: EdgeInsets.only(
                        top: MediaQuery.of(context).size.height - 275.0,
                        left: 20.0,
                        right: 15.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Text(
                          'Details',
                          style: GoogleFonts.montserrat(
                              fontSize: 25.0, fontWeight: FontWeight.w600),
                        ),
                        SizedBox(height: 12.0),
                        Text(
                          this.widget.details.description,
                          style: GoogleFonts.montserrat(
                            fontSize: 14.0,
                          ),
                        ),
                        SizedBox(height: 30.0),
                        Center(
                          child: Container(
                            decoration: BoxDecoration(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(100)),
                            ),
                            child: FlatButton(
                              onPressed: () {
                                CartApi.addToCart(
                                        this.widget.details.id.toString())
                                    .then((value) {
                                  if (value == "Item Added to Cart") {
                                    globalKey.currentState.showSnackBar(
                                        SnackBar(
                                            backgroundColor: Theme.of(context)
                                                .focusColor
                                                .withOpacity(0.8),
                                            content: Text(
                                              "Item Added to Cart",
                                              style: TextStyle(
                                                color: Colors.white70,
                                                fontSize: 10,
                                              ),
                                              textAlign: TextAlign.center,
                                            )));
                                  } else if (value == "Item already in cart") {
                                    globalKey.currentState.showSnackBar(
                                        SnackBar(
                                            backgroundColor: Theme.of(context)
                                                .focusColor
                                                .withOpacity(0.8),
                                            content: Text(
                                              "Item already in cart",
                                              style: TextStyle(
                                                color: Colors.white70,
                                                fontSize: 10,
                                              ),
                                              textAlign: TextAlign.center,
                                            )));
                                  }
                                });
                                print("Helooooooooooooooooooooooooooooo");
                                print(this.widget.details.id.toString());
                                print("Helooooooooooooooooooooooooooooo");
                              },
                              padding: EdgeInsets.symmetric(
                                  horizontal: 66, vertical: 14),
                              color: Theme.of(context).accentColor,
                              shape: StadiumBorder(),
                              child: Text(
                                "Add To Cart",
                                style: kButtonStyle,
                              ),
                            ),
                          ),
                        ),
                      ],
                    )),
              ],
            )
          ],
        ),
      ),
    );
  }
}
