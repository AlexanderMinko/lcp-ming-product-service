package com.lenovo.productservice.repository.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.lenovo.productservice.entity.Category;
import com.lenovo.productservice.entity.Product;

import java.util.List;
import java.util.UUID;

@ChangeLog
public class MongoChangeLog {

    @ChangeSet(order = "001", id="initBooks", author = "ming")
    public void initBooks(MongockTemplate db) {
        var books = Category.builder()
                .name("books")
                .count(25)
                .build();
        var savedCategory = db.save(books);

        var book0 = Product.builder()
                .name("Crash Course in Python")
                .description("Learn Python at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to" +
                        " your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1000.png")
                .price(14.99)
                .build();

        var book1 = Product.builder()
                .name("Become a Guru in JavaScript")
                .description("Learn JavaScript at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1001.png")
                .price(20.99)
                .build();

        var book2 = Product.builder()
                .name("Exploring Vue.js")
                .description("Learn Vue.js at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1002.png")
                .price(13.99)
                .build();

        var book3 = Product.builder()
                .name("Advanced Techniques in Big Data")
                .description("Learn Big Data at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1003.png")
                .price(16.99)
                .build();

        var book4 = Product.builder()
                .name("Crash Course in Big Data")
                .description("Learn Big Data at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1004.png")
                .price(19.99)
                .build();

        var book5 = Product.builder()
                .name("JavaScript Cookbook")
                .description("Learn JavaScript at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1005.png")
                .price(15.99)
                .build();

        var book6 = Product.builder()
                .name("Beginners Guide to SQL")
                .description("Learn SQL at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1006.png")
                .price(15.99)
                .build();

        var book7 = Product.builder()
                .name("Advanced Techniques in JavaScript")
                .description("Learn JavaScript at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1007.png")
                .price(15.99)
                .build();

        var book8 = Product.builder()
                .name("Introduction to Spring Boot")
                .description("Learn Spring Boot at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1008.png")
                .price(16.99)
                .build();

        var book9 = Product.builder()
                .name("Become a Guru in React.js")
                .description("Learn React.js at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1009.png")
                .price(16.99)
                .build();

        var book10 = Product.builder()
                .name("Beginners Guide to Data Science")
                .description("Learn Data Science at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1010.png")
                .price(19.99)
                .build();

        var book11 = Product.builder()
                .name("Advanced Techniques in Java")
                .description("Learn Java at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1011.png")
                .price(23.99)
                .build();

        var book12 = Product.builder()
                .name("Exploring DevOps")
                .description("Learn DevOps at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1012.png")
                .price(22.99)
                .build();

        var book13 = Product.builder()
                .name("The Expert Guide to SQL")
                .description("Learn SQL at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1013.png")
                .price(11.99)
                .build();

        var book14 = Product.builder()
                .name("Introduction to SQL")
                .description("Learn SQL at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1014.png")
                .price(18.99)
                .build();

        var book15 = Product.builder()
                .name("The Expert Guide to JavaScript")
                .description("Learn JavaScript at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1015.png")
                .price(14.99)
                .build();

        var book16 = Product.builder()
                .name("Exploring React.js")
                .description("Learn React.js at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1016.png")
                .price(29.99)
                .build();

        var book17 = Product.builder()
                .name("Advanced Techniques in React.js")
                .description("Learn React.js at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1017.png")
                .price(32.99)
                .build();

        var book18 = Product.builder()
                .name("Introduction to C#")
                .description("Learn C# at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1018.png")
                .price(22.99)
                .build();

        var book19 = Product.builder()
                .name("Crash Course in JavaScript")
                .description("Learn JavaScript at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1019.png")
                .price(24.99)
                .build();

        var book20 = Product.builder()
                .name("Introduction to Machine Learning")
                .description("Learn Machine Learning at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1020.png")
                .price(9.99)
                .build();

        var book21 = Product.builder()
                .name("Become a Guru in Java")
                .description("Learn Java at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1021.png")
                .price(19.99)
                .build();

        var book22 = Product.builder()
                .name("Introduction to Python")
                .description("Learn Python at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1022.png")
                .price(23.99)
                .build();

        var book23 = Product.builder()
                .name("Advanced Techniques in C#")
                .description("Learn C# at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1023.png")
                .price(27.99)
                .build();

        var book24 = Product.builder()
                .name("AThe Expert Guide to Machine Learning")
                .description("Learn Machine Learning at your own pace. The author explains how the technology works in" +
                        " easy-to-understand language. This book includes working examples that you can apply to " +
                        "your own projects. Purchase the book and get started today!")
                .imageUrl("assets/images/products/books/book-1024.png")
                .price(28.99)
                .build();

        var products = List.of(book0, book1, book2, book3, book4, book5, book6, book7, book8,
                book9, book10, book11, book12, book13, book14, book15, book16, book17, book18, book19, book20,
                book21, book22, book23, book24);
        products.forEach(book -> {
            book.setCategoryId(savedCategory.getId());
            book.setId(UUID.randomUUID().toString());
        });
        db.insert(products, Product.class);
    }

