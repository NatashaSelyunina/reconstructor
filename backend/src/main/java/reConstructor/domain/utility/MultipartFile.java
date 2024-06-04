package reConstructor.domain.Utillity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StreamUtils;

public class MultipartFile implements org.springframework.web.multipart.MultipartFile {

    private final InputStream inputStream;
    private final String originalFilename;
    private final String contentType;

    public MultipartFile(InputStream inputStream, String originalFilename, String contentType) {
        this.inputStream = inputStream;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        try {
            return inputStream.available();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return StreamUtils.copyToByteArray(inputStream);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        Files.copy(inputStream, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
