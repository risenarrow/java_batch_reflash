package cn.dabzhuye.mainUI;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.lang.model.element.VariableElement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class mainui {

	public static void main(String [] argc) {
		
		new uiclass().showWindows();;
	
//		ReadThread rt =  new ReadThread("11");
//		rt.start();
//		rt.suspend();
	}
	
	
	
}

class uiclass{
	private JPanel containerPanel;
	private JPanel inScrollPanel;
	private JPanel jPanel3;
	private JScrollPane scrollPane;
	public void showWindows() {
		
		ArrayList<TaskItem> arrayList = new ArrayList<TaskItem>();
		
		int framewith = 1000;
		JFrame jFrame = new JFrame("����");
		jFrame.setSize(framewith,500);
		jFrame.setLocation(300, 200);

		//��panel
		containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		containerPanel.setSize(framewith, 500);
		
			
		//������ panel
		JPanel wai_bao_panel = new JPanel();
		wai_bao_panel.setPreferredSize(new Dimension(framewith,500));
		inScrollPanel = new JPanel();
	
//		inScrollPanel.setLayout(new GridLayout(1,1));
		inScrollPanel.setLayout(new FlowLayout());
		inScrollPanel.setPreferredSize(new Dimension(framewith,0));
		scrollPane = new JScrollPane(inScrollPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		wai_bao_panel.add(scrollPane);		
		
		//��Ӱ�ť�������    ��panel
		jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		jPanel3.setPreferredSize(new Dimension(framewith,60));
		
		//��Ӱ�ť
		JButton addButton = new JButton("����");
		addButton.setPreferredSize(new Dimension(100,30));
		addButton.setName("additem");
		addButton.setIcon(new ImageIcon("./src/res/icon/addicon.png"));
		addButton.addActionListener(new clickAction(inScrollPanel,arrayList));
		
		//��������
		JLabel nameLabel = new JLabel("����");
		JTextField name = new JTextField();
		name.setPreferredSize(new Dimension(60,30));
		name.setName("name");
		jPanel3.add(nameLabel);
		jPanel3.add(name);
		
		//������ַ
		JLabel siteLabel = new JLabel("��ַ");
		JTextField site = new JTextField();
		site.setName("site");
		site.setPreferredSize(new Dimension(150,30));
		jPanel3.add(siteLabel);
		jPanel3.add(site);
		
		//ˢ�¼��
		JLabel intervalRefleshLabel =  new JLabel("ˢ�¼��");
		jPanel3.add(intervalRefleshLabel);
		//��
		
		JTextField intervalDay =  new JTextField("0");
		intervalDay.setName("intervalDay");
		intervalDay.setPreferredSize(new Dimension(40,30));
		JLabel intervalDayLabel =  new JLabel("��");
		jPanel3.add(intervalDayLabel);
		jPanel3.add(intervalDay);
		
		//ʱ
	
		JTextField intervalHour =  new JTextField("0");
		intervalHour.setName("intervalHour");
		intervalHour.setPreferredSize(new Dimension(40,30));
		JLabel intervalHourLabel =  new JLabel("ʱ");
		jPanel3.add(intervalHourLabel);
		jPanel3.add(intervalHour);

		//��
		JTextField intervalMin =  new JTextField("0");
		intervalMin.setName("intervalMin");
		intervalMin.setPreferredSize(new Dimension(40,30));
		JLabel intervalMinLabel =  new JLabel("��");
		jPanel3.add(intervalMinLabel);
		jPanel3.add(intervalMin);
		
		//��
	
		JTextField intervalSecond =  new JTextField("0");
		intervalSecond.setName("intervalSecond");
		intervalSecond.setPreferredSize(new Dimension(40,30));
		JLabel intervalSecondLabel =  new JLabel("��");
		jPanel3.add(intervalSecondLabel);
		jPanel3.add(intervalSecond);
		
		jPanel3.add(addButton);
		
		//ȫ����ʼ
		JButton beginAll = new JButton("ȫ����ʼ",new ImageIcon("./src/res/icon/begin.png"));
		beginAll.setName("beginAll");
		beginAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int leng = arrayList.size();
				for(int i = 0;i<leng;i++) {
					arrayList.get(i).startTask();
					JButton tempButton =  arrayList.get(i).getBeginbtn();
					tempButton.setName("suspend");
					tempButton.setIcon(new ImageIcon("./src/res/icon/suspend.png"));
				}
			}
		});
		jPanel3.add(beginAll);
		
		//ȫ��ȡ��
		JButton cancelAll = new JButton("ȫ����ͣ",new ImageIcon("./src/res/icon/stop.png"));
		cancelAll.setName("cancelAll");
		cancelAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int leng = arrayList.size();
				for(int i = 0;i<leng;i++) {
					arrayList.get(i).suspend();
					JButton tempButton =  arrayList.get(i).getBeginbtn();
					tempButton.setName("begin");
					tempButton.setIcon(new ImageIcon("./src/res/icon/begin.png"));
				}
			}
		});
		jPanel3.add(cancelAll);
		

		containerPanel.add(jPanel3,BorderLayout.NORTH);
		containerPanel.add(scrollPane,BorderLayout.CENTER);
		
		
	
		
		jFrame.add(containerPanel);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class clickAction implements ActionListener{

	private JPanel inScrollPanel;
	private ArrayList<TaskItem> arrayList;
	
	public clickAction(JPanel inScrollPanel,ArrayList<TaskItem> arrayList) {
		// TODO Auto-generated constructor stub	
		this.inScrollPanel = inScrollPanel;
	
		this.arrayList = arrayList;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//��ȡ�밴ťͬһ������
		JButton jButton = (JButton)arg0.getSource();
		String btnanme = jButton.getName();

		//��ȡ���panel
		Container topPanel =  this.inScrollPanel.getParent().getParent().getParent();
		switch (btnanme) {
		case "additem":
			
			TaskItem itemPanel =  new TaskItem(topPanel);
			JTextField intervalDayTF = (JTextField)UiUtil.getCompon(topPanel,"intervalDay" );
			String intervalDay = intervalDayTF.getText();
			if(intervalDay.isEmpty() ) {
				JOptionPane.showMessageDialog(null, "��������Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setIntervalDay(intervalDay);
			
			JTextField intervalHourTF = (JTextField)UiUtil.getCompon(topPanel,"intervalHour" );
			String intervalHour =  intervalHourTF.getText();
			if(intervalHour.isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Сʱ����Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setIntervalHour(intervalHourTF.getText());
			
			
			JTextField intervalMinTF = (JTextField)UiUtil.getCompon(topPanel,"intervalMin" );
			String intervalMin =  intervalMinTF.getText();
			if(intervalMin.isEmpty() ) {
				JOptionPane.showMessageDialog(null, "���Ӳ���Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setIntervalMin(intervalMinTF.getText());
			
	
			JTextField intervalSecondTF = (JTextField)UiUtil.getCompon(topPanel,"intervalSecond" );
			String intervalSecond =  intervalSecondTF.getText();
			if(intervalSecond.isEmpty() ) {
				JOptionPane.showMessageDialog(null, "���Ӳ���Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setIntervalSecond(intervalSecond);
			
			JTextField nameTF = (JTextField)UiUtil.getCompon(topPanel,"name" );
			String name = nameTF.getText();
			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "���Ʋ���Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setName(name);
			
			JTextField siteTF = (JTextField)UiUtil.getCompon(topPanel,"site" );
			String site = siteTF.getText();
			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "��ַ����Ϊ��","����",JOptionPane.ERROR_MESSAGE);
			}
			itemPanel.setSite(site);
			
			int size = arrayList.size();
			itemPanel.create("itempanel"+String.valueOf(size+1)); 
			
			arrayList.add(itemPanel);
//			this.inScrollPanel.setLayout(new GridLayout(size+1,1));
			JPanel item = itemPanel.getItem();
			int itemheight =  new Double(item.getPreferredSize().getHeight()).intValue();
			
			//�鿴�����б��ܸ߶�
			int newsize = arrayList.size();
			int tempheight = itemheight*newsize;
			int newheight = this.inScrollPanel.getHeight();
			if(tempheight > this.inScrollPanel.getHeight()) {
				newheight += itemheight;
			}
			
			this.inScrollPanel.setPreferredSize(new Dimension(this.inScrollPanel.getWidth(), newheight));
			this.inScrollPanel.add(item);
			break;

		default:
			break;
		}
		
		
		this.inScrollPanel.revalidate();
		
	}

	
	
}

class TaskItem implements Runnable{
	private String lastReflesh;
	private String refleshCondition;
	private String nextReflesh;
	private int nextReflesh_timestamp;
	private int timestamp;

	
	private String intervalDay;
	private String intervalHour;
	private String intervalMin;
	private String intervalSecond;
	private String name;
	private String site;
	
	private Container topPanel;

	private String itemname ;			//ÿһ�������panel
	private String itemname1;	//��panel1
	private String itemname2 ;	//��panel2
	private String itemnameLastRefleshValue; // �ϴ�ˢ��label name
	private String itemnameNextRefleshValue; // �´�ˢ��label name
	
	private JButton beginbtn;				//�������а�ť
	private JPanel item;					//��������jpanel

	boolean flag = false;		//�߳���ͣ������־��
	public Thread t;			//�߳̾��
	private String tname; 		//�߳�����


	
	public TaskItem(Container topPanel) {
		// TODO Auto-generated constructor stub
		this.topPanel = topPanel;
	}
	/**
	 * ��������
	 * @param itemname
	 * 
	 */
	public void create(String tempname) {
	
		 this.itemname = tempname;			//ÿһ�������panel
		 this.itemname1 = tempname+"1";	//��panel1
		 this.itemname2 = tempname+"2";	//��panel2
		 this.itemnameLastRefleshValue = tempname+"lastRefleshValue"; // �ϴ�ˢ��label name
		 this.itemnameNextRefleshValue = tempname+"nextRefleshValue"; // �´�ˢ��label name
		 
		
		//��һ��ˢ��ʱ���ʼ��
		this.lastReflesh = "0000-00-00 00:00:00";
		//ˢ�¼��ʱ��
		this.refleshCondition = this.intervalDay+"��"+this.intervalHour+"ʱ"+this.intervalMin+"��"+this.intervalSecond+"��";
		//�´�ˢ��ʱ��
		this.nextReflesh = this.intervalDay+"��"+this.intervalHour+"ʱ"+this.intervalMin+"��"+this.intervalSecond+"��";
		//������
		this.nextReflesh_timestamp = Integer.parseInt(this.intervalDay)*86400
				+Integer.parseInt(this.intervalHour)*3600
				+Integer.parseInt(this.intervalMin)*60
				+Integer.parseInt(this.intervalSecond);
		//�仯������
		this.timestamp = this.nextReflesh_timestamp;
		
		//����item
		item = new JPanel(new FlowLayout());
		item.setPreferredSize(new Dimension(1000,150));
		JPanel itemchild1 = new JPanel();
		JPanel itemchild2 = new JPanel();
		
		item.setName(itemname);
	
		
		
		itemchild1.setName(itemname1);
		itemchild1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JLabel lastRefleshLabel = new JLabel("�ϴ�ˢ�£�");
		JLabel lastRefleshValue = new JLabel(this.lastReflesh);
		lastRefleshValue.setPreferredSize(new Dimension(300,30));
		lastRefleshValue.setName(this.itemnameLastRefleshValue);
		itemchild1.add(lastRefleshLabel);
		itemchild1.add(lastRefleshValue);
		
		
		JLabel refleshConditionLabel = new JLabel("ˢ�¼����");
		JLabel refleshConditionValue = new JLabel(this.refleshCondition);
		refleshConditionValue.setPreferredSize(new Dimension(250,30));
		itemchild1.add(refleshConditionLabel);
		itemchild1.add(refleshConditionValue);
		
		
		JLabel nextRefleshLabel = new JLabel("�´�ˢ�£�");
		JLabel nextRefleshValue = new JLabel(this.nextReflesh);
		nextRefleshValue.setPreferredSize(new Dimension(100,30));
		nextRefleshValue.setName(this.itemnameNextRefleshValue);
		itemchild1.add(nextRefleshLabel);
		itemchild1.add(nextRefleshValue);
		JButton setBtn = new JButton(new ImageIcon("./src/res/icon/set.png")); 
		itemchild1.add(setBtn);
	
		
		itemchild2.setName(itemname2);
		itemchild2.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel nameLabel = new JLabel("��վ���ƣ�");
		JLabel name = new JLabel(this.name);
		name.setPreferredSize(new Dimension(300,30));
		itemchild2.add(nameLabel);
		itemchild2.add(name);
		
		JLabel siteLabel = new JLabel("��ַ��");
		JLabel site = new JLabel(this.site);
		site.setPreferredSize(new Dimension(400,30));
		itemchild2.add(siteLabel);
		itemchild2.add(site);
		
		//�ұ�������ť
		JPanel rightbtnpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		rightbtnpanel.setPreferredSize(new Dimension(100,30));
		JButton detailbtn = new JButton(new ImageIcon("./src/res/icon/pulldown.png"));
		detailbtn.setPreferredSize(new Dimension(30,30));
		detailbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel w_panel =  (JPanel)UiUtil.getCompon(topPanel, itemname+"w_dpanel");
				if(w_panel.isVisible()) {
					w_panel.setVisible(false);
					item.setPreferredSize(new Dimension(1000,150));
					JPanel parentJPanel = (JPanel)item.getParent();
					parentJPanel.setPreferredSize(new Dimension(parentJPanel.getWidth(),parentJPanel.getHeight()-100));
				}else {
					w_panel.setVisible(true);
					item.setPreferredSize(new Dimension(1000,250));
					JPanel parentJPanel = (JPanel)item.getParent();
					parentJPanel.setPreferredSize(new Dimension(parentJPanel.getWidth(),parentJPanel.getHeight()+100));
				}
				
			}
		});
		rightbtnpanel.add(detailbtn);
		
		beginbtn = new JButton(new ImageIcon("./src/res/icon/begin.png"));
		beginbtn.setPreferredSize(new Dimension(30,30));
		beginbtn.setName("begin");
		beginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn = (JButton)e.getSource();
				if(btn.getName().equals("begin")) {
					startTask();
					btn.setName("suspend");
					btn.setIcon(new ImageIcon("./src/res/icon/suspend.png"));
				}else {
					suspend();
					btn.setName("begin");
					btn.setIcon(new ImageIcon("./src/res/icon/begin.png"));
				}
			}
		});
		
		rightbtnpanel.add(beginbtn);
		rightbtnpanel.setPreferredSize(new Dimension(100,30));
		itemchild2.add(rightbtnpanel);
		
		item.add(itemchild1);
		item.add(itemchild2);
		
		
		//��ϸ������Ϣ
		JPanel w_dpanel = new JPanel(new FlowLayout());
		w_dpanel.setVisible(false);
		w_dpanel.setName(itemname+"w_dpanel");
		w_dpanel.setPreferredSize(new Dimension(900,100));
		JTextArea dTextArea = new JTextArea();
		dTextArea.setEditable(false);
		dTextArea.setLineWrap(true);
		dTextArea.setText("");
		dTextArea.setMargin(new Insets(5, 5, 5, 5));
		dTextArea.setBackground(new Color(238,238,238));
		dTextArea.setName(itemname+"dTextArea");
		
		JScrollPane djscroll = new JScrollPane(dTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		djscroll.setPreferredSize(new Dimension(900,80));
		
		w_dpanel.add(djscroll);

		item.add(w_dpanel);
		
		//����
		JLabel panel_1=new JLabel(new ImageIcon("./src/res/icon/line.png"));
		panel_1.setPreferredSize(new Dimension(1000,5));
		item.add(panel_1); 
		
		//return item;
	}
	
	/**
	 * 
	 * ��ʼ�ƻ�����
	 * 
	 */
	public void startTask() {
		//��ʼ����
		if(t == null) {
			start();
		}else {
			resume();
		}	
	}
	
	public JPanel getItem() {
		return item;
	}
	public void setItem(JPanel item) {
		this.item = item;
	}
	public JButton getBeginbtn() {
		return beginbtn;
	}
	public void setBeginbtn(JButton startButton) {
		this.beginbtn = startButton;
	}
	public String getLastReflesh() {
		return lastReflesh;
	}
	public void setLastReflesh(String lastReflesh) {
		this.lastReflesh = lastReflesh;
	}
	public String getRefleshCondition() {
		return refleshCondition;
	}
	public void setRefleshCondition(String refleshCondition) {
		this.refleshCondition = refleshCondition;
	}
	public String getNextReflesh() {
		return nextReflesh;
	}
	public void setNextReflesh(String nextReflesh) {
		this.nextReflesh = nextReflesh;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getIntervalDay() {
		return intervalDay;
	}
	public void setIntervalDay(String intervalDay) {
		this.intervalDay = intervalDay;
	}
	public String getIntervalHour() {
		return intervalHour;
	}
	public void setIntervalHour(String intervalHour) {
		this.intervalHour = intervalHour;
	}
	public String getIntervalMin() {
		return intervalMin;
	}
	public void setIntervalMin(String intervalMin) {
		this.intervalMin = intervalMin;
	}
	public String getIntervalSecond() {
		return intervalSecond;
	}
	public void setIntervalSecond(String intervalSecond) {
		this.intervalSecond = intervalSecond;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {	
			while(true) {
				while(flag) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(1000);
					if(this.timestamp == 0) {
						Thread tt = new Thread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								JTextArea jp = (JTextArea)UiUtil.getCompon(topPanel, itemname+"dTextArea");
								jp.setText("��ȡ������....");
								String result  = UiUtil.sendRequest(site);
								jp.setText(result);
							}
						}, String.valueOf(new Date().getTime()));
						tt.start();
						
						this.timestamp = this.nextReflesh_timestamp;
						
						//�޸���һ��ˢ��ʱ��
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String newtext = df.format(new Date());
						JLabel newjl = (JLabel)UiUtil.getCompon(this.topPanel,this.itemnameLastRefleshValue);
						newjl.setText(newtext);
					}
					
					//�޸��´�ˢ��ʱ��
					this.nextReflesh = UiUtil.getTimeFormat(this.timestamp);
					JLabel newjll = (JLabel)UiUtil.getCompon(this.topPanel,this.itemnameNextRefleshValue);
					newjll.setText(this.nextReflesh);
					this.timestamp-- ;
					
					
					//ˢ�����
					this.topPanel.revalidate();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
	
	
	//��ʼ
	public void start() {
		if(this.t == null) {
			this.t = new Thread(this,this.itemname);
			t.start();
		}
	}
	
	//��ͣ
	public void suspend() {
		this.flag = true;
	}
	
	//����
	public synchronized void  resume() {
		this.flag = false;
		notify();
	}
	
}

 class ReadThread implements Runnable{
	  public Thread t;
	  private String threadName;
	  boolean suspended=false;
	  public ReadThread(String threadName){
	   this.threadName=threadName;
	   System.out.println("Creating " + threadName );
	  }
	  public void run() {
	   for(int i = 10; i > 0; i--) {
	   System.out.println("Thread: " + threadName + ", " + i);
	   // Let the thread sleep for a while.
	   try {
	    Thread.sleep(300);
	    synchronized(this) {
	     while(suspended) {
	    	 wait();
	     }
	    }
	   } catch (InterruptedException e) {
	    System.out.println("Thread " + threadName + " interrupted.");
	    e.printStackTrace();
	   }
	   System.out.println("Thread " + threadName + " exiting.");
	   }
	  }
	  /**
	   * ��ʼ
	   */
	  public void start(){
	   System.out.println("Starting " + threadName );
	   if(t==null){
	    t=new Thread(this, threadName);
	    t.start();
	   }
	  }
	  /**
	   * ��ͣ
	   */
	   void suspend(){
	   suspended = true;
	  }
	   /**
	   * ����
	   */
	   synchronized void resume(){
	    suspended = false;
	    notify();
	   }
	 }
 






