package nl.bitsentools.eindprojectbackendmetabo.services;

import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ImageDataRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import nl.bitsentools.eindprojectbackendmetabo.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ImageDataService {
    private final ImageDataRepository imageDataRepository;
    private final ProductRepository productRepository;

    public ImageDataService(ImageDataRepository imageDataRepository, ProductRepository productRepository){
        this.imageDataRepository = imageDataRepository;
        this.productRepository = productRepository;
    }

    public String uploadImage(MultipartFile multipartFile, Long id) throws IOException {
        Optional<ProductModel> productModel = productRepository.findById(id);
        ProductModel product1 = productModel.get();

        ImageData imageData = new ImageData();
        imageData.setName(multipartFile.getName());
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));
        imageData.setProductModel(product1);

        ImageData savedImage = imageDataRepository.save(imageData);
        product1.setImageData((List<ImageData>) savedImage);
        productRepository.save(product1);

        return savedImage.getName();
    }

//
public List<byte[]> downloadImages(Long id) throws IOException {
    Optional<ProductModel> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()) {
        ProductModel product = productOptional.get();
        List<byte[]> imageBytesList = new ArrayList<>();
        List<ImageData> imageDataList = product.getImageData();
        for (ImageData imageData : imageDataList) {
            byte[] imageDataBytes = ImageUtil.decompressImage(imageData.getImageData());
            imageBytesList.add(imageDataBytes);
        }
        return imageBytesList;
    } else {
        throw new RecordNotFoundException("Product not found with ID: " + id);
    }
}




}
