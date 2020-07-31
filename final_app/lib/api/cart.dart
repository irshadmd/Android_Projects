import 'dart:convert';
import 'package:final_app/Preferences.dart';
import 'package:final_app/models/Cart.dart';
import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;


class CartApi{

   static Future<dynamic> addToCart(String id) async {
    final authtoken = await Preferences.getData("authToken");
    print("===============================");
    print(authtoken);
    final response = await http.post(
      "https://freshodaily.com/api/add-to-cart",
      body: {
        "product_id": id,
      },
      headers: <String, String>{
        'Accept': 'application/json',
        'Authorization': 'Bearer $authtoken',
      },
    );
    var data = jsonDecode(response.body);
    var responceJson = data["message"];
    return responceJson;
  }

  // https://freshodaily.com/api/cart-list


   static Future<dynamic> cartList() async {
    final authtoken = await Preferences.getData("authToken");
    final response = await http.get(
        Uri.encodeFull("https://freshodaily.com/api/cart-list"),
        headers: {
          'Accept': 'application/json',
          'Authorization': 'Bearer $authtoken',
        });
    var responseJson = jsonDecode(response.body);
    var data = responseJson["data"];
    var message = responseJson["message"];
    print("============================ Cart ===================================");

    if(data!=null){
      int totalCoupons = data.length;
      List<CartData> CategoriesApiDetail = List<CartData>();
      for (int i = 0; i < totalCoupons; i++) {
        CategoriesApiDetail.add(CartData.fromJson(data[i]));
      }
      print("=========================== Cart product Listing ============");
      print(CategoriesApiDetail);
      return CategoriesApiDetail;
    } else {
      return responseJson["message"];
    }
  }


  static Future<dynamic> deleteCartItemById(String id) async {
    final authtoken = await Preferences.getData("authToken");
    print("===============================");
    print(authtoken);
    final response = await http.post(
      "https://freshodaily.com/api/delete-single-product-in-cart",
      body: {
        'id': id.toString()
      },
      headers: <String, String>{
        'Accept': 'application/json',
        'Authorization': 'Bearer $authtoken',
      },
    );
    print(response.body);
    return response;
  }



  static Future<dynamic> deleteCart() async {
    final authtoken = await Preferences.getData("authToken");
    print("===============================");
    print(authtoken);
    final response = await http.get(
      "https://freshodaily.com/api/delete-cart",
      headers: <String, String>{
        'Accept': 'application/json',
        'Authorization': 'Bearer $authtoken',
      },
    );
    print(response);
    return response;
  }
}