package org.example.ernestas.user_interface;


import org.example.ernestas.model.Student;
import org.example.ernestas.model.comparators.GradeComparator;
import org.example.ernestas.sorters.BubbleSort;
import org.example.ernestas.sorters.HeapSort;
import org.example.ernestas.sorters.MergeSort;
import org.example.ernestas.utils.FileChooser;
import org.example.ernestas.utils.StudentsParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class SortingAlgorithmChooserUI extends JFrame implements ActionListener {
    private transient List<Student> students;
    private static final long serialVersionUID = 1L;
    private final JComboBox<String> algorithmComboBox;
    private final JLabel resultTimerLabel;
    private final JLabel selectedFileLabel;
    private final JCheckBox ascendingCheckbox;
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Grade"}, 0);
    private final JTable table;

    public SortingAlgorithmChooserUI() {

        super("Sorting Algorithm ");
        setLayout(new FlowLayout());

        JButton fileSelectorButton = new JButton("Select file:");
        String fileSelectedString = "No file selected";
        selectedFileLabel = new JLabel(fileSelectedString);
        fileSelectorButton.addActionListener(this);
        add(fileSelectorButton);
        add(selectedFileLabel);

        JLabel ascendingCheckboxLabel = new JLabel("Ascending: ");
        ascendingCheckbox = new JCheckBox();
        add(ascendingCheckboxLabel);
        add(ascendingCheckbox);
        algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Merge Sort", "Heap Sort"});
        add(algorithmComboBox);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        add(sortButton);

        JLabel resultLabel = new JLabel("time: ");
        resultTimerLabel = new JLabel();
        add(resultLabel);
        add(resultTimerLabel);

        table = new JTable(new DefaultTableModel(new Object[]{"Name", "Grade"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setResizable(false);
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Select file:")) {
            File file = FileChooser.chooseFileFromResources();
            selectedFileLabel.setText(file.getName());
            students = StudentsParser.parseStudents(file);
        } else if (e.getActionCommand().equals("Sort")) {
            if (students != null && !students.isEmpty()) {
                useSortingAlgorithm();
                writeResults();
            }
        }
    }

    private void useSortingAlgorithm() {
        long startTime = System.currentTimeMillis();
        switch (Objects.requireNonNull((String) algorithmComboBox.getSelectedItem())) {
            case "Bubble Sort":
                BubbleSort.bubbleSort(students, new GradeComparator(ascendingCheckbox.isSelected()));
                break;
            case "Merge Sort":
                MergeSort.mergeSort(students, new GradeComparator(ascendingCheckbox.isSelected()));
                break;
            case "Heap Sort":
                HeapSort.heapSort(students, new GradeComparator(ascendingCheckbox.isSelected()));
                break;
        }
        long endTime = System.currentTimeMillis();
        double timeInSeconds = (endTime - startTime) / 1000.0;
        resultTimerLabel.setText(Double.toString(timeInSeconds));
    }

    private void writeResults() {
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{s.getName(), s.getGrade()});
        }
        table.setModel(model);
    }

}
