import 'package:final_app/Helper/BlockButton.dart';
import 'package:final_app/Helper/FoodCategories.dart';
import 'package:final_app/Helper/SubCategoriesApi.dart';
import 'package:final_app/Helper/TrendingNowApi.dart';
import 'package:final_app/api/loginApi.dart';
import 'package:final_app/models/user.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:progress_dialog/progress_dialog.dart';

import '../Constants.dart';
import '../Preferences.dart';

class ProfilePage extends StatefulWidget {
  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  ProgressDialog pr;
  User user = new User();

  @override
  initState() {
    super.initState();
    initPrefs();
  }

  void initPrefs() async {
    await Preferences.getUser().then((value) {
      setState(() {
        user = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    pr = ProgressDialog(context,
        isDismissible: false,
        customBody: Container(
            color: Colors.transparent,
            child: SpinKitCubeGrid(
              color: Theme.of(context).accentColor,
            )));
    pr.style(
      backgroundColor: Colors.transparent,
    );
    if (this.user.name != '--') {
      return Scaffold(
        backgroundColor: Colors.white70,
        body: Padding(
          padding: EdgeInsets.fromLTRB(30.4, 40.0, 30.0, 0.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Center(
                child: CircleAvatar(
                  backgroundImage: AssetImage('assets/profile.jpg'),
                  radius: 40.0,
                ),
              ),
              Divider(
                height: 60.0,
                color: Colors.grey[700],
              ),
              Center(
                child: Text(
                  "" + this.user.name,
                  style: TextStyle(
                    color: Colors.redAccent,
                    fontSize: 28.0,
                    fontWeight: FontWeight.bold,
                    letterSpacing: 1.0,
                  ),
                ),
              ),
              SizedBox(
                height: 30,
              ),
              Center(
                child: Text(
                  "" + this.user.email,
                  style: TextStyle(
                    color: Colors.redAccent,
                    fontSize: 20.0,
                    fontWeight: FontWeight.bold,
                    letterSpacing: 1.0,
                  ),
                ),
              ),
              Center(
                child: FlatButton.icon(
                  icon: Icon(Icons.edit),
                  label: Text('Edit Profile',
                      style: TextStyle(color: Colors.black87)),
                  color: Colors.white70,
                  onPressed: () {},
                ),
              ),
              Center(
                child: RaisedButton.icon(
                  icon: Icon(
                    Icons.exit_to_app,
                    color: Colors.white70,
                  ),
                  label:
                      Text('Logout', style: TextStyle(color: Colors.white70)),
                  color: Colors.redAccent,
                  onPressed: () {
                    CustomAlertDialog(context);
                  },
                ),
              ),
            ],
          ),
        ),
      );
    } else {
      return Scaffold(
        backgroundColor: Colors.white70,
        body: Center(
          child: FlatButton(
            onPressed: () {
              Navigator.of(context).pushNamed('/LoginPage');
            },
            color: Colors.redAccent,
            child: Text(
              'Login',
              style: TextStyle(color: Colors.black87, fontSize: 30),
            ),
          ),
        ),
      );
    }
  }

  CustomAlertDialog(BuildContext context) {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text(
              "Are you sure want to logout?",
              style: Theme.of(context).textTheme.subhead,
            ),
            actions: <Widget>[
              MaterialButton(
                onPressed: () async {
//                  Services _services = Services();
                  pr.show();
                  Login.LogoutUser().then((response) {
                    pr.hide();
                    Navigator.pushNamed(context, '/LoginPage');
                  });
                },
                child: Text(
                  "Yes",
                  style: Theme.of(context).textTheme.subhead,
                ),
              ),
              MaterialButton(
                color: Theme.of(context).accentColor,
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: Text(
                  "No",
                  style: Theme.of(context).textTheme.subhead,
                ),
              )
            ],
          );
        });
  }
}
