
import 'dart:io';

import 'package:final_app/Helper/CartItemWidget.dart';
import 'package:final_app/api/Categories.dart';
import 'package:final_app/api/cart.dart';
import 'package:final_app/models/AllProducts.dart';
import 'package:final_app/models/Cart.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class Cart extends StatefulWidget {
  int currentTab = 3;
  Cart({Key key, this.currentTab}) : super(key: key);

  @override
  _CartState createState() => _CartState();
}

class _CartState extends State<Cart> {
  final globalKey = GlobalKey<ScaffoldState>();
  String message = "";
  bool _emptyCart = false;
  List<CartData> productApi = new List<CartData>();
  void getCouponList() async{
    await CartApi.cartList().then((value) {
      if(value is List){
        setState(() {
        productApi=value;
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
      } else if(value is String){
        setState(() {
          message = value;
          print(message);
        });
      }
    });
  }

  @override
  void initState() {
    super.initState();
    print("====Trending Now==============");
    getCouponList();
  }

  @override
  Widget build(BuildContext context) {
    TextEditingController couponController = new TextEditingController();
    return WillPopScope(
      onWillPop: _onBackPressed,
      child: Scaffold(
          key: globalKey,
          appBar: AppBar(
            leading: GestureDetector(
                onTap: () {
                  Navigator.of(context).pushNamed('/MainPage');
                },
                child: Icon(Icons.arrow_back)),
            actions: <Widget>[
              Column(
                children: <Widget>[
                  IconButton(
                      icon: Icon(
                        Icons.remove_shopping_cart,
                        color: Colors.black,
                      ),
                      onPressed: () {
                        CartApi.deleteCart();
                        setState(() {
                          this._emptyCart = true;

                          // productApi.length=0;
                        });
                      }),
                ],
              )
            ],
            backgroundColor: Colors.redAccent,
            elevation: 0,
            centerTitle: true,
            title: Text(
              "My Cart",
              style: Theme.of(context)
                  .textTheme
                  .headline6
                  .merge(TextStyle(letterSpacing: 1.3,color: Colors.white)),
            ),
          ),
          body: Stack(
            fit: StackFit.expand,
            children: <Widget>[
              Container(
                margin: EdgeInsets.only(bottom: 150),
                padding: EdgeInsets.only(bottom: 15),
                child: SingleChildScrollView(
                  padding: EdgeInsets.symmetric(vertical: 10),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.start,
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      productApi.length==0?Center(child: CircularProgressIndicator(),):
                  this._emptyCart?
                    Image.asset('assets/emptycart.png'):
                            ListView.separated(
                              padding: EdgeInsets.symmetric(vertical: 15),
                              scrollDirection: Axis.vertical,
                              shrinkWrap: true,
                              primary: false,
                              itemCount: productApi.length,
                              separatorBuilder: (context, index) {
                                return SizedBox(height: 15);
                              },
                              itemBuilder: (context, index) {
                                return CartItemWidget(
                                  cartData: productApi[index],
                                );
                              },
                            ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
    );
  }

  Future<bool> _onBackPressed() {
    Navigator.of(context).pushNamed('/MainPage');

  }
}
