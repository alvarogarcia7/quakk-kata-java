package com.codurance.test.quakker.test.unit.core.ports;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.domain.Quakk;
import com.codurance.test.quakker.core.domain.Timeline;
import com.codurance.test.quakker.core.domain.User;
import com.codurance.test.quakker.core.ports.QuakkRepository;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public abstract class QuakkRepositoryShould {

    private QuakkRepository repository;

    protected abstract QuakkRepository implementation ();

    @Test
    public void store_a_quakk () {
        repository = implementation();
        final User user = new User("user");
        final Quakk quakk = Quakk.aNew().withMessage("message").from(user).at(new DateTime("22:30")).build();

        repository.save(quakk);

        assertThat(repository.wall(user), is(new Timeline(quakk)));
    }

    @Test
    public void store_multiple_quakks () {
        repository = implementation();
        final User user = new User("user");
        final Quakk first = Quakk.aNew().withMessage("first").from(user).at(new DateTime("22:30")).build();
        final Quakk second = Quakk.aNew().withMessage("second").from(user).at(new DateTime("23:30")).build();

        repository.save(first);
        repository.save(second);

        assertThat(repository.wall(user), is(new Timeline(first, second)));
    }

    @Test
    public void retrieve_only_the_quakks_by_the_selected_user () {
        repository = implementation();
        final User ann = new User("ann");
        final User bob = new User("bob");
        final Quakk anns = Quakk.aNew().withMessage("first").from(ann).at(new DateTime("22:30")).build();
        final Quakk bobs = Quakk.aNew().withMessage("first").from(bob).at(new DateTime("22:30")).build();

        repository.save(anns);
        repository.save(bobs);

        assertThat(repository.wall(ann), is(new Timeline(anns)));
    }

    @Test
    public void shows_other_quakks_on_the_timeline_when_following_others () {
        repository = implementation();
        final User ann = new User("ann");
        final User bob = new User("bob");
        final User charles = new User("charles");
        final Quakk anns = Quakk.aNew().withMessage("ann's first").from(ann).at(new DateTime("22:30")).build();
        final Quakk bobs = Quakk.aNew().withMessage("bob's first").from(bob).at(new DateTime("22:30")).build();
        final Quakk charless = Quakk.aNew().withMessage("charles' first").from(bob).at(new DateTime("22:30")).build();
        repository.save(anns);
        repository.save(bobs);
        repository.save(charless);

        //ann follows bob
        repository.follow(ann, bob);

        repository.follow(ann, charles);


        assertThat(repository.followedBy(ann), contains(bob, charles));
    }


}
