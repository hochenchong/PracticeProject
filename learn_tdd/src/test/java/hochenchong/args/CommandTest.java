package hochenchong.args;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


/**
 * @author HochenChong
 * @date 2019/09/02
 */

public class CommandTest {

    @Test
    public void test_has_value() {
        String commandStr = "-l true -d /usr/local -p -z";
        Command command = new Command(commandStr);
        assertThat(command.getValue("l"), is("true"));
        assertThat(command.getValue("d"), is("/usr/local"));
        assertNull(command.getValue("p"));
        assertNull(command.getValue("z"));

        String commandStr1 = "-l -p -9 -d /usr/local";
        Command command1 = new Command(commandStr1);
        assertThat(command1.getValue("p"), is("-9"));
        assertThat(command1.getValue("d"), is("/usr/local"));
        assertNull(command1.getValue("l"));
    }

/*    @Test
    public void test_is_not_num() {
        String s = "-9";
        System.out.println(Command.isNumber("-1"));
        System.out.println(Command.isNumber("-9"));
        System.out.println(Command.isNumber("-46"));
        System.out.println(Command.isNumber("-1352fasd13"));
    }*/
}
