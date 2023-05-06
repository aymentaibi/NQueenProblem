import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class test extends JFrame {

	private static final long serialVersionUID = 1L;
	// Fenêtre
      static int WINDOW_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
      int WINDOW_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
      static String WINDOW_TITLE = "Interface";
   
   // Tableau
     static int BOARD_WIDTH = 600;
     static int BOARD_HEIGHT = 600;
     static Color BOARD_BACKGROUD_COLOR = Color.BLACK;
     int [] t ;
     
   // Slider
    static final int SLIDER_WIDTH = 300;
    static final int SLIDER_HEIGHT = 50;
   
   // Cellule
   static final Color CELL_COLOR1 = Color.WHITE;
   //static final Color CELL_COLOR2 = Color.DARK_GRAY,Color.LIGHT_GRAY;
   static final Color CELL_COLOR2 = Color.DARK_GRAY;
   
   // Types de parcour
    String PARCOURS_TYPES_STRINGS[] = {"Parcours en largeur (BFS)",
		   "Parcours en profondeur (DFS)",
		   "Heuristiques-1",
		   "Heuristiques-2",
   			"Algorithme GA",
   			"Algorithme PSO"};
    int PARCOURS_TYPES_WIDTH = 200;
    int PARCOURS_TYPES_HEIGHT = 50;
   
   // button de démarrage
    static final int START_BUTTON_HEIGHT = 40;
   // static final int START_BUTTON_WIDTH = WINDOW_WIDTH-BOARD_WIDTH-60;
    static final int START_BUTTON_WIDTH = 300;
   // button d'arret d'execution 
    static final int STOP_BUTTON_HEIGHT = 40;
  //  static final int STOP_BUTTON_WIDTH = WINDOW_WIDTH-BOARD_WIDTH-START_BUTTON_WIDTH-10;
    static final int STOP_BUTTON_WIDTH = 300;
   // Game
    private static final int INIT_N = 9;
    private static final int MIN_N = 8;
    private static final int MAX_N = 30;
    private static final String[][] TABLE_INFORMATIONS = {
        {"Nombre de noeud généré", "0"},
        {"Temps d'exécution", "0"},
        {"le temp d'éxecution en (ms)", "0.0"},
		{"Fitenesse", "None"}
    };
    
    static final String[] TABLE_INFORMATIONS_TITLE = {"s", "s"};

    // forms
    private NSelectorSlider nSelector;
    private Board gameBoard;
    private JSpinner nSelect;
    private JComboBox parcoursType;
    
    private JButton BUTTON_start, BUTTON_next, BUTTON_previous, BUTTON_first, BUTTON_last,BUTTON_stop;
    private JMenuBar menuBar;
    private JLabel LABEL_nSelect, LABEL_parcourType, LABEL_options, LABEL_solutions;
    private JTable TABLE_details;
    private static JMenuItem  exitMenuItem;
	 
	 
    // Méthode principale
    public static void main(String[] args)
    {
        test myApp = new test();
     
    }
    
    // Constructeur de la class Principale (App)
    public test()
    {
    	super(test.WINDOW_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.showForms();
        this.listeners();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLayout(null);
        this.setUndecorated(false);
        this.setVisible(true);
     
    }
	
    // méthode d'affichage des composants
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void showForms()
    {
        this.nSelector = new NSelectorSlider();
        this.gameBoard = new Board(test.INIT_N);
     
        SpinnerModel value = new SpinnerNumberModel(test.INIT_N, test.MIN_N, test.MAX_N, 1);
        this.nSelect = new JSpinner(value);
        this.nSelect.setBounds(BOARD_WIDTH+SLIDER_WIDTH+250, 55, 50, 20);
        this.add(this.nSelect);

        this.parcoursType = new JComboBox(PARCOURS_TYPES_STRINGS);
        this.parcoursType.setBounds(BOARD_WIDTH+250 , test.SLIDER_HEIGHT+70, PARCOURS_TYPES_WIDTH, PARCOURS_TYPES_HEIGHT);
        this.add(this.parcoursType);

        // Buttons
        this.BUTTON_start = new JButton();
        this.BUTTON_start.setText("Start");
        this.BUTTON_start.setBounds(BOARD_WIDTH+40,WINDOW_HEIGHT-140, test.START_BUTTON_WIDTH,test.START_BUTTON_HEIGHT);
        //this.BUTTON_start.setBounds(App.BOARD_WIDTH/2-App.START_BUTTON_WIDTH/2, App.BOARD_WIDTH+27,App.START_BUTTON_WIDTH,App.START_BUTTON_HEIGHT);
        this.add(this.BUTTON_start);
        
        this.BUTTON_stop = new JButton();
        this.BUTTON_stop.setText("Stop");
        this.BUTTON_stop.setBounds(BOARD_WIDTH+350,WINDOW_HEIGHT-140, test.STOP_BUTTON_WIDTH,test.STOP_BUTTON_HEIGHT);
       // this.add(this.BUTTON_stop);
        

        // tableau d'information
        this.TABLE_details = new JTable(TABLE_INFORMATIONS,TABLE_INFORMATIONS_TITLE);
        this.TABLE_details.setBounds(BOARD_WIDTH+40, WINDOW_HEIGHT-270,WINDOW_WIDTH-BOARD_WIDTH-60, 75);
        this.add(this.TABLE_details);
        
        // barre de menu
        this.menuBar = new JMenuBar();
        this.exitMenuItem =new JMenuItem("Quitter");
        JMenu file = new JMenu("Fichier");
        file.add(new JMenuItem("Start"));
        file.add(exitMenuItem);
        this.menuBar.add(file);

        JMenu about = new JMenu("à propos");
        about.add(new JMenuItem("Contactez-nous"));
        this.menuBar.add(about);
        this.setJMenuBar(this.menuBar);  

        this.LABEL_nSelect = new JLabel("Nombre n:");
        this.LABEL_parcourType = new JLabel("Type de parcours: ");
        this.LABEL_options = new JLabel("Options");
        this.LABEL_solutions = new JLabel("Solution    ## / ##");

        this.LABEL_nSelect.setBounds(BOARD_WIDTH+40, 50, 500, 50);
        this.LABEL_nSelect.setFont(new Font("Serif", Font.PLAIN, 20));

        this.LABEL_parcourType.setBounds(BOARD_WIDTH+40, 120, 500, 50);
        this.LABEL_parcourType.setFont(new Font("Serif", Font.PLAIN, 20));

        this.LABEL_options.setBounds(BOARD_WIDTH+40, 0, 500, 50);
        this.LABEL_options.setFont(new Font("Serif", Font.PLAIN, 35));


        this.add(this.LABEL_nSelect);
        this.add(this.LABEL_parcourType);
        this.add(this.LABEL_options);
      
    }
    
    // les déclencheurs
    private void listeners()
    { 
    	 this.nSelector.addChangeListener(
    	            new ChangeListener()
    	            {
    	                public void stateChanged(ChangeEvent event)
    	                {
    	                    if(test.this.nSelector.getValue()>0)
    	                    {
    	                        test.this.gameBoard.changeDimentions(test.this.nSelector.getValue());
    	                    }
    	                    test.this.nSelect.setValue(test.this.nSelector.getValue());
    	                }
    	            }
    	        );
    	        this.nSelect.addChangeListener(
    	new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
                if(test.this.nSelector.getValue()>0)
                {
                    test.this.gameBoard.changeDimentions(test.this.nSelector.getValue());
                }
                test.this.nSelector.setValue((Integer)test.this.nSelect.getValue());
            }
        }
    );

    	        	
    	        BUTTON_start.addActionListener((ActionListener) new ActionListener() {
    	        	boolean continueExecuting = true;
    	    		@Override
					public void actionPerformed(ActionEvent e) {
    	    			String o = (String)parcoursType.getSelectedItem();
    	    			int n = (int)test.this.nSelector.getValue();
    	    			
    	    			Problem prblm = new Problem(n);
    	    	        t = new int[n];
    	    	            
    	    	        LoadingWindow loadingWindow = new LoadingWindow(test.this, "Programme En cours...");
    	    	        
    	    	        loadingWindow.stop.addActionListener((ActionListener) new ActionListener() {
    	    	    		@Override
    						public void actionPerformed(ActionEvent e) {
    	    	    			
    	    	    			loadingWindow.dispose(); // close the current frame
    	    	    			
    	    	               // new test(); // open a new instance of MyFrame
    	    	                System.exit(0); // stop the current running program
    	    	                
    	  
    	    	                
    	    	    			
    	    	    			}
    	    	    		});
    	    	        
    	                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
    	                    @Override
    	                    protected Void doInBackground() throws Exception {
    	                        // Execute your long time process here
    	    	     	    	       if(o.equals("Parcours en largeur (BFS)")) {
    	       	    	    	    	prblm.SolveByBFS();
    	       	    	    	    	t = prblm.cases;
    	       	    	    	    	
    	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
    	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
    	       	    	    	    	test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
    	       	    	    	    	
    	       	    	    	    	
    	       	    	    	       }
    	       	    	    	       else{
    	       	    	    	    	   if(o.equals("Parcours en profondeur (DFS)")) {
    	       	    	    	    		   prblm.SolveByDFS();
    	       	    	    	    	    	t = prblm.cases;
    	       	    	    	    	    	
    	       	    	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
    	       	    	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
    	       	    	    	    	    	test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
    	       	    	    	    		   
    	       	    	    	    	   }else {
    	       	    	    	    		   if(o.equals("Heuristiques-1")) {
    	       	    	    	    			   prblm.SolveByHeurstique();
    	       	       	    	    	    	t = prblm.cases;
    	       	       	    	    	    	
    	       	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
    	       	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
    	       	       	    	    	    	test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
    	       	    	    	    		   }
    	       	    	    	    		   else {
    	       	    	    	    			   if(o.equals("Heuristiques-2")) {
    	       	    	    	    				   prblm.SolveByHeurstique_2();
    	       	    	       	    	    	    	t = prblm.cases;
    	       	    	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
    	       	    	       	    	    	    	test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
    	       	    	       	    	    	    	test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
    	           	    	    	    		   }
													  else if(o.equals("Algorithme GA")){
														  solution s = prblm.SolveByGA();
														  t = s.cases;
													   for (int i = 0; i < s.cases.length; i++) {
														   System.out.println(t[i]);
													   }
													   System.out.println(t[5]);
													   System.out.println(prblm.Fitness);test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
														  test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
														  test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
														  test.this.TABLE_details.setValueAt(Integer.toString(prblm.Fitness),3,1);
												   }
													  else if(o.equals("Algorithme PSO")){
													   prblm.SolveByGA();
													   t = prblm.cases;
													   System.out.println(prblm.Fitness);
													   test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNGenerer),0,1);
													   test.this.TABLE_details.setValueAt(Long.toString(prblm.nbrNExploiter),1,1);
													   test.this.TABLE_details.setValueAt(prblm.timeElapsed.toString(),2,1);
													   test.this.TABLE_details.setValueAt(Integer.toString(prblm.Fitness),3,1);
												   }
    	       	    	    	    		   }
    	       	    	    	    		   }
    	       	    	    	    	   }
    	                    		 
    	                    	 
    	                    	
    	                    	

    	                        return null;
    	                    }

    	                    @Override
    	                    protected void done() {
    	                        loadingWindow.done();
    	                    }
    	                };
    	                loadingWindow.setWorker(worker);
    	                loadingWindow.start();
    	                
    	 
    	    	       test.this.gameBoard.changeDimentions(test.this.nSelector.getValue(),t);
					}
    	        	});
    	       
    	        
    	        BUTTON_stop.addActionListener((ActionListener) new ActionListener() {
    	    		@Override
					public void actionPerformed(ActionEvent e) {

        	            JOptionPane.showMessageDialog(test.this, "The program has stopped running !");
        	            
    	    			}
    	    		});
    	          
    	        test.exitMenuItem.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    // Define the behavior when the menu item is clicked
  	                    System.exit(0);
  	                }
  	            });
    	
    	     
    
    
    }

	
	public static int getInitN() {
		return INIT_N;
	}


	 class Board extends JPanel
	    {     /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		
	     
			private Cell[][] cells = new Cell[50][50];
	        private int size; 
	        // Constructeur de la class Board 
	        public Board(int size)
	        {    
	            super();
	            this.setBackground(test.BOARD_BACKGROUD_COLOR);
	            this.size = 0;
	            this.changeDimentions(size);
	        }
	        // méthode pour le changement des dimensions de tableau
	        public void changeDimentions(int size)
	        {
	        
	        	 this.setBounds(0, 0, test.BOARD_WIDTH+20, test.BOARD_HEIGHT+20);
	            
	            for(int i=0;i<this.size;i++)
	            {
	                for(int j=0;j<this.size;j++)
	                {
	                    
						test.this.remove(this.cells[i][j]);
	                   
	               }
	            }
	            this.size = size;
	            
	            for(int i=0;i<size;i++)
	            {
	                for(int j=0;j<size;j++)
	                {
	                    this.cells[i][j] = new Cell(i, j, size);
	                   test.this.add(this.cells[i][j]);
	                }
	            } 
	        
	            test.this.revalidate();
	            test.this.repaint();
	           test.this.add(this);
	        }
	    
	    public void changeDimentions(int size, int [] t)
	    {
	    	
	        
	    	this.setBounds(0, 0, test.BOARD_WIDTH+20, test.BOARD_HEIGHT+20);
	        
	        for(int i=0;i<this.size;i++)
	        {
	            for(int j=0;j<this.size;j++)
	            {
	              test.this.remove(this.cells[i][j]);
	            }
	        }
	        
	        this.size = size;
	        
	        for(int i=0;i<size;i++)
	        {
	            for(int j=0;j<size;j++)
	            {
	                this.cells[i][j] = new Cell(i, j, size,t);
	               test.this.add(this.cells[i][j]);
	            }
	        } 
	        
	        test.this.revalidate();
	        test.this.repaint();
	        test.this.add(this);
	    }
	}
