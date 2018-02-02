/**
* Build a Simulator
*/
public class NBody {

	// read the num of the planets
	public static int readN(String planetsTxtPath) {
		In in = new In(planetsTxtPath);
		return in.readInt();
	}

	/** Read the Radius of a planet from a TXT. */
	public static double readRadius(String planetsTxtPath) {
		In in = new In(planetsTxtPath);

		in.readInt(); // the first thing for the txt is n
		double radius = in.readDouble(); // the second is the radius

		return radius;
	}

	/** Reads the TXT and returns a array of planets. */
	public static Planet[] readPlanets(String planetsTxtPath) {
		/** Starting read in the file. */
		In in = new In(planetsTxtPath);

		/** read the head of the txt */
		int n = in.readInt();
		in.readDouble();

		/** Create an empty array to hold the result. */
		Planet[] planets = new Planet[n];

		/** now is the part we shall use, 
		* keep looking until the file is empty. */
		for (int i = 0; i < n; i++) {
			/* Each line has pair of location, pair of velocity
			* and mass and finally the filename. */
			double pX = in.readDouble();
			double pY = in.readDouble();
			double vX = in.readDouble();
			double vY = in.readDouble();
			double m = in.readDouble();
			String name = in.readString();

			planets[i] = new Planet(pX, pY, vX, vY, m, name);

		}

		return planets;
	}

	public static void readInfo(String s) {
		In in = new In(s);
		System.out.println("first line: " + in.readInt());
		System.out.println("Second line: " + in.readDouble());

		while (!in.isEmpty()) {
			/* Each line has pair of location, pair of velocity
			* and mass and finally the filename. */
			double pX = in.readDouble();
			double pY = in.readDouble();
			double vX = in.readDouble();
			double vY = in.readDouble();
			double m = in.readDouble();
			String name = in.readString();

			System.out.println("the rest line: " + pX + ' ' + pY + ' ' +
			                    vX + ' ' + vY + ' ' + m + ' ' + name );

			break;
		}

	}

	/**
	* Drawing the initial Universe State
	*/
	public static void main(String[] args) {
		
		/* Collecting All needed input */	
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		int n = readN(filename);
		Planet[] planets = readPlanets(filename);


		// Drawing the Backgroud
		String backgroundImage = "./images/starfield.jpg";
		double r = readRadius(filename);
		double size = 2 * r;

		StdDraw.setScale(-r, r);
		StdDraw.picture(0, 0, backgroundImage, size, size);

		// Drawing all of the planets.
		for (Planet p : planets) {
			p.draw();
		}

		// Creating an Animation.
		StdDraw.enableDoubleBuffering();
		double time = 0;
		while(time < T) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			
			// Calc the net x and y forces for each planet
			for (int i = 0; i < n; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			// Update on each of the planets
			for (int i = 0; i < n; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			// Draw the background image
			StdDraw.picture(0, 0, backgroundImage, size, size);

			// Draw all of the planets again
			for (Planet p : planets) {
				p.draw();
			}

			// Show the offscreen and Pause for 10 milliseconds
			StdDraw.show();
			StdDraw.pause(10);

			// update the time
			time = time + dt;
		}

		// prints out stuff
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}