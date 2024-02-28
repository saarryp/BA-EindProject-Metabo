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
        private Date warrantyStart;
        @Column
        private Date warrantyEnds;


        @OneToOne(mappedBy = "warrantyModel")
         ProductModel productModel;

        public WarrantyModel(Long id, Date warrantyStart, Date warrantyEnds, ProductModel productModel) {
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

        public Date getWarrantyStart() {
            return warrantyStart;
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

        public ProductModel getProductModel() {
        return productModel;
    }

        public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}


