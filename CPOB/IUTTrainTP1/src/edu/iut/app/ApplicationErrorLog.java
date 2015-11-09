package edu.iut.app;

public class ApplicationErrorLog extends AbstractApplicationLog {

	public ApplicationErrorLog() {
		super();
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
		super.fireMessage("[ERROR]", this.message);
	}


}
