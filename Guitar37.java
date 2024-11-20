// skeleton version of the class

public class Guitar37 implements Guitar {
    private GuitarString[] strings = new GuitarString[37];
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'";  // keyboard layout

    public Guitar37() {
        for (int i = 0; i < strings.length; i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }
    }

    public void playNote(int pitch) {
        int idx = pitch + 24;
        if (idx < 0 || idx >= strings.length) {
            throw new IllegalArgumentException("Invalid note index: " + pitch);
        }
        strings[idx].pluck();
    }

    public boolean hasString(char string) {
        for (int i = 0; i < KEYBOARD.length(); i++) {
            if (KEYBOARD.charAt(i) == string) {
                return true;
            }
        }
        return false;
    }

    public void pluck(char string) {
        int idx = KEYBOARD.indexOf(string);
        if (idx == -1) {
            throw new IllegalArgumentException("Invalid string: " + string);
        }
        strings[idx].pluck();
    }

    public double sample() {
        double sum = 0.0;
        for (GuitarString string : strings) {
            sum += string.sample();
        }
        return sum;
    }

    public void tic() {
        for (GuitarString string : strings) {
            string.tic();
        }
    }

    public int time() {
        return -1;  // not implemented
    }
}
