package be.thomasmore.germantrio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    @ConditionalOnExpression(
            "'${supabase.s3.endpoint:}' != '' && '${supabase.s3.region:}' != '' && '${supabase.s3.access-key:}' != '' && '${supabase.s3.secret-key:}' != ''")
    public S3Client supabaseS3Client(@Value("${supabase.s3.endpoint}") String endpoint,
                                     @Value("${supabase.s3.region}") String region,
                                     @Value("${supabase.s3.access-key}") String accessKey,
                                     @Value("${supabase.s3.secret-key}") String secretKey) {
        return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .build();
    }
}
