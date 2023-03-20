package start;

import presentationLayer.MainGUI;
import tools.Serializer;


public class Main {
    public static void main(String[] args) {
        Serializer.init();
        new MainGUI();
    }
}

