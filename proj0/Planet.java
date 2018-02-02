public class Planet {
	public static final double G = 6.67e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
		          double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		/** Calculate the distance between the two planets. */
		double dx, dy;
		dx = this.xxPos - p.xxPos;
		dy = this.yyPos - p.yyPos;
		double sum_of_squares = dx * dx + dy * dy;
		return Math.pow(sum_of_squares, 0.5);
	} 

	public double calcForceExertedBy(Planet p) {
		/** Calculate the force Exerted by the given Planet. */
		double product_of_mass = this.mass * p.mass;
		double r2 = Math.pow(this.calcDistance(p), 2);
		return Planet.G * product_of_mass / r2;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		/** Calculate the net X force exerted by all the given planets. */
		double netX = 0;

		for (Planet p : allPlanets) {
			if (!(this.equals(p))) {
				double force = this.calcForceExertedBy(p);
				double distance = this.calcDistance(p);
				double dx = p.xxPos - this.xxPos;
				
				netX = netX + dx * force / distance;
			}
		}

		return netX;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		/** Calculate the net Y force exerted by all the given planets. */
		double netY = 0;

		for (Planet p : allPlanets) {
			if (!(this.equals(p))) {
				double force = this.calcForceExertedBy(p);
				double distance = this.calcDistance(p);
				double dy = p.yyPos - this.yyPos;
				
				netY = netY + dy * force / distance;
			}
		}

		return netY;
	}

	public void update(double dt, double fX, double fY) {
		/** Update the planet's position and velocity by given conditions. */
		double aX = fX / mass;
		double aY = fY / mass;

		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel; 
	}

	// Draw the planet's image at the Planet's position.
	public void draw() {
		String imagePath = "./images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imagePath);
	}

}

