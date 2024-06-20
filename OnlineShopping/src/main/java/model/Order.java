package model;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
	
	private String orderId;
	private Customer customer;
	private String orderAddress;
	private String deliveryAddress;
	private String state;// verifying, processing, shipping, shipped, returned
	private int payment;
	private boolean paymentState;
	private int paid;
	private int unpaid;
	private LocalDate orderingDate;
	private LocalDate shippingDate;
	
	public Order() {}
	
	public Order(String orderId, Customer customer, String orderAddress, String deliveryAddress, String state,
			int payment, boolean paymentState, int paid, int unpaid, LocalDate orderingDate, LocalDate shippingDate) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderAddress = orderAddress;
		this.deliveryAddress = deliveryAddress;
		this.state = state;
		this.payment = payment;
		this.paymentState = paymentState;
		this.paid = paid;
		this.unpaid = unpaid;
		this.orderingDate = orderingDate;
		this.shippingDate = shippingDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public boolean isPaymentState() {
		return paymentState;
	}

	public void setPaymentState(boolean paymentState) {
		this.paymentState = paymentState;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(int unpaid) {
		this.unpaid = unpaid;
	}

	public LocalDate getOrderingDate() {
		return orderingDate;
	}

	public void setOrderingDate(LocalDate orderingDate) {
		this.orderingDate = orderingDate;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderAddress=" + orderAddress
				+ ", deliveryAddress=" + deliveryAddress + ", state=" + state + ", payment=" + payment
				+ ", paymentState=" + paymentState + ", paid=" + paid + ", unpaid=" + unpaid + ", orderingDate="
				+ orderingDate + ", shippingDate=" + shippingDate + "]";
	}
}
