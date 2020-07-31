import 'package:flutter/material.dart';

class CarouselDishes extends StatelessWidget {
  final String name1;
  final String name2;
  final String desc;
  final String categories;
  final String imgUrl;
  CarouselDishes({Key key, @required this.name1,@required this.name2,@required this.desc,@required this.categories,@required this.imgUrl}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Stack(
      fit: StackFit.loose,
      alignment: Alignment.bottomCenter,
      children: <Widget>[
        Container(
          height: 428,
          width: 246,
          margin: EdgeInsets.symmetric(horizontal: 30.0,vertical: 10.0),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(30),
            color: Theme.of(context).accentColor.withOpacity(0.3)
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    Text(name1,style: TextStyle(
                        color: Color(0xff999DAD),
                        fontStyle: FontStyle.normal,
                        fontWeight: FontWeight.w100,
                        fontSize: 32.0,
                        fontFamily: 'Raleway'
                    ),),
                  ],
                ),
              ),
              Container(
                padding: EdgeInsets.symmetric(horizontal: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    Text(name2,style: TextStyle(
                        color: Colors.black,
                        fontStyle: FontStyle.normal,
                        fontWeight: FontWeight.w500,
                        fontSize: 32.0,
                        fontFamily: 'Raleway'
                    ),),
                  ],
                ),
              ),
              Container(
                  padding: EdgeInsets.all(20),
                  child: Text(desc,style: TextStyle(
                      color: Colors.black,
                      fontFamily: 'Raleway',
                      fontSize: 20.0
                  ),))
            ],
          ),
        ),
        Positioned(
          top: 50,
          right: -0,
          child: CircleAvatar(
            radius: 70,
            backgroundColor:  Theme.of(context).accentColor.withOpacity(0.7),
            backgroundImage: AssetImage(imgUrl),
          ),
        ),
        Positioned(
            top: -0,
            left: 10,
            child: Container(
              height: 60.0,
              width: 60.0,
              decoration: BoxDecoration(
                  color: Theme.of(context).accentColor.withOpacity(0.7),
                  borderRadius: BorderRadius.circular(50.0),
                  boxShadow: [BoxShadow(
                      color: Theme.of(context).accentColor,
                      offset: Offset(-2, 2),
                      spreadRadius: 5.0,
                      blurRadius: 10
                  )]
              ),
              child: Icon(Icons.favorite_border,color: Colors.white,),
            )
        ),
      ],
    );
  }
}



