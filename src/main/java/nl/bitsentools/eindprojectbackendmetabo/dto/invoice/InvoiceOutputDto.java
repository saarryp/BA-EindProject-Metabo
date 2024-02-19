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
}
