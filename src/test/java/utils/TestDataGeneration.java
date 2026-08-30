package utils;

import dto.BookDTO;
import dto.BookingDatesDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataGeneration {

    /**
     * Полностью заполненное бронирование — базовый payload для POST /booking
     * и PUT /booking/{id}.
     */
    public static BookDTO fullBooking() {
        return BookDTO.builder()
                .firstname("Ivan")
                .lastname("Petrov")
                .totalprice(250)
                .depositpaid(true)
                .bookingdates(BookingDatesDTO.builder()
                        .checkin("2026-09-10")
                        .checkout("2026-09-20")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }

    /**
     * Бронирование без опционального поля additionalneeds —
     * API принимает такой payload (поле отсутствует в живых ответах).
     */
    public static BookDTO bookingWithoutAdditionalNeeds() {
        return BookDTO.builder()
                .firstname("Anna")
                .lastname("Smirnova")
                .totalprice(120)
                .depositpaid(false)
                .bookingdates(BookingDatesDTO.builder()
                        .checkin("2026-10-01")
                        .checkout("2026-10-05")
                        .build())
                .build();
    }

    /**
     * Бронирование с датами на ближайшую неделю от текущего дня —
     * пригодится для фильтров GET /booking?checkin=...&checkout=... .
     */
    public static BookDTO bookingWithNearDates() {
        LocalDate checkin = LocalDate.now().plusDays(7);
        LocalDate checkout = checkin.plusDays(3);
        return BookDTO.builder()
                .firstname("Sergey")
                .lastname("Ivanov")
                .totalprice(500)
                .depositpaid(true)
                .bookingdates(BookingDatesDTO.builder()
                        .checkin(checkin.format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .checkout(checkout.format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .build())
                .additionalneeds("Lunch")
                .build();
    }
}
