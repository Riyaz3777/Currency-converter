# Currency Converter

A simple desktop **Currency Converter** built with **Java Swing**, using live exchange rates from the free [Frankfurter API](https://www.frankfurter.app/) (no API key required, backed by European Central Bank data).

## Features
- Convert between 14 major currencies (USD, EUR, GBP, INR, JPY, AUD, CAD, CHF, CNY, SGD, AED, NZD, ZAR, SEK)
- Live exchange rates fetched over the network
- One-click swap between "from" and "to" currencies
- Non-blocking UI (network calls run on a background thread via `SwingWorker`)
- Simple input validation with friendly error messages

## Tech Stack
- Java 11+
- Java Swing (GUI)
- `java.net.http.HttpClient` (built-in, for API calls)
- [org.json](https://mvnrepository.com/artifact/org.json/json) (JSON parsing)
- Maven (build tool)

## Project Structure
```
currency-converter/
├── pom.xml
├── .gitignore
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── currencyconverter/
                    ├── Main.java
                    ├── CurrencyConverterGUI.java
                    └── ExchangeRateService.java
```

## How to Build and Run

### Prerequisites
- JDK 11 or higher installed
- Maven installed

### Build
```bash
mvn clean package
```
This produces a runnable fat jar at `target/currency-converter.jar`.

### Run
```bash
java -jar target/currency-converter.jar
```

## Pushing This Project to GitHub

If you already have an empty GitHub repository created and just need to push this code into it:

```bash
cd currency-converter
git init
git add .
git commit -m "Initial commit: Java Swing currency converter"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo-name>.git
git push -u origin main
```

If the GitHub repo already has a README or license file in it (so it's not empty), pull first to avoid conflicts:
```bash
git pull origin main --allow-unrelated-histories
git push -u origin main
```

## Possible Future Improvements
- Add a currency search/filter box instead of a fixed dropdown list
- Cache rates locally to reduce API calls
- Add a historical exchange rate chart
- Package as a native installer (jpackage)

## License
Free to use for learning and portfolio purposes.
