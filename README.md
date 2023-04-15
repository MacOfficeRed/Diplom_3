# Дипломный проект Задание 3
## веб-приложение
Нужно протестировать веб-приложение Stellar Burgers.
Что нужно сделать

Регистрация
- Успешную регистрацию.
- Ошибку для некорректного пароля. Минимальный пароль — шесть символов.

Вход
- вход по кнопке «Войти в аккаунт» на главной,
- вход через кнопку «Личный кабинет»,
- вход через кнопку в форме регистрации,
- вход через кнопку в форме восстановления пароля.

Переход в личный кабинет
- Проверь переход по клику на «Личный кабинет».

Переход из личного кабинета в конструктор
- Проверь переход по клику на «Конструктор»
- и на логотип Stellar Burgers.

Выход из аккаунта
- Проверь выход по кнопке «Выйти» в личном кабинете.

Раздел «Конструктор»
работают переходы к разделам:
- «Булки»,
- «Соусы»,
- «Начинки».

## Технологии, используемые в данном проекте
В данном проекте тесты написаны на **Java 11** с использованием фреймворка для web-тестирования **Selenide** (powered by Selenium WebDriver)

**JUnit5** задействован в качестве фреймворка модульного тестирования.

Для сборки проекта используется **Maven**.

В качестве библиотеки для работы с API используется **RestAssured**.

**Allure** - библиотека, позволяющая создавать удобные отчёты и собирать историю прогона тестов

Также используются дополнительные вспомогательные библиотеки типа **GSON**, **Faker**, **Lombok**

## Запуск тестов
Для запуска тестов, в консоли нужно выполнить команду
``mvn clean test``

## Просмотр отчёта
Для запуска отчёта Allure, в консоли нужно выполнить команду
`` allure serve``
