# LocateMyLoot
LocateMyLoot is an application that uses JavaFX to keep track of personal items.

## Prerequisites
- Java Development Kit (JDK) version 18.
- JavaFX SDK (compatible with Java 18).
- Ensure the `javafx` directory is located in the `LocateMyLoot` directory and contains the `lib` folder.

## Compile the Program
Run the following command from the `LocateMyLoot` directory:

```bash
javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml src/application/*.java src/widgets/*.java

java --module-path javafx/lib --add-modules javafx.controls,javafx.fxml -cp bin application.Main
