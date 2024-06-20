package factory;

import model.product.clothe.bottom.Pants;

public class PantsCreator implements IProductCreator {

	@Override
	public Object createProduct() {
		return new Pants();
	}

}
