/*

Binomial (European) Option Pricing Model (BOPM)

This is a simple BOPM using the following values:

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

import java.util.Scanner;

public class bopm {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter stock price: ");
        double S = scanner.nextDouble();

        System.out.print("Enter strike price: ");
        double K = scanner.nextDouble();

        System.out.print("Enter risk-free rate (as a decimal): ");
        double r = scanner.nextDouble();

        System.out.print("Enter volatility (as a decimal): ");
        double sigma = scanner.nextDouble();

        System.out.print("Enter total time until expiration, in years: ");
        double T = scanner.nextDouble();

        System.out.print("Enter number of time steps: ");
        int n = scanner.nextInt();

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

        //insert if-statement, testing for call (0) or put (1)
        System.out.print("Enter Call(0) or Put(1): ");
        int call_or_put = scanner.nextInt(); 

        for (int i = 0; i <=n; i++){
            if (call_or_put == 0){
                payoffs[i] = Math.max(ST_final[i] - K, 0);
            } else {
                payoffs[i] = Math.max(K - ST_final[i], 0);
            }
        }

        //backward induction - pricing the option
        for (int step = n; step > 0; step--){
            for (int node = 0; node < step; node++){
                payoffs[node] = Math.exp(-r*dT) * (p*payoffs[node] + (1-p)*payoffs[node+1]);
            }
        }
        
        System.out.printf("Option Price: $%.4f%n", payoffs[0]);

    }

}

