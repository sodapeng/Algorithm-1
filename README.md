## Projects for Algorithm-1

#### This is course projects for Coursera online course: Algorithm-1
#### course website: https://www.coursera.org/learn/algorithms-part1

#### Week 1 - UnionFind / Percolation
Implemented a java program to estimate the value of the percolation threshold via Monte Carlo simulation.
* Percolation.java - A model for the percolation problem, determines if a 2d system of open / closed sites percolates from top to bottom.
* PercolationStats.java - Generates statistics using the percolation model.
#### Week 2 - Queues, Stacks and Bags
Implemented a generic data type for a deque and a randomized queue.
* Deque.java - A generic double ended queue implementation, double-linked list based.
* RandomizedQueue.java - A generic random queue implementation, array based.
* Subset.java - Prints n number of random strings provided through standard input.
#### Week 3 - Collinear Points
Implemented a program to recognize line patterns in a given set of points. Given a set of N distinct points in the plane, it could draw every (maximal) line segment that connects a subset of 4 or more of the points.
* Point.java - A simple point class.
* Brute.java - A n^4 algorithm for calculating 4 collinear points.
* Fast.java - A fast implementation for finding collinear points.
#### Week 4 - 8 Puzzle
Implemented a program to solve 8-puzzle problem. The 8-puzzle problem is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square. Implemented program is able to rearrange the blocks so that they are in order, using least number of steps
* Board.java - Represents a sliding puzzle board.
* Solver.java - Uses A* and to find solution to the puzzle board.
#### Week 5 - KdTree
Implemented a data type to represent a set of points in the unit square (all points have x- and y-coordinates between 0 and 1) using a 2d-tree to support efficient range search (find all of the points contained in a query rectangle) and nearest neighbor search (find a closest point to a query point). 2d-trees have numerous applications, ranging from classifying astronomical objects to computer animation to speeding up neural networks to mining data to image retrieval.
* PointSET.java - A set of points on a 2D Euclidian plane, some simple function like nearest neighbor search and range search using a brute force approach.
* KdTree.java - Uses a 2d tree to more efficiently perform functions such as nearest neighbor search and range search.
