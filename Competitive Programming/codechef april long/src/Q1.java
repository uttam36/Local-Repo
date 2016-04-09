import java.util.*;
class Q1 
{
	static int mod = 1000000007;
	static long n;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextLong();
		long m = sc.nextLong();
	
		int size = (int)(2*n);
		//System.out.println(size);
		long arr[][] = new long[size][4];
		//System.out.println(fib(6)[0]);
		for(int i=0;i<n;i++)
		{
			arr[(int) (n+i)] = fib(sc.nextLong());
		}
		
		  for (int i =(int) n-1; i > 0; i--)
			  arr[i] = add(add(arr[i<<1],arr[i<<1|1]),multiply(arr[i<<1],arr[i<<1|1]));
		
		  sc.nextLine();
		  for(int i=0;i<m;i++)
		  {
			  
			  String what[] = sc.nextLine().split(" ",3);
			 // System.out.println(what[0]);
			  if(what[0].equals("Q"))
			  {
				  int l = Integer.parseInt(what[1]);
				  int r = Integer.parseInt(what[2]);
				//  System.out.println(l+" "+r);
				  System.out.println(query(arr,l-1,r)[1]);
			  }
			  else
			  {
				  int x = Integer.parseInt(what[1]);
				  int y = Integer.parseInt(what[2]);
				  modify(arr,x-1,y);
			  }
		  }
		  
		 // for(int i=0;i<size;i++)
		//	System.out.println(arr[i][1]);
		
		
		
			
		
	}
	public static long[] query(long[][] arr,int l, int r) 
	{  // sum on interval [l, r)
		  long res[] = new long[4];
		  res[0]=-10;
		  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
		    if ((l & 1)!=0)
		    {
		    	if(res[0]==-10)
		    		res = arr[l];
		    	else
		    		res = add(add(res, arr[l]),multiply(res,arr[l]));
		    	l++;
		    }
		    
		    if ((r & 1)!=0)
		    {
		    	r--;
		    	if(res[0]==-10)
		    		res = arr[r];
		    	else
		    		res = add(add(res,arr[r]),multiply(res,arr[r]));
		    }
		  }
		  return res;
		}

	public static void modify(long[][] arr,int p, int value) 
	{  
		  for (arr[p += n] = fib(value); p > 1; p >>= 1)
			  arr[p>>1] = add(add(arr[p],arr[p^1]),multiply(arr[p],arr[p^1]));
		  
	}
	public static long[] fib(long n)
	{
		long F[] = new long[4];
		F[0] = 1;
		F[1] = 1;
		F[2] = 1;
		F[3] = 0;
		
		F = pow(n,F);
		return F;
	}
	public static long[] pow(long n,long[] F)
	{
		if(n==0 || n==1)
			return F;
		long M[] = new long[4];
		
		M[0] = 1;
		M[1] = 1;
		M[2] = 1;
		M[3] = 0;
		 F = pow(n/2,F);
		  F = multiply(F, F);
		 
		  if (n%2 != 0)
		     F = multiply(F, M);
		return F;
		
	}
	public static long[] multiply(long[] F,long[] M)
	{
		long x =  F[0]*M[0] + F[1]*M[2];
		long y =  F[0]*M[1] + F[1]*M[3];
		long z =  F[2]*M[0] + F[3]*M[2];
		long w =  F[2]*M[1] + F[3]*M[3];
		long[] ans = new long[4];
		ans[0] = x%mod;
		ans[1] = y%mod;
		ans[2] = z%mod;
		ans[3] = w%mod;
		return ans;
	}
	public static long[] add(long[] F,long[] M)
	{
		long[] ans = new long[4];
		
		ans[0]=(F[0]+M[0])%mod;
		ans[1]=(F[1]+M[1])%mod;
		ans[2]=(F[2]+M[2])%mod;
		ans[3]=(F[3]+M[3])%mod;
		
		return ans;
	}
}