////////////////////////////////////////////////////////////////////

	// class pour les cellules
	class Cell extends JPanel
	{  
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	    int [] tab ;
	    private int i, j; 
	    private int x, y;
	    private int h, w;
	    // Constructeur de la class Cell 
	    public Cell(int i, int j, int size,int[] t)
	    {
	        super();
	        this.tab = t;
	        this.i = i;
	        this.j = j;
	        this.y = i*(test.BOARD_HEIGHT/size)+((test.BOARD_HEIGHT-(((int)test.BOARD_HEIGHT/size)*size)+20)/2);
	        this.x = j*(test.BOARD_WIDTH/size)+((test.BOARD_WIDTH-(((int)test.BOARD_WIDTH/size)*size)+20)/2);
	        this.h = (test.BOARD_HEIGHT/size);
	        this.w = (test.BOARD_WIDTH/size);

	        this.setBounds(this.x, this.y, this.h, this.w);

	        if((i+j)%2==0) 
	        {
	            this.setBackground(test.CELL_COLOR1);
	        }
	        else
	        {
	            this.setBackground(test.CELL_COLOR2);
	        }
	        
	        // test
	        
	        for(int k =0 ;k <size ;k++) {
	        	 if((i==k && j==tab[k]))
	             {
	                 this.makeItQueen();
	             }
	        }
	       
	    }
	    public Cell(int i, int j, int size)
	    {
	        super();
	        this.i = i;
	        this.j = j;
	        this.y = i*(test.BOARD_HEIGHT/size)+((test.BOARD_HEIGHT-(((int)test.BOARD_HEIGHT/size)*size)+20)/2);
	        this.x = j*(test.BOARD_WIDTH/size)+((test.BOARD_WIDTH-(((int)test.BOARD_WIDTH/size)*size)+20)/2);
	        this.h = (test.BOARD_HEIGHT/size);
	        this.w = (test.BOARD_WIDTH/size);

	        this.setBounds(this.x, this.y, this.h, this.w);

	        if((i+j)%2==0) 
	        {
	            this.setBackground(test.CELL_COLOR1);
	        }
	        else
	        {
	            this.setBackground(test.CELL_COLOR2);
	        }
	        
	      
	    
	    }
	    
	    public void makeItQueen()
	    {
	            ImageIcon imageIcon;
	            if((this.i+this.j)%2==0)
	            {
	                imageIcon = new ImageIcon("queen_dark.png");
	            }
	            else
	            {
	                imageIcon = new ImageIcon("queen_light.png");
	            }
	            Image image = imageIcon.getImage();
	            image = image.getScaledInstance(this.w, this.h,  Image.SCALE_SMOOTH);
	            imageIcon = new ImageIcon(image);
	            JLabel picLabel = new JLabel(imageIcon);
	            add(picLabel);
	    }
	}
	 
	 
