package nl.bitsentools.eindprojectbackendmetabo.dto.invoice;

import jakarta.persistence.*;

import java.time.LocalDate;

public class InvoiceInputDto {


        public String invoiceId;

        public String productName;

        public double totalPrice;

        public double vat21ProductPrice;

        public double vat9ProductPrice;
        public double netPriceWithoutVat;
        public double vatRate;

        public double vatAmount;

        public int userId;

        public String userAddress;

        public boolean productWarranty;

        public int warrantyInMonths;

       public LocalDate dateOfPurchase;

        public String getInvoiceId() {
                return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
                this.invoiceId = invoiceId;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
        }

        public double getVat21ProductPrice() {
                return vat21ProductPrice;
        }

        public void setVat21ProductPrice(double vat21ProductPrice) {
                this.vat21ProductPrice = vat21ProductPrice;
        }

        public double getVat9ProductPrice() {
                return vat9ProductPrice;
        }

        public void setVat9ProductPrice(double vat9ProductPrice) {
                this.vat9ProductPrice = vat9ProductPrice;
        }

        public double getNetPriceWithoutVat() {
                return netPriceWithoutVat;
        }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public void setNetPriceWithoutVat(double netPriceWithoutVat) {
                this.netPriceWithoutVat = netPriceWithoutVat;
        }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public int getUserId() {
                return userId;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }

        public String getUserAddress() {
                return userAddress;
        }

        public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
        }

        public boolean isProductWarranty() {
                return productWarranty;
        }

        public void setProductWarranty(boolean productWarranty) {
                this.productWarranty = productWarranty;
        }

        public int getWarrantyInMonths() {
                return warrantyInMonths;
        }

        public void setWarrantyInMonths(int warrantyInMonths) {
                this.warrantyInMonths = warrantyInMonths;
        }

        public LocalDate getDateOfPurchase() {
                return dateOfPurchase;
        }

        public void setDateOfPurchase(LocalDate dateOfPurchase) {
                this.dateOfPurchase = dateOfPurchase;
        }
}

