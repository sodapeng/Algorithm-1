import java.util.*;

public class FastCollinearPoints{
	private List<LineSegment> segments = new ArrayList<LineSegment>();
	private HashMap<Double, List<Point>> foundSegments = new HashMap<Double, List<Point>>();
	private int pointnum;

	//finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points){
		int pointnum = points.length;
		Point[] copypoints = new Point[pointnum]; 
		List<Point> slopepoints = new ArrayList<>();

		//check null points
		for (int i = 0; i < pointnum; i++){
			if (points[i] == null)
				throw new NullPointerException();
			copypoints[i] = points[i];
		}

		checkRepeatedPoint(points);

		for(int i = 0; i < pointnum; i++){
			//sort copypoint array with slope order
			Arrays.sort(copypoints, points[i].slopeOrder());
			
			// for (int k = 0; k < pointnum; k++) {
			// 	System.out.println(copypoints[k].toString());
			// }
			// System.out.println();

			//initiate new slopepoints store points with same slope
			slopepoints = new ArrayList<>();
			double slope = 0;
			double preslope = Double.NEGATIVE_INFINITY;

			for(int j = 0; j < pointnum; j++) {
				slope = points[i].slopeTo(copypoints[j]);
				if(slope == preslope){
					slopepoints.add(copypoints[j]);
					// System.out.println("equal");
					// System.out.println(copypoints[j].toString());
					// System.out.println(points[i].toString());
					// System.out.println();
				}
				else{
					// System.out.println("not equal");
					// System.out.println(copypoints[j].toString());
					// System.out.println(points[i].toString());
					// System.out.println();
					if(slopepoints.size() >= 3){
						slopepoints.add(points[i]);
						checkIfNew(slopepoints, preslope);
					}
					slopepoints.clear();
					slopepoints.add(copypoints[j]);
					preslope = slope;
				}
			}
			//for the last point case, last several points have same slope
			if(slopepoints.size() >= 3){
				slopepoints.add(points[i]);
				checkIfNew(slopepoints, slope);
			}
		}
	}

	//check if segments already exist in segments
	private void checkIfNew(List<Point> slopepoints, Double slope){
		Collections.sort(slopepoints);
		Point startPoint = slopepoints.get(0);
		Point endPoint = slopepoints.get(slopepoints.size()-1);

		List<Point> existlist= foundSegments.get(slope);

		//check different line segment with same slope
		if(existlist == null) {
			List<Point> newlist = new ArrayList<Point>();
			newlist.add(endPoint);
			foundSegments.put(slope, newlist);
			segments.add(new LineSegment(startPoint,endPoint));
		}
		else {
			for(Point p: existlist) {
				if(p.compareTo(endPoint) == 0) return;
			}
			segments.add(new LineSegment(startPoint,endPoint));
			existlist.add(endPoint);
		}
			

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

	//the number of line segments
	public int numberOfSegments(){
		return segments.size();
	}

	//the line segments
	public LineSegment[] segments(){
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args){
		Point[] pointlist = new Point[9];
		pointlist[0] = new Point(1,2);
		pointlist[1] = new Point(2,3);
		pointlist[2] = new Point(4,5);
		pointlist[3] = new Point(3,4);
		pointlist[4] = new Point(1,1);
		pointlist[5] = new Point(2,2);
		pointlist[6] = new Point(3,3);
		pointlist[7] = new Point(4,12);
		pointlist[8] = new Point(5,5);

		FastCollinearPoints fasttest = new FastCollinearPoints(pointlist);
		System.out.println(fasttest.numberOfSegments());
		LineSegment[] test1 = fasttest.segments(); {}
		System.out.println("final segment");
		for (LineSegment s : test1) {
			System.out.println(s.toString());
		}
	}
}
