/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ListaEnlazada;
import controlador.exceptions.VacioException;
import controlador.model.AgenteVentaController;
import controlador.model.AutoController;
import controlador.model.MarcaController;
import controlador.model.VentaController;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.AgenteVentas;
import model.Auto;
import vista.tablas.ModeloTablaAuto;
import vista.combobox.UtilVista;
import vista.tablas.ModeloTablaVenta;

/**
 *
 * @author Apolo
 */
public class FrameGenerarVenta extends javax.swing.JFrame {

    private AutoController ac = new AutoController();
    private AgenteVentaController agvc = new AgenteVentaController();
    private MarcaController mc = new MarcaController();
    private VentaController vc = new VentaController();
    private ModeloTablaAuto mtAuto = new ModeloTablaAuto();
    private ModeloTablaVenta mtVenta = new ModeloTablaVenta();
    private ModeloTablaAuto mtAutoVender = new ModeloTablaAuto();
    Integer fila = -1;

    /**
     * Creates new form FrameGenerarVenta
     */
    public FrameGenerarVenta() {
        initComponents();
        setLocationRelativeTo(null);
        limpiar();
        limpiarVentas();
    }

    // Autos
    private void cargarTabla() {
        mtAuto.setAutos(ac.getAutos());
        tblAutos.setModel(mtAuto);
        tblAutos.updateUI();
    }

