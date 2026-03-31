# Binomial Option Pricing Model (BOPM)

A simple European call option pricer built in Java using the binomial tree method

## How It Works

The model takes six inputs: stock price, strike price, risk-free rate, volatility, time to expiration, and number of time steps, and prices a call option using backward induction through a binomial tree.

## Inputs (Hardcoded)

| Parameter | Value |
|-----------|-------|
| Stock Price (S) | $100 |
| Strike Price (K) | $105 |
| Risk-Free Rate (r) | 5% |
| Volatility (σ) | 20% |
| Time to Expiration (T) | 1 year |
| Steps (n) | 3 |

## Run
```bash
javac bopm.java
java bopm
```

## Future Improvements

- Add put option pricing
- Accept user input via Scanner
- Increase step count for convergence toward Black-Scholes
