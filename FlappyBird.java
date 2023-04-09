import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlappyBird extends JFrame implements ActionListener {

    private Timer timer;
    private JPanel gamePanel;
    private int birdX = 100, birdY = 200;
    private int pipeX = 500, pipeY = 0;
    private int score = 0;
    private boolean isGameOver = false;

    public FlappyBird() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel);

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver) {
                    birdY -= 40;
                }
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);
        g.fillRect(pipeX, pipeY, 50, 200);

        g.setColor(Color.BLUE);
        g.fillRect(birdX, birdY, 30, 30);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 10, 40);

        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", 250, 250);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            pipeX -= 5;
            if (pipeX < -50) {
                pipeX = 800;
                pipeY = (int)(Math.random() * 300) - 200;
                score++;
            }

            birdY += 2;
            if (birdY > 570) {
                isGameOver = true;
            }

            if (birdX + 30 > pipeX && birdX < pipeX + 50 &&
                (birdY < pipeY + 200 || birdY + 30 > pipeY + 400)) {
                isGameOver = true;
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
