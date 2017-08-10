
import java.util.*;

class Grid{
     int[] x_coordinate=new int[3];
    int[] y_coordinate=new int[3];
}

 class GameSetter{
    String[][] grid =new String [7][7];
    int no_of_ship=3;
    Grid[] ship=new Grid[no_of_ship] ;
    boolean flag=true;
    int count;
    boolean isPresent(Grid a){        // this is the modified version , == wasnt working nor equals . equals was showing some sort of error
      for(int j=0;j<3;j++){
       if(ship[j]!=null){
          for(int m=0;m<3;m++){
            for(int i=0;i<3;i++){
               if(ship[j].x_coordinate[i]==a.x_coordinate[m]&&ship[j].y_coordinate[i]==a.y_coordinate[m])
                        return true;
            }
          }
        }
      }
      return false;
    }

    public void setShips(){
      int i=0;
     while(count<3){
         ship[i]=new Grid();
         flag=true; 
         int x_pos,y_pos;
          x_pos= (int)(Math.random()*7);
           y_pos= (int)(Math.random()*7);
           
          int align=(int)(Math.random()*2); //0 for vertical alignment of ships on grid
           System.out.println(x_pos+"  "+y_pos+"  "+align);             //1 for horizontal alignment of ship on grid

          
          if((y_pos>4)&&(align==0)){
              Grid a=new Grid();
              for(int j=0;j<3;j++){                              
                 a.x_coordinate[j]=x_pos;
                 a.y_coordinate[j]=y_pos--;
                 
              }
              if (isPresent(a)){
                     flag=false;
              }
            if(flag){
                  ship[i]=a;
                  count++;
                  i++;
              }
          }
           else if((x_pos>4)&&(align==1)){
              
              Grid a=new Grid();
              for(int j=0;j<3;j++){        
                  a.x_coordinate[j]=x_pos--;
                 a.y_coordinate[j]=y_pos;     
              }
              if(isPresent(a))
                    flag=false;
               if(flag){
                  ship[i]=a;
                  count++;
                  i++;
              }
          }
          
            else{
              Grid a=new Grid(); 
              if(align==1){
                       
                for(int j=0;j<3;j++){
                   a.x_coordinate[j]=x_pos++;
                   a.y_coordinate[j]=y_pos;  
                  
                } 
              }
             if(align==0){
                       
                for(int j=0;j<3;j++){
                   a.x_coordinate[j]=x_pos;
                   a.y_coordinate[j]=y_pos++;   
                   
                } 
              }
               if(isPresent(a))
                    flag=false; 
              if(flag){
                  ship[i]=a;
                  count++;
                  i++;
              }
          }

        }//end of for loop
    }//end of setships

    public void display(){      // used for displaying the final grid in its form
       	ship_marker_on_grid();
        System.out.print("\t\t");
        for(int i=0;i<7;i++){
          System.out.print(" _");
        }
        for(int i=0;i<7;i++){
          System.out.print("\n\t\t|");
          for(int j=0;j<7;j++){         
            if (grid[j][i]==null) {     //j moves horizontally hence a representation of x axis
                grid[j][i]="_";         //i similarly moves line wise , hence y axis
            }
            System.out.print(grid[j][i]+"|");
          }
        }
         System.out.println("\n");
    }//end of display class
    void ship_marker_on_grid(){
    	for(int g=0;g<7;g++){
    		for(int h=0;h<7;h++){
    			grid[g][h]=null;
    		}
    	}
    	 for(int i=0;i<3;i++){
    	 	for(int j=0;j<3;j++){
    	 		grid[ship[i].x_coordinate[j]][ship[i].y_coordinate[j]]="X";
    	 	}
    	 }
    }
}//end of class gamesetter


