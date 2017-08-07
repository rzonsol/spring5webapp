package pl.rzonsol.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.rzonsol.spring5webapp.model.Author;
import pl.rzonsol.spring5webapp.model.Book;
import pl.rzonsol.spring5webapp.repositories.AuthorRepository;
import pl.rzonsol.spring5webapp.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book dd = new Book("Domain Driven Disign", "1234", "Hatper Collins");
        eric.getBooks().add(dd);
        dd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(dd);
        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J@EE D", "23444", "Worx");
        rod.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
