package org.example.ernestas.utils;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    public static File chooseFileFromResources() {
        JFileChooser fileChooser = new JFileChooser(new File(""));

        int result = fileChooser.showOpenDialog(null);

        File selectedFile = null;

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return selectedFile;
    }
}
