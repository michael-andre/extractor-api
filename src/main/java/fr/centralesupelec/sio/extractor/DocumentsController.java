package fr.centralesupelec.sio.extractor;

import fr.centralesupelec.sio.extractor.models.Document;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A controller for API routes related to search of documents.
 */
// In Spring, we can isolate code related to some API routes in a dedicated "controller" class.
// The class must be annotated with this Spring annotation to enable registration of routes by the framework.
@RestController
public class DocumentsController {

    // We obtain and keep a single reference to the repository of vehicles.
    private DocumentsRepository repository = DocumentsRepository.getInstance();

    // Individual route handling is defined by a method of a controller, annotated with the @XxxMapping annotation.
    // The (unique) string parameter of the annotation is the API path to associate with this method.
    // The @NonNull annotation can optionally be added to assert that the return value will never be null.
    // The returned value is a List of Document instances, it will automatically be serialized to JSON by Spring.
    @GetMapping("search")
    @NonNull
    public List<Document> searchDocuments(
            // Controller methods can receive various arguments, decorated with annotation, that Spring will inject.
            // We define some query parameters that are optional (the default value will be null if omitted).
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String summary
    ) {

        // TODO: Validate parameters here
        // Here, before calling the repository, we should validate the parameters provided by the client.

        return repository.getDocuments(title, author, summary);
    }

}
