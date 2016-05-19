import com.codurance.test.quakker.Commands;
import com.codurance.test.quakker.Quakk;
import com.codurance.test.quakker.QuakkRepository;
import com.codurance.test.quakker.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CommandParserShould {

	private Mockery context;

	@Before
	public void setUp () {
		context = new Mockery();
	}

	@Test
	public void parse_a_quakk_command () {
		final QuakkRepository repository = context.mock(QuakkRepository.class);
		final Commands commands = new Commands();
		context.checking(new Expectations() {{
			oneOf(repository).save(new Quakk("I love the weather today", new User("Alice")));;
		}});

		commands.applyFrom("Alice -> I love the weather today");

		context.assertIsSatisfied();
	}
}
