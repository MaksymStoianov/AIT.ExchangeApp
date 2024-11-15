package utils;

/**
 * PasswordValidator
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class PasswordValidator {

  /**
   * Проверяет, является ли строка валидным паролем.
   *
   * @param password Строка.
   * @return {@code true}, если строка является валидным паролем; иначе {@code false}.
   */
  public static boolean isValidPassword(String password)
      throws PasswordValidateException {

    boolean hasMinLength = !(password == null || password.length() < 8);
    boolean hasDigit = false;
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSpecial = false;

    String symbols = "!%$@&*()[].,-";

    if (password != null) {
      for (int i = 0; i < password.length(); i++) {
        char ch = password.charAt(i);

        if (Character.isDigit(ch)) {
          hasDigit = true;
        }

        if (Character.isLowerCase(ch)) {
          hasLower = true;
        }

        if (Character.isUpperCase(ch)) {
          hasUpper = true;
        }

        if (symbols.indexOf(ch) >= 0) {
          hasSpecial = true;
        }
      }

      if (hasMinLength && hasDigit && hasLower && hasUpper && hasSpecial) {
        return true;
      }
    }

    StringBuilder result = new StringBuilder()
        .append(Color.RESET)
        .append(Color.RED)
        .append("Невалидный пароль!")
        .append(Color.RESET);

    result
        .append("\n ")
        .append(hasMinLength ? "✅" : "❌")
        .append(" – пароль содержит минимум 8 символов;");

    result
        .append("\n ")
        .append(hasDigit ? "✅" : "❌")
        .append(" – пароль содержит минимум одну цифру;");

    result
        .append("\n ")
        .append(hasLower ? "✅" : "❌")
        .append(" – пароль содержит минимум одну маленькую букву;");

    result
        .append("\n ")
        .append(hasUpper ? "✅" : "❌")
        .append(" – пароль содержит минимум одну большую букву;");

    result
        .append("\n ")
        .append(hasSpecial ? "✅" : "❌")
        .append(" – пароль содержит минимум один специальный символ (")
        .append(Color.CYAN)
        .append(symbols)
        .append(Color.RESET)
        .append(").");

    throw new PasswordValidateException(result.toString());
  }
}
