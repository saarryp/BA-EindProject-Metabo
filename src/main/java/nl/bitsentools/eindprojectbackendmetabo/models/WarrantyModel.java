package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name ="warranties")

public class WarrantyModel {

        @GeneratedValue
        @Id
        @Column

        private Long id;
        @Column

        private String productName;
        @Column
        private Date warrantyStart;
        @Column
        private Date warrantyEnds;


        @OneToOne(mappedBy = "warrantyModel")
        InvoiceModel invoiceModel;

    public WarrantyModel(Long id, String productName, int serialNumber, Date purchaseDate, Date warrantyStart, Date warrantyEnds, InvoiceModel invoiceModel) {
        this.id = id;
        this.productName = productName;
        this.warrantyStart = warrantyStart;
        this.warrantyEnds = warrantyEnds;
        this.invoiceModel = invoiceModel;
        }

        public WarrantyModel()
        {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }



        public Date getWarrantyStart() {
            return warrantyStart;
        }

        public String getProductName() {
        return productName;
        }

        public void setProductName(String productName) {
        this.productName = productName;
        }

        public void setWarrantyStart(Date warrantyStart) {
            this.warrantyStart = warrantyStart;
        }

        public Date getWarrantyEnds() {
            return warrantyEnds;
        }

        public void setWarrantyEnds(Date warrantyEnds) {
            this.warrantyEnds = warrantyEnds;
        }

         public InvoiceModel getInvoiceModel() {
        return invoiceModel;
        }

         public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
        }
}

//TODO: ONE TO ONE MAKEN TUSSEN INVOICE EN WARRANTY. DE PURCHASEDATE VAN PRODUCT HIERIN BETREKKEN EN DIE KOPPELEN MET DE WARRANTY STARTS EN ENDS. AANKOOPDATUM IS DE DATUM DAT DE GARANTIE INGAAT.
//TODO: MAKKELIJK AANPSSAEN VAN 2 JAAR, DUS EEN METHODE AANMKAEN VAN AANKOOPDATUM DIE JE UIT DE INVOICE OF ORDER HAALT EN 2 JAAR EBRIJ OPTELT OF MAANDEN EVEN KIJKEN HOE JE DAT HEBT OPGESTELD.


