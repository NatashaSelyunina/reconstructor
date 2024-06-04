package reConstructor.repositories;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.stereotype.Repository;
import reConstructor.domain.Utillity.GoogleDriveResponse;

@Repository
public class GoogleDriveRepository {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String URL_PREFIX = "https://drive.google.com/thumbnail?id=";
    private static final String URL_POSTFIX = "&sz=w10000";

    private static InputStream getGoogleCredentialsAsStream() {
        return GoogleDriveRepository.class.getResourceAsStream("/cred.json");
    }

    private Drive createDriveService() throws IOException, GeneralSecurityException {
        InputStream credentialsStream = getGoogleCredentialsAsStream();

        GoogleCredential credential = GoogleCredential.fromStream(credentialsStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }

    public GoogleDriveResponse uploadImageToDrive(File image, String folderId) {
        GoogleDriveResponse response = new GoogleDriveResponse();

        try {
            Drive drive = createDriveService();

            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();

            fileMetaData.setName(image.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", image);
            com.google.api.services.drive.model.File uploadedFile = drive.files()
                    .create(fileMetaData, mediaContent)
                    .setFields("id").execute();
            String imageUrl = URL_PREFIX + uploadedFile.getId() + URL_POSTFIX;
            image.delete();
            response.setStatus(200);
            response.setMessage("Image successfully Uploaded to drive ");
            response.setUrl(imageUrl);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public GoogleDriveResponse deleteImageFromDrive(String id) {
        GoogleDriveResponse response = new GoogleDriveResponse();

        try {
            Drive drive = createDriveService();
            drive.files().delete(id).execute();

            response.setStatus(200);
            response.setMessage("File successfully deleted from Google Drive");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(500);
            response.setMessage("Failed to delete file: " + e.getMessage());
        }

        return response;
    }

    public GoogleDriveResponse updateImageOnDrive(String id, File image) {
        GoogleDriveResponse response = new GoogleDriveResponse();

        try {
            Drive drive = createDriveService();

            com.google.api.services.drive.model.File existingFile = drive.files().get(id).execute();
            if (existingFile == null) {

                response.setStatus(404);
                response.setMessage("File not found with ID: " + id);
                return response;
            }

            FileContent mediaContent = new FileContent("image/jpeg", image);

            com.google.api.services.drive.model.File updatedDriveFile = drive.files()
                    .update(id, null, mediaContent)
                    .setFields("id")
                    .execute();

            response.setStatus(200);
            response.setMessage("File successfully updated on Google Drive");
            response.setUrl(URL_PREFIX + updatedDriveFile.getId() + URL_POSTFIX);
        } catch (IOException e) {

            System.out.println("IOException occurred: " + e.getMessage());
            response.setStatus(500);
            response.setMessage("Failed to update file due to an I/O error: " + e.getMessage());
        } catch (GeneralSecurityException e) {

            System.out.println("GeneralSecurityException occurred: " + e.getMessage());
            response.setStatus(500);
            response.setMessage("Failed to update file due to security issues: " + e.getMessage());
        } catch (Exception e) {

            System.out.println("An unexpected error occurred: " + e.getMessage());
            response.setStatus(500);
            response.setMessage("Failed to update file: " + e.getMessage());
        }

        return response;
    }
}
