package nl.bitsentools.eindprojectbackendmetabo.controllers;

import jakarta.transaction.Transactional;
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



@Transactional
@GetMapping("/{id}")
public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws IOException {
    Optional<ProductModel> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()) {
        ProductModel product = productOptional.get();
        List<ImageData> imageDataList = product.getImageData();
        if (!imageDataList.isEmpty()) {
            ImageData imageData = imageDataList.get(0);
            byte[] image = imageDataService.downloadImage(id);
            MediaType mediaType = MediaType.valueOf(imageData.getType());
            return ResponseEntity.ok().contentType(mediaType).body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
        } else {
        return ResponseEntity.notFound().build();
        }
    }

}
