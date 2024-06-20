package model;

import java.util.Objects;

import model.product.Product;

public class OrderDetail {
	private Order order;
	private Product product;
	private int orderAmount;
	private double VAT;
	
	public OrderDetail() {}

	public OrderDetail(Order order, Product product, int orderAmount, double vAT) {
		super();
		this.order = order;
		this.product = product;
		this.orderAmount = orderAmount;
		VAT = vAT;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double vAT) {
		VAT = vAT;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "OrderDetail [order=" + order + ", product=" + product + ", orderAmount=" + orderAmount + ", VAT=" + VAT
				+ "]";
	}

}
