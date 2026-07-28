# GroupSortedList

> A Java collection that stores elements in named groups while exposing them as a single indexed list.

![Maven Central](https://img.shields.io/maven-central/v/io.github.god-l0ki/groupsortedlist)
![Java](https://img.shields.io/badge/Java-17+-orange)
![License](https://img.shields.io/badge/license-MIT-green)
![Status](https://img.shields.io/badge/status-Experimental-blue)

🇺🇸 English | 🇷🇺 [Русский](README_ru.md)

---

# Why GroupSortedList?

Java provides many collection implementations, but none of them combine:

- Named groups
- Global indexing
- List-like access
- Independent group manipulation

`com.godl0ki.groupsortedlist.GroupSortedList` solves this problem while keeping the API familiar to Java developers.

---

# Installation

## Gradle

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "io.github.god-l0ki:groupsortedlist:0.1.1"
}
```

## Maven

```xml
<dependency>
    <groupId>io.github.god-l0ki</groupId>
    <artifactId>groupsortedlist</artifactId>
    <version>0.1.1</version>
</dependency>
```

---

# Features

- Named groups
- Global indexing
- Flat list access
- Group lookup by name
- Group lookup by index
- Group reordering
- Automatic global index range recalculation
- Serializable
- Built on top of `AbstractList`

---

# Example

```java
com.godl0ki.groupsortedlist.GroupSortedList<Integer> list = new com.godl0ki.groupsortedlist.GroupSortedList<>();

list.createGroup("First");
list.getGroup("First").put(10);
list.getGroup("First").put(20);

list.createGroup("Second");
list.getGroup("Second").put(30);

System.out.println(list.get(0)); // 10
System.out.println(list.get(1)); // 20
System.out.println(list.get(2)); // 30
```

---

# How it works

Each group stores its own collection of elements.

Every group owns a global index range.

```
Global List

0 1 2 3 4 5 6 7 8

┌────────────┐
│ Group A    │
│ 0 1 2      │
└────────────┘

┌────────────┐
│ Group B    │
│ 3 4 5 6    │
└────────────┘

┌────────────┐
│ Group C    │
│ 7 8        │
└────────────┘
```

When calling `get(index)`:

1. Locate the group containing the global index.
2. Convert the global index into a local group index.
3. Return the element.

---

# Complexity

| Operation | Complexity |
|------------|-----------:|
| createGroup | O(1) |
| get(index) | O(groups) |
| getGroup(name) | O(groups) |
| moveGroup() | O(groups) |
| put() | O(1) |

> Binary search over group ranges is planned, reducing global lookup to **O(log groups)**.

---

# Project Status

## Implemented

- ✅ Group creation
- ✅ Group lookup by name
- ✅ Group lookup by index
- ✅ Group moving
- ✅ Global indexing
- ✅ Global element access
- ✅ Serialization

## Roadmap

- ⬜ Element insertion/removal
- ⬜ Iterators
- ⬜ Stream support
- ⬜ Binary search
- ⬜ Performance improvements
- ⬜ Full JavaDoc
- ⬜ Unit tests

---

# Changelog

See [CHANGELOG.md](CHANGELOG.md)

---

# License

This project is licensed under the MIT License.