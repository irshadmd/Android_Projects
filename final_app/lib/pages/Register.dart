import 'package:email_validator/email_validator.dart';
import 'package:final_app/Helper/BlockButton.dart';
import 'package:final_app/api/Register_api.dart';
import 'package:final_app/appconfig/app_config.dart' as config;
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:progress_dialog/progress_dialog.dart';

import '../Constants.dart';

class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController confirmPasswordController = TextEditingController();

  final globalKey = GlobalKey<ScaffoldState>();
  ProgressDialog pr;
  bool _hidepassword = true;

  @override
  Widget build(BuildContext context) {
    pr = ProgressDialog(context,
        isDismissible: false,
        customBody: Container(
            color: Colors.transparent,
            child: SpinKitCubeGrid(
              color: Color(0xffdf6589ff),
            )));
    pr.style(
      backgroundColor: Colors.transparent,
    );
    return Scaffold(
      resizeToAvoidBottomInset: false,
      key: globalKey,
      body: Stack(
        alignment: AlignmentDirectional.topCenter,
        children: <Widget>[
          Positioned(
            top: 0,
            child: Container(
              width: config.App(context).appWidth(100),
              height: config.App(context).appHeight(35.5),
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: AssetImage("assets/freshMeat.jpg"),
                  fit: BoxFit.cover,
                ),
              ),
            ),
          ),
          Positioned(
            top: config.App(context).appHeight(29.5) - 130,
            child: Container(
              width: config.App(context).appWidth(84),
              height: config.App(context).appHeight(29.5),
              child: Text('Let\'s Start!', style: kLoginStyle),
            ),
          ),
          Positioned(
            top: config.App(context).appHeight(29.5) - 100,
            child: Container(
              decoration: BoxDecoration(
                  color: Colors.black87.withOpacity(0.3),
                  borderRadius: BorderRadius.all(Radius.circular(40)),
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
              child: SingleChildScrollView(
                scrollDirection: Axis.vertical,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    CircleAvatar(
                      radius: 65,
                      backgroundColor: Colors.black87,
                      child: CircleAvatar(
                        radius: 63,
                        backgroundImage: AssetImage('assets/freshodaily.png'),
                        backgroundColor: Colors.white,
                      ),
                    ),
                    SizedBox(height: 20),
                    TextField(
                      controller: usernameController,
                      keyboardType: TextInputType.text,
                      decoration: InputDecoration(
                        labelText: "Full Name",
                        labelStyle: TextStyle(color: Colors.white),
                        contentPadding: EdgeInsets.all(12),
                        hintText: 'Username',
                        hintStyle:
                            TextStyle(color: Colors.white.withOpacity(0.7)),
                        prefixIcon:
                            Icon(Icons.person_outline, color: Colors.white),
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
                    SizedBox(height: 20),
                    TextField(
                      controller: emailController,
                      keyboardType: TextInputType.emailAddress,
                      decoration: InputDecoration(
                        labelText: "Email",
                        labelStyle: TextStyle(color: Colors.white),
                        contentPadding: EdgeInsets.all(12),
                        hintText: 'abc@gmail.com',
                        hintStyle:
                            TextStyle(color: Colors.white.withOpacity(0.7)),
                        prefixIcon:
                            Icon(Icons.alternate_email, color: Colors.white),
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
                    SizedBox(height: 20),
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
                    SizedBox(height: 20),
                    TextField(
                      controller: confirmPasswordController,
                      keyboardType: TextInputType.text,
                      obscureText: this._hidepassword ? true : false,
                      decoration: InputDecoration(
                        labelText: "Confirm Password",
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
                    SizedBox(height: 20),
                    BlockButtonWidget(
                      text: Text(
                        'Register',
                        style: kButtonStyle,
                      ),
                      color: Colors.redAccent,
                      onPressed: () async {
                        if (emailController.text == "" &&
                                usernameController.text == "" ||
                            confirmPasswordController.text == "" ||
                            passwordController.text == "") {
                          globalKey.currentState.showSnackBar(SnackBar(
                              backgroundColor: Theme.of(context).focusColor.withOpacity(0.8),
                              content: Text(
                                "Fill All Details",
                                style: TextStyle(
                                  color: Colors.white70,
                                  fontSize: 10,
                                ),
                                textAlign: TextAlign.center,
                              )));
                        } else if (passwordController.text.length < 6) {
                          globalKey.currentState.showSnackBar(SnackBar(
                              backgroundColor:Theme.of(context).focusColor.withOpacity(0.8),
                              content: Text(
                                "Password length should be greater than 6",
                                style: TextStyle(
                                  color: Colors.white70,
                                  fontSize: 10,
                                ),
                                textAlign: TextAlign.center,
                              )));
                        } else if (passwordController.text.toString() !=
                            confirmPasswordController.text.toString()) {
                          globalKey.currentState.showSnackBar(SnackBar(
                              backgroundColor: Theme.of(context).focusColor.withOpacity(0.8),
                              content: Text(
                                "Password and Confirm Password Should be same",
                                style: TextStyle(
                                  color: Colors.white70,
                                  fontSize: 10,
                                ),
                                textAlign: TextAlign.center,
                              )));
                        } else if (!EmailValidator.validate(
                            emailController.text)) {
                          globalKey.currentState.showSnackBar(SnackBar(
                              backgroundColor: Theme.of(context).focusColor.withOpacity(0.8),
                              content: Text(
                                "Invalid Email",
                                style: TextStyle(
                                  color: Colors.white70,
                                  fontSize: 10,
                                ),
                                textAlign: TextAlign.center,
                              )));
                        } else {
                          pr.show();
                          await Register.registerUser(usernameController.text,
                                  passwordController.text, emailController.text)
                              .then((value) {
                            pr.hide();
                            if (value is String) {
                              Navigator.of(context)
                                  .pushReplacementNamed('/LoginPage');
                            } else {
                              globalKey.currentState.showSnackBar(SnackBar(
                                  backgroundColor: Theme.of(context).focusColor.withOpacity(0.8),
                                  content: Text(
                                    "Email is already taken",
                                    style: TextStyle(
                                      color: Colors.white70,
                                      fontSize: 10,
                                    ),
                                    textAlign: TextAlign.center,
                                  )));
                            }
                          });
                        }
                        // Navigator.of(context).pushNamed('/walkthrough');
                      },
                    ),
                  ],
                ),
              ),
            ),
          ),
          Positioned(
            bottom: 10,
            child: FlatButton(
              onPressed: () {
                Navigator.of(context).pushNamed('/LoginPage');
              },
              textColor: Theme.of(context).hintColor,
              child: Text('I have account? Back to login'),
            ),
          )
        ],
      ),
    );
  }
}
