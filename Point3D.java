public class Point3D {

	double x;
	double y;
	double z;
	
	
	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	//Returns a new point containing the same data but with no references to this point
	public Point3D copy() {
		return new Point3D(x,y,z);
	}
	//Rotate this point, q degrees around the point p on the Z axis
	public void rotateZ(Point3D p, double q) {
		
		z-=p.z;
		x-=p.x;
		y-=p.y;		
		

		double xp = x;
		double yp = y;
		double zp = z;
		
		xp = ((x * Math.cos(q)) - (y * Math.sin(q)));
		yp = ((x * Math.sin(q)) + (y * Math.cos(q)));
		
		
		zp+=p.z;
		xp+=p.x;
		yp+=p.y;
		
	    this.x = xp;
	    this.y = yp;
	    this.z = zp;

	}
	//Rotate this point, q degrees around the point p on the X axis

	public void rotateX(Point3D p, double q) {

		z-=p.z;
		x-=p.x;
		y-=p.y;
		
		double xp = x;
		double yp = y;
		double zp = z;
		
		yp = ((y * Math.cos(q)) - (z * Math.sin(q)));
		zp = ((y * Math.sin(q)) + (z * Math.cos(q)));
		
		
		zp+=p.z;
		xp+=p.x;
		yp+=p.y;
		
		  this.x = xp;
		  this.y = yp;
		  this.z = zp;		
	}
	
	//Rotate this point, q degrees around the point p on the Y axis
	public void rotateY(Point3D p, double q) {
		
		z-=p.z;
		x-=p.x;
		y-=p.y;
		
		double xp = x;
		double yp = y;
		double zp = z;

		zp = ((z * Math.cos(q)) - (x * Math.sin(q)));
		xp = ((z * Math.sin(q)) + (x * Math.cos(q)));
		
		zp+=p.z;
		xp+=p.x;
		yp+=p.y;
		
		    this.x = xp;
		    this.y = yp;
		    this.z = zp;
	}
	
	
	
}