class Artillery extends GameSetter{
    int y_coordinate,x_coordinate,fire_count=0,count=0;
    void damage(){    // to access the damage done to each ship and keep a track
        boolean flag=false;//default value is false
        //default_value(); it was required when we were using merely static ships
        while(count<9&&(x_coordinate!=-1&&y_coordinate!=-1)){
            flag=false;
            display();
            fire();
            movement();
            for(int i=0;i<no_of_ship;i++){
              if(ship[i]!=null){
                for(int j=0;j<3;j++){
                  if(ship[i].x_coordinate[j]==x_coordinate&&ship[i].y_coordinate[j]==y_coordinate){

                      System.out.println("\twe have a hit !!!");
                      flag=true;
                      count++; 
                      break;                
                   }
                }
                if(flag)
                  break;
              }
            }
            if(!flag){
              grid[x_coordinate][y_coordinate]="_";
              System.out.println("\tMISS!!! \n\tRELOADING");
            }
        }
        System.out.println("\tTotal shots fired :"+fire_count+"\t\tShots on target:"+count);
    }
   /* int[] already_entered_x=new int[49];
      int[] already_entered_y=new int[49];
      static int g=0;
      void default_value(){							//for registerring already hit coordinates, dont need them when i make them moving
        for(int i=0;i<49;i++){
           already_entered_y[i]=9;
            already_entered_x[i]=9;
        }
      }
      */
    void fire(){
    	fire_count++;
      System.out.println("Enter the location for shelling : ");
      Scanner sc=new Scanner(System.in);
      boolean flag=false;
      do{System.out.println("Enter x   y coordinate ");
          flag=false;
         x_coordinate=sc.nextInt();
         y_coordinate=sc.nextInt();
         if(!(x_coordinate>=0&&x_coordinate<7||x_coordinate==-1)||!(y_coordinate>=0&&y_coordinate<7||y_coordinate==-1)){
          System.out.println("Out of Range !!");
          flag=true;
         }
         if(x_coordinate==-1||y_coordinate==-1){
         	  fire_count--;
         	 System.out.println("\tTotal shots fired :"+fire_count+"\t\tShots on target:"+count);
         	 System.exit(0);
         }
         //for registerring already hit coordinates, dont need them when i make them moving

        /* else{
         	 boolean flag_internal=true;
      		 for(int i=0;i<49;i++){
              if(x_coordinate==already_entered_x[i]&&y_coordinate==already_entered_y[i]){
                flag_internal=false;
                flag=true;
                System.out.println("\tWe have already registered a strike on this location");
              }
          }
          if(flag_internal){
           already_entered_x[g]=x_coordinate;
           already_entered_y[g++]=y_coordinate;
          }
         }*/
      }while(flag);
  
    }

	void movement(){
  		for(int i=0;i<3;i++){
			int a=(int)(Math.random()*2);//1 is for vertical movement along y axis
									 //0 is for horizontal movement along x axis
			if(a==1){// code for vertical movement
				int b=(int)(Math.random()*2);//1 is for moving up and 0 is for moving down
				boolean flag=true;
				if(b==1){
				
					for(int j=0;j<3;j++){
						if(ship[i].y_coordinate[j]==6)
							flag=false;
					}
			
					if(flag){
						for(int j=0;j<3;j++){
							ship[i].y_coordinate[j]++;
						}
					}
					else{
						for(int j=0;j<3;j++){
							ship[i].y_coordinate[j]--;
						}
					}
				}
				else{	//b will be equal to 0 here
			
					for(int j=0;j<3;j++){
						if(ship[i].y_coordinate[j]==0)
							flag=false;
					}
					if(flag){
						for(int j=0;j<3;j++){
							ship[i].y_coordinate[j]--;
						}
					}
					else{
						for(int j=0;j<3;j++){
							ship[i].y_coordinate[j]++;
						}
					}
				}
			}
			else{
				int c=(int)(Math.random()*2);//1 is for moving right and 0 is for moving left
				boolean flag=true;
				if(c==1){		// code for moving right
					for(int j=0;j<3;j++){
						if(ship[i].x_coordinate[j]==6)
							flag=false;
					}

					if(flag){
						for(int j=0;j<3;j++){
							ship[i].x_coordinate[j]++;
						}
					}
					else{
						for(int j=0;j<3;j++){
							ship[i].x_coordinate[j]--;
						}
					}
				}
				else{	//b will be equal to 0 here
			
					for(int j=0;j<3;j++){
						if(ship[i].x_coordinate[j]==0)
							flag=false;
					}
					if(flag){
					
						for(int j=0;j<3;j++){
							ship[i].x_coordinate[j]--;
						}
					}
					else{
						for(int j=0;j<3;j++){
							ship[i].x_coordinate[j]++;
						}
					}
				}
			}
  		}
	}
}
class introduction{
	int a;
	introduction(){
		System.out.println("\t\t\t\t\t\t\t\tBATTLESHIP");
		System.out.println("\n\nYou are the commander of the battleship INS Swaraj\nyou are in open waters and there is intel about three enemy"+
			" scorpene classsubmarines approaching you. Luckily the ship features a state of the art sonar mapping technology and also have powerful"+
			" torpedoes ready to be fired.There a slight problem , as sonar waves need time to travel to and fro, hence u cannot have real time data"+
			" and scorpene submarines are quite fast so u need to take care of the time lag in data.All set to to take charge ? Press 1 ");
		Scanner sc =new Scanner(System.in);
		a=sc.nextInt();
	}
	boolean start(){
		if(a==1)
			return true;
		else
			return false;
	}
}

public class Battleship {
    public static void main(String[] args) {
       introduction q=new introduction();
       if(q.start()){
			Artillery s=new Artillery();
       		s.setShips();
      		Grid c= new Grid();
       		for(int i=0;i<3;i++){
            	for(int j=0;j<3;j++)
                	System.out.println(s.ship[i].x_coordinate[j]+"  "+s.ship[i].y_coordinate[j]);
       		}
          	s.damage();
    	}
    }
}
