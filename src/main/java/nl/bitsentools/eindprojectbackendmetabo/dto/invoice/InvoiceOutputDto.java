package nl.bitsentools.eindprojectbackendmetabo.dto.invoice;

import java.time.LocalDate;

public class InvoiceOutputDto {

    public Long id;
    public String invoiceId;

    public String productName;

    public double totalPrice;

    public double vat21ProductPrice;

    public double vat9ProductPrice;

    public int userId;

    public String userAddress;

    public boolean productWarranty;

    public int warrantyInMonths;

    public LocalDate dateOfPurchase;

    public InvoiceOutputDto(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setVat21ProductPrice(double vat21ProductPrice) {
        this.vat21ProductPrice = vat21ProductPrice;
    }

    public void setVat9ProductPrice(double vat9ProductPrice) {
        this.vat9ProductPrice = vat9ProductPrice;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setProductWarranty(boolean productWarranty) {
        this.productWarranty = productWarranty;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
