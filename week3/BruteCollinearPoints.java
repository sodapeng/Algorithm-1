import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints{
	private LineSegment[] segments;
	private int pointnum;

	//finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points){
		pointnum = points.length; //difference array and arraylist
		ArrayList<LineSegment> foundsegments = new ArrayList<LineSegment>();
		Point copypoints[] = new Point[pointnum]; 

		//check null points
		for (int i = 0; i < pointnum; i++){
			if (points[i] == null)
				throw new NullPointerException();
			copypoints[i] = points[i];
		}

		checkRepeatedPoint(points);

		Arrays.sort(copypoints);

		// System.out.println("Sorted");
		// for (Point p : copypoints) {
		// 	System.out.println(p.toString());
		// }

		for(int p = 0; p < pointnum; p++){
			for(int q = p+1; q < pointnum; q++){
				for(int r = q+1; r < pointnum; r++){
					for(int s = r+1; s < pointnum; s++){
						if (
							(copypoints[p].slopeTo(copypoints[q]) == copypoints[p].slopeTo(copypoints[r])) &&
							(copypoints[p].slopeTo(copypoints[q]) == copypoints[p].slopeTo(copypoints[s]))
							)
						{
							// LineSegment test1 = new LineSegment(copypoints[p], copypoints[q]);
							// LineSegment test2 = new LineSegment(copypoints[p], copypoints[r]);
							// LineSegment test3 = new LineSegment(copypoints[p], copypoints[s]);
							// System.out.println(test1.toString());
							// System.out.println(test2.toString());
							// System.out.println(test3.toString());
							foundsegments.add(new LineSegment(copypoints[p],copypoints[s]));
						}
					}
				}
			}
		}
		segments = foundsegments.toArray(new LineSegment[foundsegments.size()]);
	}

	//the number of line segments
	public int numberOfSegments(){
		return segments.length;
	}

	//the line segments
	public LineSegment[] segments(){
		return segments;
	}

    //check repeated points
	private void checkRepeatedPoint(Point[] points){
		for(int i = 0; i < pointnum-1; i++){
			for(int j = i+1; j< pointnum; j++){
				if (points[i].compareTo(points[j]) ==0)
					throw new java.lang.IllegalArgumentException();
			}
		}
	}

	//test unit

	public static void main(String[] args){
		Point points[] = new Point[4];
		points[3] = new Point (3000, 4000);
		points[2] = new Point (6000, 7000);
		points[1] = new Point (14000, 15000);
		points[0] = new Point (20000, 21000);
		BruteCollinearPoints pointlist = new BruteCollinearPoints(points);
		System.out.println(pointlist.numberOfSegments());

	}
}