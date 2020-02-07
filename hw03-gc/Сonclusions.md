# Использование различных сборщиков мусора

## Описание задачи

Написанное приложение в цикле добавляет по 1000 объектов ```Integer``` в массив ```ArrayList<Integer>``` до момента, 
пока у приложения не закончится память.

Было проведено несколько тестов с использованием различных сборщиков мусора с целью выявить наиболее оптимальный из 
них для решения этой задачи. Оптимальность будем определять по соотношения общего времени работы приложения и 
времени затраченного на сборку.

## Результаты
### Serial GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms2048m
-Xmx2048m
-XX:+UseSerialGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-07T08:09:25.679-0300][0.050s][info][gc] Using Serial
[2020-02-07T08:09:31.794-0300][6.166s][info][gc] GC(0) Pause Young (Allocation Failure) 482M->146M(1979M) 260.451ms
[2020-02-07T08:09:32.514-0300][6.885s][info][gc] GC(1) Pause Young (Allocation Failure) 379M->363M(1979M) 613.243ms
[2020-02-07T08:09:33.786-0300][8.157s][info][gc] GC(2) Pause Young (Allocation Failure) 699M->688M(1979M) 1124.272ms
[2020-02-07T08:09:36.495-0300][10.866s][info][gc] GC(3) Pause Young (Allocation Failure) 1181M->1175M(1979M) 2523.893ms
[2020-02-07T08:09:37.336-0300][11.707s][info][gc] GC(4) Pause Full (Allocation Failure) 1175M->489M(1979M) 840.756ms
[2020-02-07T08:09:37.960-0300][12.331s][info][gc] GC(5) Pause Young (Allocation Failure) 1229M->1220M(1979M) 175.627ms
[2020-02-07T08:09:39.153-0300][13.524s][info][gc] GC(6) Pause Full (Allocation Failure) 1220M->733M(1979M) 1191.756ms
[2020-02-07T08:09:40.358-0300][14.729s][info][gc] GC(7) Pause Full (Allocation Failure) 733M->733M(1979M) 1204.684ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 5          | 4697 ms (4.697 s)      |
| Full       | 3          | 3237 ms (3.237 s)      |

Объектов в массиве: 191 712 000

Общее время: 9.459 s

### Parallel GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms2048m
-Xmx2048m
-XX:+UseParallelGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-07T08:12:15.272-0300][0.046s][info][gc] Using Parallel
[2020-02-07T08:12:21.234-0300][6.007s][info][gc] GC(0) Pause Young (Allocation Failure) 479M->146M(1963M) 307.252ms
[2020-02-07T08:12:22.948-0300][7.722s][info][gc] GC(1) Pause Young (Allocation Failure) 1191M->959M(1963M) 897.302ms
[2020-02-07T08:12:23.875-0300][8.648s][info][gc] GC(2) Pause Young (Allocation Failure) 959M->959M(1963M) 926.457ms
[2020-02-07T08:12:25.567-0300][10.340s][debug][gc] GC(3) Shrinking ParOldGen from 1398272K by 73216K to 1325056K
[2020-02-07T08:12:25.567-0300][10.340s][info ][gc] GC(3) Pause Full (Allocation Failure) 959M->489M(1891M) 1691.630ms
[2020-02-07T08:12:26.332-0300][11.105s][debug][gc] Expanding ParOldGen from 1325056K by 73216K to 1398272K
[2020-02-07T08:12:26.471-0300][11.244s][info ][gc] GC(4) Pause Young (Allocation Failure) 1236M->1220M(1963M) 139.166ms
[2020-02-07T08:12:29.045-0300][13.818s][info ][gc] GC(5) Pause Full (Ergonomics) 1220M->733M(1963M) 2573.511ms
[2020-02-07T08:12:29.048-0300][13.821s][info ][gc] GC(6) Pause Young (Allocation Failure) 733M->733M(1963M) 2.530ms
[2020-02-07T08:12:31.532-0300][16.305s][info ][gc] GC(7) Pause Full (Allocation Failure) 733M->733M(2045M) 2484.312ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 5          | 2273 ms (2.273 s)      |
| Full       | 3          | 6750 ms (6.75 s)       |

Объектов в массиве: 191 712 000

Общее время: 11.028 s


### ConcurrentMarkSweep GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms2048m
-Xmx2048m
-XX:+UseConcMarkSweepGC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-07T08:14:16.979-0300][0.042s][debug][gc] ConcGCThreads: 0
[2020-02-07T08:14:16.979-0300][0.042s][debug][gc] ParallelGCThreads: 4
[2020-02-07T08:14:16.980-0300][0.042s][info ][gc] Using Concurrent Mark Sweep
[2020-02-07T08:14:22.582-0300][5.644s][info ][gc] GC(0) Pause Young (Allocation Failure) 217M->66M(2014M) 167.108ms
[2020-02-07T08:14:23.106-0300][6.169s][info ][gc] GC(1) Pause Young (Allocation Failure) 319M->212M(2014M) 413.238ms
[2020-02-07T08:14:25.374-0300][8.436s][info ][gc] GC(2) Pause Young (Allocation Failure) 1243M->1023M(2014M) 1551.581ms
[2020-02-07T08:14:26.168-0300][9.231s][info ][gc] GC(3) Pause Full (Allocation Failure) 1023M->489M(2014M) 794.115ms
[2020-02-07T08:14:26.633-0300][9.695s][info ][gc] GC(4) Pause Young (Allocation Failure) 1224M->1221M(2014M) 90.474ms
[2020-02-07T08:14:27.828-0300][10.890s][info ][gc] GC(5) Pause Full (Allocation Failure) 1221M->733M(2014M) 1194.934ms
[2020-02-07T08:14:29.016-0300][12.079s][info ][gc] GC(6) Pause Full (Allocation Failure) 733M->733M(2014M) 1186.350ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 4          | 2223 ms (2.223 s)      |
| Full       | 3          | 3175 ms (3.175 s)      |

