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
        private LocalDate warrantyStart;
        @Column
        private LocalDate warrantyEnds;

        @OneToOne
        @JoinColumn(name = "product_model_id", referencedColumnName = "id")
      ProductModel productModel;

    public WarrantyModel(Long id, LocalDate warrantyStart, LocalDate warrantyEnds, ProductModel productModel) {
        this.id = id;
        this.warrantyStart = warrantyStart;
        this.warrantyEnds = warrantyEnds;
        this.productModel = productModel;
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

        public void setWarrantyStart(LocalDate warrantyStart) {
            this.warrantyStart = warrantyStart;
        }

        public LocalDate getWarrantyEnds() {
            return warrantyEnds;
        }

        public void setWarrantyEnds(LocalDate warrantyEnds) {
            this.warrantyEnds = warrantyEnds;
        }

         public ProductModel getProductModel() {
        return productModel;
        }

         public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
        }
}


