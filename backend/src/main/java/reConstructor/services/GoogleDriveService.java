package reConstructor.services;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reConstructor.domain.Utillity.GoogleDriveResponse;
import reConstructor.repositories.GoogleDriveRepository;

@Service
public class GoogleDriveService {

    private static final String MENU_IMAGE_FOLDER_ID = "1jBub_pSBJ8ZPrYuTTfiGDPywOZORKv8z";
    private static final String QR_CODE_IMAGE_FOLDER = "1aAE93t40pEaxfN8PBRzUp-U6J6MoVawp";
    private static final String RESTAURANT_BACKGROUND_IMAGE_FOLDER_ID = "12yNetFEYLL7dq9u77wp8ZuuVlbvRv4lO";
    private static final String RESTAURANT_LOGO_IMAGE_FOLDER_ID = "1vkegk4FLpRtcjzgY74MfPh5j8nP4pcv8";
    private GoogleDriveRepository repository;

    public GoogleDriveService(GoogleDriveRepository repository) {
        this.repository = repository;
    }

    public GoogleDriveResponse saveMenuImage(MultipartFile image) {
        return saveImage(image, MENU_IMAGE_FOLDER_ID);
    }

    public GoogleDriveResponse saveQRCodeImage(MultipartFile image) {
        return saveImage(image, QR_CODE_IMAGE_FOLDER);
    }

    public GoogleDriveResponse saveRestaurantBackgroundImage(MultipartFile image) {
        return saveImage(image, RESTAURANT_BACKGROUND_IMAGE_FOLDER_ID);
    }

    public GoogleDriveResponse saveRestaurantLogoImage(MultipartFile image) {
        return saveImage(image, RESTAURANT_LOGO_IMAGE_FOLDER_ID);
    }

    private GoogleDriveResponse saveImage(MultipartFile tempImage, String folderId) {
        try {
            if (tempImage.isEmpty()) {
                throw new FileUploadException("File can't be empty");
            }
            File image = File.createTempFile("pic", null);
            tempImage.transferTo(image);
            return repository.uploadImageToDrive(image, folderId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to process the file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }

    public GoogleDriveResponse deleteImage(String url) {
        if (url.isEmpty() || url.isBlank()) {
            throw new IllegalArgumentException("Url can't be empty!");
        }
        String[] imageId = url.split("=");

        return repository.deleteImageFromDrive(imageId[1]);
    }

    public GoogleDriveResponse updateImage(String url, MultipartFile tempImage) {
        if (url.isEmpty() || url.isBlank()) {
            throw new IllegalArgumentException("Url can't be empty!");
        }

        String[] imageId = url.split("=");

        try {
            if (tempImage.isEmpty()) {
                throw new FileUploadException("File can't be empty");
            }
            File image = File.createTempFile("pic", null);
            tempImage.transferTo(image);
            return repository.updateImageOnDrive(imageId[1], image);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to process the file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }
}
