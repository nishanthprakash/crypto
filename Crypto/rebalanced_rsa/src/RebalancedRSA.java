import java.io.*;
import java.math.BigInteger;  
import java.util.Random; 


public class RebalancedRSA
{
 private int bitlength = 3;
 private Random r;
 BigInteger p,q,n,d,e;
 static BigInteger N,phi;
 int no=2;
 
 public static BigInteger exp(BigInteger a,BigInteger e) {
        BigInteger aa = a;
		BigInteger i=new BigInteger("1");
		BigInteger ONE  = new BigInteger("1");
		//System.out.println(i+" "+e);
        while(i.compareTo(e)<0) {
            a  =  a.multiply(aa);
			i=i.add(ONE);
			//System.out.println(a);
        } 
		//
        return a;
    }
    
 public static BigInteger modexp(BigInteger a,BigInteger e,BigInteger n) {
        BigInteger aa = a;
		BigInteger i=new BigInteger("1");
		BigInteger ONE  = new BigInteger("1");
		//System.out.println(i+" "+e);
        while(i.compareTo(e)<0) {
            a  =  a.multiply(aa).mod(n);
			i=i.add(ONE);
			//System.out.println(a);
        } 
		//
        return a;
    }
 public RebalancedRSA()
 {
 	no=2;
 	BigInteger rs[]= key_gen(no);
 	
 	BigInteger m[]=new BigInteger[2];
 	m[0]=new BigInteger("7");
 	m[1]=new BigInteger("9");
 	BigInteger c[]=encrypt(m,2);
 	for( int k=0;k<no;k++)
 		System.out.println(c[k]);
 	//	System.out.println(modexp(new BigInteger("5"),new BigInteger("3"),new BigInteger("7")));
 	BigInteger mm[]=decrypt(c,rs[0],rs[1],2);
 	for( int k=0;k<no;k++)
 		System.out.println(mm[k]);
 	
 }
 
 
    
    
 BigInteger [] encrypt(BigInteger m[], int no)
 {
		System.out.println("e"+e); 
		BigInteger k[]=new BigInteger[no];
		for(int i=0;i!=no;i++){
			 k[i]=m[i].modPow(e,N);		
		}
		return k;
}


BigInteger [] decrypt(BigInteger c[],BigInteger r1,BigInteger r2,int no)
{
		BigInteger m[] = new BigInteger[no];
		for(int u=0;u<no;u++)
		{
				ChineseRemainder cr = new ChineseRemainder();
				BigInteger temp[] = new BigInteger[2];
				temp[0]=c[u].modPow(r1,p);
				temp[1]=c[u].modPow(r2,q);;
				BigInteger mods[] = new BigInteger[2];
				mods[0]=p;
				mods[1]=q;
				m[u]=cr.findSolution(temp, mods);
		}
		return m;
}



 BigInteger[]  key_gen(int no)
 {

	    BigInteger da,d,a;
	    BigInteger ars[] = new BigInteger[no];
		r = new Random();
	 	p=BigInteger.probablePrime(bitlength, r);
 		q=BigInteger.probablePrime(bitlength, r);
 		//System.out.println(p +" " +q +" "+ p.compareTo(q));
 		while((p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE))).compareTo(new BigInteger("2"))!=0)
 			{
 			q=BigInteger.probablePrime(bitlength, r);
 			System.out.println("int q "+ q);
 			}
 		System.out.println("p q "+ p+" "+q);	
 		N = p.multiply(q);
 		BigInteger ONE  = new BigInteger("1");
 		phi =  p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println(N+" "+phi);
		
		BigInteger r1,r2;
	   
		
		r1=new BigInteger("2");
		while(true)
		{	
				if(p.subtract(BigInteger.ONE).gcd(r1).compareTo(ONE)!=0 )
				{
						//System.out.println("*******"+ e[0]);
						r1=r1.add(ONE);
						//System.out.println("*******"+ e[0]);
				}
				else
					break;
		}
		if(r1.mod(new BigInteger("2")).compareTo(new BigInteger("0"))==0)
			r2=new BigInteger("2");
		else
			r2=new BigInteger("3");
			
		while(true)
		{	
				if(q.subtract(BigInteger.ONE).gcd(r2).compareTo(ONE)!=0 )
				{
						//System.out.println("*******"+ e[0]);
						r2=r2.add(new BigInteger("2"));
						//System.out.println("*******"+ e[0]);
				}
				else
					break;
		}
		a=r1.mod(new BigInteger("2"));
		ChineseRemainder cr = new ChineseRemainder();
		BigInteger temp[] = new BigInteger[2];
		temp[0]=(r1.subtract(a)).divide(new BigInteger("2"));
		temp[1]=(r2.subtract(a)).divide(new BigInteger("2"));
		BigInteger mods[] = new BigInteger[2];
		mods[0]=(p.subtract(BigInteger.ONE)).divide(new BigInteger("2"));
		mods[1]=(q.subtract(BigInteger.ONE)).divide(new BigInteger("2"));
		da=cr.findSolution(temp, mods);
		
		d=(da.multiply(new BigInteger("2"))).add(a);
		e=d.modInverse(phi);
		System.out.println("ee"+e);
		ars[0]=r1;
		ars[1]=r2;
		
		return ars;
		
}
public static void main(String[] args) throws IOException{

  RebalancedRSA r = new RebalancedRSA();

}			
			
			
}

