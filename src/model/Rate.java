package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Rate
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class Rate {
  private BigDecimal course;
  private LocalDateTime time;


  public Rate(BigDecimal course, LocalDateTime time) {
    this.course = course;
    this.time = time;
  }


  public BigDecimal getCourse() {
    return course;
  }


  public void setCourse(BigDecimal course) {
    this.course = course;
  }


  public LocalDateTime getTime() {
    return time;
  }


  public void setTime(LocalDateTime time) {
    this.time = time;
  }


  @Override
  public String toString() {
    return "Rate{" +
           "course=" + course +
           ", time=" + time +
           '}';
  }
}
