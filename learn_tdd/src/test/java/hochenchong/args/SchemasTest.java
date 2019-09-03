package hochenchong.args;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author HochenChong
 * @date 2019/09/01
 */
public class SchemasTest {

    private Schemas getSchemas() {
        String schemaConfig = "l:bool p:int d:str";
        return new Schemas(schemaConfig);
    }

    @Test
    public void testSchemas() {
        Schemas schemas = getSchemas();
        assertThat(schemas.size(), is(3));
    }

    @Test
    public void testSchemasBool() {
        Schemas schemas = getSchemas();
        assertThat(schemas.getValue("l", "true"), is(Boolean.TRUE));
        assertThat(schemas.getValue("l", "false"), is(Boolean.FALSE));
        assertThat(schemas.getValue("l", null), is(Boolean.FALSE));
    }

    @Test
    public void testSchemasInt() {
        Schemas schemas = getSchemas();
        assertThat(schemas.getValue("p", "1"), is(1));
        assertThat(schemas.getValue("p", "-9"), is(-9));
    }

    @Test
    public void testSchemasStr() {
        Schemas schemas = getSchemas();
        assertThat(schemas.getValue("d", "hello"), is(new String("hello")));
        assertThat(schemas.getValue("d", "/user/test"), is(new String("/user/test")));
    }
}
