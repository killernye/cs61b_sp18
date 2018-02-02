public class Triangle {
	public static void main(String[] args) {
		
		/*
		StdDraw.setXscale(0,3);
		StdDraw.setYscale(0,3);
		StdDraw.setPenRadius(0.01);
		
		StdDraw.enableDoubleBuffering();

		StdDraw.circle(1.5, 1.5, 1);
		StdDraw.text(1.5, 1.5, "Hi");
		
		StdDraw.show();
		StdDraw.pause(500);
		StdDraw.clear(StdDraw.LIGHT_GRAY);
		StdDraw.filledSquare(1.5, 1.5, 1);
		StdDraw.show();
		StdDraw.pause(500);

		while(true) {
			// mouse click
			if (StdDraw.isMousePressed()) StdDraw.setPenColor(StdDraw.CYAN);
			else	StdDraw.setPenColor(StdDraw.BLUE);

			//mouse location
			StdDraw.clear();
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			StdDraw.filledCircle(x, y, 0.05);
			StdDraw.show();
			StdDraw.pause(100);
		}
		
		StdDraw.square(0.5, 0.5, 0.25);
		StdDraw.pause(1000);
		StdDraw.clear();
		StdDraw.setScale(-0.2, 1.2);
		StdDraw.square(0.5, 0.5, 0.25);
		*/
		String image = "./images/starfield.jpg";
		StdDraw.setScale(-100, 100);
		StdDraw.picture(0, 0, image, 100, 100);
	}
}