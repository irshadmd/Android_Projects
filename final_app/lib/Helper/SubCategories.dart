import 'package:final_app/api/Details.dart';
import 'package:final_app/models/Details.dart';
import 'package:final_app/pages/Categories.dart';
import 'package:final_app/pages/ProductDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class SubCategories extends StatefulWidget {
  final String id;
  final String title;
  final String imagePath;
  final int TextColor;
  const SubCategories({Key key,this.id,this.imagePath, this.title,this.TextColor}) : super(key: key);

  @override
  _SubCategoriesState createState() => _SubCategoriesState();
}

class _SubCategoriesState extends State<SubCategories> {
  Detail details;
  void getCouponList() async{
    await ProductDetil.getProductbyId(this.widget.id.toString()).then((value) {
     if(mounted){
        setState(() {
        details=value;
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
    return GestureDetector(
      onTap: (){
        Navigator.push(context, MaterialPageRoute(builder: (context) => PlantDetail(
          details: details,
        )));
      },
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 5),
        padding: EdgeInsets.symmetric(horizontal: 10),
        child: Column(
          children: <Widget>[
            CircleAvatar(
              backgroundColor: Colors.white,
              backgroundImage: NetworkImage('${widget.imagePath}'),
              radius: 35,
            ),
            SizedBox(height: 10,),
            Text("${widget.title}",style: GoogleFonts.raleway(
                color: Color(widget.TextColor),
                fontWeight: FontWeight.w600,
                fontSize: 20
            ),)
          ],
        ),
      ),
    );
  }
}
