package utils;

import dto.BookDTO;
import dto.BookingDatesDTO;
import dto.PatchBookingDTO;

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

    /**
     * Полностью обновлённое бронирование — payload для шага PUT в smoke-тесте:
     * все поля отличаются от fullBooking(), чтобы обновление было заметным.
     */
    public static BookDTO updatedBooking() {
        return BookDTO.builder()
                .firstname("Pavel")
                .lastname("Sidorov")
                .totalprice(400)
                .depositpaid(false)
                .bookingdates(BookingDatesDTO.builder()
                        .checkin("2026-11-05")
                        .checkout("2026-11-15")
                        .build())
                .additionalneeds("Dinner")
                .build();
    }

    /**
     * Частичное обновление для шага PATCH в smoke-тесте:
     * меняются только firstname и lastname (контракт PatchBookingDTO),
     * значения отличаются и от fullBooking(), и от updatedBooking().
     */
    public static PatchBookingDTO partialUpdateBooking() {
        return PatchBookingDTO.builder()
                .firstname("Maria")
                .lastname("Volkova")
                .build();
    }
}
