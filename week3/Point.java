import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>{
	private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    
    // constructs the point (x, y)
	public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    //draws the line segment from this point to that point
     public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    //string representation
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    //compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that){
    	if(this.y > that.y) return 1;
    	if(this.y < that.y) return -1;
    	if(this.x > that.x) return 1;
    	if(this.x < that.x) return -1;
    	return 0;
    }

    //the slope between this point and that point
    public double slopeTo(Point that){
    	//return postive zero for horizontal line
    	if(this.y == that.y){
    		if(this.x == that.x) return Double.NEGATIVE_INFINITY;
            return 0.0;
        }
    	else if (this.x == that.x) return Double.POSITIVE_INFINITY;
    	return (double) (that.y - this.y)/(that.x - this.x);
    }

    //compare two points by slopes they make with this point
    // private class slopeOrder implements Comparator<Point>{
    	
    // 	public int compare(Point p1, Point p2){
    // 		double slopep1 = Point.this.slopeTo(p1);
    // 		double slopep2 = Point.this.slopeTo(p2);

    // 		if(slopep1 > slopep2) return 1;
    // 		if(slopep1 < slopep2) return -1;
    // 		return 0; 
    // 	}
    // }

    public Comparator<Point> slopeOrder(){
        return new Comparator<Point>(){
           public int compare(Point p1, Point p2){
               double slopep1 = Point.this.slopeTo(p1);
               double slopep2 = Point.this.slopeTo(p2);

               if(slopep1 > slopep2) return 1;
               if(slopep1 < slopep2) return -1;
               return 0; 
           }
       };
   }

    public static void main(String[] args){
    	Point p1 = new Point (1, 1);
    	Point p2 = new Point (2, 2);
    	Point p3 = new Point (2, 5);
    	System.out.println("compare to p1 p2 " + p1.compareTo(p2) +" \n");
    	System.out.println("compare to p1 p3 " + p1.compareTo(p3) +" \n");
    	System.out.println("slope p1 p2 "+ p1.slopeTo(p2) +" \n");
    	System.out.println("slope p1 p3 "+ p1.slopeTo(p3) +" \n");
    }

}