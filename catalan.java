class catalan{
	 int sum=0,i=0;
	 int catalancalc(int n){
	 	sum=0;
    	if(n<=1)
        	return 1;
        for(int i=0;i<n;i++)
    		sum+=catalancalc(n-1-i)*catalancalc(i);
    	
   		return sum;
	}

	public static void main(String[] args){

  	  	System.out.println(new catalan().catalancalc(5));
 		
	}
}