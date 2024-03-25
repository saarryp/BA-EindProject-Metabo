package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ImageDataRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class ImageDataService {
    private final ImageDataRepository imageDataRepository;
    private final ProductRepository productRepository;

    public ImageDataService(ImageDataRepository imageDataRepository, ProductRepository productRepository){
        this.imageDataRepository = imageDataRepository;
        this.productRepository = productRepository;
    }

    public String uploadImage(MultipartFile multipartFile, String brandName) throws IOException {
        ProductModel productModel = productRepository.findById(Long);

        if (productModel == null) {
            // Product niet gevonden voor de opgegeven brandName
            return "Product niet gevonden voor brandName: " + brandName;
        }

        // Product gevonden, ga door met uploaden van de afbeelding
        ImageData imageData = new ImageData();
        imageData.setName(multipartFile.getOriginalFilename()); // Gebruik getOriginalFilename() om de oorspronkelijke bestandsnaam op te halen
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(multipartFile.getBytes());
        imageData.setProductModel(productModel);

        // Sla de afbeeldingsgegevens op in de database
        ImageData savedImage = imageDataRepository.save(imageData);

        // Koppel de afbeelding aan het product
        productModel.setImageData(savedImage);
        productRepository.save(productModel);

        return savedImage.getName(); // Retourneer de naam van de opgeslagen afbeelding
    }

//    public String uploadImage(MultipartFile multipartFile, String brandName) throws IOException {
//        ProductModel productModel = productRepository.findByBrandName(brandName);
//        ProductModel product = productModel;
//
//        ImageData imageData = new ImageData();
//        imageData.setName(multipartFile.getName());
//        imageData.setType(multipartFile.getContentType());
//        imageData.setImageData(multipartFile.getBytes());
//        imageData.setProductModel(product);
//
//        ImageData savedImage = imageDataRepository.save(imageData);
//        product.setImageData(savedImage);
//        ProductRepository.save();
//
//        return savedImage.getName();



}
