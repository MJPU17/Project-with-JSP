package co.edu.unbosque.model.persistence;

public interface CRUDoperation<K, T> {
	
	
	public void create(K key,T info);
	
	public void update(K key, T info);
	
	public void delete(K key);
	
}
