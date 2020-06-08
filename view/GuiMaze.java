package view;

import controller.MoveController;
import maze.ui.MazePanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import maze.Maze;

public class GuiMaze {

    private final  MazePanel panel;
    //private final MoveController ctrl;

    public GuiMaze(Maze model, MoveController ctrl) {
        //this.ctrl = ctrl;
        panel = new MazePanel(model);
        panel.addMoveListener(ctrl);
        panel.requestFocusInWindow();
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {//clasa anonima
            @Override
            public void run() {
                JFrame localJFrame = new JFrame("Maze");
                localJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                localJFrame.setContentPane(GuiMaze.this.panel);
                
                localJFrame.pack();
                localJFrame.setResizable(false);
                localJFrame.setLocationRelativeTo(null);
                localJFrame.setVisible(true);
            }
        });
    }
}
