import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OBJLoader {

	//Parse the file at loc and use its data to create an Object3D. (Can only parse OBJ files that do not contain extra information)
	public static Object3D load(String loc) throws IOException {
		Object3D out = new Object3D();
		
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		
		String c = "";
		
			 c = new String(Files.readAllBytes(Paths.get(loc)));
		
		
		String[] sc = c.split(System.getProperty("line.separator"));
		
		
		for(String s : sc) {
			if(s.contains("v ")) {
				String tmp = s.substring(s.indexOf("v") + 1);
				String[] tmps = tmp.split(" ");
				
				points.add(new Point3D(((Double.parseDouble(tmps[1]))), ((Double.parseDouble(tmps[2]))), ((Double.parseDouble(tmps[3])))));
			}
		}
		
		for(String s : sc) {

			if(s.contains("f ")) {
				Point3D ap;
				Point3D bp;
				Point3D cp;
				
				String[] f = s.split(" ");
				
				ap = points.get(Integer.parseInt(f[1])-1);
				bp = points.get(Integer.parseInt(f[2])-1);
				cp = points.get(Integer.parseInt(f[3])-1);
				
				out.faces.add(new Face3D(ap.copy(), bp.copy(), cp.copy()));
				
			}
				
		}
		return out;

		}
	}
