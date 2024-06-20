package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;

public class OrderDAO implements IDao<Order> {

	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "tb_order";
	
	private static OrderDAO daoInstance;

    private OrderDAO() {}

    public static OrderDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new OrderDAO();
        }

        return daoInstance;
    }
	
	@Override
	public boolean insert(Order object) {
		
		try {
			
			Order order = object; 
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (customer_id, order_id, order_address,"
					+ " delivery_address, state, payment, payment_state, paid,"
					+ " unpaid, ordering_date, shipping_date)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, order.getCustomer().getCustomerId());
			preparedStatement.setString(2, order.getOrderId());
			preparedStatement.setString(3, order.getOrderAddress());
			preparedStatement.setString(4, order.getDeliveryAddress());
			preparedStatement.setString(5, order.getState());
			preparedStatement.setInt(6, order.getPayment());
			preparedStatement.setBoolean(7, order.isPaymentState());
			preparedStatement.setInt(8, order.getPaid());
			preparedStatement.setInt(9, order.getUnpaid());
			preparedStatement.setDate(10, Date.valueOf(order.getOrderingDate()));
			preparedStatement.setDate(11, Date.valueOf(order.getShippingDate()));
			
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
	public boolean deleteById(Order object) {
		
		try {
			
			Order order = (Order)object; 
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "order_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, order.getOrderId());
			
            int result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            OrderDetailDAO.getInstance().deleteAll(object);
            
            if (result <= 0) {
            	return false;
            }
            
            return true;
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteByCustomerId(Customer object) {
		
		try {
			
			List<Order> deletedOrders = SelectAllByCustomerId(object);
			
			for (Order order : deletedOrders) {
				OrderDetailDAO.getInstance().deleteAll(order);
			}
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getCustomerId());
			
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
	public boolean update(Order object) {
		
		try {
			
			Order order = (Order)object; 
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE TABLE %s "
					+ "SET customer_id = ?,"
					+ "order_address = ?,"
					+ "delivery_address = ?"
					+ "state = ?,"
					+ "payment = ?,"
					+ "payment_state = ?,"
					+ "paid = ?,"
					+ "unpaid = ?,"
					+ "ordering_date = ?,"
					+ "shipping_date = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "order_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, order.getCustomer().getCustomerId());
			preparedStatement.setString(2, order.getOrderAddress());
			preparedStatement.setString(3, order.getDeliveryAddress());
			preparedStatement.setString(4, order.getState());
			preparedStatement.setInt(5, order.getPayment());
			preparedStatement.setBoolean(6, order.isPaymentState());
			preparedStatement.setInt(7, order.getPaid());
			preparedStatement.setInt(8, order.getUnpaid());
			preparedStatement.setDate(9, Date.valueOf(order.getOrderingDate()));
			preparedStatement.setDate(10, Date.valueOf(order.getShippingDate()));
			preparedStatement.setString(11, order.getOrderId());
			
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
	public Order selectById(Order object) {
		
		Order foundOrder = null;
		
		try {
			
			Order order = (Order)object; 
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "order_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, order.getOrderId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundOrder = new Order();
            	Customer customerTmp = new Customer();
            	customerTmp.setCustomerId(rs.getString("customer_id"));
            	foundOrder.setOrderId(rs.getString("order_id"));
            	foundOrder.setCustomer(CustomerDAO.getInstance().selectById(customerTmp));
            	foundOrder.setOrderAddress(rs.getString("order_address"));
            	foundOrder.setDeliveryAddress(rs.getString("delivery_address"));
            	foundOrder.setState(rs.getString("state"));
            	foundOrder.setPayment(rs.getInt("payment"));
            	foundOrder.setPaymentState(rs.getBoolean("payment_state"));
            	foundOrder.setPaid(rs.getInt("paid"));
            	foundOrder.setUnpaid(rs.getInt("unpaid"));
            	foundOrder.setOrderingDate(LocalDate.parse(rs.getDate("ordering_date").toString()));
            	foundOrder.setShippingDate(LocalDate.parse(rs.getDate("shipping_date").toString()));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundOrder;
	}

	@Override
	public List<Order> SelectAll() {
		
		List<Order> orders = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	Order foundOrder = new Order();
            	Customer customerTmp = new Customer();
            	customerTmp.setCustomerId(rs.getString("customer_id"));
            	foundOrder.setOrderId(rs.getString("order_id"));
            	foundOrder.setCustomer(CustomerDAO.getInstance().selectById(customerTmp));
            	foundOrder.setOrderAddress(rs.getString("order_address"));
            	foundOrder.setDeliveryAddress(rs.getString("delivery_address"));
            	foundOrder.setState(rs.getString("state"));
            	foundOrder.setPayment(rs.getInt("payment"));
            	foundOrder.setPaymentState(rs.getBoolean("payment_state"));
            	foundOrder.setPaid(rs.getInt("paid"));
            	foundOrder.setUnpaid(rs.getInt("unpaid"));
            	foundOrder.setOrderingDate(LocalDate.parse(rs.getDate("ordering_date").toString()));
            	foundOrder.setShippingDate(LocalDate.parse(rs.getDate("shipping_date").toString()));
            
            	orders.add(foundOrder);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public List<Order> SelectAllByCustomerId(Customer customer) {
		
		List<Order> orders = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getCustomerId());
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	Order foundOrder = new Order();
            	Customer customerTmp = new Customer();
            	customerTmp.setCustomerId(rs.getString("customer_id"));
            	foundOrder.setOrderId(rs.getString("order_id"));
            	foundOrder.setCustomer(CustomerDAO.getInstance().selectById(customerTmp));
            	foundOrder.setOrderAddress(rs.getString("order_address"));
            	foundOrder.setDeliveryAddress(rs.getString("delivery_address"));
            	foundOrder.setState(rs.getString("state"));
            	foundOrder.setPayment(rs.getInt("payment"));
            	foundOrder.setPaymentState(rs.getBoolean("payment_state"));
            	foundOrder.setPaid(rs.getInt("paid"));
            	foundOrder.setUnpaid(rs.getInt("unpaid"));
            	foundOrder.setOrderingDate(LocalDate.parse(rs.getDate("ordering_date").toString()));
            	foundOrder.setShippingDate(LocalDate.parse(rs.getDate("shipping_date").toString()));
            
            	orders.add(foundOrder);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
}
