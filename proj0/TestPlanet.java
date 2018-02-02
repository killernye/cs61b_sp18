/**
* test pairwise force
*/
public class TestPlanet {
	/** Creates two planets and prints out the pairwise force between them. */
	public static void main(String[] args) {
		checkPairForce();
	}


	/**
	* Check whether or not two doubles are equal and prints the result.
	*
	* @param  actual	double received
	* @param  expected  expected double
	* @param  label 	lable for the test case
	* @param  eps		tolerance for the double comparison.
	*/
	private static void checkEquals(double actual, double expected, String label, double eps) {
		if (Math.abs(actual - expected) <= eps * Math.max(actual, expected)) {
			System.out.println("Pass: " + label + ": expected " + expected + " and you gave " + actual);
		} else {
			System.out.println("Fail: " + label + ": expected " + expected + " and you gave " + actual); 
		}
	}

	private static void checkPairForce() {
		System.out.println("Checking pairwise force between two planets...");
		
		Planet p = new Planet(0, 0, 100, 100, 100000, "first");
		Planet p2 = new Planet(1000, 1000, -50, -50, 5000000, "second");
		double force = p.calcForceExertedBy(p2);
		checkEquals(force, 1.6676E-5, "TestPlanet", 0.01);
	}
}