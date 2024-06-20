package factory;

public class ProductFactoryConfig {
	
	public static void addCreators() {
		
		ProductFactory.getInstance().registerProduct("tee", new TeeCreator());
		ProductFactory.getInstance().registerProduct("shirt", new ShirtCreator());
		ProductFactory.getInstance().registerProduct("hoodie", new HoodieCreator());
		ProductFactory.getInstance().registerProduct("jacket", new JacketCreator());
		ProductFactory.getInstance().registerProduct("pants", new PantsCreator());
		ProductFactory.getInstance().registerProduct("shorts", new ShortsCreator());
		ProductFactory.getInstance().registerProduct("bag", new BagCreator());
		ProductFactory.getInstance().registerProduct("headwear", new HeadWearCreator());
		
	}
	
}
