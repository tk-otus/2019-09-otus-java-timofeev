## Homework #4: Автоматическое логирование

#### Цели занятия
- Познакомиться с принципами работы виртуальной машины Java, ClassLoader-ами и байт-кодом

#### Краткое содержание
1. Байт код. Содержание .class. Декомпиляция
1. Class Loader: примеры Class Lloader'ов,
1. Самодельный простой Cloass Loader
1. Instrumentation
1. ASM – инструмент для анализа и манипуляций с байт-кодом.

#### Результаты
1. Понимание основных принципов работы JVM.
1. Понимание как и для чего можно создать свой класс из байт-кодов

#### Домашнее задание
Разработайте такой функционал:

Метод класса можно пометить самодельной аннотацией `@Log`, например, так:

```java
class TestLogging {
    @Log
    public void calculation(int param) {};
}
```

При вызове этого метода "автомагически" в консоль должны логироваться значения параметров.
Например так.

```java
class Demo {
    public void action() {
        new TestLogging().calculation(6);
    }
}
```

В консоле дожно быть:

```
executed method: calculation, param: 6
```

Обратите внимание: явного вызова логирования быть не должно.

#### Проверка ДЗ
Собрать и запустить файл `hw4-automatic-logging-1.0-SNAPSHOT-jar-with-dependencies.jar` с помощью команд:
```shell script
mvn package
java -jar hw4-automatic-logging-1.0-SNAPSHOT-jar-with-dependencies.jar 
```
Либо запустить класс `ru.nspk.osks.DemoProxy`

Результатом будет вывод:
```
Executed method: calc, params: Without params
Executed method: calc, params: 144
Executed method: calc, params: 150, 15
Oops, this method don't have the @Log annotation. So, we can't show you what params it contains
```
Который показывает, что по тем методам `calc`, которые помечены аннотацией `@Log`, выводится дополнительная информация.
