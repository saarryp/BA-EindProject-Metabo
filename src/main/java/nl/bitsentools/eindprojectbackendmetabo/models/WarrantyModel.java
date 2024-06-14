package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="warranties")

public class WarrantyModel {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column

        private Long id;
        @Column

        private int productNumber;
        @Column
        private LocalDate warrantyStart;
        @Column
        private LocalDate warrantyEnds;


        @OneToOne(mappedBy = "warrantyModel")
        InvoiceModel invoiceModel;

    public WarrantyModel(Long id, int productNumber, LocalDate warrantyStart, LocalDate warrantyEnds, InvoiceModel invoiceModel) {
        this.id = id;
        this.productNumber = productNumber;
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

        public int getProductNumber() {
        return productNumber;
        }

        public void setProductNumber(int productName) {
        this.productNumber = productName;
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


