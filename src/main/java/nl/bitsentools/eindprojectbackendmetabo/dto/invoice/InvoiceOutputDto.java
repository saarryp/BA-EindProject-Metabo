package nl.bitsentools.eindprojectbackendmetabo.dto.invoice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class InvoiceOutputDto {

    public Long id;
    @NotBlank(message = "Factuurnummer is verplicht.")
    public String invoiceId;


    @NotBlank(message = "De naam product is verplicht")
    public String productName;

    @NotNull(message = "Totaalprijs is verplicht")
    @PositiveOrZero(message = "Totaalprijs moet positief of 0 zijn.")
    public double totalPrice;

    @PositiveOrZero(message = "Bedrag van 21% BTW moet positief of 0 zijn.")
    public double vat21ProductPrice;

    @PositiveOrZero(message = "Bedrag van 9% BTW moet positief of 0 zijn.")
    public double vat9ProductPrice;

    public  double netPriceWithoutVat;

    public double vatRate;

    public double vatAmount;

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

    public void setNetPriceWithoutVat(double netPriceWithoutVat) {
        this.netPriceWithoutVat = netPriceWithoutVat;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }


    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
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
