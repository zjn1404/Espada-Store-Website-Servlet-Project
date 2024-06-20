package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.product.Type;

public class TypeDAO implements IDao<Type> {
	
	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "tb_type";
	
	private static TypeDAO daoInstance;

    private TypeDAO() {}

    public static TypeDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new TypeDAO();
        }

        return daoInstance;
    }

	@Override
	public boolean insert(Type object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (type_id, type_name) values(?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getTypeId());
			preparedStatement.setString(2, object.getTypeName());
			
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
	public boolean deleteById(Type object) {

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "type_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getTypeId());
			
            int result = preparedStatement.executeUpdate();
            
            SubTypeDAO.getInstance().deleteAll(object);
            
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
	public boolean update(Type object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET type_name = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "type_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getTypeName());
			preparedStatement.setString(2, object.getTypeId());
			
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
	public Type selectById(Type object) {
		
		Type foundType = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "type_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getTypeId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundType = new Type();
            	foundType.setTypeId(rs.getString("type_id"));
            	foundType.setTypeName(rs.getString("type_name"));
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundType;
	}

	@Override
	public List<Type> SelectAll() {
	
		List<Type> types = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	Type foundType = new Type();
            	
            	foundType = new Type();
            	foundType.setTypeId(rs.getString("type_id"));
            	foundType.setTypeName(rs.getString("type_name"));
            
            	types.add(foundType);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return types;
	}

}
