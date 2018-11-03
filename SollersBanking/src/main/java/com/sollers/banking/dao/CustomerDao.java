package com.sollers.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sollers.banking.errors.SollersSecurityException;
import com.sollers.banking.vo.CustomerVO;

@Component
public class CustomerDao {

	@Autowired
	@Qualifier("appDbConnImpl")
	private IAppDbConn appDbConn;

	public boolean createCustomer(CustomerVO customerVO) throws SollersSecurityException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			Connection conn = appDbConn.getConnection();
			appDbConn.begin();

			String query = "insert into sollers.customer (custnum,name,checkingacctbal,savingsacctbal,totalbalance,street,city,state,zip,phone,emailaddress,loginid,password) values("
					+ customerVO.getCustomerId() + ",'" + customerVO.getFirstName() + "'," + 0 + "," + 0 + "," + 0
					+ ",'" + customerVO.getStreetName() + "','" + customerVO.getCity() + "','" + customerVO.getState()
					+ "'," + customerVO.getZipcode() + ",'" + "1212121" + "','" + "ztyz@gmail.com" + "','"
					+ customerVO.getLoginId() + "','" + customerVO.getPassword() + "')";

			System.out.println(query);
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			appDbConn.commit();
			b = true;
		} catch (SQLException e) {
			throw new SollersSecurityException("SQL001", "", "");
		} catch (Exception e) {
			throw new SollersSecurityException("GEN001", "", "");
		}
		return b;
	}

	public void modifyCustomer(CustomerVO customerVO) throws SollersSecurityException {
		try {

			Connection conn = appDbConn.getConnection();
			appDbConn.begin();
			String query = "";

			System.out.println(query);
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			appDbConn.commit();

		} catch (SQLException e) {
			throw new SollersSecurityException("SQL001", "", "");
		}
	}

	public void deleteCustomer(String loginId, long customerId) throws SollersSecurityException {
		try {

			Connection conn = appDbConn.getConnection();
			appDbConn.begin();
			String query = "";

			System.out.println(query);
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			appDbConn.commit();

		} catch (SQLException e) {
			throw new SollersSecurityException("SQL001", "", "");
		}
	}

	public Map<String, CustomerVO> getAllCustomers() throws SollersSecurityException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = appDbConn.getConnection();
			ps = appDbConn.getConnection().prepareStatement("select * from customer where loginid=? and password=?");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new SollersSecurityException("SQL001", "", "");
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				appDbConn.closeConnection();
			} catch (SQLException e) {
				System.out.println("Not an issue, warn message when object not available - " + e.getMessage());
			}
		}

		return null;

	}

	public boolean validateLogin(String loginId, String password) throws SollersSecurityException {
		boolean canLogin = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = appDbConn.getConnection().prepareStatement("select * from customer where loginid=? and password=?");
			ps.setString(1, loginId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			canLogin = rs.next();
		} catch (SQLException e) {
			throw new SollersSecurityException("SQL001", "", "");
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
				appDbConn.closeConnection();
			} catch (SQLException e) {
				System.out.println("Not an issue, warn message when object not available - " + e.getMessage());
			}

		}
		return canLogin;
	}
}
