package com.lfs.GUI ;

import com.jeff.util.Print ;
import com.lfs.service.GetData ;
import com.lfs.service.URLConnection ;
import com.lfs.service.WindowHandler ;
import com.lfs.service.outputXls ;
import java.awt.Color ;
import java.awt.Dimension ;
import java.awt.GridBagConstraints ;
import java.awt.GridBagLayout ;
import java.awt.Insets ;
import java.awt.SystemColor ;
import java.awt.Toolkit ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.io.PrintStream ;
import java.text.DecimalFormat ;
import java.text.SimpleDateFormat ;
import java.util.ArrayList ;
import java.util.Date ;
import java.util.HashMap ;
import java.util.Map ;
import javax.swing.DefaultComboBoxModel ;
import javax.swing.ImageIcon ;
import javax.swing.JButton ;
import javax.swing.JCheckBox ;
import javax.swing.JComboBox ;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JOptionPane ;
import javax.swing.JPanel ;
import javax.swing.JProgressBar ;
import javax.swing.JTextField ;
import javax.swing.JToggleButton ;
import javax.swing.SwingUtilities ;
import javax.swing.UIManager ;
import javax.swing.border.EmptyBorder ;
import javax.swing.plaf.FontUIResource ;

public class LFS_GUI extends JFrame
{

	private static final long serialVersionUID = 1L ;
	private JPanel contentPane ;
	public static JTextField txtWebURL ;
	public static DecimalFormat df = new DecimalFormat( "#.###" ) ;
	JLabel[] jvalLab = new JLabel[ 8 ] ;
	JLabel[] jval2Lab = new JLabel[ 8 ] ;
	JTextField[] jtxtNode = new JTextField[ 8 ] ;
	JComboBox < ? >[] cbTorHArr = new JComboBox[ 8 ] ;
	JComboBox < Double > jcbByte = new JComboBox() ;
	JCheckBox[] chkbOutputXls = new JCheckBox[ 8 ] ;
	JComboBox < Double > cbByte = new JComboBox() ;
	JProgressBar progressBar = new JProgressBar() ;
	JToggleButton tbOutputExcel = new JToggleButton() ;
	JLabel lblSecond = new JLabel( "second" ) ;
	String port = "" ;
	String web = "" ;
	static boolean running = false ;
	String[] Unittype = { "Temperature" , "Humidity" , "TemperatureK2C" } ;
	Double[] picByte = { Double.valueOf( 1024.0D ) , Double.valueOf( 4096.0D ) } ;

	private JTextField txtSleeptme ;
	private JTextField txtwebPort ;
	SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" ) ;
	Map < String , Double > temp = new HashMap() ;

	private JTextField txtfNode ;

	private JTextField txtfNode_1 ;

	private JTextField txtfNode_2 ;

	private JTextField txtfNode_3 ;
	private JTextField txtfNode_4 ;
	private JTextField txtfNode_5 ;
	private JTextField txtfNode_6 ;
	private JTextField txtfNode_7 ;

	public static enum Unittype
	{
		Temperature , Humidity;
	}

