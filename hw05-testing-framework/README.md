## Homework #5: Аннотации

#### Цели занятия
- Познакомиться с механизмом Reflection
- Узнать что такое Аннотации и как их можно сделать

#### Краткое содержание
1. Reflection
1. Аннотации

#### Результаты
1. Понимание работы Reflection и Аннотаций

#### Домашнее задание
Написать свой тестовый фреймворк.

Поддержать свои аннотации `@Test`, `@Before`, `@After`.

Запускать вызовом статического метода с именем класса с тестами.

Т.е. надо сделать:
1. Создать три аннотации - `@Test`, `@Before`, `@After`.
1. Создать класс-тест, в котором будут методы, отмеченные аннотациями.
1. Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить 
методы отмеченные аннотациями и пункта 1.
1. Алгоритм запуска должен быть следующий (для каждой такой "тройки" надо создать СВОЙ объект класса-теста):
    - метод(ы) Before
    - текущий метод Test
    - метод(ы) After 
1. Исключение в одном тесте не должно прерывать весь процесс тестирования.
1. На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло успешно, 
сколько упало, сколько было всего)

#### Проверка ДЗ
При запуске файла `Demo.java` получим следующий вывод:

```
====================================
Running method: testFileExist
====================================
File exist!
====================================
Running method: testCanWriteToFile
====================================
Can write to file!
====================================
Running method: testCanReadFromFile
====================================
Can read from file!
====================================
Running method: testErrorOutput
====================================
java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
	at ru.nspk.osks.TestsRunner.run(TestsRunner.java:57)
	at ru.nspk.osks.Demo.main(Demo.java:7)
Caused by: java.lang.RuntimeException: Runtime error!
	at ru.nspk.osks.MyTestFrameworkTest.testErrorOutput(MyTestFrameworkTest.java:39)
	... 6 more
====================================
Running method: testDivisionByZero
====================================
java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
	at ru.nspk.osks.TestsRunner.run(TestsRunner.java:57)
	at ru.nspk.osks.Demo.main(Demo.java:7)
Caused by: java.lang.ArithmeticException: / by zero
	at ru.nspk.osks.MyTestFrameworkTest.testDivisionByZero(MyTestFrameworkTest.java:43)
	... 6 more
======== TEST RESULTS ========
Total tests: 5
Passed: 3
Failed: 2
```
