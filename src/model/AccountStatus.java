package model;

public enum AccountStatus {
  ACTIVE,          // Активный счет пользователя.
  SYSTEM,          // Системный счет компании (для получения комиссии).
  CLOSED,          // Закрытый счет.
  BLOCKED          // Заблокированный счет.
}
