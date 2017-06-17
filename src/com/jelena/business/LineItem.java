package com.jelena.business;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="lineitems")
public class LineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="line_item_id")
	private Long lineItemId;
	
	
	private Product product;	
	private int quantity;
	
	@Transient
	private BigDecimal totalPrice;
	
// https://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Undirectional_OneToMany.2C_No_Inverse_ManyToOne.2C_No_Join_Table_.28JPA_2.0.29		
	@ManyToOne
	@JoinColumn(name="invoice_number")	
	private Invoice invoice;
		
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void updateTotalPrice() {		
		totalPrice = product.getUnitPrice().multiply(new BigDecimal(quantity));
	}
	
	
	public Long getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(Long lineItemId) {
		this.lineItemId = lineItemId;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}	

}
