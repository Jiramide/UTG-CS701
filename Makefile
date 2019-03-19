EXTRA_COMPILER_ARGS := -Xlint:unchecked

.all: compile execute

.compile:
  cd src
  javac $(EXTRA_COMPILER_ARGS) -d ../out $(files)
  cd ..

.execute:
  cd out
  java $(files)
  cd ..
