import 'package:final_app/pages/Categories.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class Categories extends StatelessWidget {

  final String title;
  final String imagePath;
  final String id;
  final int TextColor;
  const Categories({Key key,this.id,this.imagePath, this.title,this.TextColor}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: (){
        Navigator.push(context,MaterialPageRoute(builder: (context)=> CategoryPage(
          id: this.id,
        )));
      },
          child: Container(
        margin: EdgeInsets.symmetric(horizontal: 5),
        padding: EdgeInsets.symmetric(horizontal: 10),
        child: Column(
          children: <Widget>[
            CircleAvatar(
              backgroundColor: Colors.white,
              // minRadius: 20,
              backgroundImage: NetworkImage('${imagePath}'),
              radius: 45,
            ),
            SizedBox(height: 10,),
            Text("$title",style: GoogleFonts.raleway(
                color: Color(TextColor),
                fontWeight: FontWeight.w600,
                fontSize: 20
            ),)
          ],
        ),
      ),
    );
  }
}
