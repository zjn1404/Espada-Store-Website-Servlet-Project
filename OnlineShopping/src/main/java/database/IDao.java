package database;

import java.util.List;

public interface IDao <T> {	
	
	boolean insert(T object);
	
	boolean deleteById(T object);
	
	boolean update(T object);
	
	T selectById(T object);
	
	List<T> SelectAll();
	
}
