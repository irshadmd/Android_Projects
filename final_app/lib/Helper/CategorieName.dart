import 'package:flutter/material.dart';

import '../Constants.dart';

class CategorieName extends StatelessWidget {
  final String text1;
  final String text2;
  CategorieName({Key key,@required this.text1,@required this.text2,}): super(key: key);
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(50.0),
      child: Row(
        children: <Widget>[
          Text(text1,style: kHeadingLight,),
          Text(text2,style: kHeadingHard,),
        ],
      ),
    );
  }
}