    @ChangeSet(order = "002", id="initCoffeeMugs", author = "ming")
    public void initCoffeeMugs(MongockTemplate db) {
        var coffeeMugsCategory = Category.builder()
                .name("Coffee Mugs")
                .count(25)
                .build();

        var savedCoffeeMugsCategory = db.save(coffeeMugsCategory);

        var coffeeMug0 = Product.builder()
                .name("Coffee Mug - Express")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1000.png")
                .price(18.99)
                .build();

        var coffeeMug1 = Product.builder()
                .name("Coffee Mug - Cherokee")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1001.png")
                .price(16.44)
                .build();

        var coffeeMug2 = Product.builder()
                .name("Coffee Mug - Sweeper")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1002.png")
                .price(34.99)
                .build();

        var coffeeMug3 = Product.builder()
                .name("Coffee Mug - Aspire")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1003.png")
                .price(17.49)
                .build();

        var coffeeMug4 = Product.builder()
                .name("Coffee Mug - Dorian")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1004.png")
                .price(20.99)
                .build();

        var coffeeMug5 = Product.builder()
                .name("Coffee Mug - Columbia")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1005.png")
                .price(19.99)
                .build();

        var coffeeMug6 = Product.builder()
                .name("Coffee Mug - Worthing")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1006.png")
                .price(21.99)
                .build();

        var coffeeMug7 = Product.builder()
                .name("Coffee Mug - Oak Cliff")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1007.png")
                .price(22.99)
                .build();

        var coffeeMug8 = Product.builder()
                .name("Coffee Mug - Tachyon")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1008.png")
                .price(21.99)
                .build();

        var coffeeMug9 = Product.builder()
                .name("Coffee Mug - Pan")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1009.png")
                .price(13.99)
                .build();

        var coffeeMug10 = Product.builder()
                .name("Coffee Mug - Phase")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1010.png")
                .price(21.99)
                .build();

        var coffeeMug11 = Product.builder()
                .name("Coffee Mug - Falling")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1011.png")
                .price(24.99)
                .build();

        var coffeeMug12 = Product.builder()
                .name("Coffee Mug - Wispy")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1012.png")
                .price(21.99)
                .build();

        var coffeeMug13 = Product.builder()
                .name("Coffee Mug - Arlington")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1006.png")
                .price(22.99)
                .build();

        var coffeeMug14 = Product.builder()
                .name("Coffee Mug - Gazing")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1014.png")
                .price(21.99)
                .build();

        var coffeeMug15 = Product.builder()
                .name("Coffee Mug - Recursion")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1015.png")
                .price(30.99)
                .build();

        var coffeeMug16 = Product.builder()
                .name("Coffee Mug - Azura")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1016.png")
                .price(21.99)
                .build();

        var coffeeMug17 = Product.builder()
                .name("Coffee Mug - Quantum Leap")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1017.png")
                .price(11.99)
                .build();

        var coffeeMug18 = Product.builder()
                .name("Coffee Mug - Treasure")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1018.png")
                .price(29.99)
                .build();

        var coffeeMug19 = Product.builder()
                .name("Coffee Mug - Light Years")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1019.png")
                .price(21.99)
                .build();

        var coffeeMug20 = Product.builder()
                .name("Coffee Mug - Taylor")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1020.png")
                .price(25.99)
                .build();

        var coffeeMug21 = Product.builder()
                .name("Coffee Mug - Gracia")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1021.png")
                .price(13.99)
                .build();

        var coffeeMug22 = Product.builder()
                .name("Coffee Mug - Relax")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1022.png")
                .price(22.99)
                .build();

        var coffeeMug23 = Product.builder()
                .name("Coffee Mug - Windermere")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1023.png")
                .price(21.99)
                .build();

        var coffeeMug24 = Product.builder()
                .name("Coffee Mug - Prancer")
                .description("Do you love mathematics? If so, then you need this elegant coffee mug with an amazing" +
                        " fractal design. You don\\'t have to worry about boring coffee mugs anymore. This coffee mug" +
                        " will be the topic of conversation in the office, guaranteed! Buy it now!")
                .imageUrl("assets/images/products/coffeemugs/coffeemug-1024.png")
                .price(21.99)
                .build();

        var coffeeMugs = List.of(
                coffeeMug0, coffeeMug1, coffeeMug2, coffeeMug3, coffeeMug4,
                coffeeMug5, coffeeMug6, coffeeMug7, coffeeMug8, coffeeMug9,
                coffeeMug10, coffeeMug11, coffeeMug12, coffeeMug13, coffeeMug14,
                coffeeMug15, coffeeMug16, coffeeMug17, coffeeMug18, coffeeMug19,
                coffeeMug20, coffeeMug21, coffeeMug22, coffeeMug23, coffeeMug24);

        coffeeMugs.forEach(coffeeMug -> {
            coffeeMug.setCategoryId(savedCoffeeMugsCategory.getId());
            coffeeMug.setId(UUID.randomUUID().toString());
        });
        db.insert(coffeeMugs, Product.class);
    }

}
