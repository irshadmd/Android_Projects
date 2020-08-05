import 'package:final_app/api/loginApi.dart';
import 'package:final_app/models/user.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:progress_dialog/progress_dialog.dart';

import '../Preferences.dart';

class ProfilePage extends StatefulWidget {
  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  ProgressDialog pr;
  User user = new User();
  String name = "";
  String email = "";

  @override
  initState() {
    super.initState();
    initPrefs();
  }

  void initPrefs() async {
    await Preferences.getUser().then((value) {
      setState(() {
        user = value;
        name = user.name;
        email = user.email;
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
    if (name != '--') {
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
                  name,
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
                  email,
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
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Image(
                image: AssetImage('assets/notfound.png'),
              ),
              ButtonTheme(
                minWidth: 250,
                height: 50,
                child: RaisedButton.icon(
                  onPressed: () {
                    Navigator.of(context).pushNamed('/LoginPage');
                  },

                  color: Colors.blueAccent,
                  icon: Icon(
                    Icons.lock_open,
                    color: Colors.white70,
                  ),
                  label:
                  Text('Login', style: TextStyle(color: Colors.white70)),
                ),
              ),
            ],
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
                    Preferences.setUser(
                      "--",
                      "--",
                      "--",
                    );
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
