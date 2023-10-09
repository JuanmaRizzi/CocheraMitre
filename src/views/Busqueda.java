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
import java.util.ArrayList;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import controller.EstadiaController;
import model.Estadia;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {
	private static final Logger logger = Logger.getLogger(Busqueda.class);
	private int xMouse;
	private int yMouse;
	private transient EstadiaController estadiaController;
	private static final String ROBOTO = "Roboto";
	private static final String ERROR = "ERROR";
	private static final String ERROR_CONSULTAR_ESTADIA = "HA OCURRIDO UN ERROR AL INTENTAR CONSULTAR LAS ESTADÍAS: ";
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

	public Busqueda() {
		this.estadiaController = new EstadiaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		JPanel jPanelPrincipal = this.obtenerJPanelPrincipal();
		JTextField jTextFieldBuscar = this.obtenerTextFieldBuscar();
		jPanelPrincipal.add(jTextFieldBuscar);
		jPanelPrincipal.add(this.obtenerLabelSistemaDeBusqueda());
		JTabbedPane jTabbedPane = this.obtenerTabbedPane();
		jPanelPrincipal.add(jTabbedPane);
		JTable jTableEstadiasMensual = this.obtenerTabla();
		JScrollPane jScrollPaneMensuales = new JScrollPane(jTableEstadiasMensual);
		jTabbedPane.addTab("Estadias Mensuales", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), jScrollPaneMensuales, null);
		jScrollPaneMensuales.setVisible(true);
		JTable jTableEstadiasDiarias = this.obtenerTabla();
		JScrollPane jScrollPaneDiarias = new JScrollPane(jTableEstadiasDiarias);
		jTabbedPane.addTab("Estadias Diarias", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), jScrollPaneDiarias, null);
		jScrollPaneDiarias.setVisible(true);
		this.consultarTablaEstadias(null, jTableEstadiasDiarias, jTableEstadiasMensual);
		JLabel lblNewLabelCocheraMitre = new JLabel("Cochera-Mitre");
		lblNewLabelCocheraMitre.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabelCocheraMitre.setBounds(56, 51, 104, 107);
		jPanelPrincipal.add(lblNewLabelCocheraMitre);
		JPanel jPanelHeader = this.obtenerHeader();
		jPanelPrincipal.add(jPanelHeader);
		JLabel labelAtras = this.obtenerLabelAtras();
		JPanel jPanelBotonAtras = this.obtenerBotonAtras(labelAtras);
		jPanelBotonAtras.add(labelAtras);
		jPanelHeader.add(jPanelBotonAtras);
		JLabel labelExit = this.obtenerLabelExit();
		JPanel jPanelBotonExit = this.obtenerBotonExit(labelExit);
		jPanelBotonExit.add(labelExit);
		jPanelHeader.add(jPanelBotonExit);
		JSeparator jSeparator = this.obtenerSeparator();
		jPanelPrincipal.add(jSeparator);
		JPanel jPanelBotonBuscar = this.obtenerBotonBuscarEstadias(jTextFieldBuscar, jTableEstadiasDiarias, jTableEstadiasMensual);
		jPanelPrincipal.add(jPanelBotonBuscar);
		JLabel jLabelBuscar = this.obtenerLabelBuscar();
		jPanelBotonBuscar.add(jLabelBuscar);
		JPanel jPanelBotonEditar = this.obtenerBotonEditarEstadia(jTableEstadiasDiarias, jTableEstadiasMensual);
		jPanelPrincipal.add(jPanelBotonEditar);
		JLabel jLabelEditar = this.obtenerLabelEditar();
		jPanelBotonEditar.add(jLabelEditar);
		JPanel jPanelBotonEliminar = this.obtenerBotonEliminarEstadia(jPanelPrincipal, jTableEstadiasDiarias, jTableEstadiasMensual);
		jPanelPrincipal.add(jPanelBotonEliminar);
		JLabel jLabelEliminar = this.obtenerLabelEliminar();
		jPanelBotonEliminar.add(jLabelEliminar);
		setResizable(false);
	}

	private JPanel obtenerJPanelPrincipal() {
		JPanel jPanelPrincipal = new JPanel();
		jPanelPrincipal.setBackground(Color.WHITE);
		jPanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jPanelPrincipal);
		jPanelPrincipal.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		return jPanelPrincipal;
	}

	private JTextField obtenerTextFieldBuscar() {
		JTextField jTextFieldBuscar = new JTextField();
		jTextFieldBuscar.setBounds(536, 127, 193, 31);
		jTextFieldBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jTextFieldBuscar.setColumns(10);
		return jTextFieldBuscar;
	}

	private JLabel obtenerLabelSistemaDeBusqueda() {
		JLabel jLabel = new JLabel("BÚSQUEDA");
		jLabel.setForeground(new Color(12, 138, 199));
		jLabel.setFont(new Font("Roboto Black", Font.BOLD, 24));
		jLabel.setBounds(331, 62, 280, 42);
		return jLabel;
	}

	private JTabbedPane obtenerTabbedPane() {
		JTabbedPane jTabbedPane = new JTabbedPane(SwingConstants.TOP);
		jTabbedPane.setBackground(new Color(12, 138, 199));
		jTabbedPane.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		jTabbedPane.setBounds(20, 169, 865, 328);
		return jTabbedPane;
	}

	private JTable obtenerTabla() {
		JTable jTableEstadias = new JTable();
		jTableEstadias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTableEstadias.setFont(new Font(ROBOTO, Font.PLAIN, 16));
		jTableEstadias.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		jTableEstadias.setDefaultRenderer(String.class, defaultTableCellRenderer);
		DefaultTableModel defaultTableModel = this.obtenerDefaultTableModelMensual(jTableEstadias);
		for(int columnIndex = 0; columnIndex < defaultTableModel.getColumnCount(); columnIndex++) {
			jTableEstadias.getColumnModel().getColumn(columnIndex).setCellRenderer(defaultTableCellRenderer);
		}
		return jTableEstadias;
	}

	private DefaultTableModel obtenerDefaultTableModelMensual(JTable jTableEstadias) {
		DefaultTableModel defaultTableModel = (DefaultTableModel)jTableEstadias.getModel();
		this.setColumnasDefaultTableModel(defaultTableModel);
		return defaultTableModel;
	}

	private void setColumnasDefaultTableModel(DefaultTableModel defaultTableModelEstadia) {
		defaultTableModelEstadia.addColumn("Fecha Entrada");
		defaultTableModelEstadia.addColumn("Lugar Asignado");
		defaultTableModelEstadia.addColumn("Marca");
		defaultTableModelEstadia.addColumn("Modelo");
		defaultTableModelEstadia.addColumn("Dominio");
		defaultTableModelEstadia.addColumn("Titular");
		defaultTableModelEstadia.addColumn("Telefono");
		defaultTableModelEstadia.addColumn("Dias");
		defaultTableModelEstadia.addColumn("Valor");
		defaultTableModelEstadia.addColumn("Mensual");
		defaultTableModelEstadia.setRowCount(0);
	}

	private JPanel obtenerHeader() {
		JPanel jPanelHeader = new JPanel();
		jPanelHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		jPanelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		jPanelHeader.setLayout(null);
		jPanelHeader.setBackground(Color.WHITE);
		jPanelHeader.setBounds(0, 0, 910, 36);
		return jPanelHeader;
	}

	private JPanel obtenerBotonAtras(JLabel labelAtras) {
		JPanel jPanelBotonAtras = new JPanel();
		jPanelBotonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jPanelBotonAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPanelBotonAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		jPanelBotonAtras.setLayout(null);
		jPanelBotonAtras.setBackground(Color.WHITE);
		jPanelBotonAtras.setBounds(0, 0, 53, 36);
		return jPanelBotonAtras;
	}

	private JLabel obtenerLabelAtras() {
		JLabel labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font(ROBOTO, Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		return labelAtras;
	}

	private JPanel obtenerBotonExit(JLabel labelExit) {
		JPanel jPanelBotonExit = new JPanel();
		jPanelBotonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			// Al usuario pasar el mouse por el botón este cambiará de color
			@Override
			public void mouseEntered(MouseEvent e) {
				jPanelBotonExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			// Al usuario quitar el mouse por el botón este volverá al estado original
			@Override
			public void mouseExited(MouseEvent e) {
				jPanelBotonExit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		jPanelBotonExit.setLayout(null);
		jPanelBotonExit.setBackground(Color.WHITE);
		jPanelBotonExit.setBounds(857, 0, 53, 36);
		return jPanelBotonExit;
	}

	private JLabel obtenerLabelExit() {
		JLabel labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		return labelExit;
	}

	private JSeparator obtenerSeparator() {
		JSeparator jSeparator = new JSeparator();
		jSeparator.setForeground(new Color(12, 138, 199));
		jSeparator.setBackground(new Color(12, 138, 199));
		jSeparator.setBounds(539, 159, 193, 2);
		return jSeparator;
	}

	private JPanel obtenerBotonBuscarEstadias(JTextField jTextFieldBuscar, JTable jTableEstadiasDiarias, JTable jTableEstadiasMensual) {
		JPanel jPanelBotonBuscar = new JPanel();
		jPanelBotonBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla(jTableEstadiasDiarias);
				limpiarTabla(jTableEstadiasMensual);
				consultarTablaEstadias(jTextFieldBuscar, jTableEstadiasDiarias, jTableEstadiasMensual);
			}
		});
		jPanelBotonBuscar.setLayout(null);
		jPanelBotonBuscar.setBackground(new Color(12, 138, 199));
		jPanelBotonBuscar.setBounds(748, 125, 122, 35);
		jPanelBotonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		return jPanelBotonBuscar;
	}

	private JLabel obtenerLabelBuscar() {
		JLabel jLabelBuscar = new JLabel("BUSCAR");
		jLabelBuscar.setBounds(0, 0, 122, 35);
		jLabelBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelBuscar.setForeground(Color.WHITE);
		jLabelBuscar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		return jLabelBuscar;
	}

	private JPanel obtenerBotonEditarEstadia(JTable jTableEstadiasDiarias, JTable jTableEstadiasMensual) {
		JPanel jPanelBotonEditar = new JPanel();
		jPanelBotonEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaEstadiaDiaria = jTableEstadiasDiarias.getSelectedRow();
				int filaEstadiaMensual = jTableEstadiasMensual.getSelectedRow();
				if(filaEstadiaDiaria >= 0) {
					actualizarEstadias(jTableEstadiasDiarias);
					limpiarTabla(jTableEstadiasDiarias);
				} else if(filaEstadiaMensual >= 0) {
					actualizarEstadias(jTableEstadiasMensual);
					limpiarTabla(jTableEstadiasMensual);
				}
				mostrarTablaEstadias(null, jTableEstadiasDiarias, jTableEstadiasMensual);
			}
		});
		jPanelBotonEditar.setLayout(null);
		jPanelBotonEditar.setBackground(new Color(12, 138, 199));
		jPanelBotonEditar.setBounds(635, 508, 122, 35);
		jPanelBotonEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		return jPanelBotonEditar;
	}

	private JLabel obtenerLabelEditar() {
		JLabel jLabelEditar = new JLabel("EDITAR");
		jLabelEditar.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelEditar.setForeground(Color.WHITE);
		jLabelEditar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		jLabelEditar.setBounds(0, 0, 122, 35);
		return jLabelEditar;
	}

	private JPanel obtenerBotonEliminarEstadia(JPanel jPanelPrincipal, JTable jTableEstadiasDiarias, JTable jTableEstadiasMensual) {
		JPanel jPanelBotonEliminar = new JPanel();
		jPanelBotonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaEstadiasDiaria = jTableEstadiasDiarias.getSelectedRow();
				int filaEstadiasMensual = jTableEstadiasMensual.getSelectedRow();
				if(filaEstadiasDiaria >= 0) {
					eliminarEstadia(jTableEstadiasDiarias, filaEstadiasDiaria, jPanelPrincipal);
				} else if(filaEstadiasMensual >= 0) {
					eliminarEstadia(jTableEstadiasMensual, filaEstadiasMensual, jPanelPrincipal);
				}
				consultarTablaEstadias(null, jTableEstadiasDiarias, jTableEstadiasMensual);
			}
		});
		jPanelBotonEliminar.setLayout(null);
		jPanelBotonEliminar.setBackground(new Color(12, 138, 199));
		jPanelBotonEliminar.setBounds(767, 508, 122, 35);
		jPanelBotonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		return jPanelBotonEliminar;
	}

	private void eliminarEstadia(JTable jTableEstadias, int filaEstadias, JPanel jPanelPrincipal) {
		int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el registro?");
		if(confirmar == JOptionPane.YES_OPTION) {
			String valor = jTableEstadias.getValueAt(filaEstadias, 4).toString();
			try {
				estadiaController.eliminarEstadia(String.valueOf(valor));
				JOptionPane.showMessageDialog(jPanelPrincipal, "Registro Eliminado con Exito");
				limpiarTabla(jTableEstadias);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, ERROR_ELIMINAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private JLabel obtenerLabelEliminar() {
		JLabel jLabelEliminar = new JLabel("ELIMINAR");
		jLabelEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelEliminar.setForeground(Color.WHITE);
		jLabelEliminar.setFont(new Font(ROBOTO, Font.PLAIN, 18));
		jLabelEliminar.setBounds(0, 0, 122, 35);
		return jLabelEliminar;
	}

	private void consultarTablaEstadias(JTextField jTextFieldBuscar, JTable jTableEstadiasDiarias, JTable jTableEstadiasMensual) {
		List<Estadia> listaEstadias = this.obtenerListaEstadias(jTextFieldBuscar);
		this.mostrarTablaEstadias(listaEstadias, jTableEstadiasDiarias, jTableEstadiasMensual);
	}

	private List<Estadia> obtenerListaEstadias(JTextField jTextFieldBuscar) {
		List<Estadia> listaEstadias = new ArrayList<>();
		try {
			if(jTextFieldBuscar != null && !jTextFieldBuscar.getText().equals("")) {
				listaEstadias = this.estadiaController.buscarEstadiasPorDominio(jTextFieldBuscar.getText());
			} else {
				listaEstadias = this.estadiaController.buscarEstadias();
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, ERROR_CONSULTAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.WARNING_MESSAGE);
		}
		return listaEstadias;
	}

	private void mostrarTablaEstadias(List<Estadia> listaEstadias, JTable jTableEstadiasDiarias, JTable jTableEstadiasMensual) {
		if(listaEstadias != null && !listaEstadias.isEmpty()) {
			DefaultTableModel defaultTableModelEstadiaDiaria = ((DefaultTableModel)jTableEstadiasDiarias.getModel());
			DefaultTableModel defaultTableModelEstadiaMensual = ((DefaultTableModel)jTableEstadiasMensual.getModel());
			for(Estadia estadia : listaEstadias) {
				if(!estadia.getMensual().equals("SI")) {
					defaultTableModelEstadiaDiaria.addRow(this.obtenerRegistro(estadia));
				} else {
					defaultTableModelEstadiaMensual.addRow(this.obtenerRegistro(estadia));
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "NO SE ENCONTRARON ESTADÍAS", "Mensaje", JOptionPane.ERROR_MESSAGE);
		}
	}

	private Object[] obtenerRegistro(Estadia estadia) {
		return new Object[]{estadia.getFechaEntrada(), estadia.getLugarAsignado(), estadia.getMarca(), estadia.getModelo(), estadia.getDominio(),
				estadia.getTitular(), estadia.getTelefono(), estadia.getDias(), estadia.getValor(), estadia.getMensual()};
	}

	private void actualizarEstadias(JTable jTableEstadias) {
		try {
			DefaultTableModel defaultTableModel = (DefaultTableModel)jTableEstadias.getModel();
			Estadia estadia = new Estadia();
			estadia.setDias(Integer.valueOf(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 7))));
			estadia.setDominio(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 4)));
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			estadia.setFechaEntrada(LocalDate.parse(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 0).toString(), dateTimeFormatter));
			estadia.setLugarAsignado(Integer.valueOf(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 1))));
			String marca = (String)defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 2);
			estadia.setMarca(String.valueOf(marca));
			estadia.setModelo(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 3)));
			estadia.setTelefono(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 6)));
			estadia.setTitular(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 5)));
			estadia.setValor(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 8)));
			estadia.setMensual(String.valueOf(defaultTableModel.getValueAt(jTableEstadias.getSelectedRow(), 9)));
			this.estadiaController.actualizarEstadia(estadia);
			JOptionPane.showMessageDialog(this, "Registro modificado con Exito");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, ERROR_ACTUALIZAR_ESTADIA + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	public String calcularValorEstadia(Integer dias, JTable jTableEstadiasDiarias) {
		if(dias != 0) {
			DefaultTableModel defaultTableModelEstadiaDiaria = (DefaultTableModel)jTableEstadiasDiarias.getModel();
			dias = Integer.valueOf(defaultTableModelEstadiaDiaria.getValueAt(jTableEstadiasDiarias.getSelectedRow(), 7).toString());
			int noche = 1500;
			int valor = dias * noche;
			return Integer.toString(valor);
		} else {
			return "Busqueda -> calcularValorEstadia -> dias == 0 -> (TODO - FALTA VER QUÉ SE DEVUELVE ACÁ...)";
		}
	}

	private void limpiarTabla(JTable jTableEstadias) {
		((DefaultTableModel)jTableEstadias.getModel()).setRowCount(0);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x" e "y"
	private void headerMousePressed(java.awt.event.MouseEvent mouseEvent) {
		xMouse = mouseEvent.getX();
		yMouse = mouseEvent.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent mouseEvent) {
		int x = mouseEvent.getXOnScreen();
		int y = mouseEvent.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}