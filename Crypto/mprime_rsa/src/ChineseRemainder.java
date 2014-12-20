import java.io.*;

import java.math.BigInteger;  
import java.util.Random; 


public class ChineseRemainder {

        //find x given x = a1 (mod m1), x = a2 (mod m2) ...
        public static BigInteger findSolution(BigInteger [] a, BigInteger[] m)
        {
             
               
                BigInteger x = a[0];
                BigInteger mInverse, y;
                BigInteger mi = new BigInteger("1");
                int i = 0;
               
               
                while(i< a.length-1)
                {
               
                        mi=m[i].multiply(mi);
                       
                        //check if the m's are relatively prime
                        if((m[i+1].gcd(mi)).compareTo(new BigInteger("1"))!=0)
                        {
                                System.out.println("The m's are not relatively prime->"+m[i+1]+","+mi);
                                return new BigInteger("-1");
                        }
                               
                        //find mi_inverse (mod m[i+1])
                        mInverse = mi.modInverse(m[i+1]);
						//System.out.println(mInverse);
                        if(mInverse.compareTo(new BigInteger("0"))<0)
                                mInverse= m[i+1].add(mInverse);
						//System.out.println(mInverse);
                        y = ((a[i+1].subtract(x)).multiply(mInverse)).mod( m[i+1]);
						  //System.out.println(y);
                        if(y.compareTo(new BigInteger("0"))<0)
                                {
									y= m[i+1].add(y);
							//		  System.out.println(y);
								}
                        //System.out.print("Step:"+i+"->x="+x+"+"+mi+"*"+y+"=");
                       
                        x = x.add( mi.multiply(y));
                       
                       
                        //System.out.println(x);
                       
                        i++;
                }
               
                return x;
        }
       
    /*    public static void main(String[] args)
        {
                ChineseRemainder cr = new ChineseRemainder();
                BigInteger a[] = new BigInteger[]{new BigInteger("2"),new BigInteger("3")};
                BigInteger m[] = new BigInteger[]{new BigInteger("3"),new BigInteger("7")};
               
                BigInteger x = cr.findSolution(a, m);
               
                System.out.println("Solution:"+x);
        }
		*/
}
