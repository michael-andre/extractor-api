package fr.centralesupelec.sio.extractor;

import fr.centralesupelec.sio.extractor.models.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Repository class for documents.
 */
// Creating "repository" classes to access data is a good architecture practice.
// The repository acts as a central point for every part of the application that need to access business data.
public class DocumentsRepository {

    // The private unique/shared instance of this class for the singleton pattern (see below).
    @Nullable
    private static DocumentsRepository shared = null;

    // Our repository should only exist once in the program, so we want to prevent multiple instances of our class.
    // For this, we use the singleton pattern: a static method that will create and return always the same instance.
    @NonNull
    public static DocumentsRepository getInstance() {
        // Check if we need to create our unique instance. After it has been done, we will never recreate a second one.
        if (shared == null) shared = new DocumentsRepository();
        return shared;
    }

    // In this demo, the documents are stored as a non-persistent in-memory list, populated at initialization.
    // In a real-life implementation, we would rely on a database instead.
    private List<Document> allDocuments;

    // Singleton pattern: the only constructor of this class is made private, so getInstance() is the only way.
    private DocumentsRepository() {
        // Populate the dummy repository with some documents.
        allDocuments = List.of(
                new Document("Hello ChatGPT", List.of("Momo", "Charles"), "Ipsum lorem"),
                new Document("Hi Siri", List.of("Steve"), "Ipsum lorem"),
                new Document("Hey Google", List.of("Larry"), "Ipsum lorem")
        );
    }

    // A method to list documents, accepting nullable filtering parameters.
    // Another pattern could be to define a DocumentFilter class to group these parameters.
    @NonNull
    public List<Document> getDocuments(@Nullable String title, @Nullable String author, @Nullable String summary) {
        // The following expression uses the "stream" chainable API of collections in Java.
        // This API provide methods to filter, group, manipulate iterable data.
        // Eventually, the data is collected and returned as a standard "List".
        return allDocuments.stream().filter(doc -> {
            if (title != null && !doc.title().toLowerCase().contains(title.toLowerCase()))
                return false;
            if (author != null && !doc.authors().stream().anyMatch(a -> a.toLowerCase().contains(author.toLowerCase())))
                return false;
            if (summary != null && !doc.summary().toLowerCase().contains(summary.toLowerCase()))
                return false;
            return true;
        }).toList();
    }

}