    public void guardar() {
        if (validar()) {
            try {
                ac.getAuto().setId_Marca(UtilVista.getComboMarcas(cbxMarca).getId());
                ac.getAuto().setColor(UtilVista.getComboColor(cbxColoor));
                ac.getAuto().setModelo(txtModelo.getText().toString());
                ac.getAuto().setPrecio(Double.parseDouble(txtPrecio.getText()));
                ac.getAuto().setCantPuertas(Integer.parseInt(txtPuertas.getText()));
                ac.getAuto().setDescapotable(checkDescapotable.getState());
                if (ac.guardar()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null,
                            "Se ha guardado correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo guardar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Boolean validar() {
        return !txtPrecio.getText().trim().isEmpty()
                && !txtModelo.getText().trim().isEmpty()
                && !txtPuertas.getText().trim().isEmpty();
    }

    private void limpiar() {
        cargarTabla();
        txtPrecio.setText("");
        txtModelo.setText("");
        txtPuertas.setText("");
        checkDescapotable.setState(false);
        ac.setAuto(null);
        ac.setAutos(new ListaEnlazada<>());
        cargarTabla();
        ac.setIndex(-1);
        tblAutos.clearSelection();

        try {
            UtilVista.cargarMarca(cbxMarca);
            UtilVista.cargarColor(cbxColoor);
            cage();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificar() {
        if (validar()) {
            try {
                ac.getAuto().setId_Marca(UtilVista.getComboMarcas(cbxMarca).getId());
                ac.getAuto().setColor(UtilVista.getComboColor(cbxColoor));
                ac.getAuto().setModelo(txtModelo.getText().toString());
                ac.getAuto().setPrecio(Double.parseDouble(txtPrecio.getText()));
                ac.getAuto().setCantPuertas(Integer.parseInt(txtPuertas.getText()));
                ac.getAuto().setDescapotable(checkDescapotable.getState());
                Integer fila = tblAutos.getSelectedRow();
                if (ac.actualizar(fila)) {
                    limpiar();
                    JOptionPane.showMessageDialog(null,
                            "Se ha modificado correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo modificar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void seleccionar() {
        Integer fila = tblAutos.getSelectedRow();
        //fila = tblAutos.getSelectedRow();
        ac.setIndex(fila);
        if (ac.getIndex().intValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecccione la fila", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ac.setAuto(mtAuto.getAutos().get(ac.getIndex()));
                txtPrecio.setText(ac.getAuto().getPrecio().toString());
                txtModelo.setText(ac.getAuto().getModelo());
                txtPuertas.setText(ac.getAuto().getCantPuertas().toString());

                cbxMarca.setSelectedIndex(ac.getAuto().getId() - 1);
                cbxColoor.setSelectedIndex(ac.getAuto().getId() - 1);
                checkDescapotable.setState(ac.getAuto().getDescapotable());
            } catch (VacioException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Agente Ventas
    public void guardarVededor() {
        if (validarVendedor()) {
            try {
                agvc.getAgenteVenta().setNombres(txtNombres.getText());
                agvc.getAgenteVenta().setApellidos(txtApellidos.getText());
                agvc.getAgenteVenta().setNacionalidad(txtNacionalidad.getText());
                agvc.getAgenteVenta().setCedula(txtCedula.getText());
                agvc.getAgenteVenta().setFechaNacimiento(LocalDate.of(Integer.parseInt(txtAnio.getText()),
                        Integer.parseInt(txtMes.getText()),
                        Integer.parseInt(txtDia.getText())));

                if (agvc.guardar()) {
                    limpiarVendedor();
                    JOptionPane.showMessageDialog(null,
                            "Se ha guardado correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo guardar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Boolean validarVendedor() {
        return !txtNombres.getText().trim().isEmpty()
                && !txtApellidos.getText().trim().isEmpty()
                && !txtNacionalidad.getText().trim().isEmpty()
                && !txtCedula.getText().trim().isEmpty()
                && !txtAnio.getText().trim().isEmpty()
                && !txtMes.getText().trim().isEmpty()
                && !txtDia.getText().trim().isEmpty();
    }

    public void limpiarVendedor() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtNacionalidad.setText("");
        txtCedula.setText("");
        txtAnio.setText("");
        txtMes.setText("");
        txtDia.setText("");
        cage();
    }

    public void cage() {
        try {
            UtilVista.cargarAgenteVentas(cbxAgentesVentas);
        } catch (VacioException ex) {
            Logger.getLogger(FrameGenerarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ventas
    private void limpiarVentas() {
        cargarTablaAutosVender();
        cargarTablaVentas();
        txtCodigo.setText(vc.generatedCode());
        txtCodigo.setEnabled(false);
        txtTotal.setEnabled(false);
        generarTotalvista();
        vc.setVenta(null);
        vc.setVentas(new ListaEnlazada<>());
        cargarTablaAutosVender();
        vc.setIndex(-1);
        tblAutos.clearSelection();
        tblVentas.clearSelection();

    }

    public void generarTotalvista() {
        try {
            txtTotal.setText(vc.generarTotal().toString());
        } catch (VacioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cargarTablaAutosVender() {
        mtAutoVender.setAutos(vc.getVenta().getAutosVenderan());
        tblAutoVender.setModel(mtAutoVender);
        tblAutoVender.updateUI();
        generarTotalvista();
    }

    public void cargarTablaVentas() {
        mtVenta.setVentas(vc.getVentas());
        tblVentas.setModel(mtVenta);
        tblVentas.updateUI();
    }

    private Boolean validarVenta() {
        return !txtCodigo.getText().trim().isEmpty();
    }

    public void seleccionarAutosVender() {
        Integer fila = tblAutos.getSelectedRow();
        //fila = tblAutos.getSelectedRow();
        ac.setIndex(fila);
        if (ac.getIndex().intValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecccione la fila", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                vc.getVenta().getAutosVenderan().add(mtAuto.getAutos().get(ac.getIndex()));
                cargarTablaAutosVender();
            } catch (VacioException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void guardarVenta() {
        if (validarVenta()) {
            try {
                vc.getVenta().setId_AgenteVenta(UtilVista.getComboAgenteVentas(cbxAgentesVentas).getId());
                vc.getVenta().setTotal(Double.parseDouble(txtTotal.getText()));
                vc.getVenta().setCodigo(txtCodigo.getText());
                if (vc.guardar()) {
                    limpiarVentas();
                    JOptionPane.showMessageDialog(null,
                            "Se ha guardado correctamente", "OK",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo guardar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void seleccionarVenta() {
        Integer fila = tblVentas.getSelectedRow();
        //fila = tblAutos.getSelectedRow();
        vc.setIndex(fila);
        if (vc.getIndex().intValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecccione la fila", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                vc.setVenta(mtVenta.getVentas().get(vc.getIndex()));
                txtCodigo.setText(vc.getVenta().getCodigo().toString());
                txtTotal.setText(vc.getVenta().getTotal().toString());
                cbxAgentesVentas.setSelectedIndex(vc.getVenta().getId() - 1);
                cargarTablaAutosVender();

            } catch (VacioException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void modificarVenta() {
        try {
            
            vc.getVenta().setId_AgenteVenta(UtilVista.getComboAgenteVentas(cbxAgentesVentas).getId());
            if (ac.actualizar(fila)) {
                limpiarVentas();
                JOptionPane.showMessageDialog(null,
                        "Se ha modificado correctamente", "OK",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "No se pudo modificar", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        cbxMarca = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxColoor = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAutos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        checkDescapotable = new java.awt.Checkbox();
        btnSeleccionarAutoVender = new javax.swing.JButton();
        txtModelo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPuertas = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        btnGuardarVendedor = new javax.swing.JButton();
        txtMes = new javax.swing.JTextField();
        txtDia = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbxAgentesVentas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAutoVender = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(638, 1112));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(638, 1112));
        jPanel1.setMinimumSize(new java.awt.Dimension(638, 1112));
        jPanel1.setPreferredSize(new java.awt.Dimension(638, 1112));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Autos"));

        jLabel1.setText("Precio");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        cbxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMarcaActionPerformed(evt);
            }
        });

        jLabel8.setText("Marca");

        cbxColoor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxColoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxColoorActionPerformed(evt);
            }
        });

        jLabel9.setText("Color");

        tblAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAutos);

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Modelo");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        checkDescapotable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        checkDescapotable.setLabel("Descapotable");

        btnSeleccionarAutoVender.setText("Seleccionar Auto a Vender");
        btnSeleccionarAutoVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarAutoVenderActionPerformed(evt);
            }
        });

        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        jLabel13.setText("Cantida de puertas");

        txtPuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPuertasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPuertas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel10))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxColoor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtModelo))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(checkDescapotable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnSeleccionarAutoVender, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(353, 353, 353))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxColoor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPuertas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkDescapotable, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(btnModificar))
                    .addComponent(btnSeleccionarAutoVender, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 310));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Vendedores"));

        jLabel2.setText("Nombres");

        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos");

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        jLabel4.setText("Cedula");

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        jLabel5.setText("Nacionalidad");

        txtNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNacionalidadActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha Nacimiento");

        txtAnio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnio.setText("y");
        txtAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAnioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtAnioMousePressed(evt);
            }
        });
        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });

        btnGuardarVendedor.setText("Guardar");
        btnGuardarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVendedorActionPerformed(evt);
            }
        });

        txtMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMes.setText("m");
        txtMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMesMousePressed(evt);
            }
        });
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        txtDia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDia.setText("d");
        txtDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiaMouseClicked(evt);
            }
        });
        txtDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 110, 110))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardarVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarVendedor)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 490, 270));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar Ventas"));

        jLabel7.setText("Vendedor");

        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        cbxAgentesVentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAgentesVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAgentesVentasActionPerformed(evt);
            }
        });

        tblAutoVender.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblAutoVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblAutoVender);

        jLabel11.setText("Autos a Vender:");

        jButton4.setText("Guardar Venta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("Codigo");

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Total:");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel15.setText("$");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCodigo))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cbxAgentesVentas, 0, 146, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 33, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton4)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxAgentesVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 620, 210));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas"));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVentas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 840, 620, 260));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 1110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacionalidadActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void cbxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMarcaActionPerformed

    private void cbxColoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxColoorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxColoorActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuardarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVendedorActionPerformed
        guardarVededor();
    }//GEN-LAST:event_btnGuardarVendedorActionPerformed

    private void cbxAgentesVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAgentesVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAgentesVentasActionPerformed

    private void btnSeleccionarAutoVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAutoVenderActionPerformed
        
        seleccionarAutosVender();
    }//GEN-LAST:event_btnSeleccionarAutoVenderActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        guardarVenta();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtPuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPuertasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPuertasActionPerformed

    private void tblAutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAutosMouseClicked
        if(evt.getClickCount() == 2)
            seleccionar();
    }//GEN-LAST:event_tblAutosMouseClicked

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaActionPerformed

    private void txtAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnioMouseClicked
        txtAnio.setText("");
    }//GEN-LAST:event_txtAnioMouseClicked

    private void txtMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMesMouseClicked
        txtMes.setText("");
    }//GEN-LAST:event_txtMesMouseClicked

    private void txtDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiaMouseClicked
        txtDia.setText("");
    }//GEN-LAST:event_txtDiaMouseClicked

    private void txtMesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMesMousePressed
        //
    }//GEN-LAST:event_txtMesMousePressed

    private void txtAnioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioMousePressed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        if(evt.getClickCount() == 2)
            seleccionarVenta();
        
    }//GEN-LAST:event_tblVentasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modificarVenta();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameGenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGenerarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGenerarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarVendedor;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionarAutoVender;
    private javax.swing.JComboBox<String> cbxAgentesVentas;
    private javax.swing.JComboBox<String> cbxColoor;
    private javax.swing.JComboBox<String> cbxMarca;
    private java.awt.Checkbox checkDescapotable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAutoVender;
    private javax.swing.JTable tblAutos;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPuertas;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
