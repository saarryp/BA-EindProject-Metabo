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
        private boolean productWarranty;
        @Column
        private int warrantyInMoths;
        @Column
        private Date warrantyStart;
        @Column
        private Date warrantyEnds;

        public WarrantyModel(Long id, boolean productWarranty, int warrantyInMoths, Date warrantyStart, Date warrantyEnds) {
            this.id = id;
            this.productWarranty = productWarranty;
            this.warrantyInMoths = warrantyInMoths;
            this.warrantyStart = warrantyStart;
            this.warrantyEnds = warrantyEnds;
        }

        public WarrantyModel()
        {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isProductWarranty() {
            return productWarranty;
        }

        public void setProductWarranty(boolean productWarranty) {
            this.productWarranty = productWarranty;
        }

        public int getWarrantyInMoths() {
            return warrantyInMoths;
        }

        public void setWarrantyInMoths(int warrantyInMoths) {
            this.warrantyInMoths = warrantyInMoths;
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
    }


