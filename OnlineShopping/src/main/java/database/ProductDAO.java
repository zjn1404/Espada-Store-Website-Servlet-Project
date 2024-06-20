package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.ProductFactory;
import model.product.Product;
import model.product.SubType;

public class ProductDAO implements IDao<Product> {
	
	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "product";
	
	private static ProductDAO daoInstance;

    private ProductDAO() {}

    public static ProductDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new ProductDAO();
        }

        return daoInstance;
    }

	@Override
	public boolean insert(Product object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (product_id, product_name, price, color,"
					+ "material, size, gender, subtype_id, product_description) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getProductId());
			preparedStatement.setString(2, object.getName());
			preparedStatement.setDouble(3, object.getPrice());
			preparedStatement.setString(4, object.getColor());
			preparedStatement.setString(5, object.getMaterial());
			preparedStatement.setString(6, object.getSize());
			preparedStatement.setString(7, object.getGender());
			preparedStatement.setString(8, object.getSubType().getSubTypeId());
			preparedStatement.setString(9, object.getDescription());
			
            int result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            if (result <= 0) {
            	return false;
            }
            
            return true;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteById(Product object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getProductId());
			
            int result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            if (result <= 0) {
            	return false;
            }
            
            return true;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Product object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET product_name = ?,"
					+ "price = ?,"
					+ "color = ?,"
					+ "material = ?,"
					+ "size = ?,"
					+ "gender = ?,"
					+ "subtype_id = ?,"
					+ "product_description = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getName());
			preparedStatement.setDouble(2, object.getPrice());
			preparedStatement.setString(3, object.getColor());
			preparedStatement.setString(4, object.getMaterial());
			preparedStatement.setString(5, object.getSize());
			preparedStatement.setString(6, object.getGender());
			preparedStatement.setString(7, object.getSubType().getSubTypeId());
			preparedStatement.setString(8, object.getDescription());
			
            int result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            if (result <= 0) {
            	return false;
            }
            
            return true;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Product selectById(Product object) {
		Product foundProduct = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getProductId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	
            	SubType subTypeTmp = new SubType();
            	subTypeTmp.setSubTypeId(rs.getString("subtype_id"));
            	SubType subType = SubTypeDAO.getInstance().selectById(null);
            	foundProduct = ProductFactory.getInstance().createProduct(subType.getSubTypeName());
            	
            	foundProduct.setProductId(rs.getString("product_id"));
            	foundProduct.setName(rs.getString("product_name"));
            	foundProduct.setPrice(rs.getDouble("price"));
            	foundProduct.setColor(rs.getString("color"));
            	foundProduct.setMaterial(rs.getString("material"));
            	foundProduct.setSize(rs.getString("size"));
            	foundProduct.setGender(rs.getString("gender"));
            	foundProduct.setSubType(subType);
            	foundProduct.setDescription(rs.getString("product_description"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundProduct;
	}

	public Product selectById(String id) {
		Product foundProduct = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	
            	SubType subTypeTmp = new SubType();
            	subTypeTmp.setSubTypeId(rs.getString("subtype_id"));
            	SubType subType = SubTypeDAO.getInstance().selectById(subTypeTmp);
            	foundProduct = ProductFactory.getInstance().createProduct(subType.getSubTypeName());
            	
            	foundProduct.setProductId(rs.getString("product_id"));
            	foundProduct.setName(rs.getString("product_name"));
            	foundProduct.setPrice(rs.getDouble("price"));
            	foundProduct.setColor(rs.getString("color"));
            	foundProduct.setMaterial(rs.getString("material"));
            	foundProduct.setSize(rs.getString("size"));
            	foundProduct.setGender(rs.getString("gender"));
            	foundProduct.setSubType(subType);
            	foundProduct.setDescription(rs.getString("product_description"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundProduct;
	}
	
	@Override
	public List<Product> SelectAll() {
		
		List<Product> products = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	SubType subTypeTmp = new SubType();
            	subTypeTmp.setSubTypeId(rs.getString("subtype_id"));
            	SubType subType = SubTypeDAO.getInstance().selectById(subTypeTmp);
            	Product foundProduct = ProductFactory.getInstance().createProduct(subType.getSubTypeName());
            	
            	foundProduct.setProductId(rs.getString("product_id"));
            	foundProduct.setName(rs.getString("product_name"));
            	foundProduct.setPrice(rs.getDouble("price"));
            	foundProduct.setColor(rs.getString("color"));
            	foundProduct.setMaterial(rs.getString("material"));
            	foundProduct.setSize(rs.getString("size"));
            	foundProduct.setGender(rs.getString("gender"));
            	foundProduct.setSubType(subType);
            	foundProduct.setDescription(rs.getString("product_description"));
            
            	products.add(foundProduct);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
		
	}
    
}
