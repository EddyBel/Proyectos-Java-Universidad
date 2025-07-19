package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener, KeyListener {

    private final int CELL_SIZE = 10; // Tamaño de cada celda
    private final int PANEL_SIZE = 520; // Tamaño del panel
    private final ArrayList<Point> snake = new ArrayList<>(); // Lista para las partes de la serpiente
    private int foodX; // Posición X de la comida
    private int foodY; // Posición Y de la comida
    private int directionX = 0; // Dirección en X (-1: izquierda, 1: derecha)
    private int directionY = 0; // Dirección en Y (-1: arriba, 1: abajo)
    private Timer timer; // Temporizador para el movimiento
    private int score = 0; // Contador de puntos
    private int speed = 100; // Velocidad inicial (en ms)
    private int minSpeed = 20; // Velocidad maxima de la snake
    private final Random random = new Random();
    private long lastDirectionChange = System.currentTimeMillis(); // Tiempo del último cambio de dirección
    private final int directionDelay = 70; // Tiempo mínimo entre cambios de dirección en milisegundos

    public SnakePanel() {
        this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        snake.add(new Point(100, 100)); // Cabeza inicial

        // Colocar la comida inicialmente
        spawnFood();

        // Configurar el temporizador
        timer = new Timer(speed, this); // 100 ms por tick
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//       drawGrid(g);
        drawSnake(g);
        drawFood(g);
        drawScore(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < getWidth(); x += CELL_SIZE) {
            for (int y = 0; y < getHeight(); y += CELL_SIZE) {
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

            }
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point part : snake) {
            g.fillRect(part.x, part.y, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(foodX, foodY, CELL_SIZE, CELL_SIZE);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Puntos: " + score, 10, 20);
    }

    private void spawnFood() {
        // Genera una posición aleatoria para la comida dentro de la cuadrícula
        foodX = random.nextInt(PANEL_SIZE / CELL_SIZE) * CELL_SIZE;
        foodY = random.nextInt(PANEL_SIZE / CELL_SIZE) * CELL_SIZE;

        // Asegúrate de que la comida no aparezca dentro del cuerpo de la serpiente
        for (Point part : snake) {
            if (part.x == foodX && part.y == foodY) {
                spawnFood(); // Recursión si la comida aparece en la serpiente
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actualiza la posición de la serpiente
        moveSnake();

        // Comprueba si la serpiente come la comida
        if (snake.get(0).x == foodX && snake.get(0).y == foodY) {
            score++;
            snake.add(new Point(-1, -1)); // Agrega una nueva parte a la serpiente
            spawnFood();
            increaseSpeed(); // Aumenta la velocidad
        }

        // Comprueba si la serpiente se sale del panel o se muerde a sí misma
        checkCollision();

        // Redibuja el panel
        repaint();
    }

    private void moveSnake() {
        // Actualiza la posición de cada parte de la serpiente
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.set(i, new Point(snake.get(i - 1)));
        }

        // Actualiza la posición de la cabeza
        Point head = snake.get(0);
        head.translate(directionX * CELL_SIZE, directionY * CELL_SIZE);
    }

    private void checkCollision() {
        Point head = snake.get(0);

        // Comprueba si la serpiente sale del panel
        if (head.x < 0 || head.x >= PANEL_SIZE || head.y < 0 || head.y >= PANEL_SIZE) {
            gameOver();
        }

        // Comprueba si la serpiente se muerde a sí misma
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
                break;
            }
        }
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "¡Game Over! Puntos: " + score);
        resetGame();
//        System.exit(0); // Termina el juego
    }

    private void resetGame() {
        // Reinicia los valores iniciales
        snake.clear();
        snake.add(new Point(100, 100)); // Cabeza inicial
        directionX = 0;
        directionY = 0;
        score = 0;
        speed = 100; // Restablece la velocidad inicial
        timer.setDelay(speed);
        spawnFood();
        timer.start();
    }

    private void increaseSpeed() {
        // Reduce el tiempo del temporizador para aumentar la velocidad
        if (speed > this.minSpeed) { // Establece un límite mínimo de velocidad
            speed -= 5; // Disminuye el tiempo entre ticks
            timer.setDelay(speed); // Aplica el nuevo tiempo al temporizador
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        long currentTime = System.currentTimeMillis();

        // Solo permite el cambio de dirección si ha pasado el tiempo suficiente
        if (currentTime - lastDirectionChange >= directionDelay) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W: // Movimiento hacia arriba
                    if (directionY != 1) { // No permitir que la serpiente se mueva hacia abajo
                        directionX = 0;
                        directionY = -1;
                        lastDirectionChange = currentTime; // Actualiza el tiempo del último cambio
                    }
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S: // Movimiento hacia abajo
                    if (directionY != -1) { // No permitir que la serpiente se mueva hacia arriba
                        directionX = 0;
                        directionY = 1;
                        lastDirectionChange = currentTime; // Actualiza el tiempo del último cambio
                    }
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A: // Movimiento hacia la izquierda
                    if (directionX != 1) { // No permitir que la serpiente se mueva hacia la derecha
                        directionX = -1;
                        directionY = 0;
                        lastDirectionChange = currentTime; // Actualiza el tiempo del último cambio
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D: // Movimiento hacia la derecha
                    if (directionX != -1) { // No permitir que la serpiente se mueva hacia la izquierda
                        directionX = 1;
                        directionY = 0;
                        lastDirectionChange = currentTime; // Actualiza el tiempo del último cambio
                    }
                    break;

                case KeyEvent.VK_ESCAPE: // Detecta la tecla Escape
                    exitGame();
                    break;
                case KeyEvent.VK_Q: // Detecta la tecla Q
                    exitGame();
                    break;
            }
        }
    }

    private void exitGame() {
        timer.stop(); // Detener el temporizador
        int choice = JOptionPane.showConfirmDialog(this,
                "¿Seguro que quieres salir?", "Salir del juego",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // Finalizar el programa
        } else {
            timer.start(); // Continuar el juego si el usuario cancela
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No se necesita implementar
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se necesita implementar
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakePanel panel = new SnakePanel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
