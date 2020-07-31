import 'dart:io';

import 'package:final_app/Helper/BlockButton.dart';
import 'package:final_app/api/loginApi.dart';
import 'package:final_app/appconfig/app_config.dart' as config;
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:progress_dialog/progress_dialog.dart';

import '../Constants.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController emailController = TextEditingController();
  final globalKey = GlobalKey<ScaffoldState>();
  TextEditingController passwordController = TextEditingController();
  ProgressDialog pr;
  bool _hidepassword = true;

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    emailController.dispose();
    passwordController.dispose();
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

    return WillPopScope(
        onWillPop: _onBackPressed,
        child: Scaffold(
      key: globalKey,
      resizeToAvoidBottomPadding: false,
      body: Stack(
        alignment: AlignmentDirectional.topCenter,
        children: <Widget>[
          Positioned(
            top: 0,
            child: Container(
              width: config.App(context).appWidth(100),
              height: config.App(context).appHeight(29.5),
              decoration:
                  BoxDecoration(color: Color(0xffe4033a).withOpacity(0.5)),
            ),
          ),
          Positioned(
            top: config.App(context).appHeight(29.5) - 120,
            child: Container(
              width: config.App(context).appWidth(84),
              height: config.App(context).appHeight(29.5),
              child: Text('Login!', style: kLoginStyle),
            ),
          ),
          Positioned(
            top: config.App(context).appHeight(29.5) - 50,
            child: Container(
              decoration: BoxDecoration(
                  color: Theme.of(context).primaryColor,
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                  boxShadow: [
                    BoxShadow(
                      blurRadius: 50,
                      color: Colors.white.withOpacity(0.4),
                    )
                  ]),
              margin: EdgeInsets.symmetric(
                horizontal: 20,
              ),
              padding: EdgeInsets.symmetric(vertical: 50, horizontal: 27),
              width: config.App(context).appWidth(88),
              //  height: config.App(context).appHeight(55),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  TextField(
                    controller: emailController,
                    keyboardType: TextInputType.emailAddress,
//                    obscureText: this._hidepassword?true:false,
                    decoration: InputDecoration(
                      labelText: "Email",
                      labelStyle: TextStyle(color: Colors.white),
                      contentPadding: EdgeInsets.all(12),
                      hintText: 'abc@gmail.com',
                      hintStyle:
                          TextStyle(color: Colors.white.withOpacity(0.7)),
                      prefixIcon: Icon(Icons.email, color: Colors.white),
                      border: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                      focusedBorder: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                      enabledBorder: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                    ),
                  ),
                  SizedBox(height: 30),
                  TextField(
                    controller: passwordController,
                    keyboardType: TextInputType.text,
                    obscureText: this._hidepassword ? true : false,
                    decoration: InputDecoration(
                      labelText: "Password",
                      labelStyle: TextStyle(color: Colors.white),
                      contentPadding: EdgeInsets.all(12),
                      hintText: '••••••••••••',
                      hintStyle:
                          TextStyle(color: Colors.white.withOpacity(0.7)),
                      prefixIcon: Icon(Icons.lock_outline, color: Colors.white),
                      suffixIcon: GestureDetector(
                        onTap: () {
                          setState(() {
                            this._hidepassword = !this._hidepassword;
                          });
                        },
                        child: Icon(Icons.remove_red_eye,
                            color: this._hidepassword
                                ? Colors.white
                                : Colors.blue),
                      ),
                      border: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                      focusedBorder: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                      enabledBorder: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Colors.white.withOpacity(1))),
                    ),
                  ),
                  SizedBox(height: 30),
                  BlockButtonWidget(
                    text: Text(
                      'Login',
                      style: kButtonStyle,
                    ),
                    color: Colors.black,
                    onPressed: () async {
                      String email = emailController.text;
                      String password = passwordController.text;
                      if (email == "" && password == "") {
                        globalKey.currentState.showSnackBar(SnackBar(
                            backgroundColor:
                                Theme.of(context).focusColor.withOpacity(0.8),
                            content: Text(
                              "Please Fill Email and Password",
                              style: TextStyle(
                                color: Colors.white70,
                                fontSize: 10,
                              ),
                              textAlign: TextAlign.center,
                            )));
                      } else {
                        pr.show();
                        await Login.LoginUser(email, password).then((value) {
                          pr.hide();
                          print("Logged In");
                          if (value is int) {
                            globalKey.currentState.showSnackBar(SnackBar(
                                backgroundColor: Theme.of(context)
                                    .focusColor
                                    .withOpacity(0.8),
                                content: Text(
                                  "Login Success",
                                  style: TextStyle(
                                    color: Colors.white70,
                                    fontSize: 10,
                                  ),
                                  textAlign: TextAlign.center,
                                )));
                            Navigator.of(context).pushNamed('/MainPage');
                          } else if (value == "Unauthorised") {
                            globalKey.currentState.showSnackBar(SnackBar(
                                backgroundColor: Theme.of(context)
                                    .focusColor
                                    .withOpacity(0.8),
                                content: Text(
                                  "Invalid Credentials",
                                  style: TextStyle(
                                    color: Colors.white70,
                                    fontSize: 10,
                                  ),
                                  textAlign: TextAlign.center,
                                )));
                          }
                        });
                      }
                    },
                  ),
                  RaisedButton(
                    onPressed: () {
                      Navigator.of(context).pushReplacementNamed('/MainPage');
                    },
                    child: Text('Skip Login', style: TextStyle(fontSize: 20)),
                  ),
                  SizedBox(
                    height: 30,
                  ),
                ],
              ),
            ),
          ),
          Positioned(
            bottom: 10,
            child: Column(
              children: <Widget>[
//                FlatButton(
//                  onPressed: () {
//                    Navigator.of(context).pushNamed('/Pages', arguments: 2);
//                  },
//                  textColor: Theme.of(context).hintColor,
//                  child: Text('I forgot password ?'),
//                ),
                FlatButton(
                  onPressed: () {
                    Navigator.of(context).pushNamed('/RegisterPage');
                  },
                  textColor: Theme.of(context).hintColor,
                  child: Text('I don\'t have an account?'),
                ),
              ],
            ),
          )
        ],
      ),
    ),
    );
  }
  Future<bool> _onBackPressed() {
    return showDialog(
      context: context,
      builder: (context) => new AlertDialog(
        title: new Text('Are you sure?'),
        content: new Text('Do you want to exit an App'),
        actions: <Widget>[
          new GestureDetector(
            onTap: () => Navigator.of(context).pop(false),
            child: Text("NO"),
          ),
          SizedBox(height: 16),
          new GestureDetector(
            onTap: () => {
              exit(0)
            },
            child: Text("YES"),
          ),
        ],
      ),
    ) ??
        false;
  }
}
