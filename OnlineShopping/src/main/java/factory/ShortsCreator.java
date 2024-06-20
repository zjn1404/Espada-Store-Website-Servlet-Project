package factory;

import model.product.clothe.bottom.Shorts;

public class ShortsCreator implements IProductCreator{

	@Override
	public Object createProduct() {
		return new Shorts();
	}

}
