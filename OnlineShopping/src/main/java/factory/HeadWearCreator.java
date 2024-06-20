package factory;

import model.product.accessory.HeadWear;

public class HeadWearCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new HeadWear();
	}
	
}
