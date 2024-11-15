package utils;

/**
 * EmailValidator
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class EmailValidator {

  /**
   * Проверяет, является ли строка валидным доменом.
   *
   * @param domain Строка.
   * @return {@code true}, если строка является валидным доменом; иначе {@code false}.
   */
  public static boolean isValidDomain(String domain) throws DomainValidateException {
    if (domain == null || domain.isEmpty() || domain.startsWith(".") || domain.endsWith(".")) {
      throw new DomainValidateException("Домен не может быть пустым или начинаться/заканчиваться с точки.");
    }

    String[] parts = domain.split("\\.");

    if (parts.length < 2) {
      throw new DomainValidateException("Домен должен содержать минимум одну точку.");
    }

    for (int i = parts.length - 1; i >= 0; i--) {
      String part = parts[i];

      if (part == null || part.isEmpty()) {
        throw new DomainValidateException("Часть домена не может быть пустой.");
      }

      if (i == 0) {
        if (part.length() == 1) {
          throw new DomainValidateException("Имя домена не может состоять из одного символа.");
        }

        for (char ch : part.toCharArray()) {
          if (!Character.isLetterOrDigit(ch)) {
            throw new DomainValidateException("Имя домена может содержать только буквы и цифры.");
          }
        }
      } else {
        if (part.startsWith("-") || part.endsWith("-")) {
          throw new DomainValidateException("Часть домена не может начинаться или заканчиваться дефисом.");
        }

        for (char ch : part.toCharArray()) {
          if (!Character.isLetterOrDigit(ch) && ch != '-') {
            throw new DomainValidateException("Часть домена может содержать только буквы, цифры и дефисы.");
          }
        }
      }
    }

    if (parts[parts.length - 1].length() < 2) {
      throw new DomainValidateException("Последняя часть домена должна содержать не менее двух символов.");
    }

    return true;
  }


  /**
   * Проверяет, является ли строка валидным email.
   *
   * @param email Строка.
   * @return {@code true}, если строка является валидным email; иначе {@code false}.
   */
  public static boolean isValidEmail(String email) throws EmailValidateException {
    if (email == null || email.isEmpty()) {
      throw new EmailValidateException("Email не может быть пустым.");
    }

    String[] parts = email.split("@");

    if (parts.length != 2) {
      throw new EmailValidateException("Email должен содержать ровно один символ '@'.");
    }

    String username = parts[0];

    if (
        username == null ||
        username.isEmpty() ||
        !Character.isAlphabetic(username.charAt(0)) ||
        !Character.isAlphabetic(username.charAt(username.length() - 1))
    ) {
      throw new EmailValidateException("Имя пользователя в email должно начинаться и заканчиваться буквой.");
    }

    for (int i = 0; i < username.length(); i++) {
      char ch = username.charAt(i);

      if (!(
          Character.isAlphabetic(ch) ||
          Character.isDigit(ch) ||
          ch == '-' ||
          ch == '_' ||
          ch == '.'
      )) {
        throw new EmailValidateException("Имя пользователя может содержать только буквы, цифры, дефисы, подчеркивания и точки.");
      }
    }

    try {
      isValidDomain(parts[1]);
    } catch (Exception error) {
      throw new EmailValidateException(error.getMessage());
    }

    return true;
  }
}
