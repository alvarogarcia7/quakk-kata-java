import com.codurance.test.quakker.Clock;
import com.codurance.test.quakker.Commands;
import com.codurance.test.quakker.DateTime;
import com.codurance.test.quakker.Output;
import com.codurance.test.quakker.QuakkBuilder;
import com.codurance.test.quakker.QuakkRepository;
import com.codurance.test.quakker.Timeline;
import com.codurance.test.quakker.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class CommandParserShould {

	private Mockery context;
	private QuakkRepository repository;
	private Output output;
	private Commands commands;
	private Clock clock;

	@Before
	public void setUp () {
		context = new Mockery();
		repository = context.mock(QuakkRepository.class);
		output = context.mock(Output.class);
		clock = context.mock(Clock.class);
		commands = new Commands(repository, output, clock);
	}

	@Test
	public void parse_a_quakk_command () {
		context.checking(new Expectations() {{
			oneOf(clock).now(); will(returnValue(new DateTime("18:30")));
			oneOf(repository).save(QuakkBuilder.aNew("I love the weather today").from(new User("Alice")).at(new
					DateTime("18:30"))
					.createQuakk());
		}});

		commands.applyFrom("Alice -> I love the weather today");

		context.assertIsSatisfied();
	}

	@Test
	public void show_a_timeline() {
		final String userName = "John";
		final User user = new User(userName);
		final Timeline userTimeline = new Timeline(
				QuakkBuilder.aNew("Good game though.").from(user).createQuakk(),
				QuakkBuilder.aNew("Damn! We lost!").from(user).createQuakk()
		);

		context.checking(new Expectations() {{
			oneOf(repository).list(user);
			will(returnValue(userTimeline));

			oneOf(output).show(userTimeline);
		}});

		commands.applyFrom(userName);

		context.assertIsSatisfied();
	}
}
