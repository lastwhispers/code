package book.chapter07.$7_2_2;

import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

public class MockReqHeader implements Record {
	private long sessionId;
	private String type;
	public MockReqHeader() {}
	public MockReqHeader( long sessionId, String type ) {
		this.sessionId = sessionId;
		this.type = type;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId( long sessionId ) {
		this.sessionId = sessionId;
	}
	public String getType() {
		return type;
	}
	public void setType( String m_ ){
		type = m_;
	}
	public void serialize( OutputArchive a_, String tag ) throws java.io.IOException {
		a_.startRecord( this, tag );
		a_.writeLong( sessionId, "sessionId" );
		a_.writeString( type, "type" );
		a_.endRecord( this, tag );
	}
	public void deserialize( InputArchive a_, String tag ) throws java.io.IOException {
		a_.startRecord( tag );
		sessionId = a_.readLong( "sessionId" );
		type = a_.readString( "type" );
		a_.endRecord( tag );
	}
}

