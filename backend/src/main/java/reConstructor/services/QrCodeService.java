package reConstructor.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import reConstructor.domain.Utillity.GoogleDriveResponse;
import reConstructor.domain.Utillity.MultipartFile;
import reConstructor.services.mapping.image.ImageMappingService;

@Service
public class QrCodeService {

    private GoogleDriveService googleDriveService;
    private ImageMappingService imageMappingService;

    public QrCodeService(GoogleDriveService googleDriveService,
                         ImageMappingService imageMappingService) {
        this.googleDriveService = googleDriveService;
        this.imageMappingService = imageMappingService;
    }

    public String generateQRCodeImage(String text) {

        try {
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode
                    (text, BarcodeFormat.QR_CODE,
                            300, 300, hintMap);

            MultipartFile qrCode = (MultipartFile) imageMappingService.convertToMultipartFile(
                    MatrixToImageWriter.toBufferedImage(bitMatrix));
            GoogleDriveResponse response = googleDriveService.saveQRCodeImage(qrCode);
            if (response.getUrl().isBlank()) {
                throw new RuntimeException("status " + response.getStatus() + response.getMessage());
            }

            return response.getUrl();

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
