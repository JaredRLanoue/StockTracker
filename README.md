# Stock Tracking Android Application

The Stock Tracking Android Application is a mobile app built using Kotlin. It allows you to search up stocks, crypto, look at graphs, statistics, trending and market summary, and even save stocks or crypto to a watchlist. The app uses the YahooFinance API to provide real-time data on the financial market.

## Features

- Search for stocks and cryptocurrencies
- View detailed financial information for each stock or cryptocurrencies, including graphs, statistics, trending, and a market summary
- Save stocks or crypto to a watchlist for easy tracking
- Real-time data provided by the YahooFinance API

## Preview

<img src="app/src/main/res/gif/StockTrackerDemo.gif" width="393" height="851" alt="Stock Tracker Demo"/>

## Getting Started

To get started with the Stock Tracking Android Application, you'll need to follow these steps:

1. Clone the code from the [GitHub repository](https://github.com/JaredRLanoue/StockTracker) onto your local machine.
2. Obtain an API key from the YahooFinance API, which can be found [here](https://financeapi.net/dashboard).
3. Open the project in Android Studio and add your API key to `RetrofitAPIFactory` file's `apiKey` variable.
4. Build and run the app on your emulator or physical device.

## Usage

Once the app is running, you can search for stocks or crypto by entering the symbol in the search bar on the Search page. The app will then display real-time data on the selected stock or crypto, including graphs, statistics, trending, and a market summary.

You can save stocks or crypto to your watchlist by clicking on the "Heart" button at the top-right of the screen. This will add the stock or crypto to your watchlist page, which will exist even after the app is closed.
