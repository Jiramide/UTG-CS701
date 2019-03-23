EXTRA_COMPILER_ARGS := -Xlint

.all: compile execute

.compile: java $(EXTRA_COMPILER_ARGS) -d out -cp src $(files)

.execute: java -cp out $(entry_point)