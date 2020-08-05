import 'package:final_app/Helper/SubCategoriesBox.dart';
import 'package:final_app/Helper/nav_drawer.dart';
import 'package:final_app/models/Categories.dart';
import 'package:final_app/pages/ProductDetailPage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import 'package:final_app/api/Categories.dart';

class CategoryPage extends StatefulWidget {
  final id;

  const CategoryPage({Key key, this.id}) : super(key: key);

  @override
  _CategoryPageState createState() => _CategoryPageState();
}

class _CategoryPageState extends State<CategoryPage> {
  List<CategoriesApi> productApi = new List<CategoriesApi>();

  @override
  void initState() {
    super.initState();
    print("====Product Listing==============");
    print(this.widget.id.toString());
    getCouponList();
    print("Get coupon list");
  }

  void getCouponList() async {
    await CategoriesList.subCategoriesById(this.widget.id.toString())
        .then((value) {
      setState(() {
        print(value);
        productApi = value;
        print("product api lengthhhhhhhhhhhh");
        print(productApi.length);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: DrawerWidget(),
      appBar: AppBar(
        elevation: 0,
        title: Text(
          "Categories",
          style: GoogleFonts.raleway(color: Colors.white, fontSize: 20),
        ),
        actions: <Widget>[
          Container(
            padding: EdgeInsets.symmetric(horizontal: 25),
            child: Icon(Icons.search),
          )
        ],
      ),
      body: GridView.count(
        scrollDirection: Axis.vertical,
        shrinkWrap: true,
        primary: false,
        crossAxisSpacing: 40,
        mainAxisSpacing: 20,
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        crossAxisCount:
            MediaQuery.of(context).orientation == Orientation.portrait ? 2 : 4,
        children: List.generate(productApi.length, (index) {
          return SubCategoriesBox(
            categoriesApi: productApi[index],
          );
        }),
      ),
    );
  }
}
