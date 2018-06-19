package com.ccclubs.action.api.chargedot.teld;


/**
 * 
 * 特来电api响应参数
 * 
 * @author Joel
 *
 */
public class TeldApiResp {
	
	private int returnCode;
	
	private int resultCode;
	
	private String returnMsg;
	
	private Object resultValue;
	
	public TeldApiResp(){
		
	}
	
	public TeldApiResp(int returnCode){
		this.returnCode = returnCode;
	}
	
	public final static TeldApiResp FAIL = new TeldApiResp(-1);

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public <T> T getResultValue() {
		return (T)resultValue;
	}

	public void setResultValue(Object resultValue) {
		this.resultValue = resultValue;
	}

	public boolean success(){
		boolean flag = false;
		if(returnCode == 0){
			flag = true;
		}
		if(flag && resultCode !=0){
			flag = false;
		}
		if(!flag && getReturnMsg()!=null){
			try {
				System.out.println(getReturnMsg());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
