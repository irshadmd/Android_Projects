import 'package:final_app/Helper/TrendingNow.dart';
import 'package:final_app/api/Categories.dart';
import 'package:final_app/models/AllProducts.dart';
import 'package:flutter/material.dart';

class TrendingNowApi extends StatefulWidget {
  @override
  _TrendingNowApiState createState() => _TrendingNowApiState();
}

class _TrendingNowApiState extends State<TrendingNowApi> {
  List<Data> productApi = new List<Data>();
  void getCouponList() async{
    await CategoriesList.TrendingNowListing().then((value) {
      if(mounted){
      setState(() {
        productApi=value;
        print("=+++++++++++++++++++++++++++======");
        print(productApi.length);
      });}
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
    return  Container(
      height: 250,
    margin: EdgeInsets.symmetric(horizontal: 10,vertical: 5),
    child: ListView.builder(
      itemCount:productApi.length,
      itemBuilder: (context, index) {
        return TrendingNow(
          title: productApi[index].productName,
          qty: productApi[index].measurementUnit,
          pices: productApi[index].id.toString(),
          mrp: productApi[index].rates.toString(),
          imgPath: "https://freshodaily.com/${productApi[index].image.toString()}",
        );
      },
      scrollDirection: Axis.horizontal,
    ));
  }
}