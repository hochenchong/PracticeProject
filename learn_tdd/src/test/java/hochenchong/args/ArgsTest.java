package hochenchong.args;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author HochenChong
 * @date 2019/09/03
 */

public class ArgsTest {
    @Test
    public void testArgs() {
        String schema = "l:bool p:int d:str";
        String command = "-l -p -9 -d /usr/local";
        Args args = new Args(schema, command);
        assertThat(args.getValue("l"), is(Boolean.FALSE));
        assertThat(args.getValue("d"), is("/usr/local"));
        assertThat(args.getValue("p"), is(Integer.valueOf("-9")));
    }
}
