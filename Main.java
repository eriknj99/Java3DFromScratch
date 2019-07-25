import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("WELCOME!\n\n\n");
		System.out.println("\tHOW TO CREATE A READABLE OBJ FILE:\n\t1. Install Blender\n\t2. Import or create a 3D object.\n\t3. Select File>Export>Wavefront (obj)>\n\t4. Select ONLY the folowing boxes on the Left hand panel:\n\t\t\"Include Edges\"\n\t\t\"Triangulate Faces\"\n\t5.Click \"Export\"");
		System.out.println("\n\n\nEnter the name of the 3D file you would like to import (Example: lightbulb.obj): ");
		Scanner sc = new Scanner(System.in);
		String file = sc.nextLine();
        if(file.equals("")){
            file = "lightbulb.obj";
        }
        GUI g = null;

        try {
			g = new GUI(file);
		} catch (IOException e) {
			System.out.println("ERROR: File \"" + file + "\" not found.");
			System.exit(1);
		}    
		
        System.out.println("Use W, A, S, D, SPACE, SHIFT to pan and zoom, Use I, K, J, L, U, O to rotate");

        
	}

}
