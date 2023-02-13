package fr.centralesupelec.sio.extractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The main class of our server program.
// Following Spring architecture, the main class must be decorated with this annotation to enable discovery.
// The framework will then inspect the classes in the package to automatically register their features.
@SpringBootApplication
public class ExtractorApiApplication {

    // The main entrypoint when the server starts.
    public static void main(String[] args) {
        // We simply launch the framework be providing the root class of the application (ourselves).
        SpringApplication.run(ExtractorApiApplication.class, args);
    }

}
