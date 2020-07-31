import 'package:shared_preferences/shared_preferences.dart';

import 'models/user.dart';

class Preferences {
  static Future<bool> setWalkThrough() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.setBool("WalkThrough", true);
  }

  static Future<bool> getWalkThrough() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getBool("WalkThrough") ?? false;
  }

  static Future<bool> setToken(String authToken) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.setString("authToken", authToken);
  }

  static Future<String> getData(String data) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString("$data") ?? "";
  }

  static Future<bool> setUser(
      [String email, String name, String authToken]) async {
    // String email, String name, String phone, String authToken,String location,String house) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    print(email);
    print(name);
    print(authToken);
    print("___________________________");
    if (email != null) prefs.setString("email", email);
    if (name != null) prefs.setString("name", name);
    if (authToken != null) setToken(authToken);
    return true;
  }

  static Future<User> getUser() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    User user = User();
    user.authToken = prefs.getString("authToken") ?? "";
    user.email = prefs.getString("email") ?? " ";
    user.name = prefs.getString("name") ?? " ";
    return user;
  }

  static Future<bool> removeUser() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.clear();
    return true;
  }
}
