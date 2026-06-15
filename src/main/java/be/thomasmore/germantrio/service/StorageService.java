package be.thomasmore.germantrio.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
public class StorageService {

    private static final long MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024;

    private final ObjectProvider<S3Client> s3ClientProvider;
    private final String bucketName;
    private final String publicBucketUrl;

    public StorageService(ObjectProvider<S3Client> s3ClientProvider,
                          @Value("${supabase.bucket-name}") String bucketName,
                          @Value("${supabase.public-bucket-url}") String publicBucketUrl) {
        this.s3ClientProvider = s3ClientProvider;
        this.bucketName = bucketName;
        this.publicBucketUrl = publicBucketUrl;
    }

    public String uploadProfilePhoto(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new IllegalArgumentException("Profile photo must be 2MB or smaller.");
        }

        String contentType = file.getContentType();
        String extension = extensionForContentType(contentType);
        String key = "profile-photos/" + UUID.randomUUID() + extension;

        S3Client s3Client = s3ClientProvider.getIfAvailable();
        if (s3Client == null || isBlank(bucketName) || isBlank(publicBucketUrl)) {
            throw new IllegalStateException("Profile photo storage is not configured.");
        }

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(contentType)
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return publicBucketUrl.replaceAll("/+$", "") + "/" + key;
        } catch (IOException | S3Exception exception) {
            throw new IllegalStateException("Could not upload profile photo.", exception);
        }
    }

    private String extensionForContentType(String contentType) {
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            default -> throw new IllegalArgumentException("Profile photo must be a JPEG, PNG, or WebP image.");
        };
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
