# GroupSortedList

> A Java collection that stores elements in named groups while exposing them as one continuous indexed list.

## Overview

**GroupSortedList** is a custom Java collection designed for situations where data should remain logically grouped while still behaving like a regular list.

Instead of storing every element in a single array, the collection stores elements inside independent named groups. Each group manages its own elements, while the collection provides a single global index over all groups.

This allows grouped organization without sacrificing simple indexed access.

---

## Features

* Named groups
* Access groups by name or index
* Global element indexing
* Automatic global index range calculation
* Reorder groups without rebuilding the collection
* Built on `AbstractList`
* Serializable
* Designed to be lightweight and extensible

---

## Example

```java
GroupSortedList<Integer> list = new GroupSortedList<>();

list.createGroup("Numbers");
list.getGroup("Numbers").put(10);
list.getGroup("Numbers").put(20);

list.createGroup("Other");
list.getGroup("Other").put(30);

System.out.println(list.get(0)); // 10
System.out.println(list.get(1)); // 20
System.out.println(list.get(2)); // 30
```

---

## Internal structure

Each group owns its own storage.

The collection assigns every group a global index range.

```text
Global List

0 1 2 3 4 5 6 7 8

┌──────────────┐
│ Group A      │
│ Range: 0..2  │
└──────────────┘

┌──────────────┐
│ Group B      │
│ Range: 3..6  │
└──────────────┘

┌──────────────┐
│ Group C      │
│ Range: 7..8  │
└──────────────┘
```

When `get(index)` is called, the collection:

1. Finds the group whose range contains the requested index.
2. Converts the global index into a local group index.
3. Returns the corresponding element.

---

## Why?

Most Java collections store every element in one continuous structure.

GroupSortedList is intended for applications where elements naturally belong to separate categories but should still be accessible as a single ordered collection.

Possible use cases include:

* registries
* grouped game objects
* resource collections
* categorized datasets
* plugin systems
* configuration sections

---

## Current status

This project is under active development.

### Implemented

* Group creation
* Group lookup by name and index
* Group reordering
* Automatic global range calculation
* Global indexed access

### Planned

* Add/remove operations
* Iterators
* Stream support
* Binary search over group ranges
* Performance optimizations
* Full JavaDoc documentation

---

## Project goals

The primary goal of this project is to provide a reusable collection that combines:

* logical grouped storage;
* simple list-like access;
* clean API;
* good performance;
* easy extensibility.

---


