package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import model.VerifyCode;

public class VerifyCodeDAO implements IDao<VerifyCode>{

	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "verify_code";
	
	private static VerifyCodeDAO daoInstance;

    private VerifyCodeDAO() {}

    public static VerifyCodeDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new VerifyCodeDAO();
        }

        return daoInstance;
    }

	
	@Override
	public boolean insert(VerifyCode object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (verify_code, customer_id, "
					+ "expiry_date, is_verified) "
					+ "values(?, ?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getVerifyCode());
			preparedStatement.setString(2, object.getCustomerId());
			if (object.getExpiryDate() == null) {
				preparedStatement.setTimestamp(3, null);
			} else {
				preparedStatement.setTimestamp(3, Timestamp.valueOf(object.getExpiryDate()));
			}
			preparedStatement.setBoolean(4, object.isVerified());
			
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
	public boolean deleteById(VerifyCode object) {

		try {
			
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
	public boolean update(VerifyCode object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET verify_code = ?,"
					+ "expiry_date = ?,"
					+ "is_verified = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getVerifyCode());
			if (object.getExpiryDate() == null) {
				preparedStatement.setTimestamp(2, null);
			} else {
				preparedStatement.setTimestamp(2, Timestamp.valueOf(object.getExpiryDate()));
			}
			preparedStatement.setBoolean(3, object.isVerified());
			preparedStatement.setString(4, object.getCustomerId());
			
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
	public VerifyCode selectById(VerifyCode object) {

		VerifyCode foundVerifyCode = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "customer_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getCustomerId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundVerifyCode = new VerifyCode();
            	foundVerifyCode.setVerifyCode(rs.getString("verify_code"));
            	foundVerifyCode.setCustomerId(rs.getString("customer_id"));
            	foundVerifyCode.setExpiryDate(rs.getTimestamp("expiry_date").toLocalDateTime());
            	foundVerifyCode.setVerified(rs.getBoolean("is_verified"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundVerifyCode;
	}

	@Override
	public List<VerifyCode> SelectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
