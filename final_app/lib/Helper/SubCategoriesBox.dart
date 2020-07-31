import 'package:final_app/models/Categories.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class SubCategoriesBox extends StatelessWidget {
  final CategoriesApi categoriesApi;
  const SubCategoriesBox({Key key, this.categoriesApi}) : super(key: key);  
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
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Expanded(
                      child: Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(15),
                            color: Colors.white,
                          image: DecorationImage(image: NetworkImage("https://freshodaily.com/${categoriesApi.image.toString()}"),fit: BoxFit.cover),
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
                        categoriesApi.name.toString(),
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
