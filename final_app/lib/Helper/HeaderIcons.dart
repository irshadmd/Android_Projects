import 'package:flutter/material.dart';

class HeaderIcons extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return                Row(
      mainAxisAlignment: MainAxisAlignment.end,
      crossAxisAlignment: CrossAxisAlignment.end,
      children: <Widget>[
        SizedBox(width: 120.0,),
        Icon(Icons.search,color: Colors.black,),
        SizedBox(width: 15.0,),
        Icon(Icons.grid_on,color: Colors.black,),
      ],
    );
  }
}