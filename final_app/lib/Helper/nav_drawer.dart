import 'package:final_app/Preferences.dart';
import 'package:final_app/api/loginApi.dart';
import 'package:final_app/models/user.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:progress_dialog/progress_dialog.dart';

import 'LogOut.dart';

class DrawerWidget extends StatefulWidget {
  // final User user ;
  const DrawerWidget({
    // this.user,
    Key key,
  }) : super(key: key);

  @override
  _DrawerWidgetState createState() => _DrawerWidgetState();
}

class _DrawerWidgetState extends State<DrawerWidget> {
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
    return Drawer(
      child: ListView(
        children: <Widget>[
          GestureDetector(
            child: UserAccountsDrawerHeader(
              decoration: BoxDecoration(
                color: Colors.white30,
                borderRadius:
                    BorderRadius.only(bottomLeft: Radius.circular(35)),
              ),
              currentAccountPicture:  CircleAvatar(
                radius: 65,
                backgroundColor: Colors.black87,
                child: CircleAvatar(
                  radius: 63,
                  backgroundImage: AssetImage('assets/profile.jpg'),
                  backgroundColor: Colors.white,
                ),
              ),
              accountName: Text(
                name,
                style: Theme.of(context).textTheme.title,
              ),
            ),
          ),
          ListTile(
            onTap: () {
              Navigator.of(context).pushNamed('/MainPage');
            },
            leading: Icon(
              Icons.home,
              color: Theme.of(context).focusColor.withOpacity(1),
            ),
            title: Text(
              "Home",
              style: Theme.of(context).textTheme.subhead,
            ),
          ),
          ListTile(
            leading: Icon(
              Icons.notifications,
              color: Theme.of(context).focusColor.withOpacity(1),
            ),
            title: Text(
              "Notifications",
              style: Theme.of(context).textTheme.subhead,
            ),
          ),
          ListTile(
            dense: true,
            title: Text(
              "Application Preferences",
              style: Theme.of(context).textTheme.body1,
            ),
            trailing: Icon(
              Icons.remove,
              color: Theme.of(context).focusColor.withOpacity(0.3),
            ),
          ),
          ListTile(
            // onTap: () {
            //   Navigator.of(context).pushNamed('/Help');
            // },
            leading: Icon(
              Icons.help,
              color: Theme.of(context).focusColor.withOpacity(1),
            ),
            title: Text(
              "Help & Support",
              style: Theme.of(context).textTheme.subhead,
            ),
          ),
//          ListTile(
//            onTap: () {
//              CustomAlertDialog(context);
//            },
//            leading: Icon(
//              Icons.exit_to_app,
//              color: Theme.of(context).focusColor.withOpacity(1),
//            ),
//            title: Text(
//              "Log out",
//              style: Theme.of(context).textTheme.subhead,
//            ),
//          ),
          ListTile(
            dense: true,
            title: Text(
              "Version 0.0.1",
              style: Theme.of(context).textTheme.body1,
            ),
            trailing: Icon(
              Icons.remove,
              color: Theme.of(context).focusColor.withOpacity(0.3),
            ),
          ),
        ],
      ),
    );
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
