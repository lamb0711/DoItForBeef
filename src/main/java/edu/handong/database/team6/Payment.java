package edu.handong.database.team6;

public class Payment {
	int paymentId;
	int userId;
	int productId;
	int numbers;
	String cardNumber;
	String cardPassword;
	
	public void Payment(int paymentId, int userId, int productId, int numbers, String cardNumber, String cardPassword) {
		this.paymentId = paymentId;
		this.userId = userId;
		this.productId = productId;
		this.numbers = numbers;
		this.cardNumber = cardNumber;
		this.cardPassword = cardPassword;
	}
	
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setCardNumber(int numbers) {
		this.numbers = numbers;
	}
	public void setNumbers(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setCarNumbers(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
}
