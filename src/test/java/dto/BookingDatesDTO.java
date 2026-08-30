package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDatesDTO {

    /** Формат YYYY-MM-DD, например 2015-06-13. */
    private String checkin;

    /** Формат YYYY-MM-DD, например 2015-06-17. */
    private String checkout;
}
