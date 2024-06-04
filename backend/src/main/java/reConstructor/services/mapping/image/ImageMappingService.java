package reConstructor.services.mapping.image;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import reConstructor.domain.Utillity.MultipartFile;

@Service
public class ImageMappingService {

  public org.springframework.web.multipart.MultipartFile convertToMultipartFile(BufferedImage image)  {
    try {
      File tempFile = createTempFile(image, "name");
      Path tempFilePath = tempFile.toPath();
      String contentType = "image/png";

      InputStream inputStream = Files.newInputStream(tempFilePath);
      return new MultipartFile(inputStream, tempFilePath.getFileName().toString(), contentType);
    }catch (Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

  private  File createTempFile(BufferedImage image, String fileName) throws IOException {
    File tempFile = Files.createTempFile(fileName, ".png").toFile();
    ImageIO.write(image, "png", tempFile);
    return tempFile;
  }

}
