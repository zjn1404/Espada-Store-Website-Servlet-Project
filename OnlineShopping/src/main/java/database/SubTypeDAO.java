package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.product.SubType;
import model.product.Type;

public class SubTypeDAO implements IDao<SubType> {
	
	private final String DEFAULTDATABASE = "espadastore";
	private final String DEFAULTTABLE = "subtype";
	
	private static SubTypeDAO daoInstance;

    private SubTypeDAO() {}

    public static SubTypeDAO getInstance() {

        if (daoInstance == null) {
            daoInstance = new SubTypeDAO();
        }

        return daoInstance;
    }

	@Override
	public boolean insert(SubType object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("INSERT INTO %s (subtype_id, type_id, subtype_name) values(?, ?, ?)", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getSubTypeId());
			preparedStatement.setString(2, object.getType().getTypeId());
			preparedStatement.setString(3, object.getSubTypeName());
			
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
	public boolean deleteById(SubType object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "subtype_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getSubTypeId());
			
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
	
	public int deleteAll(Type object) {
		
		int result = 0;
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("DELETE FROM %s WHERE %s = ?", DEFAULTTABLE, "type_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getTypeId());
			
            result = preparedStatement.executeUpdate();
            
            JDBCUtil.closeConnection(connection);
            
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean update(SubType object) {
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("UPDATE %s "
					+ "SET subtype_name = ?,"
					+ "type_id = ? "
					+ "WHERE %s = ?", DEFAULTTABLE, "subtype_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getSubTypeName());
			preparedStatement.setString(2, object.getType().getTypeId());
			preparedStatement.setString(1, object.getSubTypeId());
			
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
	public SubType selectById(SubType object) {
		
		SubType foundSubType = null;

		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s WHERE %s = ?", DEFAULTTABLE, "subtype_id");
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, object.getSubTypeId());
			
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs != null) {
            	rs.next();
            	foundSubType = new SubType();
            	Type typeTmp = new Type();
            	typeTmp.setTypeId(rs.getString("type_id"));
            	Type foundType = TypeDAO.getInstance().selectById(typeTmp);
            	
            	foundSubType.setSubTypeId(rs.getString("subtype_id"));
            	foundSubType.setSubTypeName(rs.getString("subtype_name"));
            	foundSubType.setType(foundType);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundSubType;
	}

	@Override
	public List<SubType> SelectAll() {
		
		List<SubType> subtypes = new ArrayList<>();
		
		try {
			
			Connection connection = JDBCUtil.getConnection(DEFAULTDATABASE);
			
			String sql = String.format("SELECT * FROM %s", DEFAULTTABLE);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            ResultSet rs = preparedStatement.executeQuery();
             
            while(rs.next()) {
            	
            	SubType foundSubType = new SubType();
            	Type typeTmp = new Type();
            	typeTmp.setTypeId(rs.getString("type_id"));
            	Type foundType = TypeDAO.getInstance().selectById(typeTmp);
            	
            	foundSubType.setSubTypeId(rs.getString("subtype_id"));
            	foundSubType.setSubTypeName(rs.getString("subtype_name"));
            	foundSubType.setType(foundType);
            
            	subtypes.add(foundSubType);
            }            
            
            JDBCUtil.closeConnection(connection);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subtypes;
	}

}
