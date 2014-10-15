package com.netease.backend.coordinator.log;

import com.netease.backend.coordinator.transaction.Action;
import com.netease.backend.coordinator.transaction.Transaction;
import com.netease.backend.tcc.error.HeuristicsException;

public interface LogManager {
	
	void logBegin(Transaction tx, Action action) throws LogException;
	
	void logFinish(Transaction tx, Action action) throws LogException;
	
	void logRegister(Transaction tx) throws LogException;
	
	void logHeuristics(Transaction tx, Action action, HeuristicsException e) throws LogException;
	
	boolean checkExpire(long uuid) throws LogException;
	
	void setCheckpoint(long checkpoint) throws LogException;
	
	long getCheckpoint() throws LogException;
	
	boolean checkActionInRecover(long uuid) throws LogException;
	
	boolean checkLocalLogMgrAlive();

	LogScanner beginScan(long startpoint) throws LogException;
}