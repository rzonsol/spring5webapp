package pl.rzonsol.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.rzonsol.spring5webapp.model.Author;
import pl.rzonsol.spring5webapp.model.Book;
import pl.rzonsol.spring5webapp.model.Publisher;
import pl.rzonsol.spring5webapp.repositories.AuthorRepository;
import pl.rzonsol.spring5webapp.repositories.BookRepository;
import pl.rzonsol.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher publisher1 = new Publisher("Hatper Collins","address 2");
        Book dd = new Book("Domain Driven Disign", "1234",publisher1 );
        eric.getBooks().add(dd);
        dd.getAuthors().add(eric);

        publisherRepository.save(publisher1);
        authorRepository.save(eric);
        bookRepository.save(dd);
        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher publisher2 = new Publisher("Worx","saddress1");
        Book noEJB = new Book("J@EE D", "23444", publisher2);
        rod.getBooks().add(noEJB);

        publisherRepository.save(publisher2);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
