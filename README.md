# Crypto Tracker v2.0

Real-time cryptocurrency tracker with Swing GUI + Console mode.

## Features
- Live prices from CoinGecko API
- MySQL database portfolio management (Add/View/Update/Delete)
- Price history graphs (JFreeChart)
- Price alerts (real API check)
- GUI + Console — dono modes available

## Setup

### 1. MySQL Database banao

```sql
CREATE DATABASE crypto_tracker;
USE crypto_tracker;

CREATE TABLE portfolio (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    coin_name VARCHAR(50),
    quantity  DOUBLE,
    buy_price DOUBLE
);

CREATE TABLE price_history (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    coin_name VARCHAR(50),
    price     DOUBLE,
    saved_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE favourites (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    coin_name VARCHAR(50)
);
```

### 2. Password set karo (config.properties)

`src/main/resources/config.properties` file mein apna password likho:

```properties
db.url=jdbc:mysql://localhost:3306/crypto_tracker
db.username=root
db.password=YOUR_ACTUAL_PASSWORD
```

> **Note:** `config.properties` ko `.gitignore` mein add karo — password GitHub pe mat daalo!

### 3. Build & Run

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Ya IntelliJ mein `Main.java` run karo.

## Files Removed (Duplicates)
| Removed | Reason |
|---------|--------|
| `root/Main.java` | src/Main.java mein merge ho gaya |
| `root/CryptoAPI.java` | src/CryptoAPI.java better version hai |
| `DBConnection.java` | DatabaseUtil.java use karo |
| `SavePriceHistory.java` | SaveLivePrice.java absorb kar liya |
| `CoinPrice.java` | Main.java case 5 mein absorb |

## Bugs Fixed
- DB password `config.properties` mein gaya (hardcode nahi)
- `getname()` → `getName()`, `getprice()` → `getPrice()` (Java convention)
- `PortfolioReader` ab `DatabaseUtil` use karta hai
- `AddPortfolio` ab user se input leta hai (hardcoded nahi)
- `AlertsPanel` ab real API price check karta hai
- `DashboardPanel` ab real API data dikhata hai
