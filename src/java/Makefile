.PHONY: execute clean

EXTRA_COMPILER_ARGS := -Xlint -Xdiags:verbose
PKG_DIR := com/company
JAVA_SRC_DIR := .
JAVA_OUT_DIR := ../../out/java

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
	pkg/datastructure/List.class \
	pkg/datastructure/Option.class \
	pkg/datastructure/Queue.class \
	pkg/datastructure/QueueArr.class \
	pkg/datastructure/Result.class \
	pkg/datastructure/SinglyLinkedList.class \
	pkg/datastructure/Stack.class \
	pkg/datastructure/StackArr.class

$(JAVA_OUT_DIR)/$(PKG_DIR)/Main.java : $(JAVA_SRC_DIR)/$(PKG_DIR)/Main.java $(subst pkg/,$(JAVA_OUT_DIR)/$(PKG_DIR)/,$(CLASSES))
	javac $(EXTRA_COMPILER_ARGS) -d $(JAVA_OUT_DIR) -cp $(JAVA_SRC_DIR) $(JAVA_SRC_DIR)/$(PKG_DIR)/Main.java

$(JAVA_OUT_DIR)/$(PKG_DIR)/%.class : $(JAVA_SRC_DIR)/$(PKG_DIR)/%.java
	javac $(EXTRA_COMPILER_ARGS) -d $(JAVA_OUT_DIR) -cp $(JAVA_SRC_DIR) $(subst $(JAVA_OUT_DIR),$(JAVA_SRC_DIR),$(subst class,java,$@))

$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/*Arr.class : \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/ArrStruct.class

$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/SinglyLinkedList.class : \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Option.class

$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/DoublyLinkedList.class : \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Option.class

$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Stack.class : \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/SinglyLinkedList.class \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Option.class

$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Queue.class : \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/DoublyLinkedList.class \
	$(JAVA_OUT_DIR)/$(PKG_DIR)/datastructure/Option.class

execute :
	java -cp $(JAVA_OUT_DIR) $(subst /,.,$(PKG_DIR)).Main

clean :
	del /f /q /s $(subst /,\,$(JAVA_OUT_DIR))
