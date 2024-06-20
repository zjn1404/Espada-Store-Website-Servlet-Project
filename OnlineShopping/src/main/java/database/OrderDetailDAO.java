package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import model.product.Product;

public class OrderDetailDAO implements IDao<OrderDetail> {

	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "detail_order";
	
	private static OrderDetailDAO daoInstance;

    private OrderDetailDAO() {}

    public static OrderDetailDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new OrderDetailDAO();
        }

        return daoInstance;
    }

	@Override
	public boolean insert(OrderDetail object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (order_id, product_id, order_amount, VAT) values(?, ?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getOrder().getOrderId());
			preparedStatement.setString(2, object.getProduct().getProductId());
			preparedStatement.setInt(3, object.getOrderAmount());
			preparedStatement.setDouble(4, object.getVAT());
			
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
	public boolean deleteById(OrderDetail object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?", DEFAULTTABLE, "order_id", "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getOrder().getOrderId());
			preparedStatement.setString(2, object.getProduct().getProductId());
			
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
	
	public int deleteAll(Order object) {
		
		int result = 0;
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "order_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getOrderId());
			
            result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean update(OrderDetail object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET order_amount = ?,"
					+ "VAT = ? "
					+ "WHERE %s = ? AND %s = ?", DEFAULTTABLE, "order_id", "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, object.getOrderAmount());
			preparedStatement.setDouble(2, object.getVAT());
			preparedStatement.setString(3, object.getOrder().getOrderId());
			preparedStatement.setString(4, object.getProduct().getProductId());
			
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
	public OrderDetail selectById(OrderDetail object) {

		OrderDetail foundOrderDetail = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", DEFAULTTABLE, "order_id", "product_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getOrder().getOrderId());
			preparedStatement.setString(2, object.getProduct().getProductId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundOrderDetail = new OrderDetail();
            	Order orderTmp = new Order();
            	orderTmp.setOrderId(rs.getString("order_id"));
            	Order foundOrder = OrderDAO.getInstance().selectById(orderTmp);
            	Product foundProduct = ProductDAO.getInstance().selectById(rs.getString("product_id"));
            	
            	foundOrderDetail.setOrder(foundOrder);
            	foundOrderDetail.setProduct(foundProduct);
            	foundOrderDetail.setOrderAmount(rs.getInt("order_amount"));
            	foundOrderDetail.setVAT(rs.getDouble("VAT"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundOrderDetail;
	}

	@Override
	public List<OrderDetail> SelectAll() {
		
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	OrderDetail foundOrderDetail = new OrderDetail();
            	Order orderTmp = new Order();
            	orderTmp.setOrderId(rs.getString("order_id"));
            	Order foundOrder = OrderDAO.getInstance().selectById(orderTmp);
            	Product foundProduct = ProductDAO.getInstance().selectById(rs.getString("product_id"));
            	
            	foundOrderDetail.setOrder(foundOrder);
            	foundOrderDetail.setProduct(foundProduct);
            	foundOrderDetail.setOrderAmount(rs.getInt("order_amount"));
            	foundOrderDetail.setVAT(rs.getDouble("VAT"));
            
            	orderDetails.add(foundOrderDetail);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderDetails;
	}
	
}
