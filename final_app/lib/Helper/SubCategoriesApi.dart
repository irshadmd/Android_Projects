import 'package:final_app/api/Categories.dart';
import 'package:final_app/models/Categories.dart';
import 'package:flutter/material.dart';

import 'SubCategories.dart';

class SubCategoriesApi extends StatefulWidget {
  @override
  _SubCategoriesApiState createState() => _SubCategoriesApiState();
}

class _SubCategoriesApiState extends State<SubCategoriesApi> {
  List<CategoriesApi> productApi = new List<CategoriesApi>();

  void getCouponList() async {
    await CategoriesList.subCategoriesListing().then((value) {
      if (mounted) {
        setState(() {
          productApi = value;
          print("=+++++++++++++++++++++++++++======");
          print(productApi.length);
        });
      }
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
            color: Colors.white,
            border: Border.all(color: Colors.white70,width: 0.5),
            borderRadius: BorderRadius.circular(15)),
        padding: EdgeInsets.symmetric(vertical: 15, horizontal: 10),
        margin: EdgeInsets.symmetric(vertical: 15, horizontal: 10),
        child: ListView.builder(
          itemCount: productApi.length,
          itemBuilder: (context, index) {
            return SubCategories(
              id: productApi[index].id.toString(),
              title: productApi[index].name.toString(),
              TextColor: 0xffcc0000,
              imagePath:
                  "https://freshodaily.com/${productApi[index].image.toString()}",
            );
          },
          scrollDirection: Axis.horizontal,
        ));
  }
}
