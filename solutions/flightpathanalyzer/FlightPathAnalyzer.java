package solutions.flightpathanalyzer;

public class FlightPathAnalyzer {

    public static void analyzePath(int[] headings) {
        if (headings == null || headings.length < 2) {
            System.out.println("Not enough data to analyze the path.");
            return;
        }

        for (int i = 0; i < headings.length - 1; i++) {
            double start = headings[i];
            double end = headings[i+1];

            double delta = end - start;

            if (delta > 180) {
                delta -= 360;
            } else if (delta < -180) {
                delta += 360;
            }

            String message = String.format("Step %d (%-2.0f° -> %-2.0f°): ", (i + 1), start, end);

            if (delta > 0) {
                System.out.println(message + "RIGHT turn of " + delta + " degrees.");
            } else if (delta < 0) {
                System.out.println(message + "LEFT turn of " + Math.abs(delta) + " degrees.");
            } else {
                System.out.println(message + "Flying STRAIGHT.");
            }
        }
    }

    public static void main(String[] args) {
        // Example array of flight headings in degrees
        int[] headings = {45, 90, 90, 60, 20, 350, 10, 10};

        System.out.println("Analyzing the airplane's flight path:");
        analyzePath(headings);
    }
}
