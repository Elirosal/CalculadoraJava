import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame {

    private JTextField visor;

    private double primeiroNumero = 0;
    private String operador = "";
    private boolean novoNumero = true;

    public CalculadoraGUI() {

        setTitle("Calculadora Java");
        setSize(350, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel princioal
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(new Color(25, 25, 35));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 16));

        // Visor
        visor = new JTextField("0");
        visor.setFont(new Font("Arial", Font.BOLD, 32));
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setEditable(false);
        visor.setBackground(new Color(40, 40, 55));
        visor.setForeground(Color.WHITE);
        visor.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));


        painel.add(visor, BorderLayout.NORTH);

        // Painel dos botões
        JPanel botoes = new JPanel(new GridLayout(4, 4, 8, 8));
        botoes.setBackground(new Color(25, 25, 35));

        String[] nomes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "c", "0", "=", "+"
        };

        for (String nome : nomes) {

            JButton botao = new JButton(nome);

            botao.setFont(new Font("Arial", Font.BOLD, 22));
            botao.setFocusPainted(false);
            botao.setForeground(Color.WHITE);

            if ("+-*/=".contains(nome) && nome.length() == 1) {
                botao.setBackground(new Color(0, 130, 200));
            } else if (nome.equals("C")) {
                botao.setBackground(new Color(190, 55, 55));
            } else {
                botao.setBackground(new Color(55, 55, 75));
            }
            botao.addActionListener(e -> processarBotao(nome));

            botoes.add(botao);
        }

        painel.add(botoes, BorderLayout.CENTER);
        add(painel);
    }
    private void processarBotao(String valor) {

        // Limpar calculadora
        if (valor.equals("c")) {
            visor.setText("0");
            primeiroNumero = 0;
            operador = "";
            novoNumero = true;
            return;
        }

        // Digitar números
        if (valor.matches("[0-9]")) {

            if (novoNumero) {
                visor.setText(valor);
                novoNumero = false;
            } else {
                visor.setText(visor.getText() + valor);
            }

            return;
        }

        // Escolher operação
        if ("+-*/".contains(valor) && valor.length() == 1) {

            primeiroNumero = Double.parseDouble(visor.getText());
            operador = valor;
            novoNumero = true;

            return;
        }

        // Calcular resultado
        if (valor.equals("=") && !operador.isEmpty()) {

            double segundoNumero =
                    Double.parseDouble(visor.getText());

            double resultado = 0;

            switch (operador) {

                case "+":
                    resultado = primeiroNumero + segundoNumero;
                    break;

                case "-":
                    resultado = primeiroNumero - segundoNumero;
                    break;

                case "*":
                    resultado = primeiroNumero * segundoNumero;
                    break;

                case "/":

                    if (segundoNumero == 0) {
                        visor.setText("Erro: divisão por zero");
                        operador = "";
                        novoNumero = true;
                        return;
                    }

                    resultado = primeiroNumero / segundoNumero;
                    break;
            }

            visor.setText(String.valueOf(resultado));

            operador = "";
            novoNumero = true;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculadora = new CalculadoraGUI();
            calculadora.setVisible(true);
        });
    }

}


