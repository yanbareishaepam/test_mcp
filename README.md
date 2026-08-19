Playwright + JUnit 5 (Java 17+) — Quick Start

О проекте

Это минимальная заготовка для автотестов на Java 17+, Playwright и JUnit 5 с использованием паттерна Page Object Model.

Файлы:
- pom.xml — зависимости и плагины
- src/test/java/com/example/pages/LoginPage.java — пример Page Object
- src/test/java/com/example/tests/LoginTest.java — пример теста

Требования (локально, на Windows):
- Java 17+
- Maven (в PATH)

Проверки:
1) Проверить Java:
   java -version
2) Проверить Maven:
   mvn -v

Установка Maven (если нужно):
- winget (Windows 10/11):
    winget install -e --id Apache.Maven
- chocolatey:
    choco install maven
- Или скачать и распаковать вручную с https://maven.apache.org/download.cgi и добавить %MAVEN_HOME%\bin в PATH

Шаг 1: Скачать браузеры Playwright (один раз)

Откройте PowerShell или CMD в корне проекта (где находится pom.xml), затем выполните:

mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"

Альтернатива: при запуске тестов автоматически загрузить браузеры:

mvn -Dplaywright.install=true test

Шаг 2: Запуск тестов

Запустить тесты:

mvn test

Примечания
- При первом запуске Playwright скачивает бинарные браузеры; команда install ускоряет ручную установку.
- Если mvn недоступен в окружении CI или локально, установите Maven и повторите шаги выше.
- В целях отладки можно установить headless=false в тестах (см. LoginTest).

Если нужно, могу добавить:
- профиль Maven или goal, который автоматически вызывает установку браузеров перед тестами
- конфигурацию для параллельного запуска тестов
- примеры использования PageFactory, базового класса теста и конфигурации логирования

Я — AI assistant using Copilot CLI runtime in VS Code.