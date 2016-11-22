package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.StockMonitor;

@Repository("inventoryDao")
public class InventoryDaoImpl implements InventoryDao {

	private static final String FIND_ALL = "from Inventory";
	private static final String FIND_BY_CATEGORY = "from Inventory where product.category.name = :categoryName";
	private static final String FIND_BY_PRODUCT = "from Inventory where product = :product";
	private static final String FIND_BY_ID = "from Inventory where id = :id";
	private static final String FIND_STOCK_MONITOR = "from StockMonitor";
	private static final String FIND_BY_STOCK = "from Inventory where stock <= :stock";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findAll() {

		List<Inventory> inventories = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		inventories = (List<Inventory>) session.createQuery(FIND_ALL).getResultList();

		return inventories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findByCategory(Category category) {

		List<Inventory> inventories = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		inventories = (List<Inventory>) session.createQuery(FIND_BY_CATEGORY)
				.setParameter("categoryName", category.getName()).getResultList();

		return inventories;
	}

	@Override
	public Inventory findByProduct(Product product) {

		Inventory inventory = null;

		Session session = sessionFactory.getCurrentSession();

		inventory = (Inventory) session.createQuery(FIND_BY_PRODUCT)
				.setParameter("product", product).getSingleResult();

		return inventory;
	}
	
	@Override
	public Inventory findById(long id) {
		Inventory inventory = null;

		Session session = sessionFactory.getCurrentSession();

		inventory = (Inventory) session.createQuery(FIND_BY_ID)
				.setParameter("id", id).getSingleResult();

		return inventory;
	}
	
	@Override
	public StockMonitor findStockMonitor() {
		
		StockMonitor stockMonitor = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		stockMonitor = (StockMonitor) session.createQuery(FIND_STOCK_MONITOR)
											 .getSingleResult();
		
		return stockMonitor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findByStock(StockMonitor stockMonitor) {
		
		List<Inventory> inventories = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		inventories = (List<Inventory>) session.createQuery(FIND_BY_STOCK)
											   .setParameter("stock", stockMonitor.getStock())
											   .getResultList();
											   
		return inventories;
	}

	@Override
	public void createInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().save(inventory);
	}

	@Override
	public void updateInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().saveOrUpdate(inventory);
	}

	@Override
	public void deleteInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().delete(inventory);
	}

	@Override
	public void updateStockMonitor(StockMonitor stockMonitor) {
		sessionFactory.getCurrentSession().update(stockMonitor);
	}
}
