package com.netease.backend.coordinator.log.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.netease.backend.coordinator.log.LogException;
import com.netease.backend.coordinator.log.LogRecord;
import com.netease.backend.coordinator.log.LogScanner;
import com.netease.backend.coordinator.util.DbUtil;

public class LogScannerImp implements LogScanner{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rset;
	private DbUtil dbUtil;
	

	public LogScannerImp(Connection conn, PreparedStatement pstmt,
			ResultSet rset) {
		super();
		this.conn = conn;
		this.pstmt = pstmt;
		this.rset = rset;
	}


	public void setDbUtil(DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	

	@Override
	public boolean hasNext() throws LogException {
		return dbUtil.hasNext(rset);
	}

	@Override
	public LogRecord next() throws LogException {
		return dbUtil.getNextLog(rset);
	}

	@Override
	public void endScan() throws LogException {
		dbUtil.endScan(conn, pstmt, rset);
	}
}