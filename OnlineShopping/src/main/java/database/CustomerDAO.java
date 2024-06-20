package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.VerifyCode;

public class CustomerDAO implements IDao<Customer> {
	
	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "customer";
	
	private static CustomerDAO daoInstance;

    private CustomerDAO() {}

    public static CustomerDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new CustomerDAO();
        }

        return daoInstance;
    }

	@Override
	public boolean insert(Customer object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (customer_id, customer_name, gender,"
					+ " address, delivery_address, dob, phone_number, email,"
					+ " user_name, customer_password, register_to_get_mail)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getCustomerId());
			preparedStatement.setString(2, object.getName());
			preparedStatement.setString(3, object.getGender());
			preparedStatement.setString(4, object.getAddress());
			preparedStatement.setString(5, object.getDeliveryAddress());
			if (object.getDob() != null) {
				preparedStatement.setDate(6, Date.valueOf(object.getDob()));
			} else {
				preparedStatement.setDate(6, null);
			}
			preparedStatement.setString(7, object.getPhoneNumber());
			preparedStatement.setString(8, object.getEmail());
			preparedStatement.setString(9, object.getUserName());
			preparedStatement.setString(10, object.getPassword());
			preparedStatement.setBoolean(11, object.isRegisterToGetMail());
			
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
	public boolean deleteById(Customer object) {
		
		try {
			OrderDAO.getInstance().deleteByCustomerId(object);
			
			VerifyCode vc = new VerifyCode();
			vc.setCustomerId(object.getCustomerId());
			
			VerifyCodeDAO.getInstance().deleteById(vc);
			
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
	public boolean update(Customer object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET customer_name = ?,"
					+ "gender = ?,"
					+ "address = ?,"
					+ "delivery_address = ?,"
					+ "dob = ?,"
					+ "phone_number = ?,"
					+ "email = ?,"
					+ "user_name = ?,"
					+ "customer_password = ?,"
					+ "register_to_get_mail = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getName());
			preparedStatement.setString(2, object.getGender());
			preparedStatement.setString(3, object.getAddress());
			preparedStatement.setString(4, object.getDeliveryAddress());
			if (object.getDob() != null) {
				preparedStatement.setDate(5, Date.valueOf(object.getDob()));
			} else {
				preparedStatement.setDate(5, null);
			}
			preparedStatement.setString(6, object.getPhoneNumber());
			preparedStatement.setString(7, object.getEmail());
			preparedStatement.setString(8, object.getUserName());
			preparedStatement.setString(9, object.getPassword());
			preparedStatement.setBoolean(10, object.isRegisterToGetMail());
			preparedStatement.setString(11, object.getCustomerId());
			
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

	public boolean changePassword(String id, String password) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET customer_password = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, id);
			
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
	public Customer selectById(Customer object) {
		
		Customer foundCustomer = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getCustomerId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundCustomer = new Customer();
            	foundCustomer.setCustomerId(rs.getString("customer_id"));
            	foundCustomer.setName(rs.getString("customer_name"));
            	foundCustomer.setGender(rs.getString("gender"));
            	foundCustomer.setAddress(rs.getString("address"));
            	foundCustomer.setDeliveryAddress(rs.getString("delivery_address"));
            	if (rs.getDate("dob") != null) {
            		foundCustomer.setDob(LocalDate.parse(rs.getDate("dob").toString()));
    			} else {
    				foundCustomer.setDob(null);
    			}
            	foundCustomer.setPhoneNumber(rs.getString("phone_number"));
            	foundCustomer.setEmail(rs.getString("email"));
            	foundCustomer.setUserName(rs.getString("user_name"));
            	foundCustomer.setPassword(rs.getString("customer_password"));
            	foundCustomer.setRegisterToGetMail(rs.getBoolean("register_to_get_mail"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundCustomer;
	}
	
	public Customer selectById(String id) {
		
		Customer foundCustomer = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.isBeforeFirst()) {
            	rs.next();
            	foundCustomer = new Customer();
            	foundCustomer.setCustomerId(rs.getString("customer_id"));
            	foundCustomer.setName(rs.getString("customer_name"));
            	foundCustomer.setGender(rs.getString("gender"));
            	foundCustomer.setAddress(rs.getString("address"));
            	foundCustomer.setDeliveryAddress(rs.getString("delivery_address"));
            	if (rs.getDate("dob") != null) {
            		foundCustomer.setDob(LocalDate.parse(rs.getDate("dob").toString()));
    			} else {
    				foundCustomer.setDob(null);
    			}
            	foundCustomer.setPhoneNumber(rs.getString("phone_number"));
            	foundCustomer.setEmail(rs.getString("email"));
            	foundCustomer.setUserName(rs.getString("user_name"));
            	foundCustomer.setPassword(rs.getString("customer_password"));
            	foundCustomer.setRegisterToGetMail(rs.getBoolean("register_to_get_mail"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundCustomer;
	}
	
	public Customer selectByUsername(String username) {
		
		Customer foundCustomer = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "user_name");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.isBeforeFirst()) {
            	rs.next();
            	foundCustomer = new Customer();
            	foundCustomer.setCustomerId(rs.getString("customer_id"));
            	foundCustomer.setName(rs.getString("customer_name"));
            	foundCustomer.setGender(rs.getString("gender"));
            	foundCustomer.setAddress(rs.getString("address"));
            	foundCustomer.setDeliveryAddress(rs.getString("delivery_address"));
            	if (rs.getDate("dob") != null) {
            		foundCustomer.setDob(LocalDate.parse(rs.getDate("dob").toString()));
    			} else {
    				foundCustomer.setDob(null);
    			}
            	foundCustomer.setPhoneNumber(rs.getString("phone_number"));
            	foundCustomer.setEmail(rs.getString("email"));
            	foundCustomer.setUserName(rs.getString("user_name"));
            	foundCustomer.setPassword(rs.getString("customer_password"));
            	foundCustomer.setRegisterToGetMail(rs.getBoolean("register_to_get_mail"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundCustomer;
	}

	@Override
	public List<Customer> SelectAll() {
		
		List<Customer> customers = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	Customer foundCustomer = new Customer();
            	foundCustomer = new Customer();
            	foundCustomer.setCustomerId(rs.getString("customer_id"));
            	foundCustomer.setName(rs.getString("customer_name"));
            	foundCustomer.setGender(rs.getString("gender"));
            	foundCustomer.setAddress(rs.getString("address"));
            	foundCustomer.setDeliveryAddress(rs.getString("delivery_address"));
            	if (rs.getDate("dob") != null) {
            		foundCustomer.setDob(LocalDate.parse(rs.getDate("dob").toString()));
    			} else {
    				foundCustomer.setDob(null);
    			}
            	foundCustomer.setPhoneNumber(rs.getString("phone_number"));
            	foundCustomer.setEmail(rs.getString("email"));
            	foundCustomer.setUserName(rs.getString("user_name"));
            	foundCustomer.setPassword(rs.getString("customer_password"));
            	foundCustomer.setRegisterToGetMail(rs.getBoolean("register_to_get_mail"));
            
            	customers.add(foundCustomer);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
}


