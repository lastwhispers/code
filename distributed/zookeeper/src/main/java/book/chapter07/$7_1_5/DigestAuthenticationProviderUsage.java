package book.chapter07.$7_1_5;
import java.security.NoSuchAlgorithmException;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

//对“username:password”进行编码
public class DigestAuthenticationProviderUsage {
	public static void main( String[] args ) throws NoSuchAlgorithmException {
		System.out.println( DigestAuthenticationProvider.generateDigest( "super:zk-book" ) );
	}
}