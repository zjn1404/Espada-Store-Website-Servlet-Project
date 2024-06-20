package factory;

import model.product.clothe.top.Hoodie;

public class HoodieCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new Hoodie();
	}

}
