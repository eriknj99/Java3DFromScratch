import java.util.ArrayList;

public class Object3D {

	ArrayList<Face3D> faces = new ArrayList<Face3D>();


	//Access all of the points contained in this and call their move methods.
	public void move(double x, double y, double z) {	
		for(int p = 0; p < faces.size(); p++) {
			for(int v = 0; v < faces.get(p).lines.size(); v++) {
				for(int d = 0; d < faces.get(p).lines.get(v).points.size(); d++) {
					faces.get(p).lines.get(v).points.get(d).x += x;
					faces.get(p).lines.get(v).points.get(d).y += y;
					faces.get(p).lines.get(v).points.get(d).z += z;
				}
			}
		}
	}
	//Access all of the points contained in this and call their rotate methods.
	public void rotate(double x, double y, double z, Point3D r) {
		for(int p = 0; p < faces.size(); p++) {
			for(int v = 0; v < faces.get(p).lines.size(); v++) {
				for(int d = 0; d < faces.get(p).lines.get(v).points.size(); d++) {
					faces.get(p).lines.get(v).points.get(d).rotateX(r, Math.toRadians(x));
					faces.get(p).lines.get(v).points.get(d).rotateY(r, Math.toRadians(y));
					faces.get(p).lines.get(v).points.get(d).rotateZ(r, Math.toRadians(z));
	
				}
			}
		}
	}
	//Find average point of all points contained in this.
	public Point3D getCenterOfMass() {
		
		double totX = 0;
		double totY = 0;
		double totZ = 0;
		
		int numPoints = 0;
		
		for(int f = 0; f < faces.size(); f++) {
			for(int c = 0; c < faces.get(f).lines.size(); c++) {
				for(int p = 0; p < faces.get(f).lines.get(c).points.size(); p++) {
					numPoints++;
					totX += faces.get(f).lines.get(c).points.get(p).x;
					totY += faces.get(f).lines.get(c).points.get(p).y;
					totZ += faces.get(f).lines.get(c).points.get(p).z;
	
				}
			}
		}
		
		
		totX /= numPoints;
		totY /= numPoints;
		totZ /= numPoints;
		
		Point3D out = new Point3D(totX,totY,totZ);
		
		return out;
	}
}