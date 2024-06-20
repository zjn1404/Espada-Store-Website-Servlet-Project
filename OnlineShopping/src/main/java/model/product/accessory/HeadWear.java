package model.product.accessory;

import model.product.SubType;

public class HeadWear extends Accessory{
	
	public HeadWear() {}
	
	public HeadWear(String productId, String name, double price, String color, String material, String size, String gender,
			SubType subType, String description) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.color = color;
		this.material = material;
		this.size = size;
		this.gender = gender;
		this.subType = subType;
		this.description = description;
	}
}
