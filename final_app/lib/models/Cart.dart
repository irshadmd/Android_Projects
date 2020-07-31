

class CartData {
  int id;
  String quantity;
  String productName;
  String image;
  String measurementUnit;
  int subtotal;

  CartData(
      {this.id,
      this.quantity,
      this.productName,
      this.image,
      this.measurementUnit,
      this.subtotal});

  CartData.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    quantity = json['quantity'];
    productName = json['product_name'];
    image = json['image'];
    measurementUnit = json['measurement_unit'];
    subtotal = json['subtotal'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['quantity'] = this.quantity;
    data['product_name'] = this.productName;
    data['image'] = this.image;
    data['measurement_unit'] = this.measurementUnit;
    data['subtotal'] = this.subtotal;
    return data;
  }
}
