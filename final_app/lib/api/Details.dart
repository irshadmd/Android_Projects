import 'dart:convert';
import 'package:final_app/models/Details.dart';
import 'package:http/http.dart' as http;


class ProductDetil{
  static Future<Detail> getProductbyId(String id) async {

    final response = await http.get(
    Uri.encodeFull("https://freshodaily.com/api/product-details/7"),
    headers: {"Accept": "application/json"});
    var data = jsonDecode(response.body);
    print(data);
    print("======================== Details ===========================");
    print("https://freshodaily.com/api/product-details/$id");
    print("======================== Details ===========================");
    var responseJson = data["data"][0];
    print(responseJson);
    return Detail.fromJson(responseJson);
  }
}