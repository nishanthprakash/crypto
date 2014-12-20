import java.io.*;
import java.math.BigInteger;  
import java.util.Random; 


public class Multipower
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
 public Multipower()
 {
 	no=2;
 	BigInteger rs[]= key_gen(no);
 	
 	BigInteger m[]=new BigInteger[2];
 	m[0]=new BigInteger("7");
 	m[1]=new BigInteger("6");
 	BigInteger c[]=encrypt(m,2);
 	System.out.println("Ciphertext");
 	for( int k=0;k<no;k++)
 		System.out.println(c[k]);
 	//	System.out.println(modexp(new BigInteger("5"),new BigInteger("3"),new BigInteger("7")));
 	BigInteger mm[]=decrypt(c,rs[0],rs[1],2);
 	System.out.println("Decrypted Ciphertext");
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
				BigInteger h = BigInteger.ONE;
				//c[u] p2 e
				//temp[0]=c[u].modPow(r1,p.multiply(p));
				//BigInteger einv = e.modInverse(p.subtract(BigInteger.ONE));
				//temp[0]=c[u].modPow(einv,p);
				System.out.println("h"+h);
				while(h.compareTo(p.multiply(p))!=0)
				{
					if(h.modPow(e,p.multiply(p)).compareTo(c[u].mod(p.multiply(p)))==0)
						{
						temp[0]=h;
						break;
						}
					else
						{
						h=h.add(BigInteger.ONE);
						System.out.println("h"+h+" "+h.modPow(e,p.multiply(p)));
						}
				}
				System.out.println("temp[0]"+temp[0]);
				temp[1]=c[u].modPow(r2,q);
				BigInteger mods[] = new BigInteger[2];
				mods[0]=p.multiply(p);
				mods[1]=q;
				m[u]=cr.findSolution(temp, mods);
		}
		return m;
}



 BigInteger[]  key_gen(int no)
 {

	    BigInteger n,da,d,a;
	    BigInteger ars[] = new BigInteger[no];
		r = new Random();
	 	p=BigInteger.probablePrime(bitlength, r);
 		q=BigInteger.probablePrime(bitlength, r);
 		//System.out.println(p +" " +q +" "+ p.compareTo(q));
 		while(p.compareTo(q)==0)
 			{
 			q=BigInteger.probablePrime(bitlength, r);
 			System.out.println("int q "+ q);
 			}
 		System.out.println("p q "+ p+" "+q);	
 		N = p.multiply(p).multiply(q);
 		BigInteger ONE  = new BigInteger("1");
 		phi =  p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println(N+" "+phi);
		
		BigInteger r1,r2;
	   
		e=new BigInteger("2");
		r1=new BigInteger("2");
		
		while(true)
		{	
				if(phi.gcd(e).compareTo(ONE)!=0  )
				{
						//System.out.println("*******"+ e[0]);
						e=e.add(ONE);
						//System.out.println("*******"+ e[0]);
				}
				else
					break;
		}
		d=e.modInverse(phi);
		
		r1=d.mod(p.subtract(BigInteger.ONE));
		r2=d.mod(q.subtract(BigInteger.ONE));
		ars[0]=r1;
		ars[1]=r2;
		
		return ars;
		
}
public static void main(String[] args) throws IOException{

  Multipower r = new Multipower();

}			
			
			
}

