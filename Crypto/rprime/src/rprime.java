import java.io.*;
import java.math.BigInteger;  
import java.util.Random; 


public class rprime
{
 int no=1;	
 int n = 3;
 private int bitlength = 3;
 BigInteger p[],d,e;
 BigInteger cpdp[][] = new BigInteger[no][n];
 BigInteger r[]= new BigInteger[n];

 static BigInteger N,phi;

 
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
 public rprime()
 {
 	no=2;
 	key_gen();
 	BigInteger m[]=new BigInteger[2];
 	m[0]=new BigInteger("7");
 	m[1]=new BigInteger("9");
 	BigInteger c[]=encrypt(m);
 	for( int k=0;k<no;k++)
 		System.out.println(c[k]);
 	//	System.out.println(modexp(new BigInteger("5"),new BigInteger("3"),new BigInteger("7")));
 	BigInteger mm[]=decrypt(c);
 	for( int k=0;k<no;k++)
 		System.out.println(mm[k]);
 	
 }
 
 
    
    
 BigInteger [] encrypt(BigInteger m[])
 {
		System.out.println("e"+e); 
		BigInteger k[]=new BigInteger[no];
		for(int i=0;i!=no;i++){
			 k[i]=m[i].modPow(e,N);		
		}
		return k;
}


BigInteger [] decrypt(BigInteger c[])
{
		BigInteger m[] = new BigInteger[no];
		for(int i=0;i!=no;i++){
			for(int k=0;k!=n;k++)
			{
				cpdp[i][k]=exp(c[i],(r[k]));
			
				System.out.println(" cpdp[j] : " + cpdp[i][k]);
				
			}
			ChineseRemainder cr = new ChineseRemainder();
           
			m[i] = cr.findSolution(cpdp[i],p);
			
		}
		return m;
}



void key_gen()
 {

	    BigInteger da,d,a;
	    p= new BigInteger[3];
		p[0]=new BigInteger("31");
		p[1]=new BigInteger("23");
		p[2]=new BigInteger("19");
		BigInteger pms[] = new BigInteger[n];
		pms[0]=new BigInteger("30");
		pms[1]=new BigInteger("22");
		pms[2]=new BigInteger("18");
		/*for(int i=0;i!=n;i++){
			p[i]=BigInteger.probablePrime(bitlength, r);
				if(i>0)
					while(p[i].compareTo(p[i-1])<=0){
						p[i]=BigInteger.probablePrime(bitlength, r);
						//System.out.println(p[i]+"chks");}
					}
			//System.out.println(p[i]+"hits");	
		}*/
 		//System.out.println(p +" " +q +" "+ p.compareTo(q));
 		
 		N=BigInteger.ONE;
 		for(int i=0;i!=n;i++)
 			N = p[i].multiply(N);
 		phi=BigInteger.ONE;
 		for(int i=0;i!=n;i++)
 			phi = (p[i].subtract(BigInteger.ONE)).multiply(N);
 		
		System.out.println(N+" "+phi);
		
		
		
		
		for(int i=0;i!=n;i++){
			r[i]=new BigInteger("3");
		while(true)
		{	
				if(((p[i].subtract(BigInteger.ONE)).gcd(r[i])).compareTo(BigInteger.ONE)!=0 )
				{
						
	
						r[i]=r[i].add(new BigInteger("2"));
						
					
					System.out.println("---------"+ r[i]);
				}
				else
					break;
		}System.out.println("*******"+ r[i]);
		}

			
		ChineseRemainder cr = new ChineseRemainder();

		d=cr.findSolution(r, pms);
		
		e=d.modInverse(phi);
		System.out.println("ee"+e);
		System.out.println("dd"+d);
		
}
public static void main(String[] args) throws IOException{

  rprime r = new rprime();

}			
			
			
}

