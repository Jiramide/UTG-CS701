EXTRA_COMPILER_ARGS := -Xlint
PKG_DIR := com/company

CLASSES := pkg/DoublyLinkedList.class \
	pkg/Option.class \
	pkg/Queue.class \
	pkg/QueueArr.class \
	pkg/SinglyLinkedList.class \
	pkg/Stack.class \
	pkg/StackArr.class

out/$(PKG_DIR)/Main.java: src/$(PKG_DIR)/Main.java $(subst pkg/,out/$(PKG_DIR)/,$(CLASSES))
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src src/$(PKG_DIR)/Main.java

out/$(PKG_DIR)/%.class: src/$(PKG_DIR)/%.java
	echo Building $@ from $(subst out,src,$(subst class,java,$@))
	javac $(EXTRA_COMPILER_ARGS) -d out -cp src $(subst out,src,$(subst class,java,$@))
