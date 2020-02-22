package cn.lastwhisper.rabbitmq.convert;

public class ConverterBody {

	private byte[] body;
	
	public ConverterBody() {
	}

	public ConverterBody(byte[] body) {
		this.body = body;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	
}
