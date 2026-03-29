/*

Binomial (Call) Option Pricing Model (BOPM)

This is a simple BOPM using hardcoded values:

> S (current stock price)
> K (strike price)
> r (risk-free rate, annualized)
> T (time to expiration, in years)
> sigma (volatility)
> n (number of time steps)

Calculated values:

> dT (delta T : length of each step in time)
> u ('up factor')
> d ('down factor')
> p (risk-neutral probability of upward movement)


*/

public class bopm {
    public static void main(String[] args){

        double S = 100.0;
        double K = 105.0;
        double r = 0.05;
        double sigma = 0.2;
        double T = 1.0;
        int n = 3;

        //formulas
        double dT = T/n;
        double u = Math.exp(sigma * Math.sqrt(dT));
        double d = 1/u;
        double p = (Math.exp(r*dT)-d)/(u-d);

        //building the tree
        double[] ST_final = new double[n+1];

        for (int i=0; i<=n; i++){
            ST_final[i] = S * Math.pow(u, n-i) * Math.pow(d,i);
        }

        double[] payoffs = new double[n+1];

        for (int i = 0; i <=n; i++){
            payoffs[i] = Math.max(ST_final[i] - K, 0);
        }

        //backward induction - pricing the option
        for (int step = n; step > 0; step--){
            for (int node = 0; node < step; node++){
                payoffs[node] = Math.exp(-r*dT) * (p*payoffs[node] + (1-p)*payoffs[node+1]);
        
            }
        }

        System.out.printf("Call Option Price: $%.4f%n", payoffs[0]);

    }
}

