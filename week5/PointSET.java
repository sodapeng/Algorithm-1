import java.util.*;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private SET<Point2D> pointset;
	//construct an empty set of points
	public PointSET() {
		pointset = new SET<Point2D>();
	}

	//is the set empty?
	public boolean isEmpty() {
		return pointset.isEmpty();
	}

	//number of points in the set
	public int size() {
		return pointset.size();
	}

	//add the point to the set(if it is not already in the set)
	public void insert(Point2D p) {
		pointset.add(p);
	}

	//does the set contain point p? 
	public boolean contains(Point2D p) {
		if(p == null) throw new java.lang.NullPointerException();
		return pointset.contains(p);
	}

	// draw all points to standard draw 
	public void draw() {
		for (Point2D p : pointset) {
			p.draw();
		}
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new java.lang.NullPointerException();
		SET<Point2D> range = new SET<Point2D>();
		for (Point2D p : pointset) {
			if(rect.contains(p)) range.add(p);
		}
		return range;
	}

	//a nearest neighbor in the set to p; null if set it empty
	public Point2D nearest(Point2D p) {
		if(p == null) throw new java.lang.NullPointerException();
		Point2D nearest = pointset.min();
		for (Point2D q : pointset) {
			if(p.distanceTo(q) < p.distanceTo(nearest)) nearest = q;
		}
		return nearest;
	}

	public static void main(String[] agrs) {
		Point2D p1 = new Point2D(0.7, 0.2);
		Point2D p2 = new Point2D(0.5, 0.4);
		Point2D p3 = new Point2D(0.2, 0.3);
		Point2D p4 = new Point2D(0.4, 0.7);
		Point2D p5 = new Point2D(0.9, 0.6);
		Point2D p6 = new Point2D(0.7, 0.1);
		Point2D p7 = new Point2D(0.7, 0.2);
		PointSET s = new PointSET();

		s.insert(p1);
		s.insert(p2);
		s.insert(p3);
		s.insert(p4);
		s.insert(p5);

		// System.out.println(s.contains(p6));
		// System.out.println(s.contains(p7));
		//System.out.println(s.size());
		RectHV rec = new RectHV (0.1, 0.2, 0.6, 0.6);

		for(Point2D p : s.range(rec)) {
			System.out.println(p.toString());
		}
		Point2D p8 = new Point2D(0.1, 0.1);
		Point2D nearest = s.nearest(p8);
		System.out.println(nearest.toString());

	}

}