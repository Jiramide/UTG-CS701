EXTRA_COMPILER_ARGS := -Xlint

.all: compile execute

.compile:
	cd src \
	javac $(EXTRA_COMPILER_ARGS) -d ../out $(files) \
	cd .. \

.execute:
	cd out \
	java $(entry_point) \
	cd .. \
