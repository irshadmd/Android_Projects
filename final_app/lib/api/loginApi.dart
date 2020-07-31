import 'dart:convert';

import 'package:http/http.dart' as http;

import '../Preferences.dart';

class Login {
  static Future<dynamic> LoginUser(String email, String password) async {
    final response = await http.post(
      "https://freshodaily.com/api/login",
      body: jsonEncode(
        <String, String>{
          'email': email,
          'password': password,
        },
      ),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
    );
    var data = jsonDecode(response.body);
    var responseJson = data["success"];
    print(data);
    print(data["error"]);
    print("===============================================");
    print(responseJson);
    if (data.containsKey("success")) {
      print(data["data"]["email"]);
      print(data["data"]["name"]);
      print(responseJson["token"]);
      Preferences.setUser(
        data["data"]["email"],
        data["data"]["name"],
        responseJson["token"],
      );
      print(
          "================== Id ++++++++++++++++++++++++++++++++++++++++++++++++++++");
      print(data["data"]["id"]);
      return data["data"]["id"].toInt();
    } else {
      print(data["error"]);
      return data["error"];
    }
  }

  static Future<dynamic> LogoutUser() async {
    final authtoken = await Preferences.getData("authToken");
    print("===============================");
    print(authtoken);
    final response = await http.get(
      "https://freshodaily.com/api/logout",
      headers: <String, String>{
        'Accept': 'application/json',
        'Authorization': 'Bearer $authtoken',
      },
    );
    print(response.body);
    Preferences.removeUser();
    return jsonDecode(response.body);
  }
}
