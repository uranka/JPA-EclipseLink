package com.jelena.business;

import javax.persistence.*;

@Entity
@Table(name="invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoice_number")
	private Long invoiceNumber;
	
	@Column(name="is_processed")	
	boolean processed;
		
	@ManyToOne
	private Customer customer;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public boolean isProcessed() {		
		return processed;
	}

	public void setProcessed(boolean processed) {		
		this.processed = processed;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
}
