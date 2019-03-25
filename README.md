# UTG - CS701 #

## Table of Contents ##
- [UTG - CS701](#utg---cs701)
	- [Table of Contents](#table-of-contents)
	- [Intro](#intro)
	- [Compilation and Execution](#compilation-and-execution)
		- [Compilation](#compilation)
		- [Execution](#execution)
		- [Cleaning](#cleaning)
	- [Curriculum](#curriculum)


---
## Intro ##

This repository contains code written by [Jade Piramide](https://www.github.com/Jiramide) in his CS701
course in Under The Gui Academy. (Dated 2019)

The programming language used in the course is Java, albeit I may add
code using other languages for comparison.

All Java code will follow Google's [Java style guide](https://google.github.io/styleguide/javaguide.html)
unless specified in the `.editorconfig` file located in the file tree

---
## Compilation and Execution ##

`.java` files are located within `src/pkg_name`. The package name that I'm using is `com.company`.

`.class` files should *ideally* be put into `out/pkg_name`. This repository will provide a `MAKEFILE` to make compilation and execution easy.

### Compilation ###
```
$> make
```

### Execution ###
```
$> make execute
```

### Cleaning ###
```
$> make clean
```

Variables are located in the `Makefile` (most notably `PKG_DIR` and `EXTRA_COMPILER_ARGS`) for you to change
whenever compiling and executing.

---
## Curriculum ##

This course covers common data structures and crucial computer science concepts.

Some data structures covered in this course include:
- [Disjoint set](https://en.wikipedia.org/wiki/Disjoint-set_data_structure)
- [Singly linked list](https://en.wikibooks.org/wiki/Data_Structures/Singly_Linked_Lists)
- [Doubly linked list](https://en.wikipedia.org/wiki/Doubly_linked_list)
- [Stack](https://en.wikipedia.org/wiki/Stack_(abstract_data_type))