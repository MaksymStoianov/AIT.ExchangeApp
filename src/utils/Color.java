package utils;

public enum Color {
  RED("\u001B[31m"),              // Красный
  ORANGE("\u001B[38;5;202m"),     // Оранжевый
  YELLOW("\u001B[33m"),           // Желтый
  GREEN("\u001B[32m"),            // Зеленый
  CYAN("\u001B[36m"),             // Голубой (бирюзовый, условно голубой)
  BLUE("\u001B[34m"),             // Синий
  PURPLE("\u001B[35m"),           // Фиолетовый

  BLACK("\u001B[0m"),             // Черный
  WHITE("\u001B[37m"),            // Белый

  RESET("\u001B[0m");             // Сброс


  private final String code;


  Color(String code) {
    this.code = code;
  }


  @Override
  public String toString() {
    return this.code;
  }
}
