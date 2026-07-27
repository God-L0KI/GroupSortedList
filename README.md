# GroupSortedList

> A Java collection that stores elements in named groups while exposing them as a single indexed list.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![License](https://img.shields.io/badge/license-MIT-green)
![Status](https://img.shields.io/badge/status-Experimental-blue)

🇺🇸 English | 🇷🇺 [Русский](README_ru.md)

---

## Overview

`GroupSortedList` is an experimental Java collection that combines grouped storage with flat list access.

Instead of storing every element inside one continuous array, elements are organized into named groups while still behaving like one logical list.

Every group owns a global index range, allowing constant local access after locating the corresponding group.

---

## Features

- Named groups
- Global indexing
- Access elements by a single index
- Access groups by index or name
- Move groups without rebuilding the collection
- Automatic global index range recalculation
- Serializable
- Built on top of `AbstractList`

---

## Example

```java
GroupSortedList<Integer> list = new GroupSortedList<>();

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

## How it works

Each group stores its own collection of elements.

The collection automatically assigns a global index range to every group.

Example:

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

When `get(index)` is called:

1. Find the group whose range contains the global index.
2. Convert the global index into the group's local index.
3. Return the requested element.

---

## Current Status

This project is currently under active development.

### Implemented

- Group creation
- Group lookup by name
- Group lookup by index
- Group reordering
- Global index ranges
- Global element access

### Planned

- Element insertion/removal
- Iterators
- Streams support
- Binary search over group ranges
- Performance improvements
- Full JavaDoc
- Unit tests

---

## License

This project is licensed under the MIT License.

See the [LICENSE](LICENSE) file for details.
