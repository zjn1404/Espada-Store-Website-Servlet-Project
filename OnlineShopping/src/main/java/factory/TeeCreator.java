package factory;

import model.product.clothe.top.Tee;

public class TeeCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new Tee();
	}
	
}
