.PHONY: execute clean

EXTRA_COMPILER_ARGS := -Xlint -Xdiags:verbose
PKG_DIR := com/company

CLASSES := \
	pkg/algo/BubbleSort.class \
	pkg/algo/MergeSort.class \
	pkg/algo/ParenthesizedString.class \
	pkg/algo/PascalTriangle.class \
	pkg/algo/QuickSort.class \
	pkg/algo/SelectionSort.class \
	pkg/datastructure/ArrStruct.class \
	pkg/datastructure/BinaryTree.class \
	pkg/datastructure/DisjointSet.class \
	pkg/datastructure/DoublyLinkedList.class \
	pkg/datastructure/Heap.class \
	pkg/datastructure/HeapArr.class \
	pkg/datastructure/Option.class \
	pkg/datastructure/Queue.class \
	pkg/datastructure/QueueArr.class \
	pkg/datastructure/SinglyLinkedList.class \
	pkg/datastructure/Stack.class \
	pkg/datastructure/StackArr.class

out/$(PKG_DIR)/Main.java : src/$(PKG_DIR)/Main.java $(subst pkg/,out/$(PKG_DIR)/,$(CLASSES))
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src src/$(PKG_DIR)/Main.java

out/$(PKG_DIR)/%.class : src/$(PKG_DIR)/%.java
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src $(subst out,src,$(subst class,java,$@))

out/$(PKG_DIR)/datastructure/*Arr.class : \
	out/$(PKG_DIR)/datastructure/ArrStruct.class

out/$(PKG_DIR)/datastructure/SinglyLinkedList.class : \
	out/$(PKG_DIR)/datastructure/Option.class

out/$(PKG_DIR)/datastructure/DoublyLinkedList.class : \
	out/$(PKG_DIR)/datastructure/Option.class

out/$(PKG_DIR)/datastructure/Stack.class : \
	out/$(PKG_DIR)/datastructure/SinglyLinkedList.class \
	out/$(PKG_DIR)/datastructure/Option.class

out/$(PKG_DIR)/datastructure/Queue.class : \
	out/$(PKG_DIR)/datastructure/DoublyLinkedList.class \
	out/$(PKG_DIR)/datastructure/Option.class

execute :
	java -cp out $(subst /,.,$(PKG_DIR)).Main

clean :
	del /f $(subst /,\, $(subst pkg,out/$(PKG_DIR),$(CLASSES)))
