import java.util.ArrayList;

public class Face3D {

	ArrayList<Line3D> lines = new ArrayList<Line3D>();
	
	Face3D(Point3D a, Point3D b, Point3D c){
		lines.add(new Line3D(a,b));
		lines.add(new Line3D(b,c));
		lines.add(new Line3D(c,a));
	}
}
