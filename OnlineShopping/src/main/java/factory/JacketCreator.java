package factory;

import model.product.clothe.top.Jacket;

public class JacketCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new Jacket();
	}
	
}
