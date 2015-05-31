package ntut.csie.detect.util;

public interface HashEncodingUtil {
	String encodeMD5(String input);
	String encodeSHA1(String input);
	String encodeSHA256(String input);
	String encodeSHA512(String input);
	String encodePassword(String input);
}