# rest-assured-booking

API-фреймворк для тестирования [Restful Booker API](https://restful-booker.herokuapp.com) — учебного booking-сервиса с полным CRUD и авторизацией по токену.

Покрывает полный жизненный цикл бронирования (POST → GET → PUT → PATCH → DELETE), негативные сценарии и валидирует все JSON-ответы по JSON Schema.

## Стек

| Компонент | Версия | Назначение |
|---|---|---|
| Java | 17 | |
| REST Assured | 5.4 | HTTP-вызовы и ассерты |
| TestNG | 7.9 | тестовый раннер, suite-файл |
| Lombok | 1.18 | DTO без бойлерплейта |
| Jackson | 2.17 | сериализация DTO |
| json-schema-validator | 5.4 | валидация ответов по схемам |

## Структура

```
src/
├── main/resources/application.properties   # профили окружения (local / dev)
└── test/
    ├── java/
    │   ├── clients/    # API-клиенты: BookingClient, AuthClient (HTTP только здесь)
    │   ├── config/     # AppConfig (профили), BookingConfig (константы из профиля)
    │   ├── dto/        # BookDTO, BookingDatesDTO, PatchBookingDTO
    │   ├── listeners/  # RunTestAgain — ретраи неуспешных тестов
    │   ├── specs/      # RequestSpecs / ResponseSpecs (общие проверки статуса/контента/схем)
    │   ├── testCases/  # BookingSmokeTests, GetSmokeTests (наследуют BaseTest)
    │   └── utils/      # BaseTest (токен на suite, хуки), TestDataGeneration,
    │                  #   JsonSchemaGenerator (одноразовый генератор схем)
    └── resources/
        ├── testNG.xml                      # suite — новые тесты добавлять сюда
        └── schemas/                        # образцы ответов + *.schema.json
```

Правило слоёв: тесты не строят HTTP-запросы напрямую — только через clients; общие проверки — в specs.

## Запуск

Требуются JDK 17 и Maven.

```bash
mvn clean test                                # полный suite из testNG.xml
mvn test -Dtest=GetSmokeTests                 # одиночный класс
mvn test -Dtest=BookingSmokeTests#smokeTestFullLifeCycle   # одиночный метод
```

Профили окружения (по умолчанию `local`, переключение без правки кода):

```bash
mvn clean test -Dprofile=dev        # системное свойство
# или переменная окружения PROFILE=dev
```

## Что покрыто

**`BookingSmokeTests.smokeTestFullLifeCycle`** — полный жизненный цикл брони:
создание (echo-сверка тела) → чтение → полное обновление PUT (+контрольный GET на персистентность) → частичное PATCH (мерж: меняются только патч-поля) → удаление → контрольный GET с ожиданием 404.

**`GetSmokeTests.smokeTestGetRequest`** — негативные GET: несуществующие и невалидные id (`0`, `-1`) → 404 "Not Found".

Все JSON-ответы дополнительно валидируются JSON Schema (Draft-07) прямо в спеках: `OK_TOKEN_JSON`, `OK_BOOKING_JSON`, `OK_CREATED_BOOKING_JSON`.

## Как это устроено

- **Токен авторизации** создаётся один раз на suite (`@BeforeSuite` → `POST /auth`) и уходит в PUT/PATCH/DELETE как `Cookie: token=<value>` через `RequestSpecs.authSpec(token)`.
- **Ретраи**: `@Test(retryAnalyzer = RunTestAgain.class)` — до 2 перезапусков неуспешного теста (живой API флакует).
- **JSON Schema** выводится из захваченных образцов ответов (`schemas/*-sample.json`) утилитой `JsonSchemaGenerator`:
  ```bash
  mvn test-compile
  mvn org.codehaus.mojo:exec-maven-plugin:3.1.0:java "-Dexec.mainClass=utils.JsonSchemaGenerator" "-Dexec.classpathScope=test"
  ```
  После генерации сверяйте `required` с документацией — опциональность полей из одного образца не выводится.

## Подводные камни

- **418 I'm a Teapot**: API режет составной `Accept` с javascript-MIME (почерк сканеров). REST Assured разворачивает `setAccept(ContentType.JSON)` именно в такой — поэтому в `RequestSpecs` Accept задаётся строкой `"application/json"`. Не «оптимизируйте» обратно.
- **Тесты бьют по живому внешнему API**: Heroku-песочница периодически чистит данные — не завязывайтесь на фиксированные booking id.
- **Коды 404/418 не документированы** в API-доке — поведение зафиксировано живыми проверками (см. `ResponseSpecs.NOT_FOUND`).
- `DELETE` возвращает **201**, а не 200, с текстовым телом `Created` (не JSON).
- `POST /booking` возвращает **200** (не 201) с обёрткой `{"bookingid", "booking"}`; `GET/PUT/PATCH` — плоский объект без обёртки.

## Документация

- [`doc/Restful-booker-API.md`](doc/Restful-booker-API.md) — справочник Restful Booker API
- [`src/test/resources/schemas/`](src/test/resources/schemas/) — образцы ответов и JSON Schema
