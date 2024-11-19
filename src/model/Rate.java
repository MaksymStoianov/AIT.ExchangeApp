package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс представляет курс валюты.
 */
public class Rate {

    // Код валюты (например, "USD", "EUR").
    private final String currencyCode;

    // Курс валюты.
    private BigDecimal course;

    // Время последнего обновления курса.
    private LocalDateTime time;


    /**
     * Конструктор для создания объекта {@code Rate} с кодом валюты, курсом.
     *
     * @param currencyCode Код валюты.
     * @param course       Курс валюты.
     */
    public Rate(String currencyCode, BigDecimal course) {
        this.currencyCode = currencyCode;
        this.course = course;
        this.time = LocalDateTime.now();
    }


    /**
     * Конструктор для создания объекта {@code Rate} с кодом валюты, курсом и временем.
     *
     * @param currencyCode Код валюты.
     * @param course       Курс валюты.
     * @param time         Время последнего обновления.
     */
    public Rate(String currencyCode, BigDecimal course, LocalDateTime time) {
        this.currencyCode = currencyCode;
        this.course = course;
        this.time = time;
    }


    /**
     * Получает код валюты.
     *
     * @return Код валюты.
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }


    /**
     * Получает курс валюты.
     *
     * @return Курс валюты.
     */
    public BigDecimal getCourse() {
        return this.course;
    }


    /**
     * Устанавливает новый курс валюты.
     *
     * @param course Новый курс валюты.
     */
    public void setCourse(BigDecimal course) {
        this.course = course;
        this.time = LocalDateTime.now();
    }


    /**
     * Получает время последнего обновления курса.
     *
     * @return Время последнего обновления.
     */
    public LocalDateTime getTime() {
        return time;
    }


    /**
     * Устанавливает время последнего обновления курса.
     *
     * @param time Время последнего обновления.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(currencyCode, rate.currencyCode) && Objects.equals(course, rate.course) &&
               Objects.equals(time, rate.time);
    }


    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, course, time);
    }


    @Override
    public String toString() {
        return "Rate{" +
               "currencyCode='" + currencyCode + '\'' +
               ", course=" + course +
               ", time=" + time +
               '}';
    }

}