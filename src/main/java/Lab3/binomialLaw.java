package Lab3;

import java.util.List;
import java.util.Random;

public class binomialLaw extends Distribution{

    private double BinomialBernoulli(double p, int n)
    {
        double sum = 0;
        for (int i = 0; i != n; ++i)
            sum += Bernoulli(p);
        return sum;
    }
    static Random rnd = new Random();

    private  float Bernoulli(double p)
    {
        double q, U;
        q = (float) (1.0 - p);
        U = Uniform(0, 1);
        if (U > q)
        {
//            U -= q;
//            U /= p;
            return 1;
        }
        //U /= q;
        return 0;
    }

    private  double Uniform(double a, double b)
    {
        return a + rnd.nextDouble(100) * (b - a) / 100;
    }

    public List<Float> counting() {
        for (int i = 0; i < N; i++)
        {
           values.add((float) BinomialBernoulli(p, n));
        }
        return values;
    }

}
