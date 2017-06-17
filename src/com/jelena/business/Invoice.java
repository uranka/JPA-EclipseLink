package com.jelena.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	@Temporal(TemporalType.DATE)
	@Column(name="invoice_date")
	private Date invoiceDate;
		
	@ManyToOne
	private Customer customer;
	
	// cascade- sta god radim sa invoice to se primeni i na njegove LineItem	
	@OneToMany( cascade = CascadeType.ALL,
			 mappedBy="invoice", orphanRemoval = true) 
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	

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

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}
/*
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
		
	}*/
	
	//  dodati addLineItem koji dodaje samo jedan line item, sa child.setParent(this);
	// ovo sam morala ovako da uradim inace lineItem nije svestan svog invoica i u 
	// tabelu lineitems se snimi sve osim invoice number
	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
		lineItem.setInvoice(this);
	}	
	
	public void removeLineItem(LineItem lineItem) {
		lineItem.setInvoice(null);
		this.lineItems.remove(lineItem);
	}
}
