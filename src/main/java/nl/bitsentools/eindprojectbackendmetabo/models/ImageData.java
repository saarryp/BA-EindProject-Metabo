package nl.bitsentools.eindprojectbackendmetabo.models;

import jakarta.persistence.*;

@Entity
@Table(name= "image_data")
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] imageData;

    //KN NATUURLIJK OOK EEN MANYTOONE, want meerdere foto's gekoppeld aan 1 product, maar andersoom is maar 1 product gekoppeld aan bepaalde foto's.
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel productModel;

    public ImageData(){
    }

    public ImageData(String name, String type, byte[] imageData, ProductModel productModel) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.productModel = productModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
