package nl.bitsentools.eindprojectbackendmetabo.services;

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
        imageData.setImageData(
                ImageUtil.compressImage(multipartFile.getBytes()));
        imageData.setProductModel(product1);

        ImageData savedImage = imageDataRepository.save(imageData);
        List<ImageData> imgdata = new ArrayList<>();
        imgdata.add(imageData);
        product1.setImageData(imgdata);
        productRepository.save(product1);


        return savedImage.getName();
    }

//

    public byte[] downloadImage(Long id) throws IOException {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            ProductModel product = productOptional.get();
            List<ImageData> imageDataList = product.getImageData();

            if (!imageDataList.isEmpty()) {
                // Assuming you want to return the image data of the first image in the list
                ImageData firstImageData = imageDataList.get(0);
                return ImageUtil.decompressImage(firstImageData.getImageData()); // Decompressing the image data
            } else {
                // Handle case when there are no images associated with the product
                // For now, let's throw an IOException
                throw new IOException("No images found for product with ID: " + id);
            }
        } else {
            // Handle case when the product is not found
            // For now, let's throw an IOException
            throw new IOException("Product not found with ID: " + id);
        }
    }


}
