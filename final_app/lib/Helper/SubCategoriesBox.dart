import 'package:final_app/api/Details.dart';
import 'package:final_app/models/Categories.dart';
import 'package:final_app/models/Details.dart';
import 'package:final_app/pages/ProductDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class SubCategoriesBox extends StatefulWidget {
  final CategoriesApi categoriesApi;
  final String id;
  final String title;
  final String imagePath;
  final int TextColor;
  const SubCategoriesBox({Key key, this.categoriesApi, this.id, this.title, this.imagePath, this.TextColor}) : super(key: key);

  @override
  _SubCategoriesBoxState createState() => _SubCategoriesBoxState();
}

class _SubCategoriesBoxState extends State<SubCategoriesBox> {
  Detail details;

  void getCouponList() async {
    await ProductDetil.getProductbyId(this.widget.id.toString()).then((value) {
      if (mounted) {
        setState(() {
          details = value;
          print("=+++++++Details ++++++======");
          print(details.toString());
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
    return InkWell(
      highlightColor: Colors.white,
      splashColor:Colors.white,
      child: Stack(
        alignment: AlignmentDirectional.topEnd,
        children: <Widget>[
          Stack(
            children: <Widget>[
              GestureDetector(
                onTap: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => PlantDetail(
                            details: details,
                          )));
                },
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Expanded(
                      child: Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(15),
                            color: Colors.white,
                          image: DecorationImage(image: NetworkImage("https://freshodaily.com/${widget.categoriesApi.image.toString()}"),fit: BoxFit.cover),
                          boxShadow: [
                            BoxShadow(color: Theme.of(context).focusColor.withOpacity(0.4), blurRadius: 15, offset: Offset(0, 5)),
                          ],
                          // borderRadius: BorderRadius.circular(5),
                        ),
                      ),
                    ),
                    SizedBox(height: 5),
                    Align(
                        child: Text(
                        widget.categoriesApi.name.toString(),
                        style: GoogleFonts.raleway(
                          color: Colors.black,
                          fontSize: 20,
                          fontWeight: FontWeight.w600
                        ),
                        overflow: TextOverflow.ellipsis,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
