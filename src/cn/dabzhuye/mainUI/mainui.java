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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		JFrame jFrame = new JFrame("测试");
		jFrame.setSize(framewith,500);
		jFrame.setLocation(300, 200);

		//总panel
		containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		containerPanel.setSize(framewith, 500);
		
			
		//滚动的 panel
		JPanel wai_bao_panel = new JPanel();
		wai_bao_panel.setPreferredSize(new Dimension(framewith,500));
		inScrollPanel = new JPanel();
	
//		inScrollPanel.setLayout(new GridLayout(1,1));
		inScrollPanel.setLayout(new FlowLayout());
		inScrollPanel.setPreferredSize(new Dimension(framewith,0));
		scrollPane = new JScrollPane(inScrollPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		wai_bao_panel.add(scrollPane);		
		
		//添加按钮和输入框    的panel
		jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		jPanel3.setPreferredSize(new Dimension(framewith,60));
		
		//添加按钮
		JButton addButton = new JButton("增加");
		addButton.setPreferredSize(new Dimension(100,30));
		addButton.setName("additem");
		addButton.setIcon(new ImageIcon("./src/res/icon/addicon.png"));
		addButton.addActionListener(new clickAction(inScrollPanel,arrayList));
		
		//任务名称
		JLabel nameLabel = new JLabel("名称");
		JTextField name = new JTextField();
		name.setPreferredSize(new Dimension(60,30));
		name.setName("name");
		jPanel3.add(nameLabel);
		jPanel3.add(name);
		
		//任务网址
		JLabel siteLabel = new JLabel("网址");
		JTextField site = new JTextField();
		site.setName("site");
		site.setPreferredSize(new Dimension(150,30));
		jPanel3.add(siteLabel);
		jPanel3.add(site);
		
		//刷新间隔
		JLabel intervalRefleshLabel =  new JLabel("刷新间隔");
		jPanel3.add(intervalRefleshLabel);
		//天
		
		JTextField intervalDay =  new JTextField("0");
		intervalDay.setName("intervalDay");
		intervalDay.setPreferredSize(new Dimension(40,30));
		JLabel intervalDayLabel =  new JLabel("天");
		jPanel3.add(intervalDayLabel);
		jPanel3.add(intervalDay);
		
		//时
	
		JTextField intervalHour =  new JTextField("0");
		intervalHour.setName("intervalHour");
		intervalHour.setPreferredSize(new Dimension(40,30));
		JLabel intervalHourLabel =  new JLabel("时");
		jPanel3.add(intervalHourLabel);
		jPanel3.add(intervalHour);

		//分
		JTextField intervalMin =  new JTextField("0");
		intervalMin.setName("intervalMin");
		intervalMin.setPreferredSize(new Dimension(40,30));
		JLabel intervalMinLabel =  new JLabel("分");
		jPanel3.add(intervalMinLabel);
		jPanel3.add(intervalMin);
		
		//秒
	
		JTextField intervalSecond =  new JTextField("0");
		intervalSecond.setName("intervalSecond");
		intervalSecond.setPreferredSize(new Dimension(40,30));
		JLabel intervalSecondLabel =  new JLabel("秒");
		jPanel3.add(intervalSecondLabel);
		jPanel3.add(intervalSecond);
		
		jPanel3.add(addButton);
		
		//全部开始
		JButton beginAll = new JButton("全部开始",new ImageIcon("./src/res/icon/begin.png"));
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
		
		//全部取消
		JButton cancelAll = new JButton("全部暂停",new ImageIcon("./src/res/icon/stop.png"));
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
		
		//获取与按钮同一层的组件
		JButton jButton = (JButton)arg0.getSource();
		String btnanme = jButton.getName();

		//获取最顶层panel
		Container topPanel =  this.inScrollPanel.getParent().getParent().getParent();
		switch (btnanme) {
		case "additem":
			
			TaskItem itemPanel =  new TaskItem(topPanel);
			
			JTextField intervalDayTF = (JTextField)UiUtil.getCompon(topPanel,"intervalDay" );
			String intervalDay = intervalDayTF.getText();
			
			JTextField intervalHourTF = (JTextField)UiUtil.getCompon(topPanel,"intervalHour" );
			String intervalHour =  intervalHourTF.getText();
			
			JTextField intervalMinTF = (JTextField)UiUtil.getCompon(topPanel,"intervalMin" );
			String intervalMin =  intervalMinTF.getText();
			
			JTextField intervalSecondTF = (JTextField)UiUtil.getCompon(topPanel,"intervalSecond" );
			String intervalSecond =  intervalSecondTF.getText();
			
			JTextField nameTF = (JTextField)UiUtil.getCompon(topPanel,"name" );
			String name = nameTF.getText();
			
			JTextField siteTF = (JTextField)UiUtil.getCompon(topPanel,"site" );
			String site = siteTF.getText();
			
			boolean re = itemPanel.checkform(name, site, intervalDay, intervalHour, intervalMin, intervalSecond);
			if(!re)
				return;
			
			itemPanel.setIntervalDay(intervalDay);
			itemPanel.setIntervalHour(intervalHourTF.getText());
			itemPanel.setIntervalMin(intervalMinTF.getText());
			itemPanel.setIntervalSecond(intervalSecond);
			itemPanel.setName(name);
			itemPanel.setSite(site);
			
			int size = arrayList.size();
			itemPanel.create("itempanel"+String.valueOf(size+1)+new Date().getTime()); 
			arrayList.add(itemPanel);
			
//			this.inScrollPanel.setLayout(new GridLayout(size+1,1));
			JPanel item = itemPanel.getItem();
			int itemheight =  new Double(item.getPreferredSize().getHeight()).intValue();
			
			//查看任务列表总高度
			int newsize = arrayList.size();
			int tempheight = itemheight*newsize;
			int newheight = this.inScrollPanel.getHeight();
			if(tempheight > this.inScrollPanel.getHeight()) {
				newheight += itemheight;
			}
			
			this.inScrollPanel.setPreferredSize(new Dimension(this.inScrollPanel.getWidth(), newheight));
			this.inScrollPanel.add(item);
			//删除用的
			itemPanel.setArrayList(arrayList);
			itemPanel.setParent(this.inScrollPanel);
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

	private String itemname ;			//每一项最外层panel
	private String itemname1;	//子panel1
	private String itemname2 ;	//子panel2
	private String itemnameLastRefleshValue; // 上次刷新label name
	private String itemnameNextRefleshValue; // 下次刷新label name
	
	private JButton beginbtn;				//单个运行按钮
	private JPanel item;					//单个任务jpanel

	boolean flag = false;		//线程暂停继续标志、
	public Thread t;			//线程句柄
	private String tname; 		//线程名称

	
	
	private JLabel lastRefleshValue;			//显示上次刷新 
	private JLabel refleshConditionValue;		//显示刷新时间
	private JLabel nextRefleshValue;			//显示下次刷新剩余时间
	private JLabel nameValue;					//显示任务名称
	private JLabel siteValue;					//显示网址
	
	
	private ArrayList<TaskItem> arrayList;		//任务列表
	private JPanel inScrollPane;			//父级滚动框
	
	
	public TaskItem(Container topPanel) {
		// TODO Auto-generated constructor stub
		this.topPanel = topPanel;
	}
	/**
	 * 创建任务
	 * @param itemname
	 * 
	 */
	public void create(String tempname) {
	
		 this.itemname = tempname;			//每一项最外层panel
		 this.itemname1 = tempname+"1";	//子panel1
		 this.itemname2 = tempname+"2";	//子panel2
		 this.itemnameLastRefleshValue = tempname+"lastRefleshValue"; // 上次刷新label name
		 this.itemnameNextRefleshValue = tempname+"nextRefleshValue"; // 下次刷新label name
		 
		
		//上一次刷新时间初始化
		this.lastReflesh = "0000-00-00 00:00:00";
		//刷新间隔时间
		this.refleshCondition = this.intervalDay+"天"+this.intervalHour+"时"+this.intervalMin+"分"+this.intervalSecond+"秒";
		//下次刷新时间
		this.nextReflesh = this.intervalDay+"天"+this.intervalHour+"时"+this.intervalMin+"分"+this.intervalSecond+"秒";
		//总秒数
		this.nextReflesh_timestamp = Integer.parseInt(this.intervalDay)*86400
				+Integer.parseInt(this.intervalHour)*3600
				+Integer.parseInt(this.intervalMin)*60
				+Integer.parseInt(this.intervalSecond);
		//变化的秒数
		this.timestamp = this.nextReflesh_timestamp;
		
		//构建item
		item = new JPanel(new FlowLayout());
		item.setPreferredSize(new Dimension(1000,150));
		item.setName(itemname);
		JPanel itemedit = new JPanel();
		JPanel itemchild = new JPanel(new FlowLayout());
		itemchild.setPreferredSize(new Dimension(1000,100));
		JPanel itemchild1 = new JPanel();
		JPanel itemchild2 = new JPanel();
		
		
		//点击设置按钮时显示编辑框，默认隐藏
		itemedit.setVisible(false);
				//任务名称
				JLabel edit_nameLabel = new JLabel("名称");
				JTextField edit_name = new JTextField();
				edit_name.setText(this.name);
				edit_name.setPreferredSize(new Dimension(60,30));
				itemedit.add(edit_nameLabel);
				itemedit.add(edit_name);
				
				//任务网址
				JLabel edit_siteLabel = new JLabel("网址");
				JTextField edit_site = new JTextField();
				edit_site.setText(this.site);
				edit_site.setPreferredSize(new Dimension(150,30));
				itemedit.add(edit_siteLabel);
				itemedit.add(edit_site);
				
				//刷新间隔
				JLabel edit_intervalRefleshLabel =  new JLabel("刷新间隔");
				itemedit.add(edit_intervalRefleshLabel);
				//天
				
				JTextField edit_intervalDay =  new JTextField("0");
				edit_intervalDay.setText(this.intervalDay);
				edit_intervalDay.setPreferredSize(new Dimension(40,30));
				JLabel edit_intervalDayLabel =  new JLabel("天");
				itemedit.add(edit_intervalDayLabel);
				itemedit.add(edit_intervalDay);
				
				//时
			
				JTextField edit_intervalHour =  new JTextField("0");
				edit_intervalHour.setText(this.intervalHour);
				edit_intervalHour.setPreferredSize(new Dimension(40,30));
				JLabel edit_intervalHourLabel =  new JLabel("时");
				itemedit.add(edit_intervalHourLabel);
				itemedit.add(edit_intervalHour);

				//分
				JTextField edit_intervalMin =  new JTextField("0");
				edit_intervalMin.setText(this.intervalMin);
				edit_intervalMin.setPreferredSize(new Dimension(40,30));
				JLabel edit_intervalMinLabel =  new JLabel("分");
				itemedit.add(edit_intervalMinLabel);
				itemedit.add(edit_intervalMin);
				
				//秒
			
				JTextField edit_intervalSecond =  new JTextField("0");
				edit_intervalSecond.setText(this.intervalSecond);
				edit_intervalSecond.setPreferredSize(new Dimension(40,30));
				JLabel edit_intervalSecondLabel =  new JLabel("秒");
				itemedit.add(edit_intervalSecondLabel);
				itemedit.add(edit_intervalSecond);
				
				//返回按钮
				JButton fanhui = new JButton("返回");
				fanhui.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						itemedit.setVisible(false);
						itemchild.setVisible(true);
					}
				});
				itemedit.add(fanhui);
				
				//提交按钮
				JButton submit = new JButton("提交");
				submit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						//获取表单值
						String str_name = edit_name.getText();
						String str_site = edit_site.getText();
						String str_intervalDay = edit_intervalDay.getText();
						String str_intervalHour = edit_intervalHour.getText();
						String str_intervalMin = edit_intervalMin.getText();
						String str_intervalSecond = edit_intervalSecond.getText();
						
						boolean re = checkform(str_name, str_site, str_intervalDay, str_intervalHour, str_intervalMin, str_intervalSecond);
						if(!re)
							return;
						name  = str_name;
						site = str_site;
						
						//设置最新显示
						nameValue.setText(name);
						siteValue.setText(site);
						
						if(intervalDay.equals(str_intervalDay) && intervalHour.equals(str_intervalHour) && intervalMin.equals(str_intervalMin) && intervalSecond.equals(str_intervalSecond))
						{
							
						}else {
							intervalDay = str_intervalDay;
							intervalHour = str_intervalHour;
							intervalMin = str_intervalMin;
							intervalSecond = str_intervalSecond;
							refleshConditionValue.setText(intervalDay+"天"+intervalHour+"时"+intervalMin+"分"+intervalSecond+"秒");
							nextRefleshValue.setText(intervalDay+"天"+intervalHour+"时"+intervalMin+"分"+intervalSecond+"秒");
							
							//总秒数
							nextReflesh_timestamp = Integer.parseInt(intervalDay)*86400
									+Integer.parseInt(intervalHour)*3600
									+Integer.parseInt(intervalMin)*60
									+Integer.parseInt(intervalSecond);
							//变化的秒数
							timestamp = nextReflesh_timestamp;
						}
						
						
						//隐藏itemedit ， 显示itemchild
						itemedit.setVisible(false);
						itemchild.setVisible(true);
						//开启任务
						startTask();
						beginbtn.setName("suspend");
						beginbtn.setIcon(new ImageIcon("./src/res/icon/suspend.png"));
					}
				});
				itemedit.add(submit);
				
				//删除按钮
				/**
				 * System.out.println(arrayList.size());
				System.out.println(inScrollPane.getComponentCount());
				 * 
				 */
				JButton del = new JButton("删除");
				
				del.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
							//获取高度
						int itemheight =  new Double(item.getPreferredSize().getHeight()).intValue();
						
							//删除滚动条存储的元素
							inScrollPane.remove(item);
							//删除集合里的元素
							
							
							for (int i = 0; i < arrayList.size(); i++) {
								if(arrayList.get(i).getItemname().equals(itemname)) {
									System.out.println(arrayList.get(i).getItemname());
									arrayList.remove(i);
								}
							}
							//查看任务列表总高度
							
							int newsize = arrayList.size();
							int tempheight = itemheight*newsize;
							int newheight = inScrollPane.getHeight();
							if(tempheight < inScrollPane.getHeight()) {
								newheight -= itemheight;
							}
							
							inScrollPane.setPreferredSize(new Dimension(inScrollPane.getWidth(), newheight));
							//重新刷新
							inScrollPane.repaint();
							
						}
					
				});
				itemedit.add(del);
				
		
		//非编辑状态下,itemchild 为显示状态，点击编辑后隐藏
		itemchild1.setName(itemname1);
		itemchild1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JLabel lastRefleshLabel = new JLabel("上次刷新：");
		lastRefleshValue = new JLabel(this.lastReflesh);
		lastRefleshValue.setPreferredSize(new Dimension(300,30));
		lastRefleshValue.setName(this.itemnameLastRefleshValue);
		itemchild1.add(lastRefleshLabel);
		itemchild1.add(lastRefleshValue);
		
		
		JLabel refleshConditionLabel = new JLabel("刷新间隔：");
		refleshConditionValue = new JLabel(this.refleshCondition);
		refleshConditionValue.setPreferredSize(new Dimension(250,30));
		itemchild1.add(refleshConditionLabel);
		itemchild1.add(refleshConditionValue);
		
		
		JLabel nextRefleshLabel = new JLabel("下次刷新：");
		nextRefleshValue = new JLabel(this.nextReflesh);
		nextRefleshValue.setPreferredSize(new Dimension(100,30));
		nextRefleshValue.setName(this.itemnameNextRefleshValue);
		itemchild1.add(nextRefleshLabel);
		itemchild1.add(nextRefleshValue);
		
		
		//设置按钮
		JButton setBtn = new JButton(new ImageIcon("./src/res/icon/set.png")); 
		setBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//首先停止当前任务
				if(beginbtn.getName().equals("suspend")) {
					suspend();
					beginbtn.setName("begin");
					beginbtn.setIcon(new ImageIcon("./src/res/icon/begin.png"));
				}
				//隐藏itemchild
				itemchild.setVisible(false);
				//显示编辑  itemedit
				itemedit.setVisible(true);
			}
		});
		itemchild1.add(setBtn);
	
		itemchild2.setName(itemname2);
		itemchild2.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel nameLabel = new JLabel("网站名称：");
		nameValue = new JLabel(this.name);
		nameValue.setPreferredSize(new Dimension(300,30));
		itemchild2.add(nameLabel);
		itemchild2.add(nameValue);
		
		JLabel siteLabel = new JLabel("网址：");
		siteValue = new JLabel(this.site);
		siteValue.setPreferredSize(new Dimension(400,30));
		itemchild2.add(siteLabel);
		itemchild2.add(siteValue);
		
		//右边两个按钮
		JPanel rightbtnpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		rightbtnpanel.setPreferredSize(new Dimension(100,30));
		//下拉按扭
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
		
		//单个开始按钮
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
		
		itemchild.add(itemchild1);
		itemchild.add(itemchild2);
		
		item.add(itemchild);
		item.add(itemedit);
		
		
		//详细返回信息
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
		
		//横线
		JLabel panel_1=new JLabel(new ImageIcon("./src/res/icon/line.png"));
		panel_1.setPreferredSize(new Dimension(1000,5));
		item.add(panel_1); 
		
		//return item;
	}
	
	/**
	 * 
	 * 开始计划任务
	 * 
	 */
	public void startTask() {
		//开始任务
		if(t == null) {
			start();
		}else {
			resume();
		}	
	}
	
	public String getItemname() {
		return this.itemname;
	}
	
	public void setParent(JPanel inScrollPane) {
		this.inScrollPane = inScrollPane;
	}
	
	public void setArrayList(ArrayList<TaskItem> arrayList) {
		this.arrayList = arrayList;
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
								jp.setText("获取数据中....");
								String result  = UiUtil.sendRequest(site);
								jp.setText(result);
							}
						}, String.valueOf(new Date().getTime()));
						tt.start();
						
						this.timestamp = this.nextReflesh_timestamp;
						
						//修改上一次刷新时间
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String newtext = df.format(new Date());
						JLabel newjl = (JLabel)UiUtil.getCompon(this.topPanel,this.itemnameLastRefleshValue);
						newjl.setText(newtext);
					}
					
					//修改下次刷新时间
					this.nextReflesh = UiUtil.getTimeFormat(this.timestamp);
					JLabel newjll = (JLabel)UiUtil.getCompon(this.topPanel,this.itemnameNextRefleshValue);
					newjll.setText(this.nextReflesh);
					this.timestamp-- ;
					
					
					//刷新面板
					this.topPanel.revalidate();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
	
	
	//开始
	public void start() {
		if(this.t == null) {
			this.t = new Thread(this,this.itemname);
			t.start();
		}
	}
	
	//暂停
	public void suspend() {
		this.flag = true;
	}
	
	//继续
	public synchronized void  resume() {
		this.flag = false;
		notify();
	}
	
	
	/**
	 ****************************************************************************************
	 */
	/**
	 * 
	 * 检查表单提价
	 * @param name
	 * @param site
	 * @param intervalDay
	 * @param intervalHour
	 * @param intervalMin
	 * @param intervalSecond
	 * @return
	 */
	protected boolean checkform(String name,String site,String intervalDay,String intervalHour,String intervalMin,String intervalSecond) {
		
		if(intervalDay.isEmpty() ) {
			JOptionPane.showMessageDialog(null, "天数不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		
		if(intervalHour.isEmpty() ) {
			JOptionPane.showMessageDialog(null, "小时不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		
		if(intervalMin.isEmpty() ) {
			JOptionPane.showMessageDialog(null, "分钟不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		
		if(intervalSecond.isEmpty() ) {
			JOptionPane.showMessageDialog(null, "秒钟不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		if(Integer.parseInt(intervalSecond) <= 0 && intervalMin.equals("0") && intervalHour.equals("0") &&intervalDay.equals("0") ) {
			JOptionPane.showMessageDialog(null, "秒钟必须大于0","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
	
		if(name.isEmpty()) {
			JOptionPane.showMessageDialog(null, "名称不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		
		if(site.isEmpty()) {
			JOptionPane.showMessageDialog(null, "网址不能为空","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		Pattern pattern = Pattern.compile("^(http|https){1}://[\\s\\w\\W]*?");
		Matcher matcher = pattern.matcher(site);
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "网址格式错误","错误",JOptionPane.ERROR_MESSAGE);return false;
		}
		
		return true;
	}
	
	
}

// class ReadThread implements Runnable{
//	  public Thread t;
//	  private String threadName;
//	  boolean suspended=false;
//	  public ReadThread(String threadName){
//	   this.threadName=threadName;
//	   System.out.println("Creating " + threadName );
//	  }
//	  public void run() {
//	   for(int i = 10; i > 0; i--) {
//	   System.out.println("Thread: " + threadName + ", " + i);
//	   // Let the thread sleep for a while.
//	   try {
//	    Thread.sleep(300);
//	    synchronized(this) {
//	     while(suspended) {
//	    	 wait();
//	     }
//	    }
//	   } catch (InterruptedException e) {
//	    System.out.println("Thread " + threadName + " interrupted.");
//	    e.printStackTrace();
//	   }
//	   System.out.println("Thread " + threadName + " exiting.");
//	   }
//	  }
//	  /**
//	   * 开始
//	   */
//	  public void start(){
//	   System.out.println("Starting " + threadName );
//	   if(t==null){
//	    t=new Thread(this, threadName);
//	    t.start();
//	   }
//	  }
//	  /**
//	   * 暂停
//	   */
//	   void suspend(){
//	   suspended = true;
//	  }
//	   /**
//	   * 继续
//	   */
//	   synchronized void resume(){
//	    suspended = false;
//	    notify();
//	   }
//	 }
 






