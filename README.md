# Assignment 2 – Factory Method & Abstract Factory

**Course:** Software Design Patterns
**Language:** Java 17 (Temurin JDK), Maven, JUnit 5
**Presenter:** Abdulla Nurdaulet

---

## 📌 Project Overview

A cross-platform GUI toolkit simulation that builds an OS-native form (text field, checkbox, button) without the client ever knowing which operating system family it is drawing for. The project walks through three stages:

1. **Part A:** the problem, with direct instantiation and `if/else` chains.
2. **Part B:** **Factory Method**, where object creation is deferred to subclasses.
3. **Part C:** **Abstract Factory**, where each factory produces a whole family of compatible products.
4. **Part D:** a fourth family (**Android**) added without touching the client, to prove the design is open for extension.

### Key Features

* **Four product families:** Windows, Mac, Gtk (Linux) and Android. Each has a Button, a Checkbox and a TextField.
* **Abstract Factory:** `SystemFactory` creates a full, compatible family of widgets.
* **Runtime selection:** `FactoryProvider` picks the factory from a command-line argument or the `APP_FAMILY` environment variable (default: `Mac`).
* **Abstraction-only client:** `FormRenderer` depends only on `SystemFactory`, `Button`, `Checkbox` and `TextField`.
* **Automated tests:** a JUnit 5 suite covering creation, compatibility, runtime selection, business behavior, negative cases, the fourth family and client abstraction.

---

## 🛠 Project Structure

```
Assignment2/
│
├── pom.xml
├── README.md
├── REPORT.md
│
└── src/
    ├── main/java/
    │   ├── Main.java
    │   │
    │   ├── model/                      # Product interfaces + concrete products
    │   │   ├── Button.java
    │   │   ├── Checkbox.java
    │   │   ├── TextField.java
    │   │   ├── Windows/   WindowsButton, WindowsCheckbox, WindowsTextField
    │   │   ├── Mac/       MacButton, MacCheckbox, MacTextField
    │   │   ├── Linux/     GtkButton, GtkCheckbox, GtkTextField
    │   │   └── Android/   AndroidButton, AndroidCheckbox, AndroidTextField
    │   │
    │   └── app/                        # Factories + client
    │       ├── SystemFactory.java      # Abstract Factory interface
    │       ├── WindowsFactory.java
    │       ├── MacFactory.java
    │       ├── GtkFactory.java
    │       ├── AndroidFactory.java
    │       ├── FactoryProvider.java    # runtime factory selection
    │       └── FormRenderer.java       # client (business logic)
    │
    └── test/java/
        ├── MainTest.java
        └── app/
            ├── ConsoleCapture.java     # test helper
            ├── FactoryCreationTest.java
            ├── ConcreteProductTest.java
            ├── CompatibilityTest.java
            ├── RuntimeSelectionTest.java
            ├── FormRendererBehaviorTest.java
            ├── NegativeScenarioTest.java
            ├── FourthFamilyTest.java
            └── ClientAbstractionTest.java
```

---

## 🚀 Getting Started

### Prerequisites

* JDK 17 or newer
* Maven 3.8+

### How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Shiferuk/Assignment-2-Cross-Platform-UI-GUI-Toolkit.git
   cd Assignment-2-Cross-Platform-UI-GUI-Toolkit
   ```

2. **Compile:**
   ```bash
   mvn compile
   ```

3. **Run `Main`:** pass the family as an argument (`windows`, `mac`, `gtk`/`linux`, `android`; case-insensitive):
   ```bash
   java -cp target/classes Main windows
   java -cp target/classes Main android
   ```

   Or use the environment variable (the argument takes priority; with neither, `Mac` is used):
   ```bash
   # Linux / macOS
   APP_FAMILY=gtk java -cp target/classes Main

   # Windows PowerShell
   $env:APP_FAMILY="gtk"; java -cp target/classes Main
   ```

   An unknown family throws `IllegalArgumentException` with a message listing the valid options.

### Example Output

```
========================================
App: Initializing OS-native form window...
[Android] Rendering Material underlined text field.
[Android] Rendering Material Design check box.
[Android] Rendering Material Design button.
App: Form successfully rendered on screen.

[User interaction simulated]
[Android] Typed "Jane Doe" into field.
[Android] Android checkbox toggled.
[Android] Android button tapped!
App: Form submission complete. Saved to database.
========================================
```

### Run the Tests

```bash
mvn test
```

---

## 🧪 Test Suite

| Requirement | Test class |
|---|---|
| Creation of each original family | `FactoryCreationTest` |
| Correct concrete product creation | `ConcreteProductTest` |
| Compatibility between products | `CompatibilityTest` |
| Runtime factory selection | `RuntimeSelectionTest`, `MainTest` |
| Business behavior (render order, interaction order, lifecycle, isolation, repeatability) | `FormRendererBehaviorTest` |
| Negative scenarios (unknown/null/blank family, null factory, untrimmed input) | `NegativeScenarioTest` |
| Fourth family (Android) | `FourthFamilyTest` + Android cases in `CompatibilityTest`, `MainTest` |
| Client works through abstractions | `ClientAbstractionTest` |

The suite has 37 test methods (43 executed cases counting parameterized runs). Console output is captured with `ConsoleCapture`, so tests assert on real behavior rather than on constructors or getters.

`ClientAbstractionTest` is the key proof of the abstraction: it feeds `FormRenderer` a factory and products defined inside the test, built with dynamic proxies, which the client has never seen, and checks that the client still works. A reflection check also confirms `FormRenderer` only holds interface-typed fields.

---

## 📋 Compliance Summary

| Requirement | Status |
|---|---|
| Part A: problem without factories analysed | ✅ see `REPORT.md` §1 |
| Part B: Factory Method applied | ✅ see `REPORT.md` §2 |
| Part C: Abstract Factory with product family | ✅ `SystemFactory` + 3 product interfaces |
| Part D: fourth family added with no client changes | ✅ Android |
| Runtime selection of factory | ✅ `FactoryProvider`, CLI arg, `APP_FAMILY` |
| Client depends only on abstractions | ✅ `FormRenderer` |
| Minimum 15 automated tests | ✅ 37 test methods |
| Negative scenarios | ✅ `NegativeScenarioTest` |