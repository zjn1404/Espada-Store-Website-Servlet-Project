package factory;

import model.product.accessory.Bag;

public class BagCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new Bag();
	}

}
