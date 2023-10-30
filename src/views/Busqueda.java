package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controladores.EstadiasControlador;
import modelo.Estadias;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbEstadiasDiarias;
	private JTable tbEstadiasMensual;
	private DefaultTableModel modeloEstadiasMensual;
	private DefaultTableModel modeloEstadiasDiarias;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	
	
	private EstadiasControlador estadiasControl;
			
	private RegistroEstadia estadiasVista;
	
	String estadias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.estadiasVista = new RegistroEstadia();
		this.estadiasControl = new EstadiasControlador();
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		

		tbEstadiasDiarias = new JTable();
		tbEstadiasDiarias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbEstadiasDiarias.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloEstadiasDiarias = (DefaultTableModel) tbEstadiasDiarias.getModel();
		modeloEstadiasDiarias.addColumn("Fecha Entrada");
		modeloEstadiasDiarias.addColumn("Lugar Asignado");
		modeloEstadiasDiarias.addColumn("Marca");
		modeloEstadiasDiarias.addColumn("Modelo");
		modeloEstadiasDiarias.addColumn("Dominio");
		modeloEstadiasDiarias.addColumn("Titular");
		modeloEstadiasDiarias.addColumn("Telefono");
		modeloEstadiasDiarias.addColumn("Dias");
		modeloEstadiasDiarias.addColumn("Valor");
		tbEstadiasDiarias.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		mostrarTablaEstadias();
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbEstadiasDiarias);
		panel.addTab("Estadias Diarias", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		tbEstadiasMensual = new JTable();
		tbEstadiasMensual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbEstadiasMensual.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloEstadiasMensual = (DefaultTableModel) tbEstadiasMensual.getModel();
		
		modeloEstadiasMensual.addColumn("Fecha Entrada");
		modeloEstadiasMensual.addColumn("Lugar Asignado");
		modeloEstadiasMensual.addColumn("Marca");
		modeloEstadiasMensual.addColumn("Modelo");
		modeloEstadiasMensual.addColumn("Dominio");
		modeloEstadiasMensual.addColumn("Titular");
		modeloEstadiasMensual.addColumn("Telefono");
		modeloEstadiasMensual.addColumn("Dias");
		modeloEstadiasMensual.addColumn("Valor");
		tbEstadiasMensual.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scroll_table = new JScrollPane(tbEstadiasMensual);
		panel.addTab("Estadias Mensuales", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		
		
		mostrarTablaEstadiasMensual();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if(txtBuscar.getText().equals("")) {
					mostrarTablaEstadias();
					mostrarTablaEstadiasMensual();
				}else {
					mostrarTablaEstadiasDominio();
					
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				int filaEstadiaDiaria = tbEstadiasDiarias.getSelectedRow();
				int filaEstadiaMensual = tbEstadiasMensual.getSelectedRow();
				if(filaEstadiaDiaria >= 0 ) {
					actualizarEstadias();
					
					limpiarTabla();
					mostrarTablaEstadias();
				} else if(filaEstadiaMensual >= 0 ) {
					
					actualizarEstadiasMensuales();
					limpiarTabla();
					mostrarTablaEstadias();
				}
				
				
				
			}
			
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaEstadiasDiaria = tbEstadiasDiarias.getSelectedRow();
				int filaEstadiasMensual = tbEstadiasMensual.getSelectedRow();
				
				if(filaEstadiasDiaria >= 0) {
					estadias = tbEstadiasDiarias.getValueAt(filaEstadiasDiaria, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el registro?");
					if (confirmar == JOptionPane.YES_OPTION) {
						String valor = tbEstadiasDiarias.getValueAt(filaEstadiasDiaria, 4).toString();
						System.out.println(valor);
						estadiasControl.eliminar(String.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado con Exito");
						limpiarTabla();
						mostrarTablaEstadias();
					}
				}
				else if (filaEstadiasMensual >= 0) {
					estadias = tbEstadiasMensual.getValueAt(filaEstadiasMensual, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el registro?");
					if (confirmar == JOptionPane.YES_OPTION) {
						String valor = tbEstadiasMensual.getValueAt(filaEstadiasMensual, 4).toString();
						estadiasControl.eliminar(String.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado con Exito");
						limpiarTabla();
						mostrarTablaEstadias();
					}
					
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	private List<Estadias> mostrarEstadias(){
		return this.estadiasControl.mostrar();
	}
	
	public List<Estadias> buscarDominioEstadias(){
		return this.estadiasControl.buscar(txtBuscar.getText());
	}
	
	private void mostrarTablaEstadias() {
		List<Estadias> estadia = mostrarEstadias();
		//modeloEstadiasMensual.setRowCount(0);
		modeloEstadiasDiarias.setRowCount(0);
		try {
			for(Estadias estadias: estadia) {
				if (estadias.getEsMensual() == false) {
					modeloEstadiasDiarias.addRow(new Object[] {
							estadias.getDataE(),estadias.getLugarAsignado(),estadias.getMarca(), estadias.getModelo(), estadias.getDominio(), estadias.getTitular(), estadias.getTelefono(), estadias.getDias() , estadias.getValor(), estadias.getEsMensual()
					});
				} else {
					//modeloEstadiasMensual.addRow(new Object[] {
						//	estadias.getDataE(),estadias.getLugarAsignado(),estadias.getMarca(), estadias.getModelo(), estadias.getDominio(), estadias.getTitular(), estadias.getTelefono(), estadias.getDias() , estadias.getValor(), estadias.getEsMensual()
//					});

				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void mostrarTablaEstadiasMensual() {
		List<Estadias> estadia = mostrarEstadias();
		
		modeloEstadiasMensual.setRowCount(0);
		try {
			for(Estadias estadias: estadia) {
				if (Boolean.TRUE.equals(estadias.getEsMensual())) {
					modeloEstadiasMensual.addRow(new Object[] {
							estadias.getDataE(),estadias.getLugarAsignado(),estadias.getMarca(), estadias.getModelo(), estadias.getDominio(), estadias.getTitular(), estadias.getTelefono(), estadias.getDias() , estadias.getValor(), estadias.getEsMensual()
					});
				} 
				
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private void mostrarTablaEstadiasDominio() {
		List<Estadias> estadia = buscarDominioEstadias();
		
		
		modeloEstadiasDiarias.setRowCount(0);
		
		try {
			for(Estadias estadias: estadia) {
				if (estadias.getEsMensual() == false) {
					modeloEstadiasDiarias.addRow(new Object[] {
							estadias.getDataE(),estadias.getLugarAsignado(),estadias.getMarca(), estadias.getModelo(), estadias.getDominio(), estadias.getTitular(), estadias.getTelefono(), estadias.getDias() , estadias.getValor(), estadias.getEsMensual()
					});
				} else {
					modeloEstadiasMensual.addRow(new Object[] {
							estadias.getDataE(),estadias.getLugarAsignado(),estadias.getMarca(), estadias.getModelo(), estadias.getDominio(), estadias.getTitular(), estadias.getTelefono(), estadias.getDias() , estadias.getValor(), estadias.getEsMensual()
					});

				}
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private void actualizarEstadias() {
		Optional.ofNullable(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), tbEstadiasDiarias.getSelectedColumn()))
		.ifPresentOrElse(fila ->{
			
			LocalDate dataE ;
			Integer dias;
			try {
								
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dataE = LocalDate.parse(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 0).toString(), dateFormat);
			}catch(DateTimeException e){
				throw new RuntimeException(e);
			}
			
			Integer lugarAsignado = Integer.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 1).toString());
			String marca = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 2);
			String modelo = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 3);
			String dominio = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 4);
			String titular = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 5);
			String telefono = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 6);
			 dias = Integer.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 7).toString());
			 String valor = "S/" + calcularValorEstadia(dias);
			 valor = (String) modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(),8);
			 boolean esMensual =(dias >= 30); 
			
			this.estadiasControl.actualizarEstadias(dataE, lugarAsignado, marca, modelo, dominio, titular, telefono, dias, valor, esMensual);
			
			JOptionPane.showMessageDialog(this, String.format("Registro modificado con Exito"));
			
		
		}, ()-> JOptionPane.showMessageDialog(this, "bitte, sind sie nicht ein Tier") );
	}
	
	private void actualizarEstadiasMensuales() {
		Optional.ofNullable(modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), tbEstadiasMensual.getSelectedColumn()))
		.ifPresentOrElse(fila ->{
			
			LocalDate dataE ;
			Integer dias;
			try {
								
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dataE = LocalDate.parse(modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 0).toString(), dateFormat);
			}catch(DateTimeException e){
				throw new RuntimeException(e);
			}
			
			
			Integer lugarAsignado = Integer.valueOf(modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 1).toString());
			String marca = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 2);
			String modelo = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 3);
			String dominio = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 4);
			String titular = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 5);
			String telefono = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 6);
			 dias = Integer.valueOf(modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(), 7).toString());
			 String valor = "S/" + calcularValorEstadiaMensual(dias);
			 valor = (String) modeloEstadiasMensual.getValueAt(tbEstadiasMensual.getSelectedRow(),8);
			boolean esMensual =(dias >= 30); 
			
			this.estadiasControl.actualizarEstadias(dataE, lugarAsignado, marca, modelo, dominio, titular, telefono, dias, valor, esMensual);
			
			JOptionPane.showMessageDialog(this, String.format("Registro Mensual modificado con Exito"));
			
		
		}, ()-> JOptionPane.showMessageDialog(this, "bitte, sind sie nicht ein Tier") );
	}
	
	
	
	
	public String calcularValorEstadia(Integer dias) {
		if(dias!=0) {
		 dias = Integer.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 7).toString());
			System.out.println(dias);
			
			
			int noche = 1500;	
			int valor= dias*noche;
			
			 
			System.out.println(valor);
			return Integer.toString(valor);
		}else {
			
			
		
			return "";
		}
		}
	
	public String calcularValorEstadiaMensual(Integer dias) {
		if(dias!=0) {
		 //dias = Integer.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 7).toString());
		//	System.out.println(dias);
			
			
			//int noche = 1500;	
			//int valor= dias*noche;
			int valor= 30000;
			
			 
			System.out.println(valor);
			return Integer.toString(valor);
		}else {
			
			
		
			return "";
		}
		}
		
	
	
	private void limpiarTabla() {
		((DefaultTableModel) tbEstadiasDiarias.getModel()).setRowCount(0);
		((DefaultTableModel) tbEstadiasMensual.getModel()).setRowCount(0);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
