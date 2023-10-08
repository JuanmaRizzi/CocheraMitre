package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import controller.EstadiasController;
import exception.CocheraException;
import model.Estadia;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {
	private static final Logger logger = Logger.getLogger(Busqueda.class);
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbEstadiasDiarias;
	private JTable tbEstadiasMensual;
	private DefaultTableModel modeloEstadiasMensual;
	private DefaultTableModel modeloEstadiasDiarias;
	private JLabel labelAtras;
	private JLabel labelExit;
	private int xMouse;
	private int yMouse;
	private transient EstadiasController estadiasController;
	private static final String ROBOTO = "Roboto";
	private static final String ERROR = "ERROR";
	private static final String ERROR_CONSULTAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR CONSULTAR LAS ESTADÍAS: ";
	private static final String ERROR_CONSULTAR_DOMINIO = "HA OCURRIDO UN ERROR AL INTENTAR CONSULTAR EL DOMINIO: ";
	private static final String ERROR_ACTUALIZAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR ACTUALIZAR LA ESTADÍA: ";
	private static final String ERROR_ELIMINAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR ELIMINAR LA ESTADÍA: ";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Busqueda().setVisible(true);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		});
	}

	public Busqueda() throws CocheraException {
		new RegistroEstadia();
		this.estadiasController = new EstadiasController();
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
		JLabel lblNewLabel4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel4.setForeground(new Color(12, 138, 199));
		lblNewLabel4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel4);
		JTabbedPane panel = new JTabbedPane(SwingConstants.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		tbEstadiasMensual = new JTable();
		tbEstadiasMensual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbEstadiasMensual.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		modeloEstadiasMensual = (DefaultTableModel)tbEstadiasMensual.getModel();
		modeloEstadiasMensual.addColumn("Fecha Entrada");
		modeloEstadiasMensual.addColumn("Lugar Asignado");
		modeloEstadiasMensual.addColumn("Marca");
		modeloEstadiasMensual.addColumn("Modelo");
		modeloEstadiasMensual.addColumn("Dominio");
		modeloEstadiasMensual.addColumn("Titular");
		modeloEstadiasMensual.addColumn("Telefono");
		modeloEstadiasMensual.addColumn("Dias");
		modeloEstadiasMensual.addColumn("Valor");
		JScrollPane scrollTable = new JScrollPane(tbEstadiasMensual);
		panel.addTab("Estadias Mensuales", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scrollTable, null);
		scrollTable.setVisible(true);
		tbEstadiasDiarias = new JTable();
		tbEstadiasDiarias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbEstadiasDiarias.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		modeloEstadiasDiarias = (DefaultTableModel)tbEstadiasDiarias.getModel();
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
		this.mostrarTablaEstadias();
		JScrollPane scrollTableEstadiasDiarias = new JScrollPane(tbEstadiasDiarias);
		panel.addTab("Estadias Diarias", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scrollTableEstadiasDiarias, null);
		scrollTableEstadiasDiarias.setVisible(true);
		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel2);
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
		labelAtras.setFont(new Font(ROBOTO, Font.PLAIN, 23));
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
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado original
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
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		JSeparator separator12 = new JSeparator();
		separator12.setForeground(new Color(12, 138, 199));
		separator12.setBackground(new Color(12, 138, 199));
		separator12.setBounds(539, 159, 193, 2);
		contentPane.add(separator12);
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if(txtBuscar.getText().equals("")) {
					mostrarTablaEstadias();
				} else {
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
		lblBuscar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaEstadia = tbEstadiasDiarias.getSelectedRow();
				if(filaEstadia >= 0) {
					actualizarEstadias();
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
		lblEditar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				int filaEstadias = tbEstadiasDiarias.getSelectedRow();
				if(filaEstadias >= 0) {
					tbEstadiasDiarias.getValueAt(filaEstadias, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el registro?");
					if(confirmar == JOptionPane.YES_OPTION) {
						String valor = tbEstadiasDiarias.getValueAt(filaEstadias, 4).toString();
						try {
							estadiasController.eliminar(String.valueOf(valor));
							JOptionPane.showMessageDialog(contentPane, "Registro Eliminado con Exito");
							limpiarTabla();
							mostrarTablaEstadias();
						} catch(Exception e) {
							JOptionPane.showMessageDialog(null, ERROR_ELIMINAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
						}
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
		lblEliminar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		modeloEstadiasDiarias.addTableModelListener(tableModelEvent -> {
			actualizarEstadias();
			mostrarTablaEstadias();
		});
	}

	private void mostrarTablaEstadias() {
		try {
			List<Estadia> listaEstadias = this.estadiasController.mostrar();
			modeloEstadiasMensual.setRowCount(0);
			for(Estadia estadia : listaEstadias) {
				modeloEstadiasDiarias.addRow(new Object[]{estadia.getFechaEntrada(), estadia.getLugarAsignado(), estadia.getMarca(), estadia.getModelo(),
						estadia.getDominio(), estadia.getTitular(), estadia.getTelefono(), estadia.getDias(), estadia.getValor()});
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, ERROR_CONSULTAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarTablaEstadiasDominio() {
		try {
			List<Estadia> listaEstadias = this.estadiasController.buscar(txtBuscar.getText());
			for(Estadia estadia : listaEstadias) {
				modeloEstadiasDiarias.addRow(new Object[]{estadia.getFechaEntrada(), estadia.getLugarAsignado(), estadia.getMarca(), estadia.getModelo(),
						estadia.getDominio(), estadia.getTitular(), estadia.getTelefono(), estadia.getDias(), estadia.getValor()});
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, ERROR_CONSULTAR_DOMINIO + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarEstadias() {
		Estadia estadia = new Estadia();
		estadia.setDias(Integer.valueOf(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 7))));
		estadia.setDominio(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 4)));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		estadia.setFechaEntrada(LocalDate.parse(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 0).toString(), dateTimeFormatter));
		estadia.setLugarAsignado(Integer.valueOf(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 1))));
		estadia.setMarca(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 2)));
		estadia.setModelo(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 3)));
		estadia.setTelefono(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 6)));
		estadia.setTitular(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 5)));
		estadia.setValor(String.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 8)));
		try {
			this.estadiasController.actualizarEstadias(estadia);
			JOptionPane.showMessageDialog(this, "Registro modificado con Exito");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, ERROR_ACTUALIZAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	public String calcularValorEstadia(Integer dias) {
		if(dias != 0) {
			dias = Integer.valueOf(modeloEstadiasDiarias.getValueAt(tbEstadiasDiarias.getSelectedRow(), 8).toString());
			int noche = 1500;
			int valor = dias * noche;
			return Integer.toString(valor);
		} else {
			return "";
		}
	}

	private void limpiarTabla() {
		((DefaultTableModel)tbEstadiasDiarias.getModel()).setRowCount(0);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
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