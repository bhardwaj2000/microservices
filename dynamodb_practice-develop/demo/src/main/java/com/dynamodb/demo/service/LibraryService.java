package com.dynamodb.demo.service;

import com.dynamodb.demo.exception.EntityNotFoundException;
import com.dynamodb.demo.model.*;
import com.dynamodb.demo.model.request.AuthorCreationRequest;
import com.dynamodb.demo.model.request.BookCreationRequest;
import com.dynamodb.demo.model.request.BookLendRequest;
import com.dynamodb.demo.model.request.MemberCreationRequest;
import com.dynamodb.demo.repository.AuthorRepository;
import com.dynamodb.demo.repository.BookRepository;
import com.dynamodb.demo.repository.LendRepository;
import com.dynamodb.demo.repository.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    public Book readBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ID");
    }

    public Iterable<Book> readBooks() {
        return bookRepository.findAll();
    }

    public Book readBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Cant find any book under given ISBN");
    }

    public Book createBook(BookCreationRequest book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author Not Found");
        }
        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setAuthorId(author.get().getId());
        return bookRepository.save(bookToCreate);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        member.setStatus(MemberStatus.ACTIVE);
        return memberRepository.save(member);
    }

    public Member updateMember(String id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public Author createAuthor(AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }

    public List<String> lendABook(BookLendRequest request) {
        Optional<Member> memberForId = memberRepository.findById(request.getMemberId());
        if (!memberForId.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = memberForId.get();
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new RuntimeException("User is not active to proceed a lending.");
        }
        List<String> booksApprovedToBurrow = new ArrayList<>();
        request.getBookIds().forEach(bookId -> {
            Optional<Book> bookForId = bookRepository.findById(bookId);
            if (!bookForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any book under given ID");
            }
            Optional<Lend> burrowedBook = lendRepository.findByBookIdAndStatus(bookForId.get().getId(), LendStatus.BURROWED);
            if (!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMemberId(memberForId.get().getId());
                lend.setBookId(bookForId.get().getId());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now().toString());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS).toString());
                lendRepository.save(lend);
            }
        });
        return booksApprovedToBurrow;
    }

    public Book updateBook(String bookId, BookCreationRequest request) {
        Optional<Author> author = authorRepository.findById(request.getAuthorId());
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author Not Found");
        }
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new EntityNotFoundException("Book Not Found");
        }
        Book book = optionalBook.get();
        book.setIsbn(request.getIsbn());
        book.setName(request.getName());
        book.setAuthorId(author.get().getId());
        return bookRepository.save(book);
    }

    public List<Member> readMembers() {
        Iterable<Member> members = memberRepository.findAll();
        List<Member> memberList = new ArrayList<>();
        members.forEach(memberList::add);
        return memberList;
    }

}
