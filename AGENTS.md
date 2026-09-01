# AGENTS.md — руководство для контрибьюторов

API-фреймворк для тестирования Restful Booker API (`https://restful-booker.herokuapp.com`) на Java 17: REST Assured 5.4 + TestNG 7.9 + Lombok. Проект находится на стадии каркаса — большая часть классов пустая.

## Структура проекта

Весь код тестов находится в `src/test/java`:

- `config/` — константы окружения (`BookingConfig.BASE_URI`)
- `specs/` — `RequestSpecs` / `ResponseSpecs` (общие Request/ResponseSpecification)
- `clients/` — API-клиенты (`BookingClient`), инкапсулирующие HTTP-вызовы
- `dto/` — модели запросов/ответов (Lombok: `@Data @Builder @NoArgsConstructor @AllArgsConstructor`)
- `testCases/` — классы TestNG, наследуют `utils.BaseTest`
- `listeners/` — TestNG-листенеры (`RunTestAgain` — перезапуск неуспешных тестов)
- `utils/` — `BaseTest` (хуки `@BeforeMethod` / `@AfterMethod`), `TestDataGeneration`

TestNG suite лежит в `src/test/resources/testNG.xml` и подключён к Surefire — **новые тесты нужно добавлять в suite-файл вручную**, иначе `mvn test` их не подхватит.

## Команды

```bash
mvn clean test                          # полный прогон suite из testNG.xml
mvn test -Dtest=PlaceSmokeTests         # одиночный класс
mvn test -Dtest=PlaceSmokeTests#smokeTestFullLifeCycle   # одиночный метод
```

## Стиль кода

- Отступы — 4 пробела, кодировка UTF-8, Java 17.
- Пакеты — однословные в lowercase: `clients`, `config`, `dto`, `specs`, `testCases`, `utils`.
- Слои не перемешивать: тесты не строят HTTP-запросы напрямую — только через clients; общие проверки статуса/контента — в specs.
- DTO — только Lombok, без ручных геттеров/сеттеров.
- Тестовые методы — `camelCase`, в духе `smokeTestFullLifeCycle`, с аннотацией `@Test`.

## VCS: коммиты и pull request'ы

Ветка `master`, история пуста — установленной конвенции ещё нет. Предлагается Conventional Commits:

```
test: add booking lifecycle smoke test
feat: implement BookingClient CRUD methods
fix: correct response spec for 403 case
chore: bump rest-assured to 5.5.0
```

Требования к PR: краткое описание изменений, ссылка на связанную задачу (если есть), зелёный прогон `mvn clean test` перед мержем.

## Заметки

- Тесты бьют по живому внешнему API — для PUT/DELETE нужен токен авторизации (Basic Auth на `/auth`).
- `slf4j-nop` в зависимостях гасит логирование REST Assured — не подключайте другие логгеры без необходимости.
- API одноразовое (Heroku): периодически чистит данные — не завязывайте тесты на фиксированные booking id.
