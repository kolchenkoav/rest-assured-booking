package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Частичный payload для PATCH /booking/{id}: только поля, которые меняем
 * (по контракту документации — firstname и lastname). Отдельный DTO вместо
 * BookDTO, чтобы не сериализовать незаполненные поля как null.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchBookingDTO {

    private String firstname;
    private String lastname;
}
