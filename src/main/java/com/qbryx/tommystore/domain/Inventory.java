package com.qbryx.tommystore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="INVENTORY")
public class Inventory {
	
	private long id;
	
	private Product product; 
	 
	private int stock;
	
	private User updater;
	
	private String dateUpdated;
	
	public static final int INITIAL_STOCK = 0;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	@Column(name="last_updated")
	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product=" + product + ", stock=" + stock + ", updater=" + updater
				+ ", dateUpdated=" + dateUpdated + "]";
	}
}
