package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

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
        private LocalDate warrantyStart;
        @Column
        private LocalDate warrantyEnds;


        @OneToOne(mappedBy = "warrantyModel")
        InvoiceModel invoiceModel;

    public WarrantyModel(Long id, String productName, LocalDate warrantyStart, LocalDate warrantyEnds, InvoiceModel invoiceModel) {
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



        public LocalDate getWarrantyStart() {
            return warrantyStart;
        }

        public String getProductName() {
        return productName;
        }

        public void setProductName(String productName) {
        this.productName = productName;
        }

        public void setWarrantyStart(LocalDate warrantyStart) {
            this.warrantyStart = warrantyStart;
        }

        public LocalDate getWarrantyEnds() {
            return warrantyEnds;
        }

        public void setWarrantyEnds(LocalDate warrantyEnds) {
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


