package druyaned.booklib.data;

import java.time.LocalDate;

public record Book(
        Long id,
        String name,
        String author,
        String imageURL,
        String description,
        LocalDate date
) {
}
