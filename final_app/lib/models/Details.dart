class Detail {
  int id;
  String subcategoryId;
  String productName;
  String image;
  String measurementUnit;
  String rates;
  String description;
  List<Images> images;

  Detail(
      {this.id,
      this.subcategoryId,
      this.productName,
      this.image,
      this.measurementUnit,
      this.rates,
      this.description,
      this.images});

  Detail.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    subcategoryId = json['subcategory_id'];
    productName = json['product_name'];
    image = json['image'];
    measurementUnit = json['measurement_unit'];
    rates = json['rates'];
    description = json['description'];
    if (json['images'] != null) {
      images = new List<Images>();
      json['images'].forEach((v) {
        images.add(new Images.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['subcategory_id'] = this.subcategoryId;
    data['product_name'] = this.productName;
    data['image'] = this.image;
    data['measurement_unit'] = this.measurementUnit;
    data['rates'] = this.rates;
    data['description'] = this.description;
    if (this.images != null) {
      data['images'] = this.images.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Images {
  String productId;
  String productImages;

  Images({this.productId, this.productImages});

  Images.fromJson(Map<String, dynamic> json) {
    productId = json['product_id'];
    productImages = json['product_images'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['product_id'] = this.productId;
    data['product_images'] = this.productImages;
    return data;
  }
}