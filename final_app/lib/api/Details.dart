import 'dart:convert';
import 'package:final_app/models/Details.dart';
import 'package:http/http.dart' as http;


class ProductDetil{
  static Future<Detail> getProductbyId(String id) async {
    print(id);
    final response = await http.get(
    Uri.encodeFull("https://freshodaily.com/api/product-details/1"),
    headers: {"Accept": "application/json"});
    var data = jsonDecode(response.body);
    print(data);
    print("======================== Details ===========================");
    var responseJson = data["data"][0];
    print(responseJson);
    return Detail.fromJson(responseJson);
  }
}