Объектов в массиве: 191 712 000

Общее время: 6.84 s


### G1 GC
<details>
  <summary>Конфигурация</summary>
  
```
-Xms2048m
-Xmx2048m
-XX:+UseG1GC
-Xlog:gc=debug:file=./hw03-gc/logs/gc-%p.log:tags,uptime,time,level
```

</details>
<details>
  <summary>Лог выполнения</summary>

```
[2020-02-07T08:16:33.509-0300][0.029s][debug][gc] ConcGCThreads: 1 offset 8
[2020-02-07T08:16:33.509-0300][0.030s][debug][gc] ParallelGCThreads: 4
[2020-02-07T08:16:33.509-0300][0.030s][debug][gc] Initialize mark stack with 4096 chunks, maximum 16384
[2020-02-07T08:16:33.540-0300][0.061s][info ][gc] Using G1
[2020-02-07T08:16:33.575-0300][0.096s][info ][gc] Periodic GC disabled
[2020-02-07T08:16:39.979-0300][6.499s][info ][gc] GC(0) Pause Young (Concurrent Start) (G1 Humongous Allocation) 664M->658M(2048M) 8.393ms
[2020-02-07T08:16:39.979-0300][6.500s][info ][gc] GC(1) Concurrent Cycle
[2020-02-07T08:16:41.200-0300][7.720s][debug][gc] GC(1) Reclaimed 439 empty regions
[2020-02-07T08:16:41.200-0300][7.720s][info ][gc] GC(1) Pause Remark 1473M->1034M(2048M) 0.926ms
[2020-02-07T08:16:41.941-0300][8.461s][info ][gc] GC(2) Pause Young (Normal) (G1 Humongous Allocation) 1034M->1033M(2048M) 7.536ms
[2020-02-07T08:16:41.977-0300][8.498s][debug][gc] GC(3) Clear Next Bitmap 36.781ms
[2020-02-07T08:16:42.420-0300][8.940s][info ][gc] GC(3) Pause Full (G1 Humongous Allocation) 1033M->490M(2048M) 479.256ms
[2020-02-07T08:16:42.420-0300][8.941s][info ][gc] GC(1) Concurrent Cycle 2441.044ms
[2020-02-07T08:16:44.023-0300][10.543s][info ][gc] GC(4) Pause Young (Concurrent Start) (G1 Humongous Allocation) 1222M->1222M(2048M) 1.491ms
[2020-02-07T08:16:44.024-0300][10.545s][info ][gc] GC(5) Concurrent Cycle
[2020-02-07T08:16:44.026-0300][10.546s][info ][gc] GC(6) Pause Young (Normal) (G1 Humongous Allocation) 1222M->1222M(2048M) 0.744ms
[2020-02-07T08:16:44.039-0300][10.559s][debug][gc] GC(7) Clear Next Bitmap 13.554ms
[2020-02-07T08:16:44.631-0300][11.151s][info ][gc] GC(7) Pause Full (G1 Humongous Allocation) 1222M->734M(2048M) 604.989ms
[2020-02-07T08:16:45.417-0300][11.937s][info ][gc] GC(8) Pause Full (G1 Humongous Allocation) 734M->734M(2048M) 785.741ms
[2020-02-07T08:16:45.417-0300][11.937s][info ][gc] GC(5) Concurrent Cycle 1392.837ms
```

</details>

| Тип сборки | Количество | Суммарное время сборки |
| ---------- | ---------- | ---------------------- |
| Young      | 4          | 17 ms (0.017 s)        |
| Full       | 3          | 1872 ms (1.872 s)      |

Объектов в массиве: 191 712 000

Общее время: 6.664 s

### Сводная таблица
<table>
    <tr>
        <th>Сборщик</th>
        <th>Время работы</th>
        <th>Объектов в массиве</th>
        <th>Тип сборки</th>
        <th>Количество</th>
        <th>Суммарное время сборки</th>
    </tr>
    <tr>
        <td rowspan=2>Serial</td>
        <td rowspan=2>9.459 s</td>
        <td rowspan=2>191 712 000</td>
        <td>Young</td>
        <td>5</td>
        <td>4697 ms (4.697 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>3</td>
        <td>3237 ms (3.237 s)</td>
    </tr>
    <tr>
        <td rowspan=2>Parallel</td>
        <td rowspan=2>11.028 s</td>
        <td rowspan=2>191 712 000</td>
        <td>Young</td>
        <td>5</td>
        <td>2273 ms (2.273 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>3</td>
        <td>6750 ms (6.75 s)</td>
    </tr>
    <tr>
        <td rowspan=2>CMS</td>
        <td rowspan=2>6.84 s</td>
        <td rowspan=2>191 712 000</td>
        <td>Young</td>
        <td>4</td>
        <td>2223 ms (2.223 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>3</td>
        <td>3175 ms (3.175 s)</td>
    </tr>
    <tr>
        <td rowspan=2>G1</td>
        <td rowspan=2>6.664 s</td>
        <td rowspan=2>191 712 000</td>
        <td>Young</td>
        <td>4</td>
        <td>17 ms (0.017 s)</td>
    </tr>
    <tr>
        <td>Old</td>
        <td>3</td>
        <td>1872 ms (1.872 s)</td>
    </tr>
</table>​

## Выводы
Из полученных результатов видно, что для данной задачи наиболее подходящим сборщиком является G1, так как он имеет
лучшие показатели по слудующим метрикам: Наименьшее время выполнения программы, схожее с остальными количество сборок и 
наименьшее время затраченное на сборки.
