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
}

