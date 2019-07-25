import java.util.ArrayList;

public class Line3D {

	ArrayList<Point3D> points = new ArrayList<Point3D>();
	
		public Line3D(Point3D a, Point3D b) {
			points.add(a);
			points.add(b);
		}
}
