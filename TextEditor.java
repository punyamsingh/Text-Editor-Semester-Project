import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextEditor implements ActionListener {

    public static int startindex;
    static String jFileName = "D:\\New_Unnamed.txt";
    public JFrame jFrame;
    int endindex;
    JMenuBar jMenuBar;
    JMenu file, edit, review, help;
    JMenuItem newfile, open, save, saveas, exit, cut, copy, paste, find, findnext, replace, replaceall, helpdoc;
    JPanel jTextPanePanel;
    JTextPane jTextPane;
    JScrollPane jTextPaneScroll;
    JPanel jCountPad;
    JLabel jWordCount, jCharCount, jCountMode;
    JMenuBar jButtonsPanel;
    JButton jBold, jItalic, jUnderline, jStrikethrough;
    JButton jLeftAlign, jCenterAlign, jRightAlign, jJustifyAlign;
    JButton jUpper, jLower;
    JPanel jWordPad, jSketchPad;
    Integer[] jFontSizes = {8, 10, 11, 12, 14, 16, 18, 20, 24, 28, 32, 36, 42, 48, 54, 60, 66, 72, 84, 96, 120};
    String[] jFonts = {"Arial", "Algerian", "Agency FB", "Calibri", "Cambria", "Cooper Black", "Comic Sans MS", "Helvetica", "Impact", "Times New Roman", "Verdana"};
    JComboBox<Integer> jFontSizeBox;
    JComboBox<String> jFontTypeBox;
    StyledDocument doc;
    JLabel jNothing;
    JPanel jEditPad, jSearchGroup, jSearchLabelPanel, jSearchButtonsPanel;
    JLabel jFindLabel, jReplaceLabel;
    JTextField jFindField, jReplaceField;
    JButton jFindAll, jFindNext, jReplaceAll, jReplaceNext;
    JLabel jSketchLabel;
    JPanel jSketchButtonsPanel;
    JButton jRectangle, jOval, jLine, jTriangle, jPentagon, jClear;
    JPanel jSP1;

    public TextEditor() {

        jFrame = new JFrame("Text Editor");
        jFrame.setTitle("Text Editor");
        jFrame.setLayout(new GridLayout());

        jWordPad = new JPanel(new BorderLayout());

        jEditPad = new JPanel(new BorderLayout());
        startindex = 0;
        endindex = 0;

        //<editor-fold desc="JMenu Bar Code">

        jMenuBar = new JMenuBar();

        file = new JMenu("File");
        file.setFont(new Font("Arial", Font.PLAIN, 16));
        edit = new JMenu("Edit");
        edit.setFont(new Font("Arial", Font.PLAIN, 16));
        review = new JMenu("Review");
        review.setFont(new Font("Arial", Font.PLAIN, 16));
        help = new JMenu("Help");
        help.setFont(new Font("Arial", Font.PLAIN, 16));

        newfile = new JMenuItem("New File");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveas = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        find = new JMenuItem("Find");
        findnext = new JMenuItem("Find Next");
        replace = new JMenuItem("Replace");
        replaceall = new JMenuItem("Replace All");
        helpdoc = new JMenuItem("Help Docs");

        file.add(newfile);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(exit);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        review.add(find);
        review.add(findnext);
        review.add(replace);
        review.add(replaceall);
        help.add(helpdoc);

        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(review);
        jMenuBar.add(help);

        newfile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveas.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        find.addActionListener(this);
        findnext.addActionListener(this);
        replace.addActionListener(this);
        replaceall.addActionListener(this);
        helpdoc.addActionListener(this);

        jFrame.add(jMenuBar);
        jFrame.setJMenuBar(jMenuBar);
        //</editor-fold>

        //<editor-fold desc="Text Pane Code">

        jTextPanePanel = new JPanel(new BorderLayout());
        Font Ari22 = new Font("Arial", Font.PLAIN, 22);
        jTextPane = new JTextPane();
        jTextPane.setFont(Ari22);
        jTextPanePanel.add(jTextPane, BorderLayout.CENTER);
        jWordPad.add(jTextPanePanel, BorderLayout.CENTER);

        jTextPaneScroll = new JScrollPane(jTextPane);
        jTextPanePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        jTextPanePanel.add(jTextPaneScroll);

        //</editor-fold>

        //<editor-fold desc="Buttons Panel Code">

        jButtonsPanel = new JMenuBar();

        Font TNR30 = new Font("Times New Roman", Font.PLAIN, 30);
        Font TNRB30 = new Font("Times New Roman", Font.BOLD, 30);
        Font TNRI30 = new Font("Times New Roman", Font.ITALIC, 30);

        jBold = new JButton("B");
        jBold.setFont(TNRB30);
        jBold.addActionListener(new StyledEditorKit.BoldAction());
        jBold.setToolTipText("Bold");

        jItalic = new JButton("I");
        jItalic.setFont(TNRI30);
        jItalic.addActionListener(new StyledEditorKit.ItalicAction());
        jItalic.setToolTipText("Italic");

        String s1 = "U";
        String s2 = String.join("\u0332", s1.split("", -1));
        jUnderline = new JButton(s2);
        jUnderline.setFont(TNR30);
        jUnderline.addActionListener(new StyledEditorKit.UnderlineAction());
        jUnderline.setToolTipText("Underline");

        String s3 = " S";
        String s4 = String.join("\u0336", s3.split("", -1));//"U+0336";
        jStrikethrough = new JButton(s4);
        jStrikethrough.setFont(TNR30);
        jStrikethrough.setToolTipText("Strikethrough");

        jLeftAlign = new JButton("[ ");
        jLeftAlign.setFont(TNR30);
        jLeftAlign.setToolTipText("Left Align");

        jCenterAlign = new JButton("__");
        jCenterAlign.setFont(TNR30);
        jCenterAlign.setToolTipText("Center Align");

        jRightAlign = new JButton(" ]");
        jRightAlign.setFont(TNR30);
        jRightAlign.setToolTipText("Right Align");

        jJustifyAlign = new JButton("#");
        jJustifyAlign.setFont(TNR30);
        jJustifyAlign.setToolTipText("Justify Align");

        jUpper = new JButton("A");
        jUpper.setFont(new Font("Arial Black", Font.PLAIN, 25));

        jLower = new JButton("a");
        jLower.setFont(new Font("Arial Black", Font.PLAIN, 25));

        jFontTypeBox = new JComboBox<String>(jFonts);
        jFontTypeBox.setFont(TNR30);

        jFontSizeBox = new JComboBox<Integer>(jFontSizes);
        jFontSizeBox.setFont(TNR30);

        jButtonsPanel.add(new JLabel(" "));
        jButtonsPanel.add(jBold);//, BorderLayout.WEST);
        jButtonsPanel.add(jItalic);//, BorderLayout.WEST);
        jButtonsPanel.add(jUnderline);//, BorderLayout.WEST);
        jButtonsPanel.add(jStrikethrough);//, BorderLayout.WEST);
        jButtonsPanel.add(new JLabel("  "));
        jButtonsPanel.add(jLeftAlign);
        jButtonsPanel.add(jCenterAlign);
        jButtonsPanel.add(jRightAlign);
        jButtonsPanel.add(jJustifyAlign);
        jButtonsPanel.add(new JLabel("  "));
        jButtonsPanel.add(jUpper);
        jButtonsPanel.add(jLower);
        jButtonsPanel.add(new JLabel("  "));
        jButtonsPanel.add(jFontTypeBox);
        jButtonsPanel.add(new JLabel("    "));
        jButtonsPanel.add(jFontSizeBox);
        jButtonsPanel.add(new JLabel(" "));

        jLower.addActionListener(this);
        jUpper.addActionListener(this);
        jFontTypeBox.addActionListener(this);
        jFontSizeBox.addActionListener(this);
        jEditPad.add(jButtonsPanel, BorderLayout.NORTH);

//        doc = new DefaultStyledDocument();
//        jTextPane.setDocument(doc);
//        jTextPane.setEditorKit(new StyledEditorKit());

//        JScrollPane scrollpane = new JScrollPane(jTextPane);
//        jTextPanePanel.add(scrollpane);
//        scrollpane.setPreferredSize(new Dimension(500, 400));
//        jTextPaneScroll = new JScrollPane(jTextPane);
//        jTextPanePanel.add(jTextPaneScroll, BorderLayout.CENTER);
//        jTextPaneScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jTextPane;


        jTextPanePanel.add(jTextPane);

//        Document doc = jTextPane.getDocument();
//        for (int i = 0; i < 20; i++) {
//            int offset = doc.getLength();
//            String str = "This is line number: " + i + "\n";
//            try {
//                doc.insertString(offset, str, null);
//            } catch (BadLocationException e) {
//                e.printStackTrace();
//            }
//        }
        //</editor-fold>

        //<editor-fold desc="Search Group Code">

        jSearchGroup = new JPanel(new GridLayout(2, 1));
        jSearchLabelPanel = new JPanel(new GridLayout(2, 1));
        jSearchButtonsPanel = new JPanel();

        Font Ari15 = new Font("Arial", Font.PLAIN, 20);
        jFindLabel = new JLabel("Find", SwingConstants.CENTER);
        jFindLabel.setFont(Ari15);
        jFindLabel.setSize(50, 20);
        jFindField = new JTextField(10);
        jFindField.setFont(Ari15);
        jReplaceLabel = new JLabel("Replace", SwingConstants.CENTER);
        jReplaceLabel.setSize(50, 20);
        jReplaceLabel.setFont(Ari15);
        jReplaceField = new JTextField(10);
        jReplaceField.setFont(Ari15);

        jFindAll = new JButton("Find All");
        jFindAll.setFont(Ari15);
        jFindNext = new JButton("Find Next");
        jFindNext.setFont(Ari15);
        jReplaceAll = new JButton("Replace All");
        jReplaceAll.setFont(Ari15);
        jReplaceNext = new JButton("Replace Next");
        jReplaceNext.setFont(Ari15);

        jSearchLabelPanel.add(jFindLabel);
        jSearchLabelPanel.add(jFindField);
        jSearchLabelPanel.add(jReplaceLabel);
        jSearchLabelPanel.add(jReplaceField);
//        jSearchLabelPanel.setBorder(new EmptyBorder(2, 300, 0, 300));
        jSearchButtonsPanel.add(jFindAll);
        jSearchButtonsPanel.add(jFindNext);
        jSearchButtonsPanel.add(jReplaceAll);
        jSearchButtonsPanel.add(jReplaceNext);
//        jSearchButtonsPanel.setBorder(new EmptyBorder(2, 300, 0, 300));

        jFindAll.addActionListener(this);
        jFindNext.addActionListener(this);
        jReplaceAll.addActionListener(this);
        jReplaceNext.addActionListener(this);

        jSearchGroup.add(jSearchLabelPanel);
        jSearchGroup.add(jSearchButtonsPanel);
        jEditPad.add(jSearchGroup, BorderLayout.WEST);
//        jEditPad.add(jSearchLabelPanel);//, BorderLayout.NORTH);
//        jEditPad.add(jSearchButtonsPanel);
        //</editor-fold>

        //<editor-fold desc="CountPad Code">

        jCountPad = new JPanel(new GridLayout());
        jCountPad.setBorder(new LineBorder(Color.black, 10));
        Font AriB18 = new Font("Arial", Font.BOLD, 18);
        jWordCount = new JLabel("Word Count: " + "_____");
        jWordCount.setFont(AriB18);
        jWordCount.setBorder(new EmptyBorder(0, 5, 0, 5));

        jCharCount = new JLabel("Character Count: " + "_____");
        jCharCount.setFont(AriB18);
        jCharCount.setBorder(new EmptyBorder(0, 5, 0, 5));

        jCountMode = new JLabel(("WORD COUNT OF ENTIRE DOCUMENT"));
        jCharCount.setFont(AriB18);
        jCharCount.setBorder(new EmptyBorder(0, 5, 5, 0));

        jCountPad.add(jWordCount);
        jCountPad.add(jCharCount);
        jCountPad.add(jCountMode);
        jCountPad.add(new Label("  "));
        jCountPad.add(new Label("  "));
        jCountPad.setBorder(new EmptyBorder(0, 15, 0, 15));
        jWordPad.add(jCountPad, BorderLayout.SOUTH);
        jTextPane.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                wordCount();
            }
        });
        jTextPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                wordCount();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                wordCount();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                wordCount();
            }
        });


        //</editor-fold>

        //<editor-fold desc="Sketch Pad Code">
        jSketchPad = new JPanel();
        jSP1 = new JPanel(new BorderLayout());
        jSketchButtonsPanel = new JPanel(new GridLayout());

        jSketchLabel = new JLabel("Sketch Pad\n\n", SwingConstants.CENTER);
        jSketchLabel.setFont(new Font("Agency FB", Font.PLAIN, 20));
        jRectangle = new JButton("Rectangle");
        jRectangle.addActionListener(this);
        jOval = new JButton("Oval");
        jOval.addActionListener(this);
        jLine = new JButton("Line");
        jLine.addActionListener(this);
        jTriangle = new JButton("Triangle");
        jPentagon = new JButton("Pentagon");
        jClear = new JButton("Clear");
        jClear.addActionListener(this);
        jSketchButtonsPanel.add(jRectangle);
        jSketchButtonsPanel.add(jOval);
        jSketchButtonsPanel.add(jLine);
        jSketchButtonsPanel.add(jTriangle);
        jSketchButtonsPanel.add(jPentagon);
        jSketchButtonsPanel.add(jClear);
        jSP1.add(jSketchLabel, BorderLayout.NORTH);
        jSP1.add(jSketchButtonsPanel, BorderLayout.CENTER);
        //</editor-fold>

        jSketchPad.add(jSP1, BorderLayout.NORTH);
        jWordPad.add(jEditPad, BorderLayout.NORTH);
        jFrame.add(jWordPad);
        jWordPad.add(jSketchPad, BorderLayout.EAST);

        //code to maximise a screen
        jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {

        TextEditor MainScreen = new TextEditor();

    }

    public void wordCount() {
        String text;
        int mode = 0;

        if (jTextPane.getSelectedText() == null) {
            text = jTextPane.getText();
        } else {
            text = jTextPane.getSelectedText();
            mode = 1;
        }

        //Variables declaration
        int spaces = 0;
        int word_count = 0;
        int chars = 0;
        int toggle = 0;
        if (text.isEmpty()) {
            word_count = 0;
            chars = 0;
        } else {
            String[] array = text.split(" ");
            for (String d : array) {
                String[] newLine = d.split("\n");
                if (newLine.length != 1) {
                    toggle = 1;
                    for (String line : newLine) {
                        if (!line.equals("")) {
                            word_count++;
                        }
                        chars = chars + line.length();
                    }
                }
                if (toggle == 0) {
                    word_count++;
                    chars = chars + d.length();
                }
                toggle = 0;
            }
        }

        jCharCount.setText("Character Count: " + chars);
        jWordCount.setText("Word Count: " + word_count);
//        System.out.println("Character Count: "+characters);
//        System.out.println("Character Count: "+words);
        if (mode == 1)
            jCountMode.setText("WORD COUNT OF SELECTED TEXT ONLY");
        else
            jCountMode.setText("WORD COUNT OF ENTIRE DOCUMENT");
    }

    public void jSeeknSelect(JTextPane jTextPane, String seekElement) throws BadLocationException {
        try {
            String s = jTextPane.getText();
            DefaultHighlighter.DefaultHighlightPainter jGreenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
            Highlighter jHighlighter = jTextPane.getHighlighter();

            startindex = s.indexOf(seekElement, startindex);
            endindex = startindex + seekElement.length();
            jHighlighter.removeAllHighlights();
            jHighlighter.addHighlight(startindex, endindex, jGreenPainter);
//            jTextPane.setSelectionStart(startindex);
//            jTextPane.setSelectionEnd(endindex);
            startindex = endindex;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jSeekAll(JTextPane jTextPane, String seekElement) throws BadLocationException {
        try {
            String s = jTextPane.getText();
            startindex = 0;
            DefaultHighlighter.DefaultHighlightPainter jGreenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
            Highlighter jHighlighter = jTextPane.getHighlighter();
            int occurances = (s.length() - s.replace(seekElement, "").length()) / seekElement.length();
            jHighlighter.removeAllHighlights();
            for (int i = 0; i < occurances; i++) {
                startindex = s.indexOf(seekElement, startindex);
                endindex = startindex + seekElement.length();
                jHighlighter.addHighlight(startindex, endindex, jGreenPainter);
//                jTextPane.select(startindex, endindex);
                startindex = endindex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newfile) {
            jTextPane.setText("");
            String jFileName = "D:\\New_Unnamed.txt";
        }
        if (e.getSource() == open) {
            JFileChooser jFileChooser = new JFileChooser("D:");
            int Option = jFileChooser.showOpenDialog(null);
            if (Option == JFileChooser.APPROVE_OPTION) {
                jFileName = jFileChooser.getSelectedFile().getAbsolutePath();
                File jChosenFile = new File(jFileName);
                //  System.out.println(jFileChooser.getSelectedFile());
                //  System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String jTempText = "", jText = "";
                    FileReader jFileReader = new FileReader(jChosenFile);
                    BufferedReader jBufferredReader = new BufferedReader(jFileReader);
                    jText = jBufferredReader.readLine();
                    while ((jTempText = jBufferredReader.readLine()) != null) {
                        jText = jText + "\n" + jTempText;
                    }
                    jTextPane.setText(jText);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(jFrame, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "This operation was cancelled");
            }
        }
        if (e.getSource() == save) {
            try {
                FileWriter jFileWriter = new FileWriter(jFileName, false);
                BufferedWriter jBufferedWriter = new BufferedWriter(jFileWriter);
                jBufferedWriter.write(jTextPane.getText());
                jBufferedWriter.flush();
                jBufferedWriter.close();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(jFrame, evt.getMessage());
            }
        }
        if (e.getSource() == saveas) {
            JFileChooser jFileChooser = new JFileChooser("D:\\");
            int Option = jFileChooser.showSaveDialog(null);
            if (Option == JFileChooser.APPROVE_OPTION) {
                File jFileName = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter jFileWriter = new FileWriter(jFileName, false);
                    BufferedWriter jBufferedWriter = new BufferedWriter(jFileWriter);
                    jBufferedWriter.write(jTextPane.getText());
                    jBufferedWriter.flush();
                    jBufferedWriter.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(jFrame, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "This operation was cancelled");
            }
        }
        if (e.getSource() == exit) {
            int choice = JOptionPane.showConfirmDialog(jFrame, "Confirm Exit? ");
            if (choice == JOptionPane.YES_OPTION) {
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.dispose();
            }
        }
        if (e.getSource() == cut) {
            jTextPane.cut();
        }
        if (e.getSource() == copy) {
            jTextPane.copy();
        }
        if (e.getSource() == paste) {
            jTextPane.paste();
        }
        if (e.getSource() == helpdoc) {
            JOptionPane.showMessageDialog(jFrame, "It's an easy enough software.\nYou really don't need help!");
        }
        if (e.getSource() == jFontSizeBox) {
            int size = (Integer) jFontSizeBox.getSelectedItem();
            Action fontAction = new StyledEditorKit.FontSizeAction(String
                    .valueOf(size), size);
            fontAction.actionPerformed(e);
        }
        if (e.getSource() == jFontTypeBox) {
            String type = (String) jFontTypeBox.getSelectedItem();
            Action fontAction = new StyledEditorKit.FontFamilyAction(type, type);
            fontAction.actionPerformed(e);
        }
        if (e.getSource() == jLower) {
            String s = jTextPane.getSelectedText();
            jTextPane.replaceSelection(s.toLowerCase());
        }
        if (e.getSource() == jUpper) {
            String s = jTextPane.getSelectedText();
            jTextPane.replaceSelection(s.toUpperCase());
        }
        if (e.getSource() == jFindNext || e.getSource() == findnext) {
            try {
                jSeeknSelect(jTextPane, jFindField.getText());
            } catch (BadLocationException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jReplaceNext || e.getSource() == replace) {
//            highLightreplacenext(a,find1.getText(),replace1.getText());
            jTextPane.setText(jTextPane.getText().replaceFirst(jFindField.getText(), jReplaceField.getText()));
        }
        if (e.getSource() == jFindAll || e.getSource() == find) {
            try {
                jSeekAll(jTextPane, jFindField.getText());
            } catch (BadLocationException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jReplaceAll || e.getSource() == replaceall) {
            jTextPane.setText(jTextPane.getText().replaceAll(jFindField.getText(), jReplaceField.getText()));
        }
        if (e.getSource() == jRectangle) {
            JPanel num = new JPanel() {
                public void paint(Graphics g) {
                    g.fillRect(10, 0, 100, 100);
                }
            };
            jSketchButtonsPanel.add(num, BorderLayout.CENTER);
        }
        if (e.getSource() == jOval) {
            JPanel num = new JPanel() {
                public void paint(Graphics g) {
                    g.fillOval(10, 0, 100, 100);
                }
            };
            jSketchButtonsPanel.add(num, BorderLayout.CENTER);
        }
    }
}
