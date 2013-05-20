package name.orionis.cms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

public class TestDemo {
	@Test
	public void testDemo1() throws ParseException{
		//System.out.println(new SimpleDateFormat("yyyy-mm-dd").parse("Wed May 15 00:00:00 UTC 0800 2013"));
		//Date parse = DateFormat.getDateInstance().parse("Wed May 15 00:00:00 UTC 0800 2013");
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss 'UTC' 0800 yyyy", Locale.US);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(sdf2.format(sdf.parse("Wed May 15 00:00:00 UTC 0800 2013")));
	}
}
