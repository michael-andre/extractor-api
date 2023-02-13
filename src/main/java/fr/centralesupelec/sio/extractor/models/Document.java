package fr.centralesupelec.sio.extractor.models;

import java.util.List;

public record Document(
        String title,
        List<String> authors,
        String summary
) {
}
