
package com.digio.challenge.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigioChallengeApplicationTests {


    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Test
    void contextLoads() {
        // Test if the application context loads correctly
    }


    @Test
    void testApplicationProperties() {
        // Test if application properties are loaded correctly
        assertNotNull(redisHost, "Redis host should not be null");
        assertEquals("redis", redisHost, "Redis host should be 'redis'");
    }

    @Test
    void testDataLoaderServiceUrls() {
        // Test connectivity to the URLs used by DataLoaderService
        assertTrue(checkUrlConnectivity(), "DataLoaderService URLs should be reachable");
    }

    public static boolean checkUrlConnectivity() {
        String[] urls = {
            "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json",
            "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json"
        };

        for (String urlString : urls) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
