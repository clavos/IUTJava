package edu.iut.app;

public class ApplicationInfoLog extends AbstractApplicationLog {

	public ApplicationInfoLog() {
		super();
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
        super.fireMessage("[INFO]", this.message);
	}

}
