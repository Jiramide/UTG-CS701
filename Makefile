.PHONY: execute clean

EXTRA_COMPILER_ARGS := -Xlint
PKG_DIR := com/company

CLASSES := pkg/ArrStruct.class \
	pkg/BinaryTree.class \
	pkg/BinaryTreeArr.class \
	pkg/BubbleSort.class \
	pkg/DisjointSet.class \
	pkg/DoublyLinkedList.class \
	pkg/Heap.class \
	pkg/HeapArr.class \
	pkg/MergeSort.class \
	pkg/Option.class \
	pkg/ParenthesizedString.class \
	pkg/PascalTriangle.class \
	pkg/Queue.class \
	pkg/QueueArr.class \
	pkg/QuickSort.class \
	pkg/SelectionSort.class \
	pkg/SinglyLinkedList.class \
	pkg/Stack.class \
	pkg/StackArr.class

out/$(PKG_DIR)/Main.java : src/$(PKG_DIR)/Main.java $(subst pkg/,out/$(PKG_DIR)/,$(CLASSES))
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src src/$(PKG_DIR)/Main.java

out/$(PKG_DIR)/%.class : src/$(PKG_DIR)/%.java
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src $(subst out,src,$(subst class,java,$@))

out/$(PKG_DIR)/SinglyLinkedList.class : out/$(PKG_DIR)/Option.class

out/$(PKG_DIR)/DoublyLinkedList.class : out/$(PKG_DIR)/Option.class

out/$(PKG_DIR)/Stack.class : out/$(PKG_DIR)/SinglyLinkedList.class \
	out/$(PKG_DIR)/Option.class

out/$(PKG_DIR)/Queue.class : out/$(PKG_DIR)/DoublyLinkedList.class \
	out/$(PKG_DIR)/Option.class

execute :
	java -cp out $(subst /,.,$(PKG_DIR)).Main

clean :
	del /f out\$(subst /,\,$(PKG_DIR))\*.class
