package nl.bitsentools.eindprojectbackendmetabo.controllers;

import nl.bitsentools.eindprojectbackendmetabo.models.ImageData;
import nl.bitsentools.eindprojectbackendmetabo.models.ProductModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ImageDataRepository;
import nl.bitsentools.eindprojectbackendmetabo.repositories.ProductRepository;
import nl.bitsentools.eindprojectbackendmetabo.services.ImageDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageDataController {

    private final ImageDataService imageDataService;
    private final ImageDataRepository imageDataRepository;
    private final ProductRepository productRepository;

    public ImageDataController(ImageDataService imageDataService, ImageDataRepository imageDataRepository, ProductRepository productRepository) {
        this.imageDataService = imageDataService;
        this.imageDataRepository = imageDataRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Object> uploadImage(@RequestParam("file")MultipartFile multipartFile, @RequestParam Long id) throws IOException {
    String image = imageDataService.uploadImage(multipartFile, id);
    return ResponseEntity.ok("File has been uploaded, "  + image);
}

//

    @GetMapping("/{id}")
    public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws IOException {
        byte[] image = imageDataService.downloadImage(id);

        Optional<ProductModel> product = productRepository.findById(id);
        Optional<ImageData> dbImageData = imageDataRepository.findById(product.get().getImageData().add(id));
        MediaType mediaType = MediaType.valueOf(dbImageData.get().getType());
        return ResponseEntity.ok().contentType(mediaType).body(image);
    }

//        // Retrieve the product by ID
//        Optional<ProductModel> productOptional = productRepository.findById(id);
//        if (productOptional.isPresent()) {
//            ProductModel product = productOptional.get();
//
//            // Retrieve the list of image data associated with the product
//            List<ImageData> imageDataList = product.getImageData();
//
//            // Assuming you want to handle multiple images associated with the product
//            // Loop through each ImageData object to retrieve the media type
//            List<MediaType> mediaTypes = new ArrayList<>();
//            for (ImageData imageData : imageDataList) {
//                mediaTypes.add(MediaType.valueOf(imageData.getType()));
//            }
//
//            // Use the media types to set the content type of the response
//            if (!mediaTypes.isEmpty()) {
//                return ResponseEntity.ok()
//                        .contentType(mediaTypes.get(0))  // Assuming the first media type determines the content type
//                        .body(images);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }


}
