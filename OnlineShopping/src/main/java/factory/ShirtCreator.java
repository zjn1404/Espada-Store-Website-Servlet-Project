package factory;

import model.product.clothe.top.Shirt;

public class ShirtCreator implements IProductCreator{

	@Override
	public Object createProduct() {
		return new Shirt();
	}

}
