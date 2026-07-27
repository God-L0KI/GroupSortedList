# GroupSortedList

> Java-коллекция, позволяющая хранить элементы в именованных группах, сохраняя доступ к ним как к единому списку.

![Maven Central](https://img.shields.io/maven-central/v/io.github.god-l0ki/groupsortedlist)
![Java](https://img.shields.io/badge/Java-17+-orange)
![License](https://img.shields.io/badge/license-MIT-green)
![Status](https://img.shields.io/badge/status-Experimental-blue)

🇷🇺 Русский | 🇺🇸 [English](README.md)

---

# Зачем нужен com.godl0ki.groupsortedlist.GroupSortedList?

В стандартной библиотеке Java нет коллекции, которая одновременно предоставляет:

- именованные группы;
- глобальную индексацию;
- доступ как к обычному списку;
- возможность независимо перемещать группы.

`com.godl0ki.groupsortedlist.GroupSortedList` решает эту задачу, сохраняя привычный Java API.

---

# Подключение

## Gradle

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "io.github.god-l0ki:groupsortedlist:0.1.0"
}
```

## Maven

```xml
<dependency>
    <groupId>io.github.god-l0ki</groupId>
    <artifactId>groupsortedlist</artifactId>
    <version>0.1.0</version>
</dependency>
```

---

# Возможности

- Именованные группы
- Глобальная индексация
- Доступ как к единому списку
- Получение группы по имени
- Получение группы по индексу
- Перемещение групп
- Автоматический пересчёт диапазонов индексов
- Serializable
- Основан на `AbstractList`

---

# Пример

```java
com.godl0ki.groupsortedlist.GroupSortedList<Integer> list = new com.godl0ki.groupsortedlist.GroupSortedList<>();

list.createGroup("Первая");
list.getGroup("Первая").put(10);
list.getGroup("Первая").put(20);

list.createGroup("Вторая");
list.getGroup("Вторая").put(30);

System.out.println(list.get(0)); // 10
System.out.println(list.get(1)); // 20
System.out.println(list.get(2)); // 30
```

---

# Принцип работы

Каждая группа хранит собственную коллекцию элементов.

При этом каждой группе автоматически назначается диапазон глобальных индексов.

```
Общий список

0 1 2 3 4 5 6 7 8

┌────────────┐
│ Группа A   │
│ 0 1 2      │
└────────────┘

┌────────────┐
│ Группа B   │
│ 3 4 5 6    │
└────────────┘

┌────────────┐
│ Группа C   │
│ 7 8        │
└────────────┘
```

При вызове `get(index)`:

1. Находится группа, которой принадлежит глобальный индекс.
2. Индекс преобразуется в локальный.
3. Возвращается нужный элемент.

---

# Сложность операций

| Операция | Сложность |
|-----------|----------:|
| createGroup | O(1) |
| get(index) | O(количество групп) |
| getGroup(name) | O(количество групп) |
| moveGroup() | O(количество групп) |
| put() | O(1) |

> В будущем планируется бинарный поиск по диапазонам групп (**O(log n)**).

---

# Текущее состояние проекта

## Реализовано

- ✅ Создание групп
- ✅ Поиск группы по имени
- ✅ Поиск группы по индексу
- ✅ Перемещение групп
- ✅ Глобальная индексация
- ✅ Доступ по глобальному индексу
- ✅ Сериализация

## Планируется

- ⬜ Вставка и удаление элементов
- ⬜ Итераторы
- ⬜ Поддержка Stream API
- ⬜ Бинарный поиск
- ⬜ Оптимизация производительности
- ⬜ Полная JavaDoc
- ⬜ Unit-тесты

---

# История изменений

См. [CHANGELOG.md](CHANGELOG.md)

---

# Совместимость

- Java 17+
- Maven Central
- MIT License

---

# Лицензия

Проект распространяется по лицензии MIT.

Подробности см. в файле [LICENSE](LICENSE).