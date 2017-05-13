package cn.zmhappy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;

public class ErlangB {

	static final int WIDTH=600;
	static final int HEIGHT=300;
	
	public static void main(String[] args) {
		
		
		JFrame jf = new JFrame("ErlangB Calculator");	//�½���ܣ����ò���
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		JPanel contentPane = new JPanel();	//�½����ݷ�������
		jf.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,3,5,5));	//���ò��֣�ÿ��3�У�����֮����Ϊ5��λ
		
		JLabel name_a = new JLabel("BHT(Erl.)(a)");	//�½���ǩ
		JLabel name_b = new JLabel("Blocking(b)");
		JLabel name_s = new JLabel("Lines(s)");
		contentPane.add(name_a);
		contentPane.add(name_b);
		contentPane.add(name_s);
		
		final JRadioButton jr_a = new JRadioButton("unknown");	//�½���ѡ��ť
		final JRadioButton jr_b = new JRadioButton("unknown");
		final JRadioButton jr_s = new JRadioButton("unknown");
		jr_b.setSelected(true);		//����ΪĬ��ѡ��
		ButtonGroup bg = new ButtonGroup();		//��ӵ���ť���У���֤ͬһʱ��ֻ��һ����ť��ѡ��
		bg.add(jr_a);
		bg.add(jr_b);
		bg.add(jr_s);
		contentPane.add(jr_a);
		contentPane.add(jr_b);
		contentPane.add(jr_s);
		
		final JTextField input_a = new JTextField(15);	//�½��ı���
		final JTextField input_b = new JTextField(15);
		final JTextField input_s = new JTextField(15);
		contentPane.add(input_a);
		contentPane.add(input_b);
		contentPane.add(input_s);
		
		JButton calculate = new JButton("����");		//�½�����button
		contentPane.add(calculate);
		JButton history = new JButton("����������");
		contentPane.add(history);
		
		calculate.addActionListener(new ActionListener(){	//�¼�����
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jr_a.isSelected()){
					float b = Float.parseFloat(input_b.getText());
					int s = Integer.parseInt(input_s.getText());
					input_a.setText(erlangb_a(b, s)+"");
				}else if(jr_b.isSelected()){
					float a = Float.parseFloat(input_a.getText());
					int s = Integer.parseInt(input_s.getText());
					input_b.setText(erlangb_b(a, s)+"");
				}else if(jr_s.isSelected()){
					float a = Float.parseFloat(input_a.getText());
					float b = Float.parseFloat(input_b.getText());
					input_s.setText(erlangb_s(a, b)+"");
				}
			}
		});
		
		jf.pack();
		
		
		
		/*
		 * ����ͼ��ʵ��
		 */
		/*
		// ��ͼ���ݼ�  
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();  
//        dataSet.addValue(1, "2", "3");
//        dataSet.addValue(2, "2", "4");
//        dataSet.addValue(3, "2", "5");
        
        
        for(int i = 0; i < 20; i++){
        	dataSet.addValue(erlangb_s(i, 0.25), "0.25", i+"");
        	dataSet.addValue(erlangb_s(i, 0.5), "0.5", i+"");
        	dataSet.addValue(erlangb_s(i, 0.75), "0.75", i+"");
        }
            
        JFreeChart chart = ChartFactory.createLineChart("B��������£�s��a�ı仯����", "BHT(Erl.)(a)", "Lines(s)", dataSet,  
                PlotOrientation.VERTICAL, // ���Ʒ���  
                true, // ��ʾͼ��  
                true, // ���ñ�׼������  
                false // �Ƿ����ɳ�����  
                );  
        
        //��ȡ��ͼ������  
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // ���û�ͼ������ɫ  
        plot.setRangeGridlinePaint(Color.WHITE); // ����ˮƽ���򱳾�����ɫ  
        plot.setRangeGridlinesVisible(true);// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue  
        plot.setDomainGridlinePaint(Color.WHITE); // ���ô�ֱ���򱳾�����ɫ  
        plot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse  
        
        CategoryAxis domainAxis = plot.getDomainAxis();     
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));		//���ñ������� 
        domainAxis.setLowerMargin(0.01);// ��߾� �߿����  
        domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ  
        domainAxis.setMaximumCategoryLabelLines(2);  
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,14));  //��ֱ����  
        ValueAxis rangeAxis=plot.getRangeAxis();		//��ȡ��ֱ
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y����ʾ����  
        rangeAxis.setAutoRangeMinimumSize(1);   //��С���  
        rangeAxis.setUpperMargin(0.18);//�ϱ߾�,��ֹ����һ�����ݿ����������ᡣ     
        rangeAxis.setLowerBound(0);   //��Сֵ��ʾ0  
        rangeAxis.setAutoRange(false);   //���Զ�����Y������  
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // ���������Ǵ�С  
        rangeAxis.setTickMarkPaint(Color.BLACK);     // ������������ɫ  
		
        ChartPanel frame1 = new ChartPanel(chart,true);
        JFrame frame = new JFrame("Java����ͳ��ͼ"); 
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(frame1);
        frame.pack();
        
        */
        
//		System.out.println(erlangb_b(12, 20));
		
	}
	
	
	//x!
	public static long factorial(int x){
		long sum = 1;
		for(int i = 1; i <= x; i++){
			sum *= i;
		}
		return sum;
	}
	
	//a,s-->b
		public static double erlangb_b(double a, int s){
			double b = 0, temp = 0; 
			for(int r = 0; r <= s; r++){
				temp = temp + Math.pow(a, r) / factorial(r);
			}
			b = Math.pow(a, s) / factorial(s);
			System.out.println("temp_b = "+b);
			b = b / temp;
			System.out.println("erlangb_b("+a+", "+s+") = "+b);
			return b;
		}
		
		//a,b-->s
		public static int erlangb_s(double a, double b){
			int s = 0;
			while(erlangb_b(a,s) > b){
				s++;
			}
			return s-1;
		}
		
		//b,s-->a
		public static double erlangb_a(double b, int s){
			double a = 0, a_about = s/(1-b);
			double step = 1.0, b_get;
			b_get = erlangb_b(a_about, s);
			
			while(step>0.000001 || b_get<b){
				if(b_get>b){
					a_about -= step;
				}else{
					step = step / 2;
					a_about += step;
				}
				b_get = erlangb_b(a_about, s);
//				System.out.println(b_get+" = erlangb_b("+a_about+", "+s+")");
			}
			
			return a_about;
		}

	
	

}
