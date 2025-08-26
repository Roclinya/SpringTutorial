package exception;

//客製Exception處理
public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 自訂例外代碼
	private String strExceptionCode;
	// 自訂例外訊息
	private String strExceptionMessage;

	// parent方法才能印出message
	public MyException(String message) {
		super(message);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyException(String strExceptionCode, String strExceptionMessage) {
		this.strExceptionCode = strExceptionCode;
		this.strExceptionMessage = strExceptionMessage;
	}

	public String getStrExceptionCode() {
		return strExceptionCode;
	}

	public void setStrExceptionCode(String strExceptionCode) {
		this.strExceptionCode = strExceptionCode;
	}

	public String getStrExceptionMessage() {
		return strExceptionMessage;
	}

	public void setStrExceptionMessage(String strExceptionMessage) {
		this.strExceptionMessage = strExceptionMessage;
	}
}