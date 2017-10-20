import java.util.*;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private int size = 0;
	private Node root = null;

	private static class Node {
		private Point2D p;
		private RectHV rec;
		private Node lb;
		private Node rt;
		private boolean vertical = true; //check split with x or y; true split with x; false y;

		private Node(Point2D p, RectHV rec, boolean vertical){
			this.p = p;
			this.rec = rec;
			lb = null;
			rt = null;
			this.vertical = vertical;
		}
	}

	//construct an empty set of points
	public KdTree() {

	}

	//is the set empty?
	public boolean isEmpty() {
		return (size == 0);
	}

	//number of points in the set
	public int size() {
		return size;
	}

	//add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new java.lang.NullPointerException();
		// double minX = 0.0;
		// double minY = 0.0;
		// double maxX = 1.0;
		// double maxY = 1.0;
		root = put(root, p, 0.0, 0.0, 1.0, 1.0, true);
	}

	private Node put(Node aNode, Point2D p, double minx, double miny, double maxx, double maxy, boolean vertical) {
		if (aNode == null) {
			RectHV rec = new RectHV(minx, miny, maxx, maxy);
			size ++;
			return new Node(p, rec, vertical);
		}
		int cmp = compare(p, aNode.p);

		if(cmp < 0){
			if(aNode.vertical == true) {
				vertical = false;
				double minX = aNode.rec.xmin();
				double maxX = aNode.p.x();
				double minY = aNode.rec.ymin();
				double maxY = aNode.rec.ymax();
				aNode.lb = put(aNode.lb, p, minX, minY, maxX, maxY, vertical);
			}
			if(aNode.vertical == false) {
				if(aNode.lb == null || aNode.p.y() > p.y()) {
					vertical = true;
					double minX = aNode.rec.xmin();
					double maxX = aNode.rec.xmax();
					double minY = aNode.rec.ymin();
					double maxY = aNode.p.y();
					aNode.lb = put(aNode.lb, p, minX, minY, maxX, maxY, vertical);
				}
				else {
					vertical = true;
					double minX = aNode.rec.xmin();
					double maxX = aNode.rec.xmax();
					double minY = aNode.p.y();
					double maxY = aNode.rec.ymax();
					aNode.rt = put(aNode.rt, p, minX, minY, maxX, maxY, vertical);
				}

			}
		}

		if(cmp > 0){
			if(aNode.vertical == true) {
				vertical = false;
				double minX = aNode.p.x();
				double maxX = aNode.rec.xmax();
				double minY = aNode.rec.ymin();
				double maxY = aNode.rec.ymax();
				aNode.rt = put(aNode.rt, p, minX, minY, maxX, maxY, vertical);
			}
			if(aNode.vertical == false) {
				if(aNode.rt == null || aNode.p.y() < p.y()){
					vertical = true;
					double minX = aNode.rec.xmin();
					double maxX = aNode.rec.xmax();
					double minY = aNode.p.y();
					double maxY = aNode.rec.ymax();
					aNode.rt = put(aNode.rt, p, minX, minY, maxX, maxY, vertical);
				}
				else {
					vertical = true;
					double minX = aNode.rec.xmin();
					double maxX = aNode.rec.xmax();
					double minY = aNode.rec.ymin();
					double maxY = aNode.p.y();
					aNode.lb = put(aNode.lb, p, minX, minY, maxX, maxY, vertical);
				}
			}
		}
		return aNode;
	}

	private int compare(Point2D p, Point2D q) {
		if (p.x() < q.x()) return -1;
        if (p.x() > q.x()) return +1;
        if (p.y() < q.y()) return -1;
        if (p.y() > q.y()) return +1;
        return 0;
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) throw new java.lang.NullPointerException();
		Node aNode = root;
		//if(aNode == null) return false;
		//int cmp = p.compareTo(aNode.p);

		while(aNode != null) {
			int cmp = p.compareTo(aNode.p);
			if (cmp < 0) aNode = aNode.lb;
			else if (cmp > 0) aNode = aNode.rt;
			else return true;
		}
		return false;
	}

	 // draw all points to standard draw
	public void draw() {
		// StdDraw.setPenColor(StdDraw.BLACK);
		// StdDraw.square(0.5, 0.5, 0.5);

		draw(root);
	}

	private void draw(Node aNode) {
		// if (aNode == null) throw new java.lang.NullPointerException();
		if (aNode != null) {
			StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(aNode.p.x(),aNode.p.y());
            if(aNode.vertical) {
            	StdDraw.setPenColor(StdDraw.RED);
            	StdDraw.line(aNode.p.x(), aNode.rec.ymin(), aNode.p.x(), aNode.rec.ymax());
            }
            else {
            	StdDraw.setPenColor(StdDraw.BLUE);
            	StdDraw.line(aNode.rec.xmin(), aNode.p.y(), aNode.rec.xmax(), aNode.p.y());
            }
            draw(aNode.lb);
            draw(aNode.rt);
		}
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rec) {
		if (rec == null) throw new java.lang.NullPointerException();

		//if (root == null)
		// SET<Point2D> range = searchRange(root, rec);
		SET<Point2D> aRange = new SET<Point2D>();
		aRange = searchRange(root, rec, aRange);
		return aRange;
	}

	private SET<Point2D> searchRange(Node aNode, RectHV rec, SET<Point2D> aRange) {
		if (aNode == null) return aRange;
		if (rec.contains(aNode.p)) aRange.add(aNode.p);
		if (rec.intersects(aNode.rec)) {
			searchRange(aNode.lb, rec, aRange);
			searchRange(aNode.rt, rec, aRange);
		}
		return aRange;
	}


	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new java.lang.NullPointerException();
		if (root == null) return null;
		Point2D nearest = searchNearest(root, p, root.p);
		return nearest;
	}

	private Point2D searchNearest(Node aNode, Point2D p, Point2D nearest) {

		
		if (aNode == null) return nearest;
		if (p.distanceTo(aNode.p) < p.distanceTo(nearest)) nearest = aNode.p;
		if (aNode.rec.distanceSquaredTo(p) < p.distanceSquaredTo(nearest)) {
			nearest = searchNearest(aNode.lb, p, nearest);
			nearest = searchNearest(aNode.rt, p, nearest);
		}
		// if (aNode.rec.contains(p)) {
		// 	searchNearest(aNode.lb, p);
		// 	searchNearest(aNode.rt, p);
		// }
		return nearest;
	}

	public static void main(String[] args) {
		Point2D p1 = new Point2D(0.0, 1.0);
		Point2D p2 = new Point2D(0.0, 0.0);
		Point2D p3 = new Point2D(1.0, 0.0);
		// Point2D p4 = new Point2D(0.4, 0.7);
		// Point2D p5 = new Point2D(0.9, 0.6);
		// Point2D p9 = new Point2D(0.8, 0.7);
		// Point2D p10 = new Point2D(0.95, 0.4);


		// Point2D p6 = new Point2D(0.7, 0.1);
		// Point2D p7 = new Point2D(0.7, 0.2);

		KdTree t = new KdTree();
		

		t.insert(p1);	
		t.insert(p2);
		t.insert(p3);
		t.insert(new Point2D(0.0, 1.0));
		// t.insert(p4);
		// t.insert(p5);
		// t.insert(p9);
		// t.insert(p10);
		//t.draw();
		//System.out.println(t.size());
		// RectHV rec = new RectHV (0.1, 0.2, 0.6, 0.6);

		// for(Point2D p : t.range(rec)) {
		// 	System.out.println("in range");
		// 	System.out.println(p.toString());
		// }


		// Point2D p8 = new Point2D(0.1, 0.1);
		// Point2D nearest = t.nearest(p8);
		// System.out.println(nearest.toString());
		System.out.println(t.size());
		
		//System.out.println(t.contains(p7));
	}

}