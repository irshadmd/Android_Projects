import 'package:final_app/Helper/Categories.dart';
import 'package:final_app/api/Categories.dart';
import 'package:final_app/models/Categories.dart';
import 'package:flutter/material.dart';

class ProductCategories extends StatefulWidget {
  @override
  _ProductCategoriesState createState() => _ProductCategoriesState();
}

class _ProductCategoriesState extends State<ProductCategories> {

  List<CategoriesApi> productApi = new List<CategoriesApi>();
  void getCouponList() async{
    await CategoriesList.categoriesListing().then((value) {
      setState(() {
        productApi=value;
        print("=+++++++++++++++++++++++++++======");
        print(productApi.length);
      });
    });
  }

  @override
  void initState() {
    super.initState();
    print("====Product Listing==============");
    getCouponList();
  }
  @override
  Widget build(BuildContext context) {
    return Container(
    height: 160,
    decoration: BoxDecoration(
      color: Colors.red,
        boxShadow: [BoxShadow(
          color: Colors.grey,
          offset: Offset(0, 4),
        )],
      borderRadius: BorderRadius.circular(15)
    ),
    padding: EdgeInsets.symmetric(vertical: 15,horizontal: 10),
    margin: EdgeInsets.symmetric(vertical: 15,horizontal: 10),
    child: ListView.builder(
      itemCount:productApi.length,
      itemBuilder: (context, index) {
        return Categories(
          id: productApi[index].id.toString(),
          title: productApi[index].name.toString(),
          TextColor: 0xffFFFFFF,
          imagePath: "https://freshodaily.com/${productApi[index].image.toString()}",
        );
      },
      scrollDirection: Axis.horizontal,
    ));
  }
}