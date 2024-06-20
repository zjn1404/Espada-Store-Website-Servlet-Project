package factory;

import java.util.HashMap;
import java.util.Map;

import model.product.Product;

public class ProductFactory {
	
	private static Map<String ,IProductCreator> products;
	private static ProductFactory factory;
	
	private ProductFactory() {
		
		products = new HashMap<String, IProductCreator>();
	}
	
	public static ProductFactory getInstance() {
		
		if (factory == null) {
			factory = new ProductFactory();
		}
		
		return factory;
	}
	
	public boolean registerProduct(String type, IProductCreator creater) {
		
		return products.putIfAbsent(type, creater) == null;
		
	}
	
	public Product createProduct(String type) {
		
		if (!products.containsKey(type)) {
			return null;
		}
		
		return (Product)products.get(type).createProduct();
		
	}
}