////////////////////////////////////////////////////////////////////
	 class NSelectorSlider extends JSlider
	 {
	    
	 	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		static final int FPS_MIN = 0; // N Minimal
	     static final int FPS_MAX = 50; // N maximal
	     static final int FPS_INIT = test.getInitN(); // N par défault
	    
	     // Constructeur de la class NSelectorSlider 
	     public NSelectorSlider()
	     {
	         super(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
	         this.setMajorTickSpacing(10);
	         this.setMinorTickSpacing(1);
	         this.setPaintLabels(true);
	         this.setPaintTicks(true);
	         this.setPaintTrack(true);
	         this.setBounds(test.BOARD_WIDTH+250, 50, test.SLIDER_WIDTH, test.SLIDER_HEIGHT);
	         
	         test.this.add(this);
	     }
	 }
	 
	 // thread class :
class LoadingWindow extends JDialog {
		    private JProgressBar progressBar;
		    private JLabel label;
		    private SwingWorker<Void, Void> worker;
		    private JButton stop ;
	    
		    public LoadingWindow(Frame parent, String title) {
		        super(parent, title, true);
		        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		        setResizable(false);
		        setSize(300, 100);

		        progressBar = new JProgressBar(0, 200);
		        progressBar.setIndeterminate(true);
		        
		        label = new JLabel("Programme en cours...");
		        label.setHorizontalAlignment(JLabel.CENTER);
		       
                // stop button
		        stop = new JButton();
		        stop.setText("Stop");
		       //stop.setBounds(400+350,300-250,30,20);
		        
		        
		        JPanel panel = new JPanel(new BorderLayout());
		       // panel.setBorder(BorderFactory.createEmptyBorder(400,50,400,50));
		       // panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
		       // panel.add(progressBar, BorderLayout.CENTER);
		        panel.add(label, BorderLayout.CENTER);
		        panel.add(stop,BorderLayout.AFTER_LAST_LINE);
		        setContentPane(panel);

		       // setSize(new Dimension(300, 75));
		        setLocationRelativeTo(parent);
		        
		    }

		    public void setWorker(SwingWorker<Void, Void> worker) {
		        this.worker = worker;
		    }

		    public void start() {
		        worker.execute();
		        setVisible(true);
		    }

		    public void done() {
		        setVisible(false);
		        dispose();
		    }
		}

}




    
   
