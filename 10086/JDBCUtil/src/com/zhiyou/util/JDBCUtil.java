package com.zhiyou.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
	// ������
	public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	// ���ݿ��˺�
	public static final String USER_NAME="root";
	// ���ݿ�����
	public static final String PASS_WORD="123456";
	//���ݿ�����
	public static final String DB_NAME="test";
	//���ݿ��URL
	public static final String URL="jdbc:mysql:///"+DB_NAME;
	
	static {// �����ĳЩ����ֻ��Ҫ���౻���ص�ʱ��ִ��һ��,���Էŵ���̬����		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������ݿ�����
	 * @return	Connection
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con=DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * ���ݿ�ͨ����,ɾ,�ķ���
	 * ��������Ϊ��SQL,��SQL�е�ռλ������
	 */
	public static boolean dbDML(String sql,Object...objects){
		Connection con = getConnection();
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(sql);
			//  �Ѳ����ŵ���Ӧ��ռλ��֮��,������һ�����еĲ�������ռλ��֮����sql �Ѿ�����
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i+1, objects[i]);
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			close(con,ps);
		}
	}
	
	public static <E>List<E> dbDQL(String sql,Class<E> class1,Object...objects){
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<E> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			//  �������,����sql���
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i+1, objects[i]);
			}
			// ������ݿ������е�������
			ResultSetMetaData data = ps.getMetaData();
			// ����һ���ַ�������,����Ϊ���ݿ�����е�����
			String [] names = new String [data.getColumnCount()];			
			for (int i = 0; i < names.length; i++) {
				//   ���ÿһ�е�����,Ȼ���ֵ��ֵ���ַ�������names,data���±��1��ʼ
				String name=data.getColumnLabel(i+1);
				names[i]=name;
			}
			rs = ps.executeQuery();
			while(rs.next()) { // ѭ����
				// ���ݴ����class��ȡһ��ʵ������
				E o = class1.newInstance();
				// ѭ�����ݿ�������    (���ݿ���������model�������Ʊ���һ��)
				for (String string : names) {	//ѭ����
					//  ��һ������ ����ֵ
					Object value = rs.getObject(string);
					Field field = class1.getDeclaredField(string);
					// ��������Ȩ��
					field.setAccessible(true);
					field.set(o, value);
				}
				list.add(o);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	
	
	
	/**
	 * �ر� ���ݿ�����
	 * @param con
	 */
	private static void close(Connection con) {
		try {
			if (con!=null) {
				con.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ر� PreparedStatement
	 * @param con
	 */
	private static void close(PreparedStatement ps) {
		try {
			if (ps!=null) {
				ps.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر� ResultSet
	 * @param con
	 */
	private static void close(ResultSet set) {
		try { 
			if (set!=null) {
				set.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * DML ʱ���õ�close()
	 * @param con
	 * @param ps
	 */
	private static void close(Connection con,PreparedStatement ps) {		
		close(ps);
		close(con);
	}
	/**
	 * DQLʱ���õ�close
	 * @param con
	 * @param ps
	 * @param set
	 */
	public static void close(Connection con,PreparedStatement ps,ResultSet set) {
		close(set);
		close(ps);
		close(con);
	}
}