	public static void main ( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{

			public void run ()
			{
				LFS_GUI frame = new LFS_GUI() ;
				frame.pack() ;
				frame.setVisible( true ) ;
			}
		} ) ;
	}

	public LFS_GUI ()
	{
		setIconImage( Toolkit.getDefaultToolkit()
				.getImage( "D:\\KengWoNCHUFile\\Master_2.1\\Seminar\\png\\farm\\006-scarecrow.png" ) ) ;
		setTitle( "LFS" ) ;
		setBounds( 100 , 100 , 738 , 645 ) ;
		manageGUI.setUIFont( new FontUIResource( "微軟正黑體" , 1 , 15 ) ) ;

		contentPane = new JPanel() ;
		contentPane.setBackground( new Color( 240 , 240 , 240 ) ) ;
		contentPane.setBorder( new EmptyBorder( 0 , 0 , 0 , 0 ) ) ;

		contentPane.setAutoscrolls( true ) ;
		setContentPane( contentPane ) ;
		GridBagLayout gbl_contentPane = new GridBagLayout() ;
		//gbl_contentPane.columnWidths = new int[] { 0, 0,0 } ;
		//gbl_contentPane.rowHeights = new int[ 13 ] ;
		//gbl_contentPane.columnWeights = new double[] { 0.0D , 0.0D, 0.0 , 0.0D , 0.0D , 0.0D } ;
		//gbl_contentPane.rowWeights = new double[] { 0.0D , 0.0D , 0.0D , 0.0D , 0.0D , 0.0D , 0.0D , 0.0D , 0.0D ,
		//		0.0D , 0.0D , 0.0D , Double.MIN_VALUE } ;
		contentPane.setLayout( gbl_contentPane ) ;

		JLabel lblwebaddress = new JLabel( "網址" ) ;
		lblwebaddress.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblwebaddress = new GridBagConstraints() ;
		gbc_lblwebaddress.fill = 2 ;
		gbc_lblwebaddress.gridwidth = 2 ;
		gbc_lblwebaddress.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblwebaddress.gridx = 0 ;
		gbc_lblwebaddress.gridy = 0 ;
		contentPane.add( lblwebaddress , gbc_lblwebaddress ) ;
		
				txtWebURL = new JTextField() ;
				txtWebURL.setHorizontalAlignment( 0 ) ;
				txtWebURL.setText( "140.120.115.71" ) ;
				GridBagConstraints gbc_txtWebURL = new GridBagConstraints() ;
				gbc_txtWebURL.fill = GridBagConstraints.BOTH;
				gbc_txtWebURL.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_txtWebURL.gridx = 2 ;
				gbc_txtWebURL.gridy = 0 ;
				contentPane.add( txtWebURL , gbc_txtWebURL ) ;
				txtWebURL.setColumns( 10 ) ;

		JLabel lblPort = new JLabel( "Port" ) ;
		lblPort.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblPort = new GridBagConstraints() ;
		gbc_lblPort.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblPort.gridx = 3 ;
		gbc_lblPort.gridy = 0 ;
		contentPane.add( lblPort , gbc_lblPort ) ;

		txtwebPort = new JTextField() ;
		txtwebPort.setText( "118" ) ;
		txtwebPort.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_txtwebPort = new GridBagConstraints() ;
		gbc_txtwebPort.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtwebPort.fill = 1 ;
		gbc_txtwebPort.gridx = 4 ;
		gbc_txtwebPort.gridy = 0 ;
		contentPane.add( txtwebPort , gbc_txtwebPort ) ;
		txtwebPort.setColumns( 10 ) ;

		JButton btnUpdata = new JButton( "更新" ) ;
		GridBagConstraints gbc_btnUpdata = new GridBagConstraints() ;
		gbc_btnUpdata.gridwidth = 2 ;
		gbc_btnUpdata.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_btnUpdata.gridx = 0 ;
		gbc_btnUpdata.gridy = 1 ;
		contentPane.add( btnUpdata , gbc_btnUpdata ) ;
		btnUpdata.addActionListener( new ActionListener()
		{

			public void actionPerformed ( ActionEvent e )
			{

				double picByte = ( (Double) cbByte.getSelectedItem() ).doubleValue() ;
				web = ( "http://" + LFS_GUI.txtWebURL.getText() + ":" + txtwebPort.getText() + "/simple.cgi" ) ;
				port = txtwebPort.getText() ;

				ArrayList < String > adValues = null ;
				adValues = new URLConnection().connection( web , port , picByte ) ;
				for (int i = 0; i < jvalLab.length; i++)
				{
					jvalLab[i].setText( (String) adValues.get( i ) ) ;

					unitUpdate( i , jvalLab , jval2Lab , cbTorHArr[i].getSelectedIndex() , Double.valueOf( picByte ) ) ;
				}

			}

		} ) ;
		JToggleButton btnSelected = new JToggleButton( "開/關" ) ;
		btnSelected.setForeground( Color.BLACK ) ;
		btnSelected.setBackground( UIManager.getColor( "menu" ) ) ;
		btnSelected.setSelectedIcon( new ImageIcon( "resources/if_toggle-on.png " ) ) ;
		btnSelected.setIcon( new ImageIcon( "resources/if_toggle-off.png" ) ) ;
		GridBagConstraints gbc_btnSelected = new GridBagConstraints() ;
		gbc_btnSelected.fill = GridBagConstraints.BOTH;
		gbc_btnSelected.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_btnSelected.gridx = 2 ;
		gbc_btnSelected.gridy = 1 ;
		contentPane.add( btnSelected , gbc_btnSelected ) ;
		btnSelected.addActionListener( new ActionListener()
		{

			public void actionPerformed ( final ActionEvent ev )
			{

				final outputXls outXls = new outputXls( txtwebPort.getText() ) ;
				Thread outThread = new Thread( new Runnable()
				{

					public void run ()
					{

						synchronized (this)
						{
							web = ( "http://" + LFS_GUI.txtWebURL.getText() + ":" + txtwebPort.getText()
									+ "/simple.cgi" ) ;
							port = txtwebPort.getText() ;
							JToggleButton tBtn = (JToggleButton) ev.getSource() ;
							progressBar.setValue( 0 ) ;

							while (tBtn.isSelected())
							{

								progressBar.setValue( 10 ) ;
								Print.println( sdf.format( new Date() ) + "  " ) ;
								try
								{
									double picByte = ( (Double) cbByte.getSelectedItem() ).doubleValue() ;
									ArrayList < String > adValues = new URLConnection().connection( web , port ,
											picByte ) ;

									for (int i = 0; i < jval2Lab.length; i++)
									{

										jvalLab[i].setText( (String) adValues.get( i ) ) ;
										unitUpdate( i , jvalLab , jval2Lab , cbTorHArr[i].getSelectedIndex() ,
												Double.valueOf( picByte ) ) ;
										if (chkbOutputXls[i].isSelected())
										{
											String strID = "" ;

											switch (cbTorHArr[i].getSelectedIndex())
											{
												case 0:
													strID = "T" ;
													break ;
												case 1:
													strID = "H" ;
													break ;
											}

											if (!jtxtNode[i].getText().toString().isEmpty())
											{
												temp.put( strID + jtxtNode[i].getText().toString() , Double.valueOf(
														Double.parseDouble( jval2Lab[i].getText().toString() ) ) ) ;
											} else
											{
												tBtn.setSelected( false ) ;
												JOptionPane.showMessageDialog( null ,
														"請輸入sensor(" + ( i + 1 ) + ")中 Node的編號" , "錯誤" , 0 ) ;
											}
										}
									}

									progressBar.setValue( 50 ) ;
									if (( tbOutputExcel.isSelected() ) && ( outXls.isFindFiles() )
											&& ( !temp.isEmpty() ))
									{
										progressBar.setValue( 80 ) ;
										if (outXls.sheetIsToday())
										{

											outXls.insertData( temp ) ;
										} else
										{
											outXls.createTodaySheet() ;
										}
									} else if (!outXls.isFindFiles())
									{

										outXls.createXls() ;
									}

									double totTime = Double.parseDouble( txtSleeptme.getText().toString() ) ;
									for (double time = 0.0D; time < totTime; time += 1.0D)
									{
										int progreessVal = (int) ( 90.0D + 10.0D * time / totTime ) ;
										lblSecond.setText( String.valueOf( time + 1.0D ) + "秒" ) ;
										progressBar.setValue( progreessVal ) ;
										Thread.sleep( 1000L ) ;
									}
								} catch (NumberFormatException e)
								{
									System.err.println(
											sdf.format( new Date() ) + " " + port + " Format Exception :" + e ) ;
								} catch (InterruptedException e)
								{
									System.err.println(
											sdf.format( new Date() ) + " " + port + " Thread Exception :" + e ) ;
								} catch (Exception e)
								{
									System.err
											.println( sdf.format( new Date() ) + " " + port + " GUI Exception " + e ) ;
								}

							}
						}
					}
				} ) ;
				outThread.start() ;

				JToggleButton tgButton = (JToggleButton) ev.getSource() ;
				if (!tgButton.isSelected())
				{
					Print.printErr( sdf.format( new Date() ) + " Stopped" ) ;
				}

			}

		} ) ;
		JLabel label_2 = new JLabel( "時間間隔" ) ;
		label_2.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_label_2 = new GridBagConstraints() ;
		gbc_label_2.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_label_2.gridx = 3 ;
		gbc_label_2.gridy = 1 ;
		contentPane.add( label_2 , gbc_label_2 ) ;

		txtSleeptme = new JTextField() ;
		txtSleeptme.setHorizontalAlignment( 0 ) ;
		txtSleeptme.setText( "1" ) ;
		GridBagConstraints gbc_txtSleeptme = new GridBagConstraints() ;
		gbc_txtSleeptme.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtSleeptme.fill = 1 ;
		gbc_txtSleeptme.gridx = 4 ;
		gbc_txtSleeptme.gridy = 1 ;
		contentPane.add( txtSleeptme , gbc_txtSleeptme ) ;
		txtSleeptme.setColumns( 10 ) ;

		lblSecond.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblSecond = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_lblSecond.insets = new Insets(0, 0, 5, 0);
=======
		gbc_lblSecond.insets = new Insets(0, 0, 5, 5);
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_lblSecond.gridx = 5;
		gbc_lblSecond.gridy = 0;
		gbc_txtSleeptme.fill = 2 ;
		gbc_txtSleeptme.insets = new Insets( 0 , 0 , 5 , 0 ) ;
		gbc_txtSleeptme.gridx = 5 ;
		gbc_txtSleeptme.gridy = 1 ;
		contentPane.add( lblSecond , gbc_lblSecond ) ;
<<<<<<< HEAD
=======

		JLabel lblByte = new JLabel( "Byte" ) ;
		GridBagConstraints gbc_lblByte = new GridBagConstraints() ;
		gbc_lblByte.insets = new Insets(0, 0, 5, 0);
		gbc_lblByte.gridx = 6;
		gbc_lblByte.gridy = 0;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_txtSleeptme.gridwidth = 2 ;
		gbc_txtSleeptme.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtSleeptme.gridx = 0 ;
		gbc_txtSleeptme.gridy = 2 ;
<<<<<<< HEAD
				
				JLabel label_4 = new JLabel("Byte");
				GridBagConstraints gbc_label_4 = new GridBagConstraints();
				gbc_label_4.insets = new Insets(0, 0, 5, 5);
				gbc_label_4.gridx = 1;
				gbc_label_4.gridy = 2;
				contentPane.add(label_4, gbc_label_4);
=======
		contentPane.add( lblByte , gbc_lblByte ) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		
				GridBagConstraints gbc_cbByte = new GridBagConstraints() ;
				gbc_cbByte.fill = 1 ;
				gbc_cbByte.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_cbByte.gridx = 2 ;
				gbc_cbByte.gridy = 2 ;
				cbByte.setModel( new DefaultComboBoxModel( picByte ) ) ;
				cbByte.setSelectedIndex( 0 ) ;
				contentPane.add( cbByte , gbc_cbByte ) ;

		JLabel label_3 = new JLabel( "處理進程" ) ;
		GridBagConstraints gbc_label_3 = new GridBagConstraints() ;
		gbc_label_3.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_label_3.gridx = 3 ;
		gbc_label_3.gridy = 2 ;
		contentPane.add( label_3 , gbc_label_3 ) ;

		GridBagConstraints gbc_progressBar = new GridBagConstraints() ;
		gbc_progressBar.fill = 2 ;
		gbc_progressBar.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_progressBar.gridx = 4 ;
		gbc_progressBar.gridy = 2 ;
		progressBar.setForeground( SystemColor.textHighlight ) ;
		contentPane.add( progressBar , gbc_progressBar ) ;

		tbOutputExcel = new JToggleButton( "Ouput Excel" ) ;
		GridBagConstraints gbc_tbOutputExcel = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_tbOutputExcel.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_tbOutputExcel.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_tbOutputExcel.gridx = 5 ;
		gbc_tbOutputExcel.gridy = 2 ;
		contentPane.add( tbOutputExcel , gbc_tbOutputExcel ) ;

		JLabel label = new JLabel( "輸出端" ) ;
		GridBagConstraints gbc_label = new GridBagConstraints() ;
		gbc_label.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_label.gridx = 0 ;
		gbc_label.gridy = 3 ;
		contentPane.add( label , gbc_label ) ;

<<<<<<< HEAD
		JLabel lblNode = new JLabel( "感測點編號" ) ;
=======
		JLabel lblNode = new JLabel( "節點編號" ) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		GridBagConstraints gbc_lblNode = new GridBagConstraints() ;
		gbc_lblNode.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNode.gridx = 1 ;
		gbc_lblNode.gridy = 3 ;
		contentPane.add( lblNode , gbc_lblNode ) ;
		
				JLabel label_1 = new JLabel( "值" ) ;
				GridBagConstraints gbc_label_1 = new GridBagConstraints() ;
				gbc_label_1.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_label_1.gridx = 2 ;
				gbc_label_1.gridy = 3 ;
				contentPane.add( label_1 , gbc_label_1 ) ;

		JLabel lblNewLabel_17 = new JLabel( "Translate" ) ;
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints() ;
		gbc_lblNewLabel_17.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_17.gridx = 3 ;
		gbc_lblNewLabel_17.gridy = 3 ;
		contentPane.add( lblNewLabel_17 , gbc_lblNewLabel_17 ) ;

		JLabel lblNewLabel_18 = new JLabel( "Value" ) ;
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints() ;
		gbc_lblNewLabel_18.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_18.gridx = 4 ;
		gbc_lblNewLabel_18.gridy = 3 ;
		contentPane.add( lblNewLabel_18 , gbc_lblNewLabel_18 ) ;

		JLabel lbloutputMark = new JLabel( "輸出選項" ) ;
		GridBagConstraints gbc_lbloutputMark = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_lbloutputMark.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_lbloutputMark.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_lbloutputMark.gridx = 5 ;
		gbc_lbloutputMark.gridy = 3 ;
		contentPane.add( lbloutputMark , gbc_lbloutputMark ) ;

		JLabel lblNewLabel_1 = new JLabel( "1" ) ;
		lblNewLabel_1.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints() ;
		gbc_lblNewLabel_1.fill = 2 ;
		gbc_lblNewLabel_1.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_1.gridx = 0 ;
		gbc_lblNewLabel_1.gridy = 4 ;
		contentPane.add( lblNewLabel_1 , gbc_lblNewLabel_1 ) ;

		txtfNode = new JTextField() ;
		txtfNode.setColumns( 10 ) ;
		jtxtNode[0] = txtfNode ;
		GridBagConstraints gbc_txtfNode = new GridBagConstraints() ;
		gbc_txtfNode.fill = 1 ;
		gbc_txtfNode.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode.gridx = 1 ;
		gbc_txtfNode.gridy = 4 ;
		contentPane.add( txtfNode , gbc_txtfNode ) ;
		
				JLabel lblNewLabel_9 = new JLabel( "pin1" ) ;
				lblNewLabel_9.setHorizontalAlignment( 0 ) ;
				jvalLab[0] = lblNewLabel_9 ;
				GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints() ;
				gbc_lblNewLabel_9.fill = 2 ;
				gbc_lblNewLabel_9.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_9.gridx = 2 ;
				gbc_lblNewLabel_9.gridy = 4 ;
				contentPane.add( lblNewLabel_9 , gbc_lblNewLabel_9 ) ;

		JComboBox < String > cbTorH = new JComboBox() ;
		cbTorHArr[0] = cbTorH ;
		cbTorH.setActionCommand( "0" ) ;
		cbTorH.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH.setSelectedIndex( 0 ) ;
		GridBagConstraints gbc_cbTorH = new GridBagConstraints() ;
		gbc_cbTorH.fill = 2 ;
		gbc_cbTorH.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH.gridx = 3 ;
		gbc_cbTorH.gridy = 4 ;
		contentPane.add( cbTorH , gbc_cbTorH ) ;

		JLabel lblNewLabel_19 = new JLabel( "" ) ;
		jval2Lab[0] = lblNewLabel_19 ;
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints() ;
		gbc_lblNewLabel_19.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_19.gridx = 4 ;
		gbc_lblNewLabel_19.gridy = 4 ;
		contentPane.add( lblNewLabel_19 , gbc_lblNewLabel_19 ) ;

		JCheckBox chckbxNewCheckBox_7 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[0] = chckbxNewCheckBox_7 ;
		GridBagConstraints gbc_chckbxNewCheckBox_7 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_7.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_7.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_7.gridx = 5 ;
		gbc_chckbxNewCheckBox_7.gridy = 4 ;
		contentPane.add( chckbxNewCheckBox_7 , gbc_chckbxNewCheckBox_7 ) ;

		JLabel lblNewLabel_2 = new JLabel( "2" ) ;
		lblNewLabel_2.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints() ;
		gbc_lblNewLabel_2.fill = 2 ;
		gbc_lblNewLabel_2.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_2.gridx = 0 ;
		gbc_lblNewLabel_2.gridy = 5 ;
		contentPane.add( lblNewLabel_2 , gbc_lblNewLabel_2 ) ;

		txtfNode_1 = new JTextField() ;
		Dimension dMiniSize = new Dimension( 5 , 5 ) ;
		txtfNode.setMinimumSize( dMiniSize ) ;
		jtxtNode[1] = txtfNode_1 ;
		GridBagConstraints gbc_txtfNode_1 = new GridBagConstraints() ;
		gbc_txtfNode_1.fill = 1 ;
		gbc_txtfNode_1.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_1.gridx = 1 ;
		gbc_txtfNode_1.gridy = 5 ;
		contentPane.add( txtfNode_1 , gbc_txtfNode_1 ) ;
		txtfNode_1.setColumns( 10 ) ;
		
				JLabel lblNewLabel_10 = new JLabel( "pin2" ) ;
				lblNewLabel_10.setHorizontalAlignment( 0 ) ;
				jvalLab[1] = lblNewLabel_10 ;
				GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints() ;
				gbc_lblNewLabel_10.fill = 2 ;
				gbc_lblNewLabel_10.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_10.gridx = 2 ;
				gbc_lblNewLabel_10.gridy = 5 ;
				contentPane.add( lblNewLabel_10 , gbc_lblNewLabel_10 ) ;

		JComboBox < String > cbTorH_1 = new JComboBox() ;
		cbTorHArr[1] = cbTorH_1 ;
		cbTorH_1.setActionCommand( "0" ) ;
		cbTorH_1.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_1.setSelectedIndex( 1 ) ;
		GridBagConstraints gbc_cbTorH_1 = new GridBagConstraints() ;
		gbc_cbTorH_1.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_1.fill = 2 ;
		gbc_cbTorH_1.gridx = 3 ;
		gbc_cbTorH_1.gridy = 5 ;
		contentPane.add( cbTorH_1 , gbc_cbTorH_1 ) ;

		JLabel lblNewLabel_20 = new JLabel( "" ) ;
		jval2Lab[1] = lblNewLabel_20 ;
		GridBagConstraints gbc_lblNewLabel_20 = new GridBagConstraints() ;
		gbc_lblNewLabel_20.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_20.gridx = 4 ;
		gbc_lblNewLabel_20.gridy = 5 ;
		contentPane.add( lblNewLabel_20 , gbc_lblNewLabel_20 ) ;

		JCheckBox chckbxNewCheckBox = new JCheckBox( "輸出" ) ;
		chkbOutputXls[1] = chckbxNewCheckBox ;
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox.gridx = 5 ;
		gbc_chckbxNewCheckBox.gridy = 5 ;
		contentPane.add( chckbxNewCheckBox , gbc_chckbxNewCheckBox ) ;

		JLabel lblNewLabel_3 = new JLabel( "3" ) ;
		lblNewLabel_3.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints() ;
		gbc_lblNewLabel_3.fill = 2 ;
		gbc_lblNewLabel_3.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_3.gridx = 0 ;
		gbc_lblNewLabel_3.gridy = 6 ;
		contentPane.add( lblNewLabel_3 , gbc_lblNewLabel_3 ) ;

		txtfNode_2 = new JTextField() ;
		jtxtNode[2] = txtfNode_2 ;
		GridBagConstraints gbc_txtfNode_2 = new GridBagConstraints() ;
		gbc_txtfNode_2.fill = 1 ;
		gbc_txtfNode_2.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_2.gridx = 1 ;
		gbc_txtfNode_2.gridy = 6 ;
		contentPane.add( txtfNode_2 , gbc_txtfNode_2 ) ;
		txtfNode_2.setColumns( 10 ) ;
		
				JLabel lblNewLabel_11 = new JLabel( "pin3" ) ;
				lblNewLabel_11.setHorizontalAlignment( 0 ) ;
				jvalLab[2] = lblNewLabel_11 ;
				GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints() ;
				gbc_lblNewLabel_11.fill = 2 ;
				gbc_lblNewLabel_11.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_11.gridx = 2 ;
				gbc_lblNewLabel_11.gridy = 6 ;
				contentPane.add( lblNewLabel_11 , gbc_lblNewLabel_11 ) ;

		JComboBox < String > cbTorH_2 = new JComboBox() ;
		cbTorHArr[2] = cbTorH_2 ;
		cbTorH_2.setActionCommand( "0" ) ;
		cbTorH_2.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_2.setSelectedIndex( 0 ) ;
		GridBagConstraints gbc_cbTorH_2 = new GridBagConstraints() ;
		gbc_cbTorH_2.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_2.fill = 2 ;
		gbc_cbTorH_2.gridx = 3 ;
		gbc_cbTorH_2.gridy = 6 ;
		contentPane.add( cbTorH_2 , gbc_cbTorH_2 ) ;

		JLabel lblNewLabel_21 = new JLabel( "" ) ;
		jval2Lab[2] = lblNewLabel_21 ;
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints() ;
		gbc_lblNewLabel_21.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_21.gridx = 4 ;
		gbc_lblNewLabel_21.gridy = 6 ;
		contentPane.add( lblNewLabel_21 , gbc_lblNewLabel_21 ) ;

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[2] = chckbxNewCheckBox_1 ;
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_1.gridx = 5 ;
		gbc_chckbxNewCheckBox_1.gridy = 6 ;
		contentPane.add( chckbxNewCheckBox_1 , gbc_chckbxNewCheckBox_1 ) ;

		JLabel lblNewLabel_4 = new JLabel( "4" ) ;
		lblNewLabel_4.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints() ;
		gbc_lblNewLabel_4.fill = 2 ;
		gbc_lblNewLabel_4.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_4.gridx = 0 ;
		gbc_lblNewLabel_4.gridy = 7 ;
		contentPane.add( lblNewLabel_4 , gbc_lblNewLabel_4 ) ;

		txtfNode_3 = new JTextField() ;
		jtxtNode[3] = txtfNode_3 ;
		GridBagConstraints gbc_txtfNode_3 = new GridBagConstraints() ;
		gbc_txtfNode_3.fill = 1 ;
		gbc_txtfNode_3.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_3.gridx = 1 ;
		gbc_txtfNode_3.gridy = 7 ;
		contentPane.add( txtfNode_3 , gbc_txtfNode_3 ) ;
		txtfNode_3.setColumns( 10 ) ;
		
				JLabel lblNewLabel_12 = new JLabel( "pin4" ) ;
				lblNewLabel_12.setHorizontalAlignment( 0 ) ;
				jvalLab[3] = lblNewLabel_12 ;
				GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints() ;
				gbc_lblNewLabel_12.fill = 2 ;
				gbc_lblNewLabel_12.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_12.gridx = 2 ;
				gbc_lblNewLabel_12.gridy = 7 ;
				contentPane.add( lblNewLabel_12 , gbc_lblNewLabel_12 ) ;

		JComboBox < String > cbTorH_3 = new JComboBox() ;
		cbTorHArr[3] = cbTorH_3 ;
		cbTorH_3.setActionCommand( "0" ) ;
		cbTorH_3.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_3.setSelectedIndex( 1 ) ;
		GridBagConstraints gbc_cbTorH_3 = new GridBagConstraints() ;
		gbc_cbTorH_3.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_3.fill = 2 ;
		gbc_cbTorH_3.gridx = 3 ;
		gbc_cbTorH_3.gridy = 7 ;
		contentPane.add( cbTorH_3 , gbc_cbTorH_3 ) ;

		JLabel lblNewLabel_22 = new JLabel( "" ) ;
		jval2Lab[3] = lblNewLabel_22 ;
		GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints() ;
		gbc_lblNewLabel_22.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_22.gridx = 4 ;
		gbc_lblNewLabel_22.gridy = 7 ;
		contentPane.add( lblNewLabel_22 , gbc_lblNewLabel_22 ) ;

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[3] = chckbxNewCheckBox_2 ;
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_2.gridx = 5 ;
		gbc_chckbxNewCheckBox_2.gridy = 7 ;
		contentPane.add( chckbxNewCheckBox_2 , gbc_chckbxNewCheckBox_2 ) ;

		JLabel lblNewLabel_5 = new JLabel( "5" ) ;
		lblNewLabel_5.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints() ;
		gbc_lblNewLabel_5.fill = 2 ;
		gbc_lblNewLabel_5.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_5.gridx = 0 ;
		gbc_lblNewLabel_5.gridy = 8 ;
		contentPane.add( lblNewLabel_5 , gbc_lblNewLabel_5 ) ;

		txtfNode_4 = new JTextField() ;
		jtxtNode[4] = txtfNode_4 ;
		GridBagConstraints gbc_txtfNode_4 = new GridBagConstraints() ;
		gbc_txtfNode_4.fill = 1 ;
		gbc_txtfNode_4.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_4.gridx = 1 ;
		gbc_txtfNode_4.gridy = 8 ;
		contentPane.add( txtfNode_4 , gbc_txtfNode_4 ) ;
		txtfNode_4.setColumns( 10 ) ;
		
				JLabel lblNewLabel_13 = new JLabel( "pin5" ) ;
				lblNewLabel_13.setHorizontalAlignment( 0 ) ;
				jvalLab[4] = lblNewLabel_13 ;
				GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints() ;
				gbc_lblNewLabel_13.fill = 2 ;
				gbc_lblNewLabel_13.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_13.gridx = 2 ;
				gbc_lblNewLabel_13.gridy = 8 ;
				contentPane.add( lblNewLabel_13 , gbc_lblNewLabel_13 ) ;

		JComboBox < String > cbTorH_4 = new JComboBox() ;
		cbTorHArr[4] = cbTorH_4 ;
		cbTorH_4.setActionCommand( "0" ) ;
		cbTorH_4.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_4.setSelectedIndex( 0 ) ;
		GridBagConstraints gbc_cbTorH_4 = new GridBagConstraints() ;
		gbc_cbTorH_4.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_4.fill = 2 ;
		gbc_cbTorH_4.gridx = 3 ;
		gbc_cbTorH_4.gridy = 8 ;
		contentPane.add( cbTorH_4 , gbc_cbTorH_4 ) ;

		JLabel lblNewLabel_23 = new JLabel( "" ) ;
		jval2Lab[4] = lblNewLabel_23 ;
		GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints() ;
		gbc_lblNewLabel_23.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_23.gridx = 4 ;
		gbc_lblNewLabel_23.gridy = 8 ;
		contentPane.add( lblNewLabel_23 , gbc_lblNewLabel_23 ) ;

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[4] = chckbxNewCheckBox_3 ;
		GridBagConstraints gbc_chckbxNewCheckBox_3 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_3.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_3.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_3.gridx = 5 ;
		gbc_chckbxNewCheckBox_3.gridy = 8 ;
		contentPane.add( chckbxNewCheckBox_3 , gbc_chckbxNewCheckBox_3 ) ;

		JLabel lblNewLabel_6 = new JLabel( "6" ) ;
		lblNewLabel_6.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints() ;
		gbc_lblNewLabel_6.fill = 2 ;
		gbc_lblNewLabel_6.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_6.gridx = 0 ;
		gbc_lblNewLabel_6.gridy = 9 ;
		contentPane.add( lblNewLabel_6 , gbc_lblNewLabel_6 ) ;

		txtfNode_5 = new JTextField() ;
		jtxtNode[5] = txtfNode_5 ;
		GridBagConstraints gbc_txtfNode_5 = new GridBagConstraints() ;
		gbc_txtfNode_5.fill = 1 ;
		gbc_txtfNode_5.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_5.gridx = 1 ;
		gbc_txtfNode_5.gridy = 9 ;
		contentPane.add( txtfNode_5 , gbc_txtfNode_5 ) ;
		txtfNode_5.setColumns( 10 ) ;
		
				JLabel lblNewLabel_14 = new JLabel( "pin6" ) ;
				lblNewLabel_14.setHorizontalAlignment( 0 ) ;
				jvalLab[5] = lblNewLabel_14 ;
				GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints() ;
				gbc_lblNewLabel_14.fill = 2 ;
				gbc_lblNewLabel_14.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_14.gridx = 2 ;
				gbc_lblNewLabel_14.gridy = 9 ;
				contentPane.add( lblNewLabel_14 , gbc_lblNewLabel_14 ) ;

		JComboBox < String > cbTorH_5 = new JComboBox() ;
		cbTorHArr[5] = cbTorH_5 ;
		cbTorH_5.setActionCommand( "0" ) ;
		cbTorH_5.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_5.setSelectedIndex( 1 ) ;
		GridBagConstraints gbc_cbTorH_5 = new GridBagConstraints() ;
		gbc_cbTorH_5.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_5.fill = 2 ;
		gbc_cbTorH_5.gridx = 3 ;
		gbc_cbTorH_5.gridy = 9 ;
		contentPane.add( cbTorH_5 , gbc_cbTorH_5 ) ;

		JLabel lblNewLabel_24 = new JLabel( "" ) ;
		jval2Lab[5] = lblNewLabel_24 ;
		GridBagConstraints gbc_lblNewLabel_24 = new GridBagConstraints() ;
		gbc_lblNewLabel_24.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_24.gridx = 4 ;
		gbc_lblNewLabel_24.gridy = 9 ;
		contentPane.add( lblNewLabel_24 , gbc_lblNewLabel_24 ) ;

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[5] = chckbxNewCheckBox_4 ;
		GridBagConstraints gbc_chckbxNewCheckBox_4 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_4.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_4.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_4.gridx = 5 ;
		gbc_chckbxNewCheckBox_4.gridy = 9 ;
		contentPane.add( chckbxNewCheckBox_4 , gbc_chckbxNewCheckBox_4 ) ;

		JLabel lblNewLabel_7 = new JLabel( "7" ) ;
		lblNewLabel_7.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints() ;
		gbc_lblNewLabel_7.fill = 2 ;
		gbc_lblNewLabel_7.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_7.gridx = 0 ;
		gbc_lblNewLabel_7.gridy = 10 ;
		contentPane.add( lblNewLabel_7 , gbc_lblNewLabel_7 ) ;

		txtfNode_6 = new JTextField() ;
		jtxtNode[6] = txtfNode_6 ;
		GridBagConstraints gbc_txtfNode_6 = new GridBagConstraints() ;
		gbc_txtfNode_6.fill = 1 ;
		gbc_txtfNode_6.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_txtfNode_6.gridx = 1 ;
		gbc_txtfNode_6.gridy = 10 ;
		contentPane.add( txtfNode_6 , gbc_txtfNode_6 ) ;
		txtfNode_6.setColumns( 10 ) ;
		
				JLabel lblNewLabel_15 = new JLabel( "pin7" ) ;
				lblNewLabel_15.setHorizontalAlignment( 0 ) ;
				jvalLab[6] = lblNewLabel_15 ;
				GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints() ;
				gbc_lblNewLabel_15.fill = 2 ;
				gbc_lblNewLabel_15.insets = new Insets( 0 , 0 , 5 , 5 ) ;
				gbc_lblNewLabel_15.gridx = 2 ;
				gbc_lblNewLabel_15.gridy = 10 ;
				contentPane.add( lblNewLabel_15 , gbc_lblNewLabel_15 ) ;

		JComboBox < String > cbTorH_6 = new JComboBox() ;
		cbTorHArr[6] = cbTorH_6 ;
		cbTorH_6.setActionCommand( "0" ) ;
		cbTorH_6.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_6.setSelectedIndex( 0 ) ;
		GridBagConstraints gbc_cbTorH_6 = new GridBagConstraints() ;
		gbc_cbTorH_6.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_cbTorH_6.fill = 2 ;
		gbc_cbTorH_6.gridx = 3 ;
		gbc_cbTorH_6.gridy = 10 ;
		contentPane.add( cbTorH_6 , gbc_cbTorH_6 ) ;

		JLabel lblNewLabel_25 = new JLabel( "" ) ;
		jval2Lab[6] = lblNewLabel_25 ;
		GridBagConstraints gbc_lblNewLabel_25 = new GridBagConstraints() ;
		gbc_lblNewLabel_25.insets = new Insets( 0 , 0 , 5 , 5 ) ;
		gbc_lblNewLabel_25.gridx = 4 ;
		gbc_lblNewLabel_25.gridy = 10 ;
		contentPane.add( lblNewLabel_25 , gbc_lblNewLabel_25 ) ;

		JCheckBox chckbxNewCheckBox_5 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[6] = chckbxNewCheckBox_5 ;
		GridBagConstraints gbc_chckbxNewCheckBox_5 = new GridBagConstraints() ;
<<<<<<< HEAD
		gbc_chckbxNewCheckBox_5.insets = new Insets(0, 0, 5, 0) ;
=======
		gbc_chckbxNewCheckBox_5.insets = new Insets(0, 0, 5, 5) ;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_5.gridx = 5 ;
		gbc_chckbxNewCheckBox_5.gridy = 10 ;
		contentPane.add( chckbxNewCheckBox_5 , gbc_chckbxNewCheckBox_5 ) ;

		JLabel lblNewLabel_8 = new JLabel( "8" ) ;
		lblNewLabel_8.setHorizontalAlignment( 0 ) ;
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints() ;
		gbc_lblNewLabel_8.fill = 2 ;
		gbc_lblNewLabel_8.insets = new Insets( 0 , 0 , 0 , 5 ) ;
		gbc_lblNewLabel_8.gridx = 0 ;
		gbc_lblNewLabel_8.gridy = 11 ;
		contentPane.add( lblNewLabel_8 , gbc_lblNewLabel_8 ) ;

		txtfNode_7 = new JTextField() ;
		jtxtNode[7] = txtfNode_7 ;
		GridBagConstraints gbc_txtfNode_7 = new GridBagConstraints() ;
		gbc_txtfNode_7.fill = 1 ;
		gbc_txtfNode_7.insets = new Insets( 0 , 0 , 0 , 5 ) ;
		gbc_txtfNode_7.gridx = 1 ;
		gbc_txtfNode_7.gridy = 11 ;
		contentPane.add( txtfNode_7 , gbc_txtfNode_7 ) ;
		txtfNode_7.setColumns( 10 ) ;
		
				JLabel lblNewLabel_16 = new JLabel( "pin8" ) ;
				lblNewLabel_16.setHorizontalAlignment( 0 ) ;
				jvalLab[7] = lblNewLabel_16 ;
				GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints() ;
				gbc_lblNewLabel_16.fill = 2 ;
				gbc_lblNewLabel_16.insets = new Insets( 0 , 0 , 0 , 5 ) ;
				gbc_lblNewLabel_16.gridx = 2 ;
				gbc_lblNewLabel_16.gridy = 11 ;
				contentPane.add( lblNewLabel_16 , gbc_lblNewLabel_16 ) ;

		JComboBox < String > cbTorH_7 = new JComboBox() ;
		cbTorHArr[7] = cbTorH_7 ;
		cbTorH_7.setActionCommand( "0" ) ;
		cbTorH_7.setModel( new DefaultComboBoxModel( Unittype ) ) ;
		cbTorH_7.setSelectedIndex( 0 ) ;
		GridBagConstraints gbc_cbTorH_7 = new GridBagConstraints() ;
		gbc_cbTorH_7.insets = new Insets( 0 , 0 , 0 , 5 ) ;
		gbc_cbTorH_7.fill = 2 ;
		gbc_cbTorH_7.gridx = 3 ;
		gbc_cbTorH_7.gridy = 11 ;
		contentPane.add( cbTorH_7 , gbc_cbTorH_7 ) ;

		JLabel lblNewLabel_26 = new JLabel( "" ) ;
		jval2Lab[7] = lblNewLabel_26 ;
		GridBagConstraints gbc_lblNewLabel_26 = new GridBagConstraints() ;
		gbc_lblNewLabel_26.insets = new Insets( 0 , 0 , 0 , 5 ) ;
		gbc_lblNewLabel_26.gridx = 4 ;
		gbc_lblNewLabel_26.gridy = 11 ;
		contentPane.add( lblNewLabel_26 , gbc_lblNewLabel_26 ) ;

		JCheckBox chckbxNewCheckBox_6 = new JCheckBox( "輸出" ) ;
		chkbOutputXls[7] = chckbxNewCheckBox_6 ;
		GridBagConstraints gbc_chckbxNewCheckBox_6 = new GridBagConstraints() ;
<<<<<<< HEAD
=======
		gbc_chckbxNewCheckBox_6.insets = new Insets(0, 0, 0, 5);
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
		gbc_chckbxNewCheckBox_6.gridx = 5 ;
		gbc_chckbxNewCheckBox_6.gridy = 11 ;

		contentPane.add( chckbxNewCheckBox_6 , gbc_chckbxNewCheckBox_6 ) ;

		setDefaultCloseOperation( 0 ) ;
		addWindowListener( new WindowHandler() ) ;
	}

	protected void updateData ( JButton btnNewButton , ActionListener btnActionListener )
	{
		btnNewButton.addActionListener( btnActionListener ) ;
	}

	protected void unitUpdate ( int count , JLabel[] jvalLab , JLabel[] jval2Lab , int unitIndex , Double picByte )
	{
		switch (unitIndex)
		{
			case 0:
				jval2Lab[count]
						.setText( GetData.Value2Temperature( jvalLab[count].getText() , picByte.doubleValue() ) ) ;
				break ;

			case 1:
				jval2Lab[count].setText( GetData.Value2Humidity( jvalLab[count].getText() , picByte.doubleValue() ,
						Double.parseDouble( jval2Lab[( count - 1 )].getText().toString() ) ) ) ;
				break ;
			case 2:
				jval2Lab[count]
						.setText( GetData.Value2TemperatureK2C( jvalLab[count].getText() , picByte.doubleValue() ) ) ;
				break ;
		}
	}
}
