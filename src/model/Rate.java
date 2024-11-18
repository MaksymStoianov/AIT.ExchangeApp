package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Класс для представления курса валюты с меткой времени и кодом валюты.
 *
 * Автор: Maksym Stoianov (stoianov.maksym@gmail.com)
 */
public class Rate {
  private String currencyCode; // Код валюты (например, "USD", "EUR")
  private BigDecimal course;   // Курс валюты
  private LocalDateTime time;  // Время последнего обновления курса

  /**
   * Конструктор для создания объекта Rate с кодом валюты, курсом и временем.
   *
   * @param currencyCode Код валюты.
   * @param course       Курс валюты.
   * @param time         Время последнего обновления.
   */
  public Rate(String currencyCode, BigDecimal course, LocalDateTime time) {
    if (course.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Курс должен быть больше нуля");
    }
    this.currencyCode = currencyCode;
    this.course = course;
    this.time = time;
  }

  /**
   * Конструктор для создания объекта Rate с кодом валюты и курсом.
   * Время обновления устанавливается автоматически на текущее.
   *
   * @param currencyCode Код валюты.
   * @param course       Курс валюты.
   */
  public Rate(String currencyCode, BigDecimal course) {
    if (course.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Курс должен быть больше нуля");
    }
    this.currencyCode = currencyCode;
    this.course = course;
    this.time = LocalDateTime.now(); // Устанавливаем текущее время
  }

  /**
   * Получает код валюты.
   *
   * @return Код валюты.
   */
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Устанавливает код валюты.
   *
   * @param currencyCode Код валюты.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Получает курс валюты.
   *
   * @return Курс валюты.
   */
  public BigDecimal getCourse() {
    return course;
  }

  /**
   * Устанавливает новый курс валюты.
   *
   * @param course Новый курс валюты.
   */
  public void setCourse(BigDecimal course) {
    if (course.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Курс должен быть больше нуля");
    }
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
  public String toString() {
    return "Rate{" +
            "currencyCode='" + currencyCode + '\'' +
            ", course=" + course +
            ", time=" + time +
            '}';
  }
}