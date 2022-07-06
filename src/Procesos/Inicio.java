package Procesos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Inicio {
	//iniciamos nuestros atributos de la vista 
	JFrame inicio = new JFrame();
	JPanel p1 = new JPanel();
	JLabel label1 = new JLabel("INICIO DE SESION");
	JLabel label2 = new JLabel("Usuario");
	JLabel label3 = new JLabel("Contrase�a");
	JTextField txtUsuario = new JTextField();
	JPasswordField txtContrase�a = new JPasswordField();
	JButton btnIniciar = new JButton("INICIAR SESION");
	
	//cargamos el codigo de colores y los asignamos a una variable
	Color color1 = new Color(0x516FFF);
	Object[][] Vendedores = new Object[400][6];

	public static void main(String[] args) {
		Inicio p1 = new Inicio();
		try {
			p1.ejecutar();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	//cargamos nuestros atributos a traves de este netodo
	public void frame() throws ClassNotFoundException {
		inicio.setLocationRelativeTo(null);
		inicio.setUndecorated(true);
		inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
		inicio.setTitle("INICIO DE SESION");

		// x y ancho y alto
		inicio.setBounds(700, 300, 500, 500);
		inicio.setVisible(true);
		inicio.setResizable(false);

		inicio.add(p1);
		p1.setSize(800, 500);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setBackground(Color.white);

		// carga de archivo .data para la presistencia de datos
		try {
			ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_Vendedores.dat"));
			Vendedores = (Object[][]) recuperar.readObject();
			recuperar.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "NO EXISTEN VENDEDORES");
		}

	}
	
	//cargamos nuestros atributos label a traves de este netodo
	public void label() {
		p1.add(label1);
		label1.setFont(new Font("Roboto Black", Font.PLAIN, 22));
		label1.setBounds(150, 22, 300, 93);

		p1.add(label2);
		label2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		label2.setBounds(120, 120, 125, 93);

		p1.add(label3);
		label3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		label3.setBounds(120, 270, 125, 93);

		p1.add(txtUsuario);
		txtUsuario.setBounds(120, 190, 200, 25);
		txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 22));

		p1.add(txtContrase�a);
		txtContrase�a.setBounds(120, 340, 200, 25);
		txtContrase�a.setFont(new Font("Roboto Light", Font.PLAIN, 22));

	}

	//cargamos nuestros atributos button a traves de este netodo
	public void button() {
		btnIniciar.setBounds(140, 400, 200, 50);
		btnIniciar.setFont(new Font("Roboto Medium", Font.PLAIN, 20));

		btnIniciar.setForeground(Color.white);
		btnIniciar.setBackground(color1);
		p1.add(btnIniciar);

		// Funcionalidad
		ActionListener ingresar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				if (verificarInicio(txtUsuario.getText(), txtContrase�a.getText())) {
					JOptionPane.showMessageDialog(null, "BIENVENIDO");
					inicio.setVisible(false);
					MenuPrincipal mp = new MenuPrincipal();
					mp.ejecutar();
				} else if (verificarInicioV(txtUsuario.getText(), txtContrase�a.getText())) {
					LoginVendedores lv = new LoginVendedores();
					lv.setNombre(txtUsuario.getText());
					JOptionPane.showMessageDialog(null, "BIENVENIDO");
					inicio.setVisible(false);
					lv.ejecutar();
				

				} else {
					JOptionPane.showMessageDialog(null, "DATOS EQUIVOCADOS O EL USUARIO NO EXISTE");
				}
				
			}
		};

		// Acci�n del evento
		btnIniciar.addActionListener(ingresar);
		JButton cerrarProgra = new JButton ("X");
		cerrarProgra.setBounds(0, 0, 50, 50);
		cerrarProgra.setForeground(Color.BLACK);
		cerrarProgra.setBackground(Color.WHITE);
		cerrarProgra.setFont(new Font("Roboto black", Font.PLAIN, 22));
		p1.add(cerrarProgra);
		
		ActionListener funcionCerrar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		};

		//Acci�n del evento
		cerrarProgra.addActionListener(funcionCerrar);

	}

	//iniciamos todos nuestros metodos donde cargamos los atributos
	public void ejecutar() throws ClassNotFoundException {
		button();
		frame();
		label();

	}

	//verificadores para ver que tipo de usuario es el que entra a la aplicacion
	public boolean verificarInicio(String nombreUsuario, String contrase�aUsuario) {
		Boolean Nombre = false;
		Boolean Contra = false;
		if ("Admin".equals(nombreUsuario)) {
			Nombre = true;
		}
		if ("Admin".equals(contrase�aUsuario)) {
			Contra = true;
		}
		if (Nombre == true && Contra == true) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verificarInicioV(String nombreUsuario, String contrase�aUsuario) {
		boolean login = false;

		for (int i = 0; i < Vendedores.length; i++) {
			if (nombreUsuario.equals(Vendedores[i][1]) && contrase�aUsuario.equals(Vendedores[i][5])) {
				login = true;
				break;
			} else {
				login = false;
			}
		}
		return login;
	}

	
